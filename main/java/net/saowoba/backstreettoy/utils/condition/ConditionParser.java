package net.saowoba.backstreettoy.utils.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 条件字符串解析
 * @author shl
 *
 */
public class ConditionParser {

	public static ConditionHolder parseProcessString(String processName) {
		String mainProc = getMainProcessName(processName);
		String subProc = getSubProcess(processName);
		List<String> funcs = getAllFunctions(processName);
		return new ConditionHolder(mainProc, subProc, new HashSet<String>(funcs));
	}
	
	
	public static String generateProcessString(ConditionHolder holder) {
		String t = (StringUtils.isNotBlank(holder.getProcess()) ? holder.getProcess() : "")
			+ "." 
			+ (StringUtils.isNotBlank(holder.getSubProcess()) ? holder.getSubProcess() : "");
		if(holder.getFunctions()!=null) {
			for(String f : holder.getFunctions()) {
				t += "." + f;
			}
		}
		
		return t;
	}
	
	
	public static String constructProcessName(String process,String subProcess , String[] function) {
		if(StringUtils.isBlank(process)) {
			throw new IllegalArgumentException("process is empty");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(process);
		
		sb.append(".").append(subProcess!=null?subProcess:"");
		
		if(function!=null) {
			for(String s : function) {
				sb.append(".").append(s);
			}
		}
		return sb.toString();
	}
	
	public static String getMainProcessName(String processName) {
		String t[] = processName.split("\\.");
		return t.length >=1 ? t[0] : null;
	}
	
	public static boolean hasFunction(String processName,String function) {
		return processName.contains("." + function);
	}	
	
	public static String getSubProcess(String processName) {
		String t[] = processName.split("\\.");
		return t.length >=2 ? t[1] : null;
	}
	
	public static List<String> getAllFunctions(String processName) {
		String t[] = processName.split("\\.");
		return t.length >=2 ? Arrays.asList(Arrays.copyOfRange(t, 2, t.length)) : new ArrayList<String>(0);
	}
	
	
	public static ConditionHolder parseConditionStr(String s) {
		return parseProcessString(s);
	}
	
	public static void main(String[] args) {
		System.out.println(parseConditionStr("proc.subproc.func1"));
		System.out.println(parseConditionStr("proc.func1.func2"));
		System.out.println(parseConditionStr("browserPlugin.chromePlugin.1-0-2"));
		
	}
}
