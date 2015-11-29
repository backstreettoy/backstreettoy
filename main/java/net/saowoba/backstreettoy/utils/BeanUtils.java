package net.saowoba.backstreettoy.utils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class BeanUtils {
	
	private static Cache<Class<?>, Method[]> methodCache = CacheBuilder.newBuilder().build();

	
	/**
	 * 返回对应对象的所有可见方法
	 * @param obj
	 * @return
	 */
	public static Method[] getAllMethod(Object obj) {
		final Object obj_ = obj;
		try {
			return methodCache.get(obj.getClass(), new Callable<Method[]>() {

				@Override
				public Method[] call() throws Exception {
					Class<?> clazz = obj_.getClass();
					Method[] methods = clazz.getMethods();
					return methods;
				}
			});
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
