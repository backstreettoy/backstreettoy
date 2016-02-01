package net.saowoba.backstreettoy.switches.annotation.dataobject;

import java.lang.reflect.Method;

import com.google.gson.annotations.Expose;

import net.saowoba.backstreettoy.switches.annotation.VisibleTypes;

public class OperationDO {

	@Expose
	private String name;
	@Expose
	private  Parameter[] parameters;
	@Expose
	private String desp;
	@Expose
	private VisibleTypes visible;
	@Expose
	private String displayName;
	@Expose (serialize = false, deserialize = false)
	private Method method;
	@Expose(serialize = false, deserialize = false)
	private String[] excludeLoggingFileds; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	public VisibleTypes getVisible() {
		return visible;
	}
	public void setVisible(VisibleTypes visible) {
		this.visible = visible;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Parameter[] getParameters() {
		return parameters;
	}
	public void setParameters(Parameter[] parameters) {
		this.parameters = parameters;
	}
	public String[] getExcludeLoggingFileds() {
		return excludeLoggingFileds;
	}
	public void setExcludeLoggingFileds(String[] excludeLoggingFileds) {
		this.excludeLoggingFileds = excludeLoggingFileds;
	}
	
	
}
