package com.list;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class COOKRCP01 {
    private String total_count;
    private List<RecipeItem> row;
    
    @JsonProperty("RESULT")
    private RESULT RESULT;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<RecipeItem> getRow() {
        return row;
    }
    

    public void setRow(List<RecipeItem> row) {
        this.row = row;
    }
    

	public RESULT getRESULT() {
		return RESULT;
	}

	public void setRESULT(RESULT RESULT) {
		this.RESULT = RESULT;
	}

	@Override
    public String toString() {
        return "COOKRCP01{" +
                "total_count='" + total_count + '\'' +
                ", row=" + row +
                '}';
    }
}

