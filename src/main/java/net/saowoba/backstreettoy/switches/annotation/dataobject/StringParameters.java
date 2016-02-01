package net.saowoba.backstreettoy.switches.annotation.dataobject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * String类型的简单HttpRequest参数
 * @author shl
 *
 */
public class StringParameters {
	
	private Map<String,String[]> parameters = new HashMap<String,String[]>();
	
	public String getSimpleStringParameter(String key) {
		return parameters.containsKey(key) ? parameters.get(key)[0] : null;
	}
	
	public String[] getStringParameter(String key) {
		return parameters.get(key);
	}
	
	public void setParameters(Map<String,String[]> m) {
		this.parameters = m;
	}
	
	public void setParameter(String key, String[] values) {
		parameters.put(key, values);
	}
	
	
	@Override
	public String toString() {
		return toString(null);
	}
	
	public String toString(String[] excludeFields) {
		Set<String> excludeFieldSet = excludeFields!=null ?  new HashSet<String>(Arrays.asList(excludeFields)) : new HashSet<String>();
		if(parameters != null) {
			Map<String,List<String>> t = new HashMap<String,List<String>>(parameters.size());
			for(Entry<String,String[]> e : parameters.entrySet()) {
				if(!excludeFieldSet.contains(e.getKey())) {
					t.put(e.getKey(), Arrays.asList(e.getValue()));
				}
			}
			return t.toString();
		}
		else {
			return "";
		}
	}

}
