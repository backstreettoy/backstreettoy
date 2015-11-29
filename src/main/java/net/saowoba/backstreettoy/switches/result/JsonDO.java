package net.saowoba.backstreettoy.switches.result;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;

public class JsonDO implements Content{
	
	private JsonElement value;
	
	public JsonDO() {
		value = new JsonNull();
	}
	
	public JsonDO(String json) {
		JsonParser parser = new JsonParser();
		value = parser.parse(json);
	}
	
	public JsonDO(JsonElement jsonElement) {
		value = jsonElement;
	}

	@Override
	public String getType() {
		return "json";
	}

	public JsonElement getValue() {
		return value;
	}

	public void setValue(JsonElement value) {
		this.value = value;
	}

	
}
