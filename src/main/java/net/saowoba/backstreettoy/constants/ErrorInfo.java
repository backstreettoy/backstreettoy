package net.saowoba.backstreettoy.constants;



/**
 * 错误信息列表
 * @author shl
 *
 */
public enum ErrorInfo {
	
	
	EXCEPTION(Types.COMMON_TYPE,1,"系统错误"),
	PARAM_ILLEGAL(Types.COMMON_TYPE,2,"参数错误"),
	PROCESS_MISMATCH(Types.COMMON_TYPE,3,"流程错误"),
	
	USER_NOT_EXISTS(Types.USER_BUSINESS_TYPE,1,"用户不存在"),
	USER_EXISTS(Types.USER_BUSINESS_TYPE,2,"用户已存在"), 
	PASSWORD_NOT_SAME(Types.USER_BUSINESS_TYPE,3,"两次输入密码不一致"),
	PASSWORD_TOO_SHORT(Types.USER_BUSINESS_TYPE,4,"密码太短"),
	PASSWORD_WRONG(Types.USER_BUSINESS_TYPE,5,"密码错误"),

	SESSION_NOT_EXISTS(Types.SESSION_TYPE,1,"会话不存在"),
	SESSION_ILLEGAL(Types.SESSION_TYPE,2,"会话验证错误"), 
	SESSION_NOT_LOGGED_IN(Types.SESSION_TYPE,3,"会话没有登录"),
	
	DATA_NOT_EXISTS(Types.DATA_TYPE,1,"数据不存在");
	
	
	private long value;
	private String desp;
	
	ErrorInfo(int type ,int subValue, String desp) {
		value = type * Short.MAX_VALUE + subValue;
		this.desp = desp;
	}
	
	public long getCode() {
		return value;
	}
	
	public String getDesp() {
		return desp;
	}
	
	private class Types {
		public final static int COMMON_TYPE = 1;
		public final static int USER_BUSINESS_TYPE=2;
		public final static int SESSION_TYPE = 3;
		public final static int DATA_TYPE = 4;
	}
}
