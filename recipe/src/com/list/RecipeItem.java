package com.list;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeItem {
	@JsonProperty("RCP_NM")
	private String RCP_NM; //메뉴명
	
	@JsonProperty("RCP_PARTS_DTLS")
    private String RCP_PARTS_DTLS; //재료 정보
	
	@JsonProperty("RCP_WAY2")
    private String RCP_WAY2;  //조리 방법(ex.찌기)
	
	@JsonProperty("RCP_PAT2")
	private String RCP_PAT2;  //요리 종류(ex.반찬)
	@JsonProperty("ATT_FILE_NO_MK")
    private String ATT_FILE_NO_MK;  //이미지 경로(대)
	@JsonProperty("ATT_FILE_NO_MAIN")
    private String ATT_FILE_NO_MAIN; //이미지 경로(소)
	 
	@JsonProperty("MANUAL_IMG01")  //만드는법_이미지_i
	private String MANUAL_IMG01;
	@JsonProperty("MANUAL_IMG02")
    private String MANUAL_IMG02;
	@JsonProperty("MANUAL_IMG03")
    private String MANUAL_IMG03;
	@JsonProperty("MANUAL_IMG04")
    private String MANUAL_IMG04;
	@JsonProperty("MANUAL_IMG05")
    private String MANUAL_IMG05;
	
	@JsonProperty("MANUAL_IMG06")
    private String MANUAL_IMG06;
	@JsonProperty("MANUAL_IMG07")
    private String MANUAL_IMG07;
	@JsonProperty("MANUAL_IMG08")
    private String MANUAL_IMG08;
	@JsonProperty("MANUAL_IMG09")
    private String MANUAL_IMG09;
	@JsonProperty("MANUAL_IMG10")
    private String MANUAL_IMG10;
	
	@JsonProperty("MANUAL_IMG11")
    private String MANUAL_IMG11;
	@JsonProperty("MANUAL_IMG12")
    private String MANUAL_IMG12;
	@JsonProperty("MANUAL_IMG13")
    private String MANUAL_IMG13;
	@JsonProperty("MANUAL_IMG14")
    private String MANUAL_IMG14;
	@JsonProperty("MANUAL_IMG15")
    private String MANUAL_IMG15;
	
	@JsonProperty("MANUAL_IMG16")
    private String MANUAL_IMG16;
	@JsonProperty("MANUAL_IMG17")
    private String MANUAL_IMG17;
	@JsonProperty("MANUAL_IMG18")
    private String MANUAL_IMG18;
	@JsonProperty("MANUAL_IMG19")
    private String MANUAL_IMG19;
	@JsonProperty("MANUAL_IMG20")
    private String MANUAL_IMG20;
	
	@JsonProperty("MANUAL01")  //만드는법
    private String MANUAL01;
	@JsonProperty("MANUAL02")
    private String MANUAL02;
	@JsonProperty("MANUAL03")
    private String MANUAL03;
	@JsonProperty("MANUAL04")
    private String MANUAL04;
	@JsonProperty("MANUAL05")
    private String MANUAL05;
	
	@JsonProperty("MANUAL06")
    private String MANUAL06;
	@JsonProperty("MANUAL07")
    private String MANUAL07;
	@JsonProperty("MANUAL08")
    private String MANUAL08;
	@JsonProperty("MANUAL09")
    private String MANUAL09;
	@JsonProperty("MANUAL10")
    private String MANUAL10;
	
	@JsonProperty("MANUAL11")
    private String MANUAL11;
	@JsonProperty("MANUAL12")
    private String MANUAL12;
	@JsonProperty("MANUAL13")
    private String MANUAL13;
	@JsonProperty("MANUAL14")
    private String MANUAL14;
	@JsonProperty("MANUAL15")
    private String MANUAL15;
	
	@JsonProperty("MANUAL16")
    private String MANUAL16;
	@JsonProperty("MANUAL17")
    private String MANUAL17;
	@JsonProperty("MANUAL18")
    private String MANUAL18;
	@JsonProperty("MANUAL19")
    private String MANUAL19;
	@JsonProperty("MANUAL20")
    private String MANUAL20;
    
	@JsonProperty("INFO_NA")
    private String INFO_NA; //나트륨
	@JsonProperty("INFO_WGT")
    private String INFO_WGT; //중량 1인분
	@JsonProperty("INFO_PRO")
    private String INFO_PRO;  //단백질
	@JsonProperty("INFO_FAT")
    private String INFO_FAT;  //지방
	@JsonProperty("HASH_TAG")
    private String HASH_TAG; //해쉬태그
	@JsonProperty("RCP_SEQ")
    private String RCP_SEQ; //일련번호
	@JsonProperty("INFO_CAR")
    private String INFO_CAR; //탄수화물
	@JsonProperty("INFO_ENG")
    private String INFO_ENG;  //열량
    
	@JsonProperty("RCP_NA_TIP")
    private String RCP_NA_TIP;  //저감 조리법 Tip
    
    
	
	
	public String getMANUAL_IMG20() {
		return MANUAL_IMG20;
	}
	public void setMANUAL_IMG20(String mANUAL_IMG20) {
		MANUAL_IMG20 = mANUAL_IMG20;
	}
	public String getMANUAL08() {
		return MANUAL08;
	}
	public void setMANUAL08(String mANUAL08) {
		MANUAL08 = mANUAL08;
	}
	public String getMANUAL09() {
		return MANUAL09;
	}
	public void setMANUAL09(String mANUAL09) {
		MANUAL09 = mANUAL09;
	}
	public String getMANUAL10() {
		return MANUAL10;
	}
	public void setMANUAL10(String mANUAL10) {
		MANUAL10 = mANUAL10;
	}
	public String getMANUAL11() {
		return MANUAL11;
	}
	public void setMANUAL11(String mANUAL11) {
		MANUAL11 = mANUAL11;
	}
	public String getMANUAL12() {
		return MANUAL12;
	}
	public void setMANUAL12(String mANUAL12) {
		MANUAL12 = mANUAL12;
	}
	public String getMANUAL19() {
		return MANUAL19;
	}
	public void setMANUAL19(String mANUAL19) {
		MANUAL19 = mANUAL19;
	}
	public String getMANUAL20() {
		return MANUAL20;
	}
	public void setMANUAL20(String mANUAL20) {
		MANUAL20 = mANUAL20;
	}
	public String getRCP_PARTS_DTLS() {
		return RCP_PARTS_DTLS;  
	}
	public void setRCP_PARTS_DTLS(String rCP_PARTS_DTLS) {
		RCP_PARTS_DTLS = rCP_PARTS_DTLS;
	}
	public String getRCP_WAY2() {
		return RCP_WAY2;
	}
	public void setRCP_WAY2(String rCP_WAY2) {
		RCP_WAY2 = rCP_WAY2;
	}
	public String getMANUAL_IMG02() {
		return MANUAL_IMG02;
	}
	public void setMANUAL_IMG02(String mANUAL_IMG02) {
		MANUAL_IMG02 = mANUAL_IMG02;
	}
	public String getMANUAL_IMG03() {
		return MANUAL_IMG03;
	}
	public void setMANUAL_IMG03(String mANUAL_IMG03) {
		MANUAL_IMG03 = mANUAL_IMG03;
	}
	public String getRCP_PAT2() {
		return RCP_PAT2;
	}
	public void setRCP_PAT2(String rCP_PAT2) {
		RCP_PAT2 = rCP_PAT2;
	}
	public String getMANUAL_IMG04() {
		return MANUAL_IMG04;
	}
	public void setMANUAL_IMG04(String mANUAL_IMG04) {
		MANUAL_IMG04 = mANUAL_IMG04;
	}
	public String getMANUAL_IMG05() {
		return MANUAL_IMG05;
	}
	public void setMANUAL_IMG05(String mANUAL_IMG05) {
		MANUAL_IMG05 = mANUAL_IMG05;
	}
	public String getMANUAL_IMG01() {
		return MANUAL_IMG01;
	}
	public void setMANUAL_IMG01(String mANUAL_IMG01) {
		MANUAL_IMG01 = mANUAL_IMG01;
	}
	public String getMANUAL01() {
		return MANUAL01;
	}
	public void setMANUAL01(String mANUAL01) {
		MANUAL01 = mANUAL01;
	}
	public String getATT_FILE_NO_MK() {
		return ATT_FILE_NO_MK;
	}
	public void setATT_FILE_NO_MK(String aTT_FILE_NO_MK) {
		ATT_FILE_NO_MK = aTT_FILE_NO_MK;
	}
	public String getMANUAL_IMG06() {
		return MANUAL_IMG06;
	}
	public void setMANUAL_IMG06(String mANUAL_IMG06) {
		MANUAL_IMG06 = mANUAL_IMG06;
	}
	public String getMANUAL_IMG07() {
		return MANUAL_IMG07;
	}
	public void setMANUAL_IMG07(String mANUAL_IMG07) {
		MANUAL_IMG07 = mANUAL_IMG07;
	}
	public String getMANUAL_IMG08() {
		return MANUAL_IMG08;
	}
	public void setMANUAL_IMG08(String mANUAL_IMG08) {
		MANUAL_IMG08 = mANUAL_IMG08;
	}
	public String getMANUAL_IMG09() {
		return MANUAL_IMG09;
	}
	public void setMANUAL_IMG09(String mANUAL_IMG09) {
		MANUAL_IMG09 = mANUAL_IMG09;
	}
	public String getMANUAL06() {
		return MANUAL06;
	}
	public void setMANUAL06(String mANUAL06) {
		MANUAL06 = mANUAL06;
	}
	public String getMANUAL07() {
		return MANUAL07;
	}
	public void setMANUAL07(String mANUAL07) {
		MANUAL07 = mANUAL07;
	}
	public String getMANUAL04() {
		return MANUAL04;
	}
	public void setMANUAL04(String mANUAL04) {
		MANUAL04 = mANUAL04;
	}
	public String getMANUAL05() {
		return MANUAL05;
	}
	public void setMANUAL05(String mANUAL05) {
		MANUAL05 = mANUAL05;
	}
	public String getMANUAL02() {
		return MANUAL02;
	}
	public void setMANUAL02(String mANUAL02) {
		MANUAL02 = mANUAL02;
	}
	public String getMANUAL03() {
		return MANUAL03;
	}
	public void setMANUAL03(String mANUAL03) {
		MANUAL03 = mANUAL03;
	}
	public String getATT_FILE_NO_MAIN() {
		return ATT_FILE_NO_MAIN;
	}
	public void setATT_FILE_NO_MAIN(String aTT_FILE_NO_MAIN) {
		ATT_FILE_NO_MAIN = aTT_FILE_NO_MAIN;
	}
	public String getINFO_NA() {
		return INFO_NA;
	}
	public void setINFO_NA(String iNFO_NA) {
		INFO_NA = iNFO_NA;
	}
	public String getINFO_WGT() {
		return INFO_WGT;
	}
	public void setINFO_WGT(String iNFO_WGT) {
		INFO_WGT = iNFO_WGT;
	}
	public String getINFO_PRO() {
		return INFO_PRO;
	}
	public void setINFO_PRO(String iNFO_PRO) {
		INFO_PRO = iNFO_PRO;
	}
	public String getINFO_FAT() {
		return INFO_FAT;
	}
	public void setINFO_FAT(String iNFO_FAT) {
		INFO_FAT = iNFO_FAT;
	}
	public String getHASH_TAG() {
		return HASH_TAG;
	}
	public void setHASH_TAG(String hASH_TAG) {
		HASH_TAG = hASH_TAG;
	}
	public String getRCP_SEQ() {
		return RCP_SEQ;
	}
	public void setRCP_SEQ(String rCP_SEQ) {
		RCP_SEQ = rCP_SEQ;
	}
	public String getINFO_CAR() {
		return INFO_CAR;
	}
	public void setINFO_CAR(String iNFO_CAR) {
		INFO_CAR = iNFO_CAR;
	}
	public String getMANUAL_IMG10() {
		return MANUAL_IMG10;
	}
	public void setMANUAL_IMG10(String mANUAL_IMG10) {
		MANUAL_IMG10 = mANUAL_IMG10;
	}
	public String getMANUAL_IMG11() {
		return MANUAL_IMG11;
	}
	public void setMANUAL_IMG11(String mANUAL_IMG11) {
		MANUAL_IMG11 = mANUAL_IMG11;
	}
	public String getMANUAL_IMG12() {
		return MANUAL_IMG12;
	}
	public void setMANUAL_IMG12(String mANUAL_IMG12) {
		MANUAL_IMG12 = mANUAL_IMG12;
	}
	public String getMANUAL_IMG13() {
		return MANUAL_IMG13;
	}
	public void setMANUAL_IMG13(String mANUAL_IMG13) {
		MANUAL_IMG13 = mANUAL_IMG13;
	}
	public String getMANUAL_IMG14() {
		return MANUAL_IMG14;
	}
	public void setMANUAL_IMG14(String mANUAL_IMG14) {
		MANUAL_IMG14 = mANUAL_IMG14;
	}
	public String getMANUAL_IMG15() {
		return MANUAL_IMG15;
	}
	public void setMANUAL_IMG15(String mANUAL_IMG15) {
		MANUAL_IMG15 = mANUAL_IMG15;
	}
	public String getMANUAL_IMG16() {
		return MANUAL_IMG16;
	}
	public void setMANUAL_IMG16(String mANUAL_IMG16) {
		MANUAL_IMG16 = mANUAL_IMG16;
	}
	public String getMANUAL_IMG17() {
		return MANUAL_IMG17;
	}
	public void setMANUAL_IMG17(String mANUAL_IMG17) {
		MANUAL_IMG17 = mANUAL_IMG17;
	}
	public String getMANUAL_IMG18() {
		return MANUAL_IMG18;
	}
	public void setMANUAL_IMG18(String mANUAL_IMG18) {
		MANUAL_IMG18 = mANUAL_IMG18;
	}
	public String getMANUAL_IMG19() {
		return MANUAL_IMG19;
	}
	public void setMANUAL_IMG19(String mANUAL_IMG19) {
		MANUAL_IMG19 = mANUAL_IMG19;
	}
	public String getRCP_NA_TIP() {
		return RCP_NA_TIP;
	}
	public void setRCP_NA_TIP(String rCP_NA_TIP) {
		RCP_NA_TIP = rCP_NA_TIP;
	}
	public String getINFO_ENG() {
		return INFO_ENG;
	}
	public void setINFO_ENG(String iNFO_ENG) {
		INFO_ENG = iNFO_ENG;
	}
	public String getMANUAL17() {
		return MANUAL17;
	}
	public void setMANUAL17(String mANUAL17) {
		MANUAL17 = mANUAL17;
	}
	public String getMANUAL18() {
		return MANUAL18;
	}
	public void setMANUAL18(String mANUAL18) {
		MANUAL18 = mANUAL18;
	}
	public String getRCP_NM() {
		return RCP_NM;
	}
	public void setRCP_NM(String rCP_NM) {
		RCP_NM = rCP_NM;
	}
	public String getMANUAL15() {
		return MANUAL15;
	}
	public void setMANUAL15(String mANUAL15) {
		MANUAL15 = mANUAL15;
	}
	public String getMANUAL16() {
		return MANUAL16;
	}
	public void setMANUAL16(String mANUAL16) {
		MANUAL16 = mANUAL16;
	}
	public String getMANUAL13() {
		return MANUAL13;
	}
	public void setMANUAL13(String mANUAL13) {
		MANUAL13 = mANUAL13;
	}
	public String getMANUAL14() {
		return MANUAL14;
	}
	public void setMANUAL14(String mANUAL14) {
		MANUAL14 = mANUAL14;
	}

    // 생성자, getter 및 setter 메서드
    
    
    
    // 필요한 생성자, getter 및 setter 메서드를 추가하세요.
}
