package net.saowoba.backstreettoy.utils.check;

import net.saowoba.backstreettoy.constants.ErrorInfo;
import net.saowoba.backstreettoy.utils.check.exception.CheckException;

public class EqualsChecker {

	public static void check (Object expect , Object actual) {
		if(expect != null && actual ==null) {
			throw new CheckException();
		}
		
		boolean equal = expect.equals(actual);
		if(!equal) {
			throw new CheckException();
		}
	}
	
	public static void check (Object expect , Object actual,ErrorInfo info) {
		if(expect != null && actual ==null) {
			throw new CheckException(info);
		}
		
		boolean equal = expect.equals(actual);
		if(!equal) {
			throw new CheckException(info);
		}
	}
}
