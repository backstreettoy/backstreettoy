package net.saowoba.backstreettoy.switches.controller.util.actions;

import java.util.Map;

import net.saowoba.backstreettoy.dataobject.Result;
import net.saowoba.backstreettoy.switches.controller.util.JsonUtil;

import com.google.gson.JsonElement;

public class SwitchNotExistsAction
implements Action{

	@Override
	public JsonElement act(String switchName, String operation,
			Map<String, String> param) {
		StringBuilder b = new StringBuilder();
		b.append("Switch ").append(switchName).append(" NOT Exists!");
		
		Result ret = new Result();
		ret.setFailReason(b.toString());
		ret.setSuccess(false);
		
		return JsonUtil.toJsonElement(ret);
	}

}
