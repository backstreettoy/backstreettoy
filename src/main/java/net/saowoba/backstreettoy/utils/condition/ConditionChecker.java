package net.saowoba.backstreettoy.utils.condition;

import net.saowoba.backstreettoy.utils.check.exception.CheckException;
import net.saowoba.server.constants.ErrorInfo;

import org.apache.commons.lang.StringUtils;

public class ConditionChecker {

	public static void processEquals(ConditionHolder cHolder , String process) {
		if(!StringUtils.equals(cHolder.getProcess(), process)) {
			throw new CheckException(ErrorInfo.PROCESS_MISMATCH);
		}
	}
	
	public static void subProcessEquals(ConditionHolder cHolder , String subProcess) {
		if(!StringUtils.equals(cHolder.getSubProcess(), subProcess)) {
			throw new CheckException(ErrorInfo.PROCESS_MISMATCH);
		}
	}
	
	public static void subProcessEquals(ConditionHolder cHolder , String subProcess,String msg) {
		if(!StringUtils.equals(cHolder.getSubProcess(), subProcess)) {
			throw new CheckException(ErrorInfo.PROCESS_MISMATCH,msg);
		}
	}
}
