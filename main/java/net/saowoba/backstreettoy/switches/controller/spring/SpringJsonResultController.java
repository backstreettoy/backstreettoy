package net.saowoba.backstreettoy.switches.controller.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.saowoba.backstreettoy.switches.controller.JsonResultController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 基于Spring框架的JsonResultController的包装类
 * @author shl
 *
 */
public abstract class SpringJsonResultController extends JsonResultController
implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.doHandleRequest(request, response);
		return null;
	}

}
