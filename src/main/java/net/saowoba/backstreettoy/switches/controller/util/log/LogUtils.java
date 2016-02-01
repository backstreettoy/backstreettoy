package net.saowoba.backstreettoy.switches.controller.util.log;

import net.saowoba.backstreettoy.switches.annotation.dataobject.StreamParameters;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;

import org.slf4j.Logger;

public class LogUtils {
	
	public static final String EMPTY_ARRAY[] = new String[]{};

	
	public static void logActions(String switchName, String operation,
			StringParameters simpliedParams, StreamParameters streamParameter,String[] excludeFields, Logger logger, boolean logDetailParameters) {
		
		String msgPttern;
		if(logDetailParameters) {
			msgPttern = (streamParameter != null ? 
					"Invoke inner switch Action: switchName:{}, operationName:{}, stringParameter:{}, streamParameter:{}"
					: "Invoke inner switch Action: switchName:{}, operationName:{}, stringParameter:{}");
			if(logger.isInfoEnabled()) {
				logger.info(msgPttern,switchName,operation,
						simpliedParams != null ? simpliedParams.toString(excludeFields) : "",
						streamParameter != null ? streamParameter.toString(excludeFields) : "");
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
