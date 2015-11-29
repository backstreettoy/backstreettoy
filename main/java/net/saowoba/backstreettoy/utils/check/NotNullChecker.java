package net.saowoba.backstreettoy.utils.check;

import net.saowoba.backstreettoy.utils.check.exception.CheckException;
import net.saowoba.server.constants.ErrorInfo;

/**
 * 对枚举的检查类
 * @author shl
 *
 */
public class NotNullChecker {

	public static void check(Object e) {
		if(e==null) {
			throw new CheckException(ErrorInfo.PARAM_ILLEGAL);
		}
	}
	
	public static void check(Object e,String msg) {
		if(e==null) {
			throw new CheckException(ErrorInfo.PARAM_ILLEGAL,msg);
		}
	}
	
	public static void check(Object e,ErrorInfo errorInfo , String msg) {
		if(e==null) {
			throw new CheckException(errorInfo,msg);
		}
	}
	
	public static void check(Object e,Class<? extends RuntimeException> exceptionClazz) {
		if(e==null) {
			try {
				RuntimeException excep = exceptionClazz.newInstance();
				throw excep;
			} catch (InstantiationException e1) {
			} catch (IllegalAccessException e1) {
			}
			
		}
	}
}
