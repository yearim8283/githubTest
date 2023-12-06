package com.guest;

public class GuestDTO {
    private int num;

    private String RCP_SEQ;
	private String GUEST_ID;
	private String GUEST_NAME;
   
    private String content;
    private String created;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRCP_SEQ() {
		return RCP_SEQ;
	}
	public void setRCP_SEQ(String rCP_SEQ) {
		RCP_SEQ = rCP_SEQ;
	}
	public String getGUEST_ID() {
		return GUEST_ID;
	}
	public void setGUEST_ID(String gUEST_ID) {
		GUEST_ID = gUEST_ID;
	}
	public String getGUEST_NAME() {
		return GUEST_NAME;
	}
	public void setGUEST_NAME(String gUEST_NAME) {
		GUEST_NAME = gUEST_NAME;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
    
    
}
