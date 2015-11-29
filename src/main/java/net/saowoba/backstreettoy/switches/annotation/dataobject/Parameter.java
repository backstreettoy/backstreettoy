package net.saowoba.backstreettoy.switches.annotation.dataobject;

import com.google.gson.annotations.Expose;

/**
 * 每个Operation中需要的参数
 * @author shl
 *
 */
public class Parameter {

	@Expose
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
