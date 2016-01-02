package net.saowoba.backstreettoy.test.testcase;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;
import net.saowoba.backstreettoy.beanfactory.BeanFactory;
import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;
import net.saowoba.backstreettoy.switches.annotation.util.AnnotationUtil;
import net.saowoba.backstreettoy.switches.controller.SwitchController;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SwitchControllerTest {

	private SwitchController sc = new SwitchController();
	private BeanFactory testBeanFactory;
	
	@Before
	public void setUp() {
		testBeanFactory = new TestBeanFactory();
		sc.setApplicationContext(testBeanFactory);
		sc.setSwitchNames(Arrays.asList(new String[]{"TestClassName"}));
		sc.init();
	}
	
	@Test
	public void testInvokeOperation() throws Exception {
		Object target = testBeanFactory.getBean("TestClassName");
		HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
		HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
		StringParameters params = new StringParameters();
		Object ret = AnnotationUtil.invokeOperation(target, "operationA", request,response,params);
		Assert.assertNotNull(ret);
		AnnotationUtil.invokeOperation(target, "operationB", request);
	}
	
	
	@Ignore
	@Test
	public void testMockWebRequestInvoke() throws Exception {
		HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
		HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
		PrintWriter pw = EasyMock.createMock(PrintWriter.class);

//		EasyMock.expectLastCall();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		EasyMock.expect(request.getRequestURL()).andReturn(new StringBuffer("http://test.saowoba.net/switches/TestClassName")).times(1, 1000);
		EasyMock.expect(response.getWriter()).andReturn(pw).times(1,1000);
		EasyMock.expect(request.getParameter("op")).andReturn("operationA").times(1, 1000);
		EasyMock.expect(request.getParameterMap()).andReturn(new HashMap<String,String[]>()).times(1,1000);
		pw.write("{\"success\":true,\"failReason\":\"Fail Reason\",\"extra\":{\"value\":\"hello\"}}");
		pw.flush();
		
		EasyMock.replay(request,response,pw);
		
		sc.doHandleRequest(request, response);
	}
}
