package net.saowoba.backstreettoy.switches.annotation.dataobject;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class StreamParameters {
	
	private Map<String,StreamInfo> parameters = new HashMap<String,StreamInfo>();
	
	
	public static class StreamInfo {
		
		private InputStream stream;
		private String contentType;
		private long size;
		private String name;
		
		
		@Override
		public String toString() {
			StringBuilder content = new StringBuilder();
			content.append("contentType:").append(contentType)
					.append(";size:").append(size)
					.append(";name:").append(name);
			return content.toString();
		}
		
		public InputStream getStream() {
			return stream;
		}
		public void setStream(InputStream stream) {
			this.stream = stream;
		}
		public String getContentType() {
			return contentType;
		}
		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		public long getSize() {
			return size;
		}
		public void setSize(long size) {
			this.size = size;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	
	@Override
	public String toString() {
		return toString(null);
	}
	
	public String toString(String[] excludeFields) {
		Set<String> excludeFieldSet = excludeFields!=null ?  new HashSet<String>(Arrays.asList(excludeFields)) : new HashSet<String>();
		if(parameters != null) {
			Map<String,StreamInfo> t = new HashMap<String,StreamInfo>(parameters.size());
			for(Entry<String,StreamInfo> e : parameters.entrySet()) {
				if(!excludeFieldSet.contains(e.getKey())) {
					t.put(e.getKey(), e.getValue());
				}
			}
			return t.toString();
		}
		else {
			return "";
		}
	}


	public Map<String,StreamInfo> getParameters() {
		return parameters;
	}
	
	public StreamInfo getParameter(String key) {
		return parameters.get(key);
	}


	public void setParameters(Map<String,StreamInfo> parameters) {
		this.parameters = parameters;
	}

	public void setParameter(String key, StreamInfo info) {
		parameters.put(key, info);
	}
}
