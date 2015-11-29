package net.saowoba.backstreettoy.utils.condition;

import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 作为条件对象的容器
 * @author shl
 *
 */
public class ConditionHolder {
	private String process;
	private String subProcess;
	private Set<String> functions;
	
	public ConditionHolder(String process,String subProcess,Set<String> func) {
		this.process=process;
		this.subProcess=subProcess;
		this.functions = func;
	}
	
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getSubProcess() {
		return subProcess;
	}
	public void setSubProcess(String subProcess) {
		this.subProcess = subProcess;
	}
	public Set<String> getFunctions() {
		return functions;
	}
	public void setFunctions(Set<String> functions) {
		this.functions = functions;
	}
	
	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
	
}