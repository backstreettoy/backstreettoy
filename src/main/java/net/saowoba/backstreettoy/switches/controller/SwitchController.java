package net.saowoba.backstreettoy.switches.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.saowoba.backstreettoy.aware.ApplicationContextAware;
import net.saowoba.backstreettoy.aware.SwitchNamesAware;
import net.saowoba.backstreettoy.beanfactory.BeanFactory;
import net.saowoba.backstreettoy.dataobject.Result;
import net.saowoba.backstreettoy.switches.annotation.util.AnnotationUtil;
import net.saowoba.backstreettoy.switches.controller.util.HttpUtil;
import net.saowoba.backstreettoy.switches.controller.util.JsonUtil;
import net.saowoba.backstreettoy.switches.controller.util.actions.Action;
import net.saowoba.backstreettoy.switches.controller.util.actions.DescribeSwitch;
import net.saowoba.backstreettoy.switches.controller.util.actions.ListAllSwitches;
import net.saowoba.backstreettoy.switches.controller.util.actions.OperationNotExistsAction;
import net.saowoba.backstreettoy.switches.controller.util.actions.SwitchNotExistsAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Maps.EntryTransformer;
import com.google.gson.JsonElement;

public class SwitchController extends JsonResultController implements
		ApplicationContextAware {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public static final String KEY_OPERATION = "op";
	
	private static Map<String,Action> innerSwitchNameActions;
	private static Map<String,Action> innerOperationActions;
	
	/**
	 * 描述操作符，保留字
	 */
	public static String OPERATION_DESP = "_DESP";
	
	private static Action switchNotExistsAction ;
	private static Action operationNotExistsAction;

	private BeanFactory context;
	private List<String> switchNames;

	public SwitchController() {	

	}
	
	public void init() {
		innerSwitchNameActions = new HashMap<String,Action>();
		innerSwitchNameActions.put(OPERATION_DESP, new ListAllSwitches());
		initActions(innerSwitchNameActions);
		
		innerOperationActions = new HashMap<String,Action>();
		innerOperationActions.put(OPERATION_DESP, new DescribeSwitch());
		initActions(innerOperationActions);
		
		switchNotExistsAction = new SwitchNotExistsAction();
		initAction(switchNotExistsAction);
		operationNotExistsAction = new OperationNotExistsAction();
		initAction(operationNotExistsAction);
	}


	@Override
	protected JsonElement doHandleRequestInternal(HttpServletRequest request,
			HttpServletResponse response, JsonElement jsonElement)
			throws Exception {

		URI reqUrl = new URI(request.getRequestURL().toString());
		// 去掉开关前面的路径，去掉后面的.htm等后缀
		String switchName = HttpUtil.parseSwitchName(reqUrl);
		String operation = ServletRequestUtils.getStringParameter(request, KEY_OPERATION);
		Map<String,String[]> params = HttpUtil.collectParameters(request);
		
		Map<String,String> simpliedParams = Maps.transformEntries(params, new EntryTransformer<String,String[],String>(){

			@Override
			public String transformEntry(String key, String[] value) {
				if(value!=null && value.length > 0) {
					return value[0];
				}
				else {
					return "";
				}
			}});

		if (!Strings.isNullOrEmpty(switchName)) {

			if (innerSwitchNameActions.containsKey(switchName)) {
				//switchName级别开关
				return invokeInnerSwitchNameAction(switchName,operation,simpliedParams);
			} else if (!switchNames.contains(switchName)) {
				// switchName 不存在
				return switchNotExistsAction.act(switchName,operation,simpliedParams);
			} else {
				Object sw = null;
				sw = context.getBean(switchName);
				
				if(sw == null) {
					return switchNotExistsAction.act(switchName, operation, simpliedParams);
				}

				if (Strings.isNullOrEmpty(operation)) {
					// Operation级别开关
					return operationNotExistsAction.act(switchName, operation, simpliedParams);
				} else if (innerOperationActions.containsKey(operation)) {
					// 系统开关
					return invokeOperationAction(switchName,operation,simpliedParams);
				} else {
					// 调用函数
					Result opInvokeRet = invokeSwitchOperation(request,response,sw,params,switchName,operation);
					return JsonUtil.toJsonElement(opInvokeRet);
				}
			}
		} else {
			return switchNotExistsAction.act(switchName, operation, simpliedParams);
		}
	}

	private JsonElement invokeInnerSwitchNameAction(String switchName,
			String operation, Map<String, String> simpliedParams) {
		return innerSwitchNameActions.get(switchName).act(switchName, operation, simpliedParams);
	}

	private JsonElement invokeOperationAction(String switchName,
			String operation, Map<String, String> simpliedParams) {
		return innerOperationActions.get(switchName).act(switchName, operation, simpliedParams);
	}


	// 返回本机所有开关列表
//	private void listAllSwitch(Map<String, Object> ret) {
//		List<SwitchDO> swDOList = new ArrayList<SwitchDO>();
//		if (switchNames != null) {
//			for (String swName : switchNames) {
//				Object o = null;
//				try {
//					o = context.getBean(swName);
//				} catch (Exception e) {
//					log.error("Error occured when get Switch Bean from context: "
//							+ swName);
//					continue;
//				}
//				swDOList.add(AnnotationUtil.getSwitch(o, swName));
//			}
//			JsonElement jsonElement = jsonUtil.getJsonTree(swDOList);
//			ret.put(ResponseKeys.KEY_SUCCESS, true);
//			ret.put(ResponseKeys.KEY_INFO, new JsonDO(jsonElement));
//		}
//	}

	private Result invokeSwitchOperation(HttpServletRequest request, HttpServletResponse response,
			Object sw,
			Map<String,String[]> params, String swName, String opName) {

		Result invokeRet = null;
		try {
			//Force cast!
			invokeRet = (Result) AnnotationUtil.invokeOperation(sw, opName, request,response,params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return invokeRet; 
	}
	
	
	private void initAction(Action action) {
		if(action instanceof SwitchNamesAware) {
			((SwitchNamesAware) action).setSwitchNames(this.switchNames);
		}
		
		if(action instanceof ApplicationContextAware) {
			((ApplicationContextAware) action).setApplicationContext(this.context);
		}
	}

	private void initActions(Map<String, Action> actions) {
		for(Entry<String,Action> e : actions.entrySet()) {
			initAction(e.getValue());
		}
	} 

//	private void generateResult(Object invokeRet_, String swName, String opName, Map<String, Object> ret) {
//		if (invokeRet_ instanceof Result) {
//			Result invokeRet = (Result) invokeRet_;
//			if(invokeRet.isSuccess()) {
//				if(StringUtils.equals(invokeRet.getDefault().getType(), new JsonDO().getType())) {
//					//json
//					JsonDO jsDO = (JsonDO) invokeRet.getDefault();
//					JsonElement je = jsDO.getValue();
//					if(je.isJsonObject()) {
//						JsonObject jo = je.getAsJsonObject();
//						for(Entry<String, JsonElement> e : jo.entrySet()) {
//							ret.put(e.getKey(), e.getValue());
//						}
//					}
//					else {
//						throw new RuntimeException("unsupport");
//					}
//				}
//			}
//		} else {
//			Result result = new Result();
//			result.setSuccess(false);
//			result.setFailReason("return type is invalid");
//			throw new RuntimeException("unsupport");
//		}
//	}

//	private void despSwitch(Object sw, Map<String, Object> ret) {
//		Map<String, OperationDO> operations = AnnotationUtil.getOperations(sw);
//		ret.put(ResponseKeys.KEY_INFO, new JsonDO(jsonUtil.getJsonTree(operations.values())));
//	}

	public List<String> getSwitchNames() {
		return switchNames;
	}

	public void setSwitchNames(List<String> switchNames) {
		this.switchNames = switchNames;
	}

	@Override
	public void setApplicationContext(BeanFactory appCtx) {
		this.context = appCtx;
	}

	@Override
	protected JsonElement exceptionOccur(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
