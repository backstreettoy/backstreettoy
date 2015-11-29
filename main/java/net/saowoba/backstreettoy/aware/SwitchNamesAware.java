package net.saowoba.backstreettoy.aware;

import java.util.List;

/**
 * 感知当前Controller的开关列表
 * @author shl
 *
 */
public interface SwitchNamesAware {
	
	public void setSwitchNames(List<String> names);

}
