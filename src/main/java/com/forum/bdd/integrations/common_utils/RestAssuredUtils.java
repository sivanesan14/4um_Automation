package com.forum.bdd.integrations.common_utils;

import static io.restassured.RestAssured.given;


import java.io.File;
import java.util.Map;

import com.forum.bdd.integrations.report_utils.ReportManager;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import io.restassured.config.EncoderConfig;
import io.restassured.config.ParamConfig.UpdateStrategy;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.ParamConfig;

public class RestAssuredUtils {

  //public static final String baseURI = ConfigReader.getValue("baseURI");
	public static String baseURI ;

	public static RequestSpecBuilder getSpec() {
		RestAssured.config = RestAssuredConfig.config()
				.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
				.and().paramConfig(ParamConfig.paramConfig().queryParamsUpdateStrategy(UpdateStrategy.REPLACE));
		return new RequestSpecBuilder().setBaseUri(baseURI).setRelaxedHTTPSValidation().setConfig(RestAssured.config);
	}

	public static RequestSpecBuilder getJsonReqSpec() {
		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		basicAuth.setUserName("");
		basicAuth.setPassword("");
		return new RequestSpecBuilder().setBaseUri(baseURI).setRelaxedHTTPSValidation().setAuth(basicAuth)
				.setAccept("application/json").setContentType("application/json");
	}

	public static RequestSpecBuilder getXxxEncodedReqSpec() {
		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		basicAuth.setUserName("");
		basicAuth.setPassword("");
		return new RequestSpecBuilder().setBaseUri(baseURI).setRelaxedHTTPSValidation().setAuth(basicAuth)
				.setAccept("application/json").setContentType("application/x-www-form-urlencoded");
	}

	public static Response get(RequestSpecification specification, String resourceURI) {

		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().get(resourceURI);
		String hdr = response.headers().toString();
//			ReportManager.logInfoAPI("URL: " + resourceURI);
//			ReportManager.logInfoAPI("Header: " + hdr);
		ReportManager
				.logInfoAPI("<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
						+ resourceURI + "</pre></script></p></details>");
		ReportManager.logInfoAPI(
				"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
						+ "</pre></script></p></details>");
		return response;
	}

	public static Response get(RequestSpecification specification, Map<String, Object> pathParam, String resourceURI) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).log()
				.all().get(resourceURI);
//			ReportManager.logInfoAPI("URL: " + resourceURI);
		String hdr = response.headers().toString();
		ReportManager
				.logInfoAPI("<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
						+ resourceURI + "</pre></script></p></details>");
		ReportManager.logInfoAPI(
				"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
						+ "</pre></script></p></details>");
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> queryParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		if (resourceURI.contains("?")) {
			resourceURI = resourceURI.substring(0, resourceURI.indexOf('?'));
		}
		Response response = given().log().all().spec(specification).relaxedHTTPSValidation().when()
				.queryParams(queryParam).get(resourceURI);
		String hdr = response.headers().toString();
