package net.saowoba.backstreettoy.dataobject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;

/**
 * 
 * @author shl
 *
 */
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5493590342081285769L;
	
	private boolean success=true;
	private String failReason;
	
	private JsonElement extra;
	
	private Map<String,Object> intermediateInfo;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public String getFailReason() {
		return failReason;
	}
	public JsonElement getExtra() {
		return extra;
	}
	public void setExtra(JsonElement extra) {
		this.extra = extra;
	}
	public Map<String,Object> getIntermediateInfo() {
		if (intermediateInfo == null) {
			intermediateInfo = new HashMap<String, Object>();
		}
		return intermediateInfo;
	}
	public void setIntermediateInfo(Map<String,Object> intermidateInfo) {
		this.intermediateInfo = intermidateInfo;
	}
	
	
}
