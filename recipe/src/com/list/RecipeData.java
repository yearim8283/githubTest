package com.list;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeData {
	
	@JsonProperty("COOKRCP01")
    private COOKRCP01 COOKRCP01;

    public COOKRCP01 getCOOKRCP01() {
        return COOKRCP01;
    }

    public void setCOOKRCP01(COOKRCP01 COOKRCP01) {
        this.COOKRCP01 = COOKRCP01;
    }

    @Override
    public String toString() {
        return "RecipeData{" +
                "COOKRCP01=" + COOKRCP01 +
                '}';
    }
	
}
