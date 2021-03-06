package net.saowoba.backstreettoy.test.testcase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.saowoba.backstreettoy.beanfactory.BeanFactory;
import net.saowoba.backstreettoy.dataobject.Result;
import net.saowoba.backstreettoy.switches.annotation.Operation;
import net.saowoba.backstreettoy.switches.annotation.Switch;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;




public class TestBeanFactory implements BeanFactory {

	@Override
	public Object getBean(String id) {
		return new TestClass();
	}
	
	@Switch(name="TestClassName",desp="TestClassDesp")
	public class TestClass {
		
		@Operation(name="operationA",parameters={"paramA","paramB"})
		public Result operationA(HttpServletRequest request, HttpServletResponse response,
				StringParameters param) {
			Result ret = new Result();
			ret.setSuccess(true);
			ret.setFailReason("Fail Reason");
			
			JsonObject extra = new JsonObject();
			extra.add("value", new JsonPrimitive("hello"));
			ret.setExtra(extra);
			
			return ret;
		}
		
		@Operation(name="operationB", parameters = {"paramA"})
		public Result operationB() {
			return null;
		}
		
		public void methodC(){}
	}

}
