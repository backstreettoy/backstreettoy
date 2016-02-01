package net.saowoba.backstreettoy.switches.annotation.dataobject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

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
			content.append("contentType=").append(contentType)
					.append(",size=").append(size)
					.append(".name=").append(name);
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
		if(parameters != null) {
			return new ReflectionToStringBuilder(parameters).toString();
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
