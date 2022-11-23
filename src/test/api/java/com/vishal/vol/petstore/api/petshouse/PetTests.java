package com.vishal.vol.petstore.api.petshouse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.vishal.vol.petstore.common.api.ApiTestResponseBase;
import com.vishal.vol.petstore.common.api.ApiTestWithPayload;
import com.vishal.vol.petstore.config.ConfigBeforeCallback;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Tag(TestTags.PET_SUITE)
@DisplayName("Create Pets by details")
@ExtendWith(ConfigBeforeCallback.class)
class CreatePets extends AbstractCommonOperations implements ApiTestWithPayload {
	private final Map<String, ApiTestResponseBase> TEST_DATA = new HashMap<>();

	private static final String API_URL = "/pet/";
	private static final String RESOURCE_FOLDER = "/pet/createpets/";
	private static final String COMPLETE_RESOURCE_PATH = Constants.BASE_PACKAGE_PATH + RESOURCE_FOLDER;

	@Tag(TestTags.CREATE_PET)
	@Tag(TestTags.POSITIVE_TEST)
	@ParameterizedTest(name = "Positive Test Case: [id: {0}, status: {1}, http_status: {2}, request_payload_file_name: {3}, response_schema_file_name: {4}, response_exact_matcher_file_name: {5}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "positive_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void createPetsPostiveTest(
			@AggregateWith(PositiveTestRequestAggregatorForSubmitPetsDetails.class) PositiveTestRequest positiveTestRequest)
			throws IOException {

		Response response = prepareRequest(positiveTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, true)
				.post(API_URL);

		performPositiveTestCaseValidations(response, COMPLETE_RESOURCE_PATH, positiveTestRequest);

	}

	@Tag(TestTags.CREATE_PET)
	@Tag(TestTags.NEGATIVE_TEST)
	@ParameterizedTest(name = "Negative Test Case: [id: {0}, status: {1}, http_status: {2}, request_payload_file_name: {3}, request_payload_file_name: {4}, code: {5}, sub_code: {6}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "negative_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void updatePetsNegativeTest(
			@AggregateWith(NegativeTestRequestAggregatorForSubmitPetsDetails.class) NegativeTestRequest negativeTestRequest)
			throws IOException {

		Response response = prepareRequest(negativeTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, false)
				.post(API_URL);

		performNegativeTestCaseValidations(response, negativeTestRequest);

	}

	@Override
	public void enhanceRequest(RequestSpecification request, ApiTestRequestBase requestBase) {
		//request.pathParam("statusName", requestBase.getStatusName());

		if (requestBase instanceof PositiveTestRequest) {
			PositiveTestRequest positiveTestRequest = (PositiveTestRequest) requestBase;
			//Add extra query param here
		}
	}

	@Override
	public void validateResponseForPositiveTestCase(Response response, ApiTestRequestBase requestBase) {
		PositiveTestRequest positiveTestRequest = (PositiveTestRequest) requestBase;
	}
	public static class PositiveTestRequestAggregator implements ArgumentsAggregator {
		@Override
		public PositiveTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			PositiveTestRequest req = new PositiveTestRequest(arguments.getString(0), arguments.getString(1),arguments.getInteger(2));
			req.setRequestPayloadFileName(arguments.getString(3));
			req.setResponseSchemaFileName(arguments.getString(4));
			req.setResponseExactMatcherFileName(arguments.getString(5));
			return req;
		}
	}
	public static class NegativeTestRequestAggregator implements ArgumentsAggregator {
		@Override
		public NegativeTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			NegativeTestRequest req = new NegativeTestRequest(arguments.getString(0), arguments.getString(1),arguments.getInteger(2));
			req.setFailureCode(arguments.getString(3));
			req.setFailureSubCode(arguments.getString(4));
			return req;
		}
	}
	public static class PositiveTestRequestAggregatorForSubmitPetsDetails implements ArgumentsAggregator {
		@Override
		public PositiveTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			PositiveTestRequest req = new PositiveTestRequest(arguments.getString(0), arguments.getString(1),arguments.getInteger(2));
			req.setRequestPayloadFileName(arguments.getString(3));
			req.setResponseSchemaFileName(arguments.getString(4));
			req.setResponseExactMatcherFileName(arguments.getString(5));
			return req;
		}
	}

	public static class NegativeTestRequestAggregatorForSubmitPetsDetails implements ArgumentsAggregator {
		@Override
		public NegativeTestRequest aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			NegativeTestRequest req = new NegativeTestRequest(arguments.getString(0), arguments.getString(1),arguments.getInteger(2));
			req.setRequestPayloadFileName(arguments.getString(3));
			req.setFailureCode(arguments.getString(4));
			req.setFailureSubCode(arguments.getString(5));
			return req;
		}
	}

}


	@DisplayName("Update Pets by details")
	@Nested
	@ExtendWith(ConfigBeforeCallback.class)
	class UpdateDetailsForPets extends AbstractCommonOperations implements ApiTestWithPayload {
		private final Map<String, ApiTestResponseBase> TEST_DATA = new HashMap<>();

		private static final String API_URL = "/pet/";
		private static final String RESOURCE_FOLDER = "/pet/updatepets/";
		private static final String COMPLETE_RESOURCE_PATH = Constants.BASE_PACKAGE_PATH + RESOURCE_FOLDER;

		@Tag(TestTags.UPDATE_PET_INFO)
		@Tag(TestTags.POSITIVE_TEST)
		@ParameterizedTest(name = "Positive Test Case: [id: {0}, status: {1}, http_status: {2}, request_payload_file_name: {3}, response_schema_file_name: {4}, response_exact_matcher_file_name: {5}]")
		@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
				+ "positive_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
		public void updatePetsDetailsPostiveTest(
				@AggregateWith(CreatePets.PositiveTestRequestAggregatorForSubmitPetsDetails.class) PositiveTestRequest positiveTestRequest)
				throws IOException {

			Response response = prepareRequest(positiveTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, true)
					.put(API_URL);

			performPositiveTestCaseValidations(response, COMPLETE_RESOURCE_PATH, positiveTestRequest);

		}

		@Tag(TestTags.UPDATE_PET_INFO)
		@Tag(TestTags.NEGATIVE_TEST)
		@ParameterizedTest(name = "Negative Test Case: [id: {0}, status: {1}, http_status: {2}, request_payload_file_name: {3}, request_payload_file_name: {4}, code: {5}, sub_code: {6}]")
		@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
				+ "negative_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
		public void updatePetsDetailsNegativeTest(
				@AggregateWith(CreatePets.NegativeTestRequestAggregatorForSubmitPetsDetails.class) NegativeTestRequest negativeTestRequest)
				throws IOException {

			Response response = prepareRequest(negativeTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, false)
					.put(API_URL);

			performNegativeTestCaseValidations(response, negativeTestRequest);

		}

		@Override
		public void enhanceRequest(RequestSpecification request, ApiTestRequestBase requestBase) {
			//pass storee_id in path param & gamification_client_id
			//request.pathParam("statusName", requestBase.getStatusName());

			if (requestBase instanceof PositiveTestRequest) {
				PositiveTestRequest positiveTestRequest = (PositiveTestRequest) requestBase;
				//Add extra query param here
			}
		}

		@Override
		public void validateResponseForPositiveTestCase(Response response, ApiTestRequestBase requestBase) {
			PositiveTestRequest positiveTestRequest = (PositiveTestRequest) requestBase;

		}

	}

