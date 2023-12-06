package com.list;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RESULT {
	
	@JsonProperty("MSG")
	private String MSG;
	
	@JsonProperty("CODE")
	private String CODE;
	
	public String getMSG() {
		return MSG;
	}
	public void setMSG(String mSG) {
		MSG = mSG;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	
	
}
