package net.saowoba.backstreettoy.utils.condition.function;

import net.saowoba.backstreettoy.utils.condition.ConditionHolder;

/**
 * 作为condition的Function处理器需要实现的方法
 * @author shl
 *
 */
public interface FunctionHandler {

	/**
	 * 是否支持当前的condition
	 * @param condition
	 * @return
	 */
	public boolean isSupport(ConditionHolder condition);
}
