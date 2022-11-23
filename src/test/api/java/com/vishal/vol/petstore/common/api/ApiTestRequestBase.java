package com.vishal.vol.petstore.common.api;

public class ApiTestRequestBase {

	private final String id;

	private final String entityName;
	private final Integer httpStatusCode;

	private String requestSchemaFileName;
	private String requestPayloadFileName;
	private String requestPayload;

	private String responseSchemaFileName;
	private String responseExactMatcherFileName;

	private String failureCode;
	private String failureSubCode;
	private String failureDescription;
	

	private String headervalues;


	public ApiTestRequestBase(String id, String entityName, Integer httpStatusCode) {
		super();
		this.id = id;
		this.entityName = entityName;
		this.httpStatusCode = httpStatusCode;
		
	}

	public String getId() {
		return id;
	}

	public String getRequestSchemaFileName() {
		return requestSchemaFileName;
	}

	public void setRequestSchemaFileName(String requestSchemaFileName) {
		this.requestSchemaFileName = requestSchemaFileName;
	}

	public String getResponseSchemaFileName() {
		return responseSchemaFileName;
	}

	public void setResponseSchemaFileName(String responseSchemaFileName) {
		this.responseSchemaFileName = responseSchemaFileName;
	}

	public String getFailureCode() {
		return failureCode;
	}

	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}

	public String getFailureSubCode() {
		return failureSubCode;
	}

	public void setFailureSubCode(String failureSubCode) {
		this.failureSubCode = failureSubCode;
	}
	
	public String getFailureDescription() {
		return failureDescription;
	}

	public void setFailureDescription(String failureDescription) {
		this.failureDescription = failureDescription;
	}

	public String getEntityName() {
		return entityName;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getRequestPayloadFileName() {
		return requestPayloadFileName;
	}

	public void setRequestPayloadFileName(String requestPayloadFileName) {
		this.requestPayloadFileName = requestPayloadFileName;
	}

	public String getRequestPayload() {
		return requestPayload;
	}

	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}

	public String getResponseExactMatcherFileName() {
		return responseExactMatcherFileName;
	}

	public void setResponseExactMatcherFileName(String responseExactMatcherFileName) {
		this.responseExactMatcherFileName = responseExactMatcherFileName;
	}
	
	
	public String getRubricHeaderIpPort() {
		return headervalues;
	}

	public void setRubricHeaderIpPort(String headervalues) {
		this.headervalues = headervalues;
	}

}
