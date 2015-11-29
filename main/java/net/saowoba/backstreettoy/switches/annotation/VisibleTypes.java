package net.saowoba.backstreettoy.switches.annotation;

/**
 * 可见范围设定值
 * @author shl
 *
 */
public enum VisibleTypes {
	
	/**
	 * 所有人都可见
	 */
	ALL_VISIBLE(0),
	
	/**
	 * 没人可见
	 */
	NO_VISIBLE(Integer.MAX_VALUE);

	
	private int visbleValue;
	private VisibleTypes(int i) {
		this.visbleValue = i;
	}
	
	public int getValue() {
		return this.visbleValue;
	}
}
