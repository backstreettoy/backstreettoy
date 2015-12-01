package net.saowoba.backstreettoy.switches.annotation.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import net.saowoba.backstreettoy.switches.annotation.Operation;
import net.saowoba.backstreettoy.switches.annotation.Switch;
import net.saowoba.backstreettoy.switches.annotation.dataobject.OperationDO;
import net.saowoba.backstreettoy.switches.annotation.dataobject.Parameter;
import net.saowoba.backstreettoy.switches.annotation.dataobject.SwitchDO;
import net.saowoba.backstreettoy.utils.BeanUtils;

import com.google.common.base.Function;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;




public class AnnotationUtil {
	
	/**
	 * key:class name<br>
	 * value:Map,key:op,value:method
	 */
	private static Cache<String, Map<String,OperationDO>> operationCache = CacheBuilder.newBuilder().build();

	/**
	 * 从一个Object上面获取Switch Annotation
	 * 每一个Class对应一个Switch
	 * @param obj
	 * @return
	 */
	public static SwitchDO getSwitch(Object obj ,String switchBeanName) {
		Switch sw = obj.getClass().getAnnotation(Switch.class);
		if(sw!=null) {
			SwitchDO swDO = new SwitchDO();
			swDO.setDesp(sw.desp());
			swDO.setName(sw.name());
			swDO.setBeanId(switchBeanName);
			return swDO;
		}
		return null;
	}
	
	
	/**
	 * 从一个Object上面获取每个开关操作上面的Operation
	 * @param obj
	 * @return
	 */
	public static Map<String,OperationDO> getOperations(Object obj) {
		String clazzName = obj.getClass().getName();
		final Object obj_ = obj;
		try {
			Map<String, OperationDO> ret = operationCache.get(clazzName, new Callable<Map<String,OperationDO>>() {

				@Override
				public Map<String, OperationDO> call() throws Exception {
					Method[] methods = BeanUtils.getAllMethod(obj_);
					LinkedList<OperationDO> operations = new LinkedList<OperationDO>();
					for(Method m : methods ) {
						if(m.isAnnotationPresent(Operation.class)) {
							Operation op = m.getAnnotation(Operation.class);
							OperationDO opDO = new OperationDO();
							opDO.setMethod(m);
							//新版本的name通过方法名反射生成，内容和老的一致
							opDO.setName(op.name());
							//name里面放显示名
							opDO.setDisplayName(op.desp());
							//可见程度
							opDO.setVisible(op.visible());
							String[] params = op.parameters();
							Parameter[] paramDOs = new Parameter[params.length]; 
							for(int i=0 ;i< params.length ;++i) {
								Parameter paramDO = new Parameter();
								paramDO.setName(params[i]);
								paramDOs[i]=paramDO;
							}
							opDO.setParameters(paramDOs);
							operations.add(opDO);
						} 
					}
					
					Map<String,OperationDO> ret = Maps.uniqueIndex(operations,new Function<OperationDO, String>() {
						@Override
						public String apply(OperationDO input) {
							return input.getName();
						}
					});
					
					return ret;
				}
			});
			return ret;
		} catch (ExecutionException e) {
			return null;
		}
	}
	
	
	public static Object invokeOperation(Object target ,String opName , Object... args) {
		Map<String, OperationDO> operations = getOperations(target);
		if(operations.containsKey(opName)) {
			Method method = operations.get(opName).getMethod();
			
			Class<?>[] parameterTypes = method.getParameterTypes();
			Object[] invokeObj = generateArgument(parameterTypes,args);
			
			Object ret;
			try {
				ret = method.invoke(target, invokeObj);
				return ret;
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		else {
			throw new RuntimeException("Operation NOT exist");
		}
	}


	private static Object[] generateArgument(Class<?>[] parameterTypes,
			Object[] args) {
		
		LinkedList<Object> l = new LinkedList<Object>();
		
		for(Class<?> clazz : parameterTypes) {
			for(Object arg : args) {
				if(clazz.isInstance(arg)) {
					l.add(arg);
				}
			}
		}
		return l.toArray(new Object[0]);
	}

}
