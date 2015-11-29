package net.saowoba.backstreettoy.test.testcase;

import junit.framework.Assert;
import net.saowoba.backstreettoy.switches.controller.util.actions.DescribeSwitch;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ActionDescribeSwitchTest {

	@Test
	public void testDescribeSwitch() {
		TestBeanFactory beanFactory = new TestBeanFactory();
		
		DescribeSwitch ds = new DescribeSwitch();
		ds.setApplicationContext(beanFactory);
		
		JsonElement je = ds.act("testClass", "_DESP", null);
		Assert.assertTrue(je instanceof JsonObject);
		
		JsonObject jo = je.getAsJsonObject();
		Assert.assertTrue(jo.get("success").getAsBoolean());
		Assert.assertNotNull(jo.get("extra"));
		JsonObject extra = jo.get("extra").getAsJsonObject();
		Assert.assertNotNull(extra.get("operations"));
		Assert.assertNotNull(extra.get("switch"));

		JsonObject switchObj = extra.get("switch").getAsJsonObject();
		Assert.assertEquals("TestClassName", switchObj.get("name").getAsString());
		Assert.assertEquals("TestClassDesp", switchObj.get("desp").getAsString());
		
		JsonObject switchOperations = extra.get("operations").getAsJsonObject();
		Assert.assertNotNull(switchOperations.get("operationA"));
		
		JsonObject operationAObj = switchOperations.get("operationA").getAsJsonObject();
		Assert.assertEquals("operationA", operationAObj.get("name").getAsString());
		Assert.assertNotNull(operationAObj.get("parameters"));
		Assert.assertEquals(2, operationAObj.get("parameters").getAsJsonArray().size());
	}

}
