package com.vishal.vol.petstore.common.api;

import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public interface ApiTestWithoutPayload extends ApiTestOperations {

	default RequestSpecification prepareRequest(ApiTestRequestBase requestBase) {
		RequestSpecification request = given().filter(new AllureRestAssured()).log().all()
				.contentType(ContentType.JSON);

		fillCommonParams(request, requestBase);

		enhanceRequest(request, requestBase);

		return request;
	}

	default void fillCommonParams(RequestSpecification request, ApiTestRequestBase requestBase) {
		if (requestBase.getEntityName() != null) {
			request.queryParam("statusName", requestBase.getEntityName());
		}
	}

}
