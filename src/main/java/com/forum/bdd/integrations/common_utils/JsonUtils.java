package com.forum.bdd.integrations.common_utils;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;

import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.forum.bdd.integrations.NG_listners.MobileEvent;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class JsonUtils {

	private static HashMap<String, String> tempHashmap = new HashMap<String, String>();
	public static String mobileExecutionType = "android";
	public static String locatorFile;

	public String getIDBasedOnJsonPath(String json, String jsonPath) {
		return JsonPath.read(json, jsonPath).toString().replace("[", "").replace("]", "").replace("\"", "");
	}

	public static JSONObject getPageNodeFromLocatorJson(String page) {
		try {

			if (MobileEvent.applicationName.contains("forum")) {
				locatorFile = "./src/test/resources/Locators";
			} else {

			}
			String fileString = new String(Files.readAllBytes(Paths.get(locatorFile)), StandardCharsets.UTF_8);
			JSONObject parentNode = new JSONObject(fileString);
			JSONObject pageNode = (JSONObject) parentNode.get(page);
			return pageNode;
		} catch (IOException e) {
			return null;
		}
	}

	public static JSONObject getLocatorNodeFromLocatorJson(String page, String object) {
		JSONObject pageNode = getPageNodeFromLocatorJson(page);
		JSONObject objectNode = (JSONObject) pageNode.get(object);
		return objectNode;
	}

	public static String getLocatorType(String page, String object) {
		return getLocatorNodeFromLocatorJson(page, object).get("locatorType").toString();
	}

	public static String getLocatorValue(String page, String object) {
		return getLocatorNodeFromLocatorJson(page, object).get("locatorValue").toString();
	}

	public static void loadEnvironmentProperties(String environmentFileName) {
		try {
			String environmentFile = SystemEnvironmentVariables.createEnvironmentVariables()
					.getProperty("environment.filepath");
			String fileString = new String(
					Files.readAllBytes(Paths.get(environmentFile + environmentFileName + ".json")),
					StandardCharsets.UTF_8);
			JSONObject parentNode = new JSONObject(fileString);
			Iterator<String> keys = parentNode.keys();

			while (keys.hasNext()) {
				String key = keys.next();
				System.setProperty(key, parentNode.get(key).toString());
			}
		} catch (IOException e) {
		}
	}

	public String getValueForSerenityProperties(String propertyName) {
		return SystemEnvironmentVariables.createEnvironmentVariables().getProperty(propertyName);
	}

	public static String updateJsonField(String jsonString, String pointer, Object value) {
		if (String.valueOf(jsonString).startsWith("{") && String.valueOf(jsonString).endsWith("}")) {
			if (String.valueOf(value).startsWith("{") && String.valueOf(value).endsWith("}")) {
				JsonObject object = Json.createReader(new StringReader(jsonString)).readObject();
				JsonPointer activePointer = Json.createPointer(pointer);
				object = activePointer.add(object, Json.createReader(new StringReader((String) value)).readObject());
				return object.toString();
			} else {
				JsonObject object = Json.createReader(new StringReader(jsonString)).readObject();
				JsonPointer activePointer = Json.createPointer(pointer);
				object = activePointer.add(object, Json.createReader(new StringReader((String) value)).readArray());
				return object.toString();
			}
		} else {
			if (String.valueOf(value).startsWith("{") && String.valueOf(value).endsWith("}")) {
				JsonArray array = Json.createReader(new StringReader(jsonString)).readArray();
				JsonPointer activePointer = Json.createPointer(pointer);
				array = activePointer.add(array, Json.createReader(new StringReader((String) value)).readObject());
				return array.toString();
			} else {
				JsonArray array = Json.createReader(new StringReader(jsonString)).readArray();
				JsonPointer activePointer = Json.createPointer(pointer);
				array = activePointer.add(array, Json.createReader(new StringReader((String) value)).readArray());
				return array.toString();
			}
		}

	}

	public String readJsonFile(String keyFromJSON) {

		String valuefromjson = (System.getProperty(keyFromJSON));

		if (valuefromjson == null) {
			valuefromjson = keyFromJSON;
			return valuefromjson;
		} else {

			return valuefromjson;
		}
	}

	public static String ModifyJSON(String jsonString, String jPath, Object newValue) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		DocumentContext documentContext = JsonPath.using(conf).parse(jsonString);
		documentContext = documentContext.set(jPath, newValue);
		return documentContext.jsonString();
	}

	public boolean compareJsonStrings(String json1, String json2) {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode tree1 = null;
		JsonNode tree2 = null;
		try {
			tree1 = mapper.readTree(json1);
			tree2 = mapper.readTree(json2);
			// Log.info("Comparing jsons --- ");
			// Log.info("first json \n" + tree1.toString());
			// Log.info("second json \n" + tree2.toString());
		} catch (IOException e) {
		}
		boolean bool = tree1.equals(tree2);
		return bool;

	}

	public void compare(String json1) {
		JSONObject json = new JSONObject(json1);
		System.out.println(json.toString(2));
		listJson(json);

	}

	public HashMap<String, String> listJson(JSONObject json) {
		JsonUtils.tempHashmap = null;
		JsonUtils.tempHashmap = new HashMap<String, String>();
		listJSONObject("", json);
		// Log.info("Hashmap created with key count as " +
		// JsonUtils.tempHashmap.keySet().size());
		return JsonUtils.tempHashmap;
	}

	public void listObject(String parent, Object data) {
		if (data instanceof JSONObject) {
			listJSONObject(parent, (JSONObject) data);
		} else if (data instanceof JSONArray) {
			listJSONArray(parent, (JSONArray) data);
		} else {
			listPrimitive(parent, data);
		}
	}

	public void listJSONObject(String parent, JSONObject json) {
		Iterator it = json.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object child = json.get(key);
			String childKey = parent.isEmpty() ? key : parent + "." + key;
			listObject(childKey, child);
		}
	}

	public void listJSONArray(String parent, JSONArray json) {
		for (int i = 0; i < json.length(); i++) {
			Object data = json.get(i);
			listObject(parent + "[" + i + "]", data);

		}
	}

	public static Object getJSonObjectValue(String jsonString, String jPath) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		Object value = JsonPath.using(conf).parse(jsonString).read(jPath);
		return value;
	}

	public void listPrimitive(String parent, Object obj) {
		JsonUtils.tempHashmap.put(parent, String.valueOf(obj));

	}

	public static String removeJsonObject(String jsonString, String jPath) {
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
		DocumentContext documentContext = JsonPath.using(conf).parse(jsonString);
		documentContext.delete(jPath);
		return documentContext.jsonString();
	}

	public boolean compareHashmaps(Map<String, String> hashmap1, Map<String, String> hashmap2,
			String stringsToBeCompared) {
		boolean flag = false;
		if (hashmap1.toString().trim().equals(hashmap2.toString().trim()) && stringsToBeCompared.equals("")) {
			flag = true;
			// Log.info("Expected json \n" + hashmap1.toString());
			// Log.info("Actual json \n" + hashmap2.toString());
		}

		else if (hashmap1.toString().trim() != (hashmap2.toString().trim()) && stringsToBeCompared.equals("")) {
			flag = false;
			// Log.info("Expected json \n" + hashmap1.toString());
			// Log.info("Actual json \n" + hashmap2.toString());
		}

		else {
			flag = true;
			if (stringsToBeCompared.equals("")) {
				// Log.info("strings To Be Compared is null");
			} else {
				if (hashmap1.size() != hashmap2.size()) {
					// Log.error("Hashmap size does not match " + hashmap1.size() + " " +
					// hashmap2.size());
					return false;
				} else {
					// Log.info("Number of keys matched " + hashmap1.size() + " " +
					// hashmap2.size());
					for (String key : hashmap1.keySet()) {
						if (!hashmap1.get(key).equals(hashmap2.get(key)) && !stringsToBeCompared.contains(key)) {
							flag = false;
							// Log.error("Values for key " + key + " does not match");
						} else if (hashmap1.get(key).equals(hashmap2.get(key))) {
							// Log.info("Matching for " + key);
						} else {
							// Log.info("Not Compared for " + key);
						}
					}
				}

			}

		}

		if (flag == false)
			return false;
		else
			return true;
	}
}