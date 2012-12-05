package com.hoiio.sdk.objects.enums;

import java.util.HashMap;
import java.util.Map;

public enum CallState {
	RINGING("ringing"),
	ENDED("ended"),
	ONGOING("ongoing");
	
	private static final Map<String, CallState> lookup = new HashMap<String, CallState>();
	
	private final String status;
	
	private CallState(String status) {
		this.status = status;
	}
	
	/**
	 * Returns a string representation of the object
	 * @return string representation of the object
	 */
	public String toString() {
		return status;
	}
	
	static {
		for (CallState s : CallState.values()) {
			lookup.put(s.toString(), s);
		}
	}
	
	/**
	 * Converts the string to {@code CallState} object
	 * @param status The call state in string
	 * @return {@code CallState} object
	 */
	public static CallState fromString(String status) {
		return lookup.get(status);
	}
}
