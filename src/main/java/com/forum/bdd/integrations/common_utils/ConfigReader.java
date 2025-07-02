package com.forum.bdd.integrations.common_utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class ConfigReader {
	private ConfigReader() {

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

	public static String getValue(String key) {

		Properties prop = loadPropertyFile("src/test/resources/Configs/config.properties");

		return prop.getProperty(key);
	}

	public static String getValue1(String key) {

		Properties prop = loadPropertyFile("src/test/resources/Configs/proviewURL.properties");

		return prop.getProperty(key);
	}

	public static int getIntValue(String key) {
		Properties prop = loadPropertyFile("src/test/resources/Configs/config.properties");

		String strKey = prop.getProperty(key);

		return Integer.parseInt(strKey);
	}

	public static String getValue2(String path, String key) {

		Properties prop = loadPropertyFile(path);

		return prop.getProperty(key);
	}

	public static void setStringValue(String path, String key, String value) throws IOException {

		FileOutputStream output = new FileOutputStream(path);

		Properties prop = new Properties();

		// set the properties value
		prop.setProperty(key, value);

		// save properties to project root folder
		prop.store(output, null);
		output.close();

	}

	public static void moveFile(String outFileName, String sourcePath, String destinationPath, String fileextention)
			throws IOException {
		File source = new File(sourcePath);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String ts = sdf.format(source.lastModified());
		File destination = new File(destinationPath + outFileName + ts);
		FileUtils.copyFile(source, destination);
		System.out.println(" new file name is " + outFileName);
	}

	public static void writeDataToConfigFile(String variable, String value) {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/Configs/config.properties";

		Properties properties = new Properties();
		try (FileInputStream fileIn = new FileInputStream(filePath)) {
			properties.load(fileIn);
		} catch (IOException e) {
			System.err.println("Error loading existing configuration: " + e.getMessage());
			return;
		}

		String existingUserName = properties.getProperty(variable);

		if (existingUserName != null) {
			System.out.println("Existing userName: " + existingUserName);
		} else {
			System.out.println("userName property not found. Adding it...");
		}

		String newUserName = value + RandomGenerator.randomInteger(3);
		properties.setProperty(variable, newUserName);

		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {

			properties.store(fileOut, "User Configuration");

			System.out.println("Configuration updated successfully.");
		} catch (IOException e) {
			System.err.println("Error saving updated configuration: " + e.getMessage());
		}
	}
}
