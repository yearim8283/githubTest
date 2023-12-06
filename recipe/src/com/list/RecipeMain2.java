package com.list;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeMain2 {

	public static void main(String[] args) {
		try {
			
			// 1. URL을 만들기 위한 StringBuilder.
	        StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api/6585679ac50542e8b033/COOKRCP01/json/1/5"); /*URL*/
	        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
	        urlBuilder.append("/" + URLEncoder.encode("RCP_NM","UTF-8") + "=" + URLEncoder.encode("감말랭이 찰빵", "UTF-8")); /*Service Key*/
	        /*urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); XML 또는 JSON
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); 한 페이지 결과 수
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); 페이지 번호
	        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); 한글 국가명
	        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); ISO 2자리코드*/
	        // 3. URL 객체 생성.
	        URL url = new URL(urlBuilder.toString());
	        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        // 5. 통신을 위한 메소드 SET.
	        conn.setRequestMethod("GET");
	        // 6. 통신을 위한 Content-type SET. 
	        conn.setRequestProperty("Content-type", "application/json");
	        // 7. 통신 응답 코드 확인.
	        System.out.println("Response code: " + conn.getResponseCode());
	        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        // 10. 객체 해제.
	        rd.close();
	        conn.disconnect();
	        // 11. 전달받은 데이터 확인.
	        System.out.println(sb.toString());
	        
	        try {
	            // JSON 문자열을 JSONObject로 파싱합니다.
	            JSONObject jsonObject = new JSONObject(sb.toString());

	            // "COOKRCP01" 객체 내부의 "row" 배열을 가져옵니다.
	            JSONObject cookRcp01 = jsonObject.getJSONObject("COOKRCP01");
	            JSONArray rowArray = cookRcp01.getJSONArray("row");

	            // "row" 배열을 순회하며 원하는 데이터를 추출합니다.
	            for (int i = 0; i < rowArray.length(); i++) {
	                JSONObject recipeObject = rowArray.getJSONObject(i);
	                String rcpName = "하이";
	                String rcpPartsDetails = recipeObject.getString("RCP_PARTS_DTLS");
	                
	                if(recipeObject.has("RCP_NM") && recipeObject.getString("RCP_NM") != null){

	                	//RCP_NM 키가 있고 값이 있을때 
	                	rcpName = recipeObject.getString("RCP_NM");

	            	}

	                System.out.println("요리 이름: " + rcpName);
	                System.out.println("재료 상세: " + rcpPartsDetails);
	                System.out.println("-----------------------------");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

}
