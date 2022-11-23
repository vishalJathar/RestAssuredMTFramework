package com.vishal.vol.petstore.api.petshouse;

import com.vishal.vol.petstore.common.api.ApiTestRequestBase;

class NegativeTestRequest extends ApiTestRequestBase {

	NegativeTestRequest(String id, String statusName,Integer httpStatusCode) {
		super(id, statusName, httpStatusCode);
	}

}
