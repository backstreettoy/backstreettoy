package net.saowoba.backstreettoy.switches.controller.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.saowoba.backstreettoy.switches.controller.SwitchController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**
 * 基于Spring框架的SwitchController的包装类
 * @author shl
 *
 */
public class SpringSwitchController extends SwitchController
implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.doHandleRequest(request, response);
		return null;
	}
}
