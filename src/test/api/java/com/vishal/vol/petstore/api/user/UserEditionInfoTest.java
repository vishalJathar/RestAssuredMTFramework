package com.vishal.vol.petstore.api.user;

import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.vishal.vol.petstore.common.api.ApiTestResponseBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
import com.vishal.vol.petstore.common.api.ApiTestWithPayload;
import com.vishal.vol.petstore.config.ConfigBeforeCallback;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@DisplayName("User Addition details")
@Tag(TestTags.CREATE_USER)
@ExtendWith(ConfigBeforeCallback.class)
public class UserEditionInfoTest extends AbstractCommonOperations implements ApiTestWithPayload {

	private static final String API_URL = "/user";

	private static final String RESOURCE_FOLDER = "/pet/createusers/";
	private static final String COMPLETE_RESOURCE_PATH = Constants.BASE_PACKAGE_PATH + RESOURCE_FOLDER;

	@Tag(TestTags.POSITIVE_TEST)
	@ParameterizedTest(name = "Positive Test Case: [id: {0}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "positive_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void updateUserInfoPositiveTests(
			@AggregateWith(PositiveTestRequestAggregator.class) PositiveTestRequest positiveTestRequest)
			throws IOException {

		Response response = prepareRequest(positiveTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, true)
				.post(API_URL);

		performPositiveTestCaseValidations(response, COMPLETE_RESOURCE_PATH, positiveTestRequest);

	}

	@Tag(TestTags.NEGATIVE_TEST)
	@ParameterizedTest(name = "Positive Test Case: [id: {0}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "negative_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void updateUserInfoNegativeTests(
			@AggregateWith(NegativeTestRequestAggregator.class) NegativeTestRequest negativeTestRequest)
			throws IOException {

		Response response = prepareRequest(negativeTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, false)
				.post(API_URL);

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

		response.then().body("userName", equalTo(positiveTestRequest.getEntityName()));
	}

	public static class PositiveTestRequestAggregator implements ArgumentsAggregator {
		@Override
		public PositiveTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			PositiveTestRequest req = new PositiveTestRequest(arguments.getString(0), arguments.getString(1), arguments.getInteger(2));
			req.setRequestPayloadFileName(arguments.getString(3));
			req.setResponseSchemaFileName(arguments.getString(4));
			req.setResponseExactMatcherFileName(arguments.getString(5));
			return req;
		}
	}

	public static class NegativeTestRequestAggregator implements ArgumentsAggregator {
		@Override
		public NegativeTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			NegativeTestRequest req = new NegativeTestRequest(arguments.getString(0), arguments.getString(1), arguments.getInteger(2));
			req.setRequestPayloadFileName(arguments.getString(3));
			req.setFailureSubCode(arguments.getString(4));
			req.setFailureDescription(arguments.getString(5));
			return req;
		}
	}

}

@DisplayName("Update User by details")
@Nested
@ExtendWith(ConfigBeforeCallback.class)
class UpdateDetailsForUsers extends AbstractCommonOperations implements ApiTestWithPayload {
	private final Map<String, ApiTestResponseBase> TEST_DATA = new HashMap<>();

	private static final String API_URL = "/user/";
	private static final String RESOURCE_FOLDER = "/pet/updateusers/";
	private static final String COMPLETE_RESOURCE_PATH = Constants.BASE_PACKAGE_PATH + RESOURCE_FOLDER;

	@Tag(TestTags.UPDATE_USER_INFO)
	@Tag(TestTags.POSITIVE_TEST)
	@ParameterizedTest(name = "Positive Test Case: [id: {0}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "positive_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void updateUsersPostiveTest(
			@AggregateWith(UserEditionInfoTest.PositiveTestRequestAggregator.class) PositiveTestRequest positiveTestRequest)
			throws IOException {

		Response response = prepareRequest(positiveTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, true)
				.put(API_URL);

		performPositiveTestCaseValidations(response, COMPLETE_RESOURCE_PATH, positiveTestRequest);

	}

	@Tag(TestTags.UPDATE_USER_INFO)
	@Tag(TestTags.NEGATIVE_TEST)
	@ParameterizedTest(name = "Negative Test Case: [id: {0}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "negative_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void updateUsersNegativeTets(
			@AggregateWith(UserEditionInfoTest.NegativeTestRequestAggregator.class) NegativeTestRequest negativeTestRequest)
			throws IOException {

		Response response = prepareRequest(negativeTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, false)
				.put(API_URL);

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

		response.then().body("userName", equalTo(positiveTestRequest.getEntityName()));
	}

}
