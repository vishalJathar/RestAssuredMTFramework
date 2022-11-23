package com.vishal.vol.petstore.api.petshouse;

import com.vishal.vol.petstore.common.api.ApiTestRequestBase;

class PositiveTestRequest extends ApiTestRequestBase {


	private String language;
	private String requestgamificationclienid;

	PositiveTestRequest(String id, String statusName, Integer httpStatusCode) {
		super(id,statusName , httpStatusCode);
	}


	public String getLanguages() {
		return language;
	}


	public void setLangauges(String language) {
		this.language =language;

	}
	
	public String getGamificationClientId() {
		return requestgamificationclienid;
	}

	public void setGamificationClientId(String requestgamificationclienid) {
		this.requestgamificationclienid = requestgamificationclienid;
	}
	
	

}
