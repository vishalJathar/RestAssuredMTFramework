package com.vishal.vol.petstore.api.user;

import com.vishal.vol.petstore.common.api.ApiTestRequestBase;

class NegativeTestRequest extends ApiTestRequestBase {

	NegativeTestRequest(String id, String username, Integer httpStatusCode) {
		super(id, username, httpStatusCode);
	}

}
