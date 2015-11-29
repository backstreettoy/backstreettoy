package net.saowoba.backstreettoy.test.testcase.utils;

import java.lang.reflect.Method;
import java.util.Set;

import junit.framework.Assert;
import net.saowoba.backstreettoy.utils.DynamicBridge;

import org.junit.Test;

import com.google.common.collect.Sets;

public class DynamicBridgeTest {

	@Test
	public void testDynamicBridge() {
		InterfaceA ifa = DynamicBridge.createDynamicBridge(InterfaceA.class, new ImplementionA(), null);
		Assert.assertTrue(ifa.foo(new String(), new String[] {"A","B"}));
	}
	
	@Test
	public void testDynamicBridgeFilter() throws Exception {
		Set<Method> method = Sets.newHashSet(new Method[]{InterfaceA.class.getMethod("filter", new Class<?>[]{})});
		try {
			InterfaceA ifa = DynamicBridge.createDynamicBridge(InterfaceA.class, new ImplementionA(),method );
			ifa.filter();
			ifa.foo(new String(), new String[] {"A","B"});
		} catch(RuntimeException e) {
			Assert.assertEquals("method not in methodFilter: foo", e.getMessage());
		}
	}
	
	
	public interface InterfaceA {
		boolean foo(String s , String[] sa);
		void filter();
	}
	
	public class ImplementionA {
		public boolean foo(String s , String[] sa) {
			return true;
		}
		
		public void filter(){}
	}
}
