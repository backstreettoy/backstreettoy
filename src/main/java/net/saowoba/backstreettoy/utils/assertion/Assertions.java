package net.saowoba.backstreettoy.utils.assertion;

public final class Assertions {

	public static void assertIllegalAccess() {
		throw new RuntimeException(new IllegalAccessException());
	}
	
	public static void assertIllegalAccess(String msg) {
		throw new RuntimeException(new IllegalAccessException(msg));
	}
	
}
