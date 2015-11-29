package net.saowoba.backstreettoy.utils.check.exception;

import net.saowoba.server.constants.ErrorInfo;

/**
 * 校验异常
 * @author shl
 *
 */
public class CheckException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1706243595823083978L;
	
	private ErrorInfo errorInfo = ErrorInfo.EXCEPTION;
	
	
	public CheckException() {
		super();
	}
	
	public CheckException(ErrorInfo ei) {
		super();
		setErrorInfo(ei);
	}
	
	public CheckException(ErrorInfo ei,String msg) {
		super(msg);
		setErrorInfo(ei);
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
	

}
