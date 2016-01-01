package net.saowoba.backstreettoy.switches.controller.util.actions;

import net.saowoba.backstreettoy.switches.annotation.dataobject.StringParameters;

import com.google.gson.JsonElement;

/**
 * 用于内部动作的Action接口
 * @author shl
 *
 */
public interface Action {

	public JsonElement act(String switchName,String operation, StringParameters param);
}
