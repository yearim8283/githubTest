package com.rec;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.join.CustomInfo;
import com.join.MemberDAO;
import com.join.MemberDTO;
import com.util.DBConn;

public class RecServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String cp = req.getContextPath();
		
		RecDAO dao = new RecDAO(DBConn.getConnection());
		
		String uri = req.getRequestURI();
		String url;
		

		if(uri.indexOf("/AddRec.do")!=-1){
			try {
				String RCP_SEQ = req.getParameter("no");
		        String userId = req.getParameter("id");
		        
		        RecDTO dto = new RecDTO();
		        dto.setRCP_SEQ(RCP_SEQ);
		        dto.setREQ_ID(userId);
		        // 이전에 만든 RecDAO를 사용하여 데이터베이스 업데이트 작업 수행
		        dao.updateRecommendation(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (uri.indexOf("/RecCount.do")!=-1) {
			try {
				String RCP_SEQ = req.getParameter("no");
		        // 이전에 만든 RecDAO를 사용하여 추천 수를 데이터베이스에서 조회
		        int count = dao.getRecommendationCount(RCP_SEQ);
		        resp.getWriter().write(String.valueOf(count));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (uri.indexOf("/CheckRecStatus.do")!=-1) {
			
			String userId = req.getParameter("id");
			System.out.println("userId: " + userId);
	        
			String RCP_SEQ = req.getParameter("no");
			System.out.println("no: " + RCP_SEQ);

	        // 여기에서 userId와 recipeId를 사용하여 데이터베이스를 확인하여 좋아요 상태를 반환합니다.

	        // 데이터베이스에서 userId와 recipeId를 확인하여 이미 좋아요를 눌렀다면 "liked"를 반환하고, 그렇지 않으면 "not_liked"를 반환합니다.
	        boolean alreadyLiked = dao.checkIfUserLikedRecipe(userId, RCP_SEQ);

	        String likeStatus = alreadyLiked ? "liked" : "not_liked";
	        

	        resp.getWriter().write(likeStatus);
		}else if (uri.indexOf("/CheckRecStatus2.do") != -1) {
		    try {
		        // Read JSON data from the request
		        BufferedReader reader = req.getReader();
		        StringBuilder requestData = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            requestData.append(line);
		        }

		        // Convert JSON data to a Java object
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode jsonNode = mapper.readTree(requestData.toString());

		        String userId = jsonNode.get("id").asText();
		        String recipeId = jsonNode.get("no").asText();

		        // 여기에서 userId와 recipeId를 사용하여 데이터베이스를 확인하여 좋아요 상태를 반환합니다.
		        // 데이터베이스에서 userId와 recipeId를 확인하여 이미 좋아요를 눌렀다면 "liked"를 반환하고, 그렇지 않으면 "not_liked"를 반환합니다.
		        boolean alreadyLiked = dao.checkIfUserLikedRecipe(userId, recipeId);

		        // Prepare response data
		        Map<String, Object> responseData = new HashMap<>();
		        responseData.put("likeStatus", alreadyLiked ? "liked" : "not_liked");

		        // Convert Java object to JSON and send it as the response
		        resp.setContentType("application/json");
		        resp.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}else if (uri.indexOf("/RemoveRec.do")!=-1) {
			
			String rcpSeq = req.getParameter("no");
	        String userId = req.getParameter("id");
	        
	        dao.removeRecommendation(rcpSeq, userId);
		}
		
	}
	
}


