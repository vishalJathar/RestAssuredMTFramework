package com.vishal.vol.petstore.common.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.file.Files;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public interface ApiTestWithPayload extends ApiTestOperations {

	default RequestSpecification prepareRequest(ApiTestRequestBase requestBase, String resourceFolderName,
			String completeResourcePath, boolean validateSchema) throws IOException {

		if (requestBase.getRequestPayloadFileName() != null) {
			String requestPayload = Files.readString(getPayloadRelativePath(resourceFolderName, requestBase.getRequestPayloadFileName()));
			requestBase.setRequestPayload(requestPayload);
		}

		RequestSpecification request = given().filter(new AllureRestAssured()).log().all()
				.contentType(ContentType.JSON);

		if (requestBase.getRequestPayload() != null) {
			request.body(requestBase.getRequestPayload());
		}

		fillCommonParams(request, requestBase);

		enhanceRequest(request, requestBase);

		/*if (validateSchema && requestBase.getRequestSchemaFileName() != null) {
			// REQUEST BODY JSON SCHEMA VALIDATION
			assertThat(requestBase.getRequestPayload(),
					matchesJsonSchemaInClasspath(completeResourcePath + requestBase.getRequestSchemaFileName()));
		}
*/
		return request;
	}

	default void fillCommonParams(RequestSpecification request, ApiTestRequestBase requestBase) {
		if (requestBase.getEntityName() != null) {
			request.queryParam("statusName", requestBase.getEntityName());
		}
	}

}
