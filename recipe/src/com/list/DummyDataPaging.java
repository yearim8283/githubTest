package com.list;
import java.util.ArrayList;
import java.util.List;

public class DummyDataPaging {
	
	// 페이징 처리된 더미 데이터 가져오기
    public static List<RecipeItem> getPaginatedDummyData(List<RecipeItem> allDummyData, int start, int end) {
        List<RecipeItem> paginatedData = new ArrayList<>();

        for (int i = start-1; i < end && i < allDummyData.size(); i++) {
            paginatedData.add(allDummyData.get(i));
            System.out.println(i);
        }

        return paginatedData;
    }
}
