package net.saowoba.backstreettoy.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.Callable;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.reflect.Reflection;

/**
 * 一个构建Bridge模式的工具类
 * @author shl
 *
 */
public class DynamicBridge {

	/**
	 * 创建代理Bridge
	 * @param interfaceName
	 * @param target
	 * @param methodFilter
	 * @return
	 */
	public static <T> T createDynamicBridge(
			Class<T> interfaceName,
			Object target,
			Set<Method> methodFilter) {
		return Reflection.newProxy(interfaceName, new DynamicBridgeInvocationHandler(target, methodFilter));
	}
	
	
	/**
	 * 内部Invoke包装类
	 * @author shl
	 *
	 */
	private static class DynamicBridgeInvocationHandler
	implements InvocationHandler{
		private Set<Method> methodFilter;
		private Object target;
		
		//key:进入的方法，vlaue:需要执行的方法
		private Cache<Method, Method> methodCache = CacheBuilder.newBuilder().build();
		
		public DynamicBridgeInvocationHandler(Object target,Set<Method> mf) {
			this.methodFilter = mf;
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if(methodFilter!=null && methodFilter.size()>0) {
				if(!methodFilter.contains(method)) {
					throw new RuntimeException("method not in methodFilter: " + method.getName());
				}
			}
			
			final Method srcMethod = method;
			Method targetMetod = methodCache.get(method, new Callable<Method>() {

				@Override
				public Method call() throws Exception {
					String srcSignature = generateSignature(srcMethod);
					Method[] targetMethods = BeanUtils.getAllMethod(target);
					for(Method tm : targetMethods) {
						if(generateSignature(tm).equals(srcSignature)) {
							return tm;
						}
					}
					throw new RuntimeException("No match method found: " + srcSignature);
				}
			});
			return targetMetod.invoke(target, args);
		}
		
		private String generateSignature(Method m) {
			StringBuilder c = new StringBuilder()
				.append(m.getReturnType().getName())
				.append(":")
				.append(m.getName());
			
			Class<?> p[] = m.getParameterTypes();
			if(p.length>0) {
				c.append(":");
				for(Class<?> clazz : p) {
					c.append(clazz.getName()).append(":");
				}
			}
			return c.toString();
		}
	}
}
