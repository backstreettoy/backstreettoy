package net.saowoba.backstreettoy.switches.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Operation {

	/**
	 * 显示的名称
	 * @return
	 */
	String name();
	
	/**
	 * 参数列表
	 * @return
	 */
	String[] parameters();
	
	/**
	 * 操作描述
	 * @return
	 */
	String desp() default  "";
	
	/**
	 * 操作是否可见
	 * @return
	 */
	VisibleTypes visible() default VisibleTypes.ALL_VISIBLE;
}
