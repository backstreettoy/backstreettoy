package net.saowoba.backstreettoy.aware;

import net.saowoba.backstreettoy.beanfactory.BeanFactory;

/**
 * 
 * @author shl
 *
 */
public interface ApplicationContextAware {

	public void setApplicationContext(BeanFactory appCtx);
}
