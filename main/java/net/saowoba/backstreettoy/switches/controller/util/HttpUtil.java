package net.saowoba.backstreettoy.switches.controller.util;

import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	
	
	public static Map<String, String> parseParameter(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		HashMap<String,String> ret = new HashMap<String, String>();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			ret.put(name,request.getParameter(name));
		}
		return ret;
	}
	
	/**
	 * 生成开关的名称，这个名称不包含路径和页面的后缀，例如,querySingle
	 * @param reqUrl
	 */
	public static String parseSwitchName(URI reqUrl) {
		String url = reqUrl.toString();
		String t[] =  url.split("/");
		if(t.length >=2 ) {
			//替换后缀
			return t[t.length-1].replaceAll("\\..*", "");
		}
		else {
			return null;
		}
	}

	
	/**
	 * 收集所有参数
	 * @param request
	 * @return
	 */
	public static Map<String, String[]> collectParameters(HttpServletRequest request) {
		return request.getParameterMap();
	}
}
