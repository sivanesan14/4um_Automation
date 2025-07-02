package com.forum.bdd.integrations.common_utils;

import java.io.File;

import java.io.FileInputStream;
import java.util.Properties;

import com.forum.bdd.enums.EnvType;

/*Singleton Design pattern*/
public class ConfigLoader {

	private static final String SEND_EMAIL_TO_USERS = "send_email_to_users";
	private static final String OVERRIDE_REPORTS = "override_reports";
	private static final String SKIPPED_STEPS_SCREENSHOT = "skipped_steps_screenshot";
	private static final String PASSED_STEPS_SCREENSHOT = "passed_steps_screenshot";
	private static final String FAILED_STEPS_SCREENSHOT = "failed_steps_screenshot";
	private static final String RETRY_FAILED_TESTS = "retry_failed_tests";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String BASE_URL = "baseUrl";
	private static final String REQUEST_DETAILS_IN_REPORTS = "request_details_in_reports";
	private static final String Execution_type = "Execution_type";

	private static final String ENV = "env";
	private static final String CONFIG_PROPERTIES = "_config.properties";

	/* Default config file is stg_config.properties */
	private static final String STG_CONFIG_PROPERTIES = "stg" + CONFIG_PROPERTIES;
	private static final String PROD_CONFIG_PROPERTIES = "prod" + CONFIG_PROPERTIES;
	private static final String QA_CONFIG_PROPERTIES = "qa" + CONFIG_PROPERTIES;

	private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/Configs/";
	private Properties properties;
	// private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {

		/* Setting EnvType.STAGE as default environment */
		/*
		 * This will check for the env value from Jenkins/Maven first. If it does not
		 * get any input from Jenkins/mvn cmd line, then, will take
		 * stg_config.properties file as default
		 */
		String env = System.getProperty(ENV, EnvType.STAGE.toString());

		switch (EnvType.valueOf(env)) {
		/* Only STAGE is working, Rest are taken for example */

		case STAGE: {
			properties = getConfigPropertyFile(STG_CONFIG_PROPERTIES);
			break;
		}
		case QA: {
			properties = getConfigPropertyFile(QA_CONFIG_PROPERTIES);
			break;
		}
		case PRODUCTION: {
			properties = getConfigPropertyFile(PROD_CONFIG_PROPERTIES);
			break;
		}
		default: {
			throw new IllegalStateException("Invalid EnvType: " + env);
		}
		}
	}

	private Properties getConfigPropertyFile(String configFile) {
		return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
	}

	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public static Properties loadPropertyFile(String filePath) {
		// Read from properties file
		File file = new File(filePath);
		Properties prop = new Properties();

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
		} catch (Exception e) {
			// LogUtil.errorLog(ConfigReader.class, "Caught the exception", e);

		}
		return prop;

	}

	public String getBaseUrl() {
		return getPropertyValue(BASE_URL);
	}

	public String getUsername() {
		return getPropertyValue(USERNAME);
	}

	public String getPassword() {
		return getPropertyValue(PASSWORD);
	}

	public String getFailedStepsScreenshot() {
		return getPropertyValue(FAILED_STEPS_SCREENSHOT);
	}

	public String getPassedStepsScreenshot() {
		return getPropertyValue(PASSED_STEPS_SCREENSHOT);
	}

	public String getSkippedStepsScreenshot() {
		return getPropertyValue(SKIPPED_STEPS_SCREENSHOT);
	}

	public String getRetryFailedTests() {
		return getPropertyValue(RETRY_FAILED_TESTS);
	}

	public String getOverrideReports() {
		return getPropertyValue(OVERRIDE_REPORTS);
	}

	public String getSendEmailToUsers() {
		return getPropertyValue(SEND_EMAIL_TO_USERS);
	}

	public String getRequestDetailsInReports() {
		return getPropertyValue(REQUEST_DETAILS_IN_REPORTS);
	}

	public static String getValue(String key) {

		Properties prop = loadPropertyFile("src/test/resources/Configs/config.properties");

		return prop.getProperty(key);
	}

	public String executetype() {
		return getPropertyValue(Execution_type);
	}
}
