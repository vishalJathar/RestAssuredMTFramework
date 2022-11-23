package com.vishal.vol.petstore.api.user;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.vishal.vol.petstore.Constants;
import com.vishal.vol.petstore.TestTags;
import com.vishal.vol.petstore.common.api.AbstractCommonOperations;
import com.vishal.vol.petstore.common.api.ApiTestRequestBase;
import com.vishal.vol.petstore.common.api.ApiTestWithoutPayload;
import com.vishal.vol.petstore.config.ConfigBeforeCallback;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@DisplayName("Get User Info")
@Tag(TestTags.GET_USER_INFO)
@ExtendWith(ConfigBeforeCallback.class)
public class GetUserInfoTest extends AbstractCommonOperations implements ApiTestWithoutPayload {

	private static final String API_URL = "/user/{username}";
	private static final String RESOURCE_FOLDER = "/pet/getusers/";
	private static final String COMPLETE_RESOURCE_PATH = Constants.BASE_PACKAGE_PATH + RESOURCE_FOLDER;

	@Tag(TestTags.POSITIVE_TEST)
	@ParameterizedTest(name = "Positive Test Case: [id: {0}, username: {1}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "positive_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void getUserInfoPositiveTests(
			@AggregateWith(PositiveTestRequestAggregator.class) PositiveTestRequest positiveTestRequest) {

		Response response = prepareRequest(positiveTestRequest).get(API_URL);

		performPositiveTestCaseValidations(response, COMPLETE_RESOURCE_PATH, positiveTestRequest);

	}

	@Tag(TestTags.NEGATIVE_TEST)
	@ParameterizedTest(name = "Negative Test Case: [id: {0}, store_id: {1}, msisdn: {2}, http_status: {3}, code: {4}, sub_code: {5}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "negative_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void getUserInfoNegativeTests(
			@AggregateWith(NegativeTestRequestAggregator.class) NegativeTestRequest negativeTestRequest) {

		Response response = prepareRequest(negativeTestRequest).get(API_URL);

		performNegativeTestCaseValidations(response, negativeTestRequest);

	}

	@Override
	public void enhanceRequest(RequestSpecification request, ApiTestRequestBase requestBase) {
		// additional request parameters

	}

	@Override
	public void validateResponseForPositiveTestCase(Response response, ApiTestRequestBase requestBase) {
		PositiveTestRequest positiveTestRequest = (PositiveTestRequest) requestBase;
		// additional common value validations

		response.then().body("username", equalTo(positiveTestRequest.getEntityName()));
	}

	private static class PositiveTestRequestAggregator implements ArgumentsAggregator {
		@Override
		public PositiveTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			PositiveTestRequest req = new PositiveTestRequest(arguments.getString(0), arguments.getString(1), arguments.getInteger(2));
			req.setResponseSchemaFileName(arguments.getString(3));
			req.setResponseExactMatcherFileName(arguments.getString(4));
			return req;
		}
	}

	private static class NegativeTestRequestAggregator implements ArgumentsAggregator {
		@Override
		public NegativeTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			NegativeTestRequest req = new NegativeTestRequest(arguments.getString(0), arguments.getString(1), arguments.getInteger(2));
			req.setFailureCode(arguments.getString(3));
			req.setFailureSubCode(arguments.getString(4));
			return req;
		}
	}

}
