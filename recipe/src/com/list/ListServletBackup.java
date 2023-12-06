package com.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


//import com.score.ScoreDAO;
//import com.util.DBConn;
//import com.util.MyUtil;

//import com.board.BoardDAO;
//import com.board.BoardDTO;
//import com.util.DBConn;
//import com.util.MyUtil;

public class ListServletBackup extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		RequestDispatcher rd = 
				req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String cp = req.getContextPath();
		
		String uri = req.getRequestURI();
		
		String url;
		
		if(uri.indexOf("main.do")!=-1) {
			
			url = "/home/main.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("login.do")!=-1) {
			
			url = "/home/login.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("list.do")!=-1) {
			
			String category = req.getParameter("keyword");
			
			if (category != null) {
			    
				try {
					category = java.net.URLDecoder.decode(category, "UTF-8");
		            String apiData = RecipeMain4.fetchDataFromAPI(category);
		            
		            ObjectMapper objectMapper = new ObjectMapper(); 
		            RecipeData data = objectMapper.readValue(apiData, RecipeData.class);
		            
		            
		            COOKRCP01 cookrcp01 = data.getCOOKRCP01();
		            
		            List<RecipeItem> row = data.getCOOKRCP01().getRow();
		            String totalCount = data.getCOOKRCP01().getTotal_count(); 

		            req.setAttribute("dataList", row);
		            req.setAttribute("totalCount", totalCount);
		            req.setAttribute("category", category);

		            url = "/home/list.jsp";
					forward(req, resp, url);
		            
		           
		        } catch (Exception e) {
		            e.printStackTrace();
		            
		        }
			}	

		}else if (uri.indexOf("search.do") != -1) {
		    String keyword = req.getParameter("keyword");
		    String category = req.getParameter("category"); 
		    
		     
		    if (keyword != null) {
		        // 키워드를 사용하여 필요한 작업을 수행합니다.
		        try {
		            keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
		            
		            // category 파라미터가 존재하는 경우에만 해당 메서드를 호출합니다.
		            String apiData = (category != null) ? 
		            		RecipeMain4.searchCategoryDataFromAPI(keyword, category) : RecipeMain4.searchDataFromAPI(keyword);
		            
		            ObjectMapper objectMapper = new ObjectMapper();
		            RecipeData data = objectMapper.readValue(apiData, RecipeData.class);

		            
		            COOKRCP01 cookrcp01 = data.getCOOKRCP01();
		            
		            List<RecipeItem> row = data.getCOOKRCP01().getRow();
		            String totalCount = data.getCOOKRCP01().getTotal_count();

		            req.setAttribute("dataList", row);
		            req.setAttribute("totalCount", totalCount);
		            
		            if (category != null) {
		                req.setAttribute("category", category);
		            }

		            url = "/home/list2.jsp";
		            forward(req, resp, url);

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}else if(uri.indexOf("details.do")!=-1) {
			
			String rcpName = req.getParameter("rcpName"); 
		 
			rcpName = rcpName.replaceAll("\\s", ""); 

			if (rcpName != null) {  
		
				try {
					rcpName = java.net.URLDecoder.decode(rcpName, "UTF-8");
		            String apiData = RecipeMain4.getItemDetailsFromAPI(rcpName);
		          
		            ObjectMapper objectMapper = new ObjectMapper(); 
		            RecipeData data = objectMapper.readValue(apiData, RecipeData.class);

		            
		            COOKRCP01 cookrcp01 = data.getCOOKRCP01();
		            List<RecipeItem> row = data.getCOOKRCP01().getRow();
		            
		            req.setAttribute("RecipeItem", row);

		            
				    url = "/home/details.jsp";
				    forward(req, resp, url);
		            
		           
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}

		    
			
			
		}
	}
}