@DisplayName("Activity of Pets by details")
@Nested
@ExtendWith(ConfigBeforeCallback.class)
class GetDetailsForPets extends AbstractCommonOperations implements ApiTestWithPayload {
	private final Map<String, ApiTestResponseBase> TEST_DATA = new HashMap<>();

	private static final String API_URL = "/pet/findByStatus";
	private static final String RESOURCE_FOLDER = "/pet/getpetdetails/";
	private static final String COMPLETE_RESOURCE_PATH = Constants.BASE_PACKAGE_PATH + RESOURCE_FOLDER;

	@Tag(TestTags.GET_PET_INFO)
	@Tag(TestTags.POSITIVE_TEST)
	@ParameterizedTest(name = "Positive Test Case: [id: {0}, status: {1}, http_status: {2}, request_payload_file_name: {3}, response_schema_file_name: {4}, response_exact_matcher_file_name: {5}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "positive_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void getPetsDetailsPostiveTest(
			@AggregateWith(CreatePets.PositiveTestRequestAggregator.class) PositiveTestRequest positiveTestRequest)
			throws IOException {

		Response response = prepareRequest(positiveTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, true)
				.get(API_URL);

		performPositiveTestCaseValidations(response, COMPLETE_RESOURCE_PATH, positiveTestRequest);

	}

	@Tag(TestTags.GET_PET_INFO)
	@Tag(TestTags.NEGATIVE_TEST)
	@ParameterizedTest(name = "Negative Test Case: [id: {0}, status: {1}, http_status: {2}, request_payload_file_name: {3}, request_payload_file_name: {4}, code: {5}, sub_code: {6}]")
	@CsvFileSource(resources = "/" + COMPLETE_RESOURCE_PATH
			+ "negative_test_cases.csv", numLinesToSkip = 1, nullValues = { "null" })
	public void getPetsDetailsNegativeTest(
			@AggregateWith(CreatePets.NegativeTestRequestAggregator.class) NegativeTestRequest negativeTestRequest)
			throws IOException {

		Response response = prepareRequest(negativeTestRequest, RESOURCE_FOLDER, COMPLETE_RESOURCE_PATH, false)
				.get(API_URL);

		performNegativeTestCaseValidations(response, negativeTestRequest);

	}

	@Override
	public void enhanceRequest(RequestSpecification request, ApiTestRequestBase requestBase) {
		//pass status in path param

		if (requestBase instanceof PositiveTestRequest) {
			PositiveTestRequest positiveTestRequest = (PositiveTestRequest) requestBase;
			if (positiveTestRequest.getLanguages() != null) {
				request.queryParam("statusName", requestBase.getEntityName());
			}
		}
	}

	@Override
	public void validateResponseForPositiveTestCase(Response response, ApiTestRequestBase requestBase) {
		PositiveTestRequest positiveTestRequest = (PositiveTestRequest) requestBase;
		response.then().body("status", equalTo(positiveTestRequest.getEntityName()));
	}

}




