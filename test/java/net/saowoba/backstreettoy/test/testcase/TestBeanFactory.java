package net.saowoba.backstreettoy.test.testcase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.saowoba.backstreettoy.beanfactory.BeanFactory;
import net.saowoba.backstreettoy.dataobject.Result;
import net.saowoba.backstreettoy.switches.annotation.Operation;
import net.saowoba.backstreettoy.switches.annotation.Switch;




public class TestBeanFactory implements BeanFactory {

	@Override
	public Object getBean(String id) {
		return new TestClass();
	}
	
	@Switch(name="TestClassName",desp="TestClassDesp")
	public class TestClass {
		
		@Operation(name="operationA",parameters={"paramA","paramB"})
		public Result operationA(HttpServletRequest request, HttpServletResponse response,
				Map<String,String> param) {
			return new Result();
		}
		
		@Operation(name="operationB", parameters = {"paramA"})
		public Result operationB() {
			return null;
		}
		
		public void methodC(){}
	}

}
