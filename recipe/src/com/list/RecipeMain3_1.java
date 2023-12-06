package com.list;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RecipeMain3_1 {

	public static void main(String[] args) {
		try {
			
			String apiUrl = "http://openapi.foodsafetykorea.go.kr/api/6585679ac50542e8b033/COOKRCP01/json/1/5";
	        URL url = new URL(apiUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("GET");

	        int responseCode = conn.getResponseCode();
	        if (responseCode == 200) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String inputLine;
	            StringBuilder content = new StringBuilder();

	            while ((inputLine = in.readLine()) != null) {
	                content.append(inputLine);
	            }

	            in.close();
	            System.out.println(content.toString());
	           
	        } else {
	            throw new Exception("API 요청 실패: " + responseCode);
	        }
		
		} catch (Exception e) {
        e.printStackTrace();
		}

	}
}

