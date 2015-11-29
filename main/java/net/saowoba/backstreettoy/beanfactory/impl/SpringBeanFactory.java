package net.saowoba.backstreettoy.beanfactory.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import net.saowoba.backstreettoy.beanfactory.BeanFactory;

/**
 * 
 * @author shl
 *
 */
public class SpringBeanFactory implements BeanFactory,ApplicationContextAware {
	
	private ApplicationContext appCtx;

	@Override
	public Object getBean(String id) {
		return appCtx.getBean(id);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appCtx = applicationContext;
	}

}
