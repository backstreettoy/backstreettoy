package net.saowoba.backstreettoy.test.testcase;

import java.util.Arrays;

import junit.framework.Assert;
import net.saowoba.backstreettoy.beanfactory.BeanFactory;
import net.saowoba.backstreettoy.switches.annotation.util.AnnotationUtil;
import net.saowoba.backstreettoy.switches.controller.SwitchController;

import org.junit.Before;
import org.junit.Test;

public class SwitchControllerTest {

	private SwitchController sc = new SwitchController();
	private BeanFactory testBeanFactory;
	
	@Before
	public void setUp() {
		testBeanFactory = new TestBeanFactory();
		sc.setApplicationContext(testBeanFactory);
		sc.setSwitchNames(Arrays.asList(new String[]{"TestClassName"}));
	}
	
	@Test
	public void testInvokeOperation() {
		Object target = testBeanFactory.getBean("TestClassName");
		Object ret = AnnotationUtil.invokeOperation(target, "operationA", null,null,null);
		Assert.assertNotNull(ret);
	}
}
