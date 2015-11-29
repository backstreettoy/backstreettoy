package net.saowoba.backstreettoy.switches.controller.util.actions;

import java.util.List;
import java.util.Map;

import net.saowoba.backstreettoy.aware.SwitchNamesAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class ListAllSwitches implements Action,SwitchNamesAware {
	
	private List<String> switchNames;
	private Gson gson = new GsonBuilder().create();

	@Override
	public JsonElement act(String switchName, String operation,
			Map<String, String> param) {
		return gson.toJsonTree(switchNames);
	}

	@Override
	public void setSwitchNames(List<String> names) {
		this.switchNames = names;
	}

}
