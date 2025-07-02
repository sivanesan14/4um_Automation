package com.forum.bdd.integrations.common_utils;

public class Constants {

	public static final Object VIDEO_PATH = "./src/test/resources/FaceDetection.mjpeg";
	public static final String JSONINPUT_DIR = "./src/test/resources/Input_data/";

	public final static String extent_reportPath = System.getProperty("user.dir")
			+ "/Execution_Reports/Web_Reports/Extent_Web.html";
	public final static String extent_apireportPath = System.getProperty("user.dir")
			+ "/Execution_Reports/API_Reports/Extent_Api.html";
	public final static String extent_mobilereportPath = System.getProperty("user.dir")
			+ "/Execution_Reports/Mobile_Reports/Extent_Mobile.html";
	public final static String extent_desktopreportPath = System.getProperty("user.dir")
			+ "/Execution_Reports/Extent_Desktop.html";

	// inside reporting modification
	public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
	public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/author";
	public static final String ICON_SOCIAL_GITHUB_URL = "";
	public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
			+ "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
	public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
			+ "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";

}
