package net.saowoba.backstreettoy.switches.controller;

import java.io.IOException;
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
import net.saowoba.backstreettoy.switches.annotation.dataobject.StreamParameters;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StreamParameters.StreamInfo;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;
import net.saowoba.backstreettoy.switches.annotation.util.AnnotationUtil;
import net.saowoba.backstreettoy.switches.controller.util.HttpUtil;
import net.saowoba.backstreettoy.switches.controller.util.JsonUtil;
import net.saowoba.backstreettoy.switches.controller.util.actions.Action;
import net.saowoba.backstreettoy.switches.controller.util.actions.DescribeSwitch;
import net.saowoba.backstreettoy.switches.controller.util.actions.ListAllSwitches;
import net.saowoba.backstreettoy.switches.controller.util.actions.OperationNotExistsAction;
import net.saowoba.backstreettoy.switches.controller.util.actions.SwitchNotExistsAction;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestUtils;

import com.google.common.base.Strings;
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
		
		boolean mulipartCont = ServletFileUpload.isMultipartContent(request);
		
		String operation = null;
		StringParameters stringParams = null;
		StreamParameters streamParams = null;
		if(mulipartCont) {
			Map<String, Object> multipartParseRet = parseParamFromMultipart(request);
			streamParams = (StreamParameters)multipartParseRet.get("streamParam");
			stringParams = (StringParameters) multipartParseRet.get("stringParam");
		}
		else {
			Map<String,String[]> p = HttpUtil.collectParameters(request);
			stringParams = new StringParameters();
			stringParams.setParameters(p);
		}
		
		operation = ServletRequestUtils.getStringParameter(request, KEY_OPERATION);
		if (!Strings.isNullOrEmpty(switchName)) {

			if (innerSwitchNameActions.containsKey(switchName)) {
				//switchName级别开关
				return invokeInnerSwitchNameAction(switchName,operation,stringParams);
			} else if (!switchNames.contains(switchName)) {
				// switchName 不存在
				return switchNotExistsAction.act(switchName,operation,stringParams);
			} else {
				Object sw = null;
				sw = context.getBean(switchName);
				
				if(sw == null) {
					return switchNotExistsAction.act(switchName, operation, stringParams);
				}

				if (Strings.isNullOrEmpty(operation)) {
					// Operation级别开关
					return operationNotExistsAction.act(switchName, operation, stringParams);
				} else if (innerOperationActions.containsKey(operation)) {
					// 系统开关
					return invokeOperationAction(switchName,operation,stringParams);
				} else {
					// 调用函数
					Result opInvokeRet = invokeSwitchOperation(request,response,sw,stringParams,streamParams,switchName,operation);
					return JsonUtil.toJsonElement(opInvokeRet);
				}
			}
		} else {
			return switchNotExistsAction.act(switchName, operation, stringParams);
		}
	}

	private JsonElement invokeInnerSwitchNameAction(String switchName,
			String operation, StringParameters simpliedParams) {
		return innerSwitchNameActions.get(switchName).act(switchName, operation, simpliedParams);
	}

	private JsonElement invokeOperationAction(String switchName,
			String operation, StringParameters simpliedParams) {
		return innerOperationActions.get(operation).act(switchName, operation, simpliedParams);
	}


	private Result invokeSwitchOperation(HttpServletRequest request, HttpServletResponse response,
			Object sw,
			StringParameters stringParams, StreamParameters streamParams, String swName, String opName) {

		Result invokeRet = null;
		try {
			//Force cast!
			invokeRet = (Result) AnnotationUtil.invokeOperation(sw, opName, request,response,stringParams,streamParams);
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
	
	
	private Map<String, Object> parseParamFromMultipart(
			HttpServletRequest request) {
		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());
		List<FileItem> paramters;
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			paramters = upload.parseRequest(request);
			//组装参数
			
			StringParameters stringp = new StringParameters();
			StreamParameters streamp = new StreamParameters();
			ret.put("stringParam", stringp);
			ret.put("streamParam",streamp);
			if(paramters ==null) {
				return ret;
			}
			
			for (FileItem item : paramters) {
				String name = item.getFieldName();
				if (item.isFormField()) {
					stringp.setParameter(name, new String[] {item.getString()});
				} else {
					StreamInfo si = new StreamInfo();
					si.setContentType(item.getContentType());
					si.setStream(item.getInputStream());
					si.setName(item.getName());
					si.setSize(item.getSize());
					streamp.setParameter(item.getFieldName(), si);
				}
			}
		} catch (FileUploadException e) {
			log.error("parseParamFromMultipart",e);
		} catch (IOException e) {
			log.error("parseParamFromMultipart",e);
		}
		
		return ret;
	}


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
