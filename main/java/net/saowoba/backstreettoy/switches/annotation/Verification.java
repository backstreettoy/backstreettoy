package net.saowoba.backstreettoy.switches.annotation;

/**
 * 这个枚举标示了一个开关的访问权限
 * <strong>建议普通查询开关对所有人开放，
 * 数据相关接口开放给普通用户，
 * 系统开关开放给系统管理员，
 * 超级用户权限不建议使用并且很少有账号有这个权限
 * </strong>
 * @author shl
 *
 */
public enum Verification {

	/**
	 * 所有人
	 */
	EVERYONE,
	
	/**
	 * 普通用户
	 */
	USER,
	
	/**
	 * 系统管理员
	 */
	ADIMINISTRATOR,
	
	/**
	 * 超级用户
	 */
	ROOT
}