//			ReportManager.logInfoAPI("URL: " + resourceURI);
		ReportManager
				.logInfoAPI("<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
						+ resourceURI + "</pre></script></p></details>");
		ReportManager.logInfoAPI(
				"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
						+ "</pre></script></p></details>");
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> queryParam,
			Map<String, Object> pathParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		if (resourceURI.contains("?")) {
			resourceURI = resourceURI.substring(0, resourceURI.indexOf('?'));
		}
		Response response = RestAssured.given().spec(specification).when().queryParams(queryParam).pathParams(pathParam)
				.log().all().get(resourceURI);
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			String body) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		Response response = given().spec(specification).when().body(body).pathParams(pathParam).get(resourceURI);
		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			Map<String, Object> queryParam, String body) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);
		Response response = given().spec(specification).when().body(body).queryParams(queryParam).pathParams(pathParam)
				.get(resourceURI);

		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, String body,
			Map<String, Object> queryParam) {

		Response response = given().spec(specification).when().body(body).queryParams(queryParam).get(resourceURI);

		return response;
	}

	public static Response get(RequestSpecification specification, String resourceURI, String body) {
		Response response = given().spec(specification).relaxedHTTPSValidation().when().body(body).log().all()
				.get(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, Map<String, Object> pathParams,
			String resourceURI) {
		return given().spec(specification).body(pathParams).post(resourceURI);
	}

	public static Response post(RequestSpecification specification, String body, String resourceURI) {
		Response response;
		if (resourceURI.contains("test/get_otp")) {
			RestAssured.baseURI = ConfigReader.getValue("baseURI");
			specification = RestAssured.given();
			response = given().spec(specification).log().all().relaxedHTTPSValidation().when().body(body).log().all()
					.post(resourceURI);
		} else {
			response = given().spec(specification).log().all().relaxedHTTPSValidation().when().body(body).log().all()
					.post(resourceURI);
		}

		String hdr = response.headers().toString();
		try {
			ReportManager.logInfoAPI(
					"<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
							+ resourceURI + "</pre></script></p></details>");
			ReportManager.logInfoAPI(
					"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
							+ "</pre></script></p></details>");
			ReportManager.logInfoAPI(
					"<details><summary><font color=\"green\"><b>Click to view Payload</b></font></summary><p><pre>"
							+ body + "</pre></script></p></details>");
		} catch (Exception e) {
			ReportManager.logInfoAPI(
					"<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
							+ resourceURI + "</pre></script></p></details>");
			ReportManager.logInfoAPI(
					"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
							+ "</pre></script></p></details>");
			ReportManager.logInfoAPI(
					"<details><summary><font color=\"green\"><b>Click to view Payload</b></font></summary><p><pre>"
							+ body + "</pre></script></p></details>");
		}

		return response;
	}

	public static Response post(RequestSpecification specification, String body, Map<String, Object> pathParam,
			String resourceURI) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).body(body)
				.log().all().post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, Map<String, Object> pathParam, String resourceURI,
			String FilePath) {
		File file = new File(FilePath);
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).log()
				.all().multiPart(file).post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, Map<String, Object> pathParam,
			Map<String, Object> queryParam, String resourceURI) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().log().all().pathParams(pathParam).queryParams(queryParam)
				.post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, String body, Map<String, Object> pathParam,
			String resourceURI, Map<String, Object> queryParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam).body(body)
				.post(resourceURI);

		return response;
	}

	public static Response post(RequestSpecification specification, String body, String resourceURI,
			Map<String, Object> queryParam) {
		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).when().queryParams(queryParam).body(body).log().all()
				.post(resourceURI);

		return response;
	}

	public static Response put(RequestSpecification specification, String body, String resourceURI) {

		Response response = given().spec(specification).relaxedHTTPSValidation().when().body(body).log().all()
				.put(resourceURI);

		return response;
	}

	public static Response put(RequestSpecification specification, String body, String resourceURI,
			Map<String, Object> pathParam) {

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).body(body)
				.log().all().put(resourceURI);
		String hdr = response.headers().toString();
		ReportManager
				.logInfoAPI("<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
						+ resourceURI + "</pre></script></p></details>");
		ReportManager.logInfoAPI(
				"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
						+ "</pre></script></p></details>");
		return response;

	}

	public static Response put(RequestSpecification specification, String body, Map<String, Object> queryParam,
			String resourceURI) {

		Response response = given().spec(specification).when().queryParams(queryParam).body(body).put(resourceURI);

		return response;
	}

	public static Response put(RequestSpecification specification, String body, Map<String, Object> queryParam,
			String resourceURI, Map<String, Object> pathParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam).body(body)
				.put(resourceURI);

		return response;
	}

	public static Response patch(RequestSpecification specification, String body, Map<String, Object> pathParam,
			String resourceURI) {

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam).body(body)
				.log().all().patch(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI) {

		Response response = given().log().all().spec(specification).when().delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, Map<String, Object> pathParam,
			String resourceURI) {

		Response response = given().spec(specification).when().pathParams(pathParam).delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, Map<String, Object> pathParam, String resourceURI,
			Map<String, Object> queryParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam)
				.delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI,
			Map<String, Object> queryParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).delete(resourceURI);

		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI,
			Map<String, Object> queryParam, Map<String, Object> pathParam) {

		Response response = given().spec(specification).when().queryParams(queryParam).pathParams(pathParam)
				.delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			String body) {
		Response response = given().spec(specification).when().body(body).pathParams(pathParam).delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, Map<String, Object> pathParam,
			Map<String, Object> queryParam, String body) {
		Response response = given().spec(specification).when().body(body).queryParams(queryParam).pathParams(pathParam)
				.delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, String body,
			Map<String, Object> queryParam) {
		if (resourceURI.contains("?")) {
			resourceURI = resourceURI.substring(0, resourceURI.indexOf('?'));
		}
		Response response = given().spec(specification).when().body(body).queryParams(queryParam).log().all()
				.delete(resourceURI);
		return response;
	}

	public static Response delete(RequestSpecification specification, String resourceURI, String body) {
		Response response = given().spec(specification).body(body).when().delete(resourceURI);
		return response;
	}

	public static Response post(RequestSpecification specification, Map<String, Object> pathParam, String formParameter,
			String resourceURI, String FilePath) {
		File file = new File(FilePath);

		QueryableRequestSpecification queryable = SpecificationQuerier.query(specification);

		Response response = given().spec(specification).relaxedHTTPSValidation().when().pathParams(pathParam)
				.formParam("type", formParameter).log().all().multiPart("file", file, "application/pdf")
				.post(resourceURI);

		String hdr = response.headers().toString();
//			ReportManager.logInfoAPI("URL: " + resourceURI);
//			ReportManager.logInfoAPI("Header: " + hdr);

		ReportManager
				.logInfoAPI("<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
						+ resourceURI + "</pre></script></p></details>");
		ReportManager.logInfoAPI(
				"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
						+ "</pre></script></p></details>");

		return response;

	}

	public static Response post(RequestSpecification specification, String resourceURI) {

		Response response = given().spec(specification).log().all().relaxedHTTPSValidation().when().log().all()
				.post(resourceURI);
		String hdr = response.headers().toString();
		ReportManager
				.logInfoAPI("<details><summary><font color=\"green\"><b>Click to view URL</b></font></summary><p><pre>"
						+ resourceURI + "</pre></script></p></details>");
		ReportManager.logInfoAPI(
				"<details><summary><font color=\"green\"><b>Click to view Header</b></font></summary><p><pre>" + hdr
						+ "</pre></script></p></details>");

		return response;
	}

}
