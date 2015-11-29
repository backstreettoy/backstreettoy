package net.saowoba.backstreettoy.switches.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * 返回的是Json对象
 * 
 * @author shl
 * 
 */
public abstract class JsonResultController {

	private Logger log = LoggerFactory.getLogger("JsonResultController");
	private Gson gson = new GsonBuilder().create();

	public void doHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		JsonElement ret = null;
		JsonElement exceptionRet = null;
		boolean exceptOccr = false;
		try {
			ret = doHandleRequestInternal(request, response, ret);
		} catch (Exception e) {
			log.error("Exception occur in JsonResultController", e);
			exceptionRet = exceptionOccur(request, response);
		}

		// 在最后getWriter，可以在上面修改返回内容
		PrintWriter writer = response.getWriter();
		if(!exceptOccr) {
			if(ret!=null) {
				writer.write(convertResult2JsonStr(ret));
			}
		}
		else {
			if( exceptionRet != null) {
				writer.write(convertResult2JsonStr(exceptionRet));
			}
		}
		writer.flush();
	}

	private String convertResult2JsonStr(JsonElement je) {
		return gson.toJson(je);
	}

	protected abstract JsonElement doHandleRequestInternal(HttpServletRequest request,
			HttpServletResponse response, JsonElement jsonElement)
			throws Exception;

	protected abstract JsonElement exceptionOccur(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception;
}
