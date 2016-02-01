package net.saowoba.backstreettoy.switches.controller.util.log;

import org.slf4j.Logger;

import net.saowoba.backstreettoy.switches.annotation.dataobject.StreamParameters;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;

public class LogUtils {

	
	public static void logActions(String switchName, String operation,
			StringParameters simpliedParams, StreamParameters streamParameter, Logger logger, boolean logDetailParameters) {
		
		String msgPttern;
		if(logDetailParameters) {
			msgPttern = (streamParameter != null ? 
					"Invoke inner switch Action: switchName:{}, operationName:{}, stringParameter:{}, streamParameter:{}"
					: "Invoke inner switch Action: switchName:{}, operationName:{}, stringParameter:{}");
			if(logger.isInfoEnabled()) {
				logger.info(msgPttern,switchName,operation,simpliedParams,streamParameter);
			}
		}
		else {
			msgPttern = "Invoke inner switch Action: switchName:{}, operationName:{}";
			if(logger.isInfoEnabled()) {
				logger.info(msgPttern,switchName,operation);
			}
		}
	}
	
}
