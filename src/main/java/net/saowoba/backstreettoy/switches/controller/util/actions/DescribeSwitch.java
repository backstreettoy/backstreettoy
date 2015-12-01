package net.saowoba.backstreettoy.switches.controller.util.actions;

import java.lang.reflect.Type;
import java.util.Map;

import net.saowoba.backstreettoy.aware.ApplicationContextAware;
import net.saowoba.backstreettoy.beanfactory.BeanFactory;
import net.saowoba.backstreettoy.dataobject.Result;
import net.saowoba.backstreettoy.switches.annotation.dataobject.OperationDO;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;
import net.saowoba.backstreettoy.switches.annotation.dataobject.SwitchDO;
import net.saowoba.backstreettoy.switches.annotation.util.AnnotationUtil;
import net.saowoba.backstreettoy.switches.controller.util.JsonUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * 用于描述当前Switch的功能
 * @author shl
 *
 */
public class DescribeSwitch 
implements Action,ApplicationContextAware{
	
	private BeanFactory context;

	@Override
	public JsonElement act(String switchName, String operation,
			StringParameters param) {
		
		Result ret = new Result();
		Object bean = context.getBean(switchName);
		
		if(bean == null) {
			ret.setSuccess(false);
			ret.setFailReason("Bean " + switchName + " NOT exists");
			return JsonUtil.toJsonElement(ret);
		}
		
		Map<String, OperationDO> operations = AnnotationUtil.getOperations(bean);
		SwitchDO switchDesp = AnnotationUtil.getSwitch(bean, switchName);
		
		JsonObject jo = new JsonObject();
		jo.add("switch", JsonUtil.toJsonElement(switchDesp));
		Type type = new TypeToken<Map<String,OperationDO>>() {}.getType();
		jo.add("operations", JsonUtil.toJsonElementWithExclude(operations,type));
		
		ret.setExtra(jo);
		ret.setSuccess(true);
		return JsonUtil.toJsonElement(ret);
	}

	@Override
	public void setApplicationContext(BeanFactory appCtx) {
		context = appCtx;
	}

}
