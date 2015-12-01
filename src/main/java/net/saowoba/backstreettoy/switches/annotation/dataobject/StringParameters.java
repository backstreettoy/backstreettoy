package net.saowoba.backstreettoy.switches.annotation.dataobject;

import java.util.Map;

/**
 * String类型的简单HttpRequest参数
 * @author shl
 *
 */
public class StringParameters {
	
	private Map<String,String[]> paramters;
	
	public String getSimpleStringParameter(String key) {
		return paramters.containsKey(key) ? paramters.get(key)[0] : null;
	}
	
	public String[] getStringParameter(String key) {
		return paramters.get(key);
	}
	
	public void setParameters(Map<String,String[]> m) {
		this.paramters = m;
	}

}
