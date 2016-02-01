package net.saowoba.backstreettoy.switches.annotation.dataobject;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * String类型的简单HttpRequest参数
 * @author shl
 *
 */
public class StringParameters {
	
	private Map<String,String[]> paramters = new HashMap<String,String[]>();
	
	public String getSimpleStringParameter(String key) {
		return paramters.containsKey(key) ? paramters.get(key)[0] : null;
	}
	
	public String[] getStringParameter(String key) {
		return paramters.get(key);
	}
	
	public void setParameters(Map<String,String[]> m) {
		this.paramters = m;
	}
	
	public void setParameter(String key, String[] values) {
		paramters.put(key, values);
	}
	
	
	@Override
	public String toString() {
		if(paramters != null) {
			return new ReflectionToStringBuilder(paramters).toString();
		}
		else {
			return "";
		}
	}

}
