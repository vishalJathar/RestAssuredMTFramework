package com.vishal.vol.petstore.api.user;

import com.vishal.vol.petstore.common.api.ApiTestRequestBase;

class PositiveTestRequest extends ApiTestRequestBase {

	PositiveTestRequest(String id, String username, Integer httpStatusCode) {
		super(id, username, httpStatusCode);
	}

}
