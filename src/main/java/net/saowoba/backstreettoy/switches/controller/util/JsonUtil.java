package net.saowoba.backstreettoy.switches.controller.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class JsonUtil {
	
	private static Gson gson = new GsonBuilder().create();
	private static Gson gsonWithExclude = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().create();

	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static JsonElement toJsonElement(Object o) {
		return gson.toJsonTree(o);
	}
	
	public static JsonElement toJsonElement(Object o , GsonBuilder gb) {
		Gson g = gb.create();
		return g.toJsonTree(o);
	}
	
	public static JsonElement toJsonElement(Object o, Type typeOfSrc) {
		return gson.toJsonTree(o,typeOfSrc);
	}
	
	public static JsonElement toJsonElementWithExclude(Object src, Type typeOfSrc) {
		return gsonWithExclude.toJsonTree(src, typeOfSrc);
	}

	
}
