package com.forum.bdd.integrations.common_utils;

public final class OSInfoUtils {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private OSInfoUtils() {
	}

	public static String getOSInfo() {
		return System.getProperty("os.name").replace(" ", "_");
	}

}
