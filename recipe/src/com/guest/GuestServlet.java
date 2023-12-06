package com.guest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.util.DBConn;
import com.util.MyUtil;

public class GuestServlet extends HttpServlet{

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
		
		GuestDAO dao = new GuestDAO(DBConn.getConnection());
		
		String uri = req.getRequestURI();
		String url;
		

		if (uri.indexOf("/list.do")!=-1) {
			try {
	            // 데이터베이스 댓글 가져오는 로직으로 교체
				String RCP_SEQ = req.getParameter("no");
	            List<GuestDTO> comments = dao.getLists(RCP_SEQ);

	            // 댓글을 JSON으로 변환
	            String json = convertCommentsToJSON(comments);

	            // 응답 타입을 JSON으로 설정
	            resp.setContentType("application/json");
	            resp.setCharacterEncoding("UTF-8");

	            // JSON 응답 작성
	            PrintWriter out = resp.getWriter();
	            out.print(json);
	            out.flush();
	        } catch (Exception e) {
	            // 적절한 방법으로 예외 처리 (예: 로깅)
	            e.printStackTrace();
	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
		}else if (uri.indexOf("/save.do")!=-1) {
			
			try {
		
				String userId = req.getParameter("id");
				
				
		        //int RCP_SEQ = Integer.parseInt(req.getParameter("no"));
				
				String RCP_SEQ = req.getParameter("no");
				
				System.out.println("no: " + RCP_SEQ);
				
				
				String name = req.getParameter("name");
				System.out.println("name: " + name);
				
				String content = req.getParameter("content");
				System.out.println("content: " + content);
				
				int maxMum = dao.getMaxNum();
				
				GuestDTO dto = new GuestDTO();
				dto.setNum(maxMum+1);
				
				dto.setContent(content);
				dto.setGUEST_ID(userId);
				dto.setGUEST_NAME(name);
				dto.setRCP_SEQ(RCP_SEQ);
				
				dao.insertData(dto);
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (uri.indexOf("/delete.do")!=-1) {
			
			int num = Integer.parseInt(req.getParameter("num"));
			
			System.out.println(num);
			int result = dao.deleteData(num);
			
		}
		
	}
	
	// 댓글 객체를 기반으로 적절한 JSON 변환 방법으로 이 메서드를 교체
    private String convertCommentsToJSON(List<GuestDTO> comments) {
        // 댓글 객체를 기반으로 JSON 변환 로직을 구현
        // 간단하게 설명하기 위해 기본 접근 방식을 사용
        StringBuilder json = new StringBuilder("[");
        for (GuestDTO comment : comments) {
            json.append("{\"guestName\":\"").append(comment.getGUEST_NAME()).append("\",");
            json.append("\"guestId\":\"").append(comment.getGUEST_ID()).append("\",");
            json.append("\"content\":\"").append(comment.getContent()).append("\",");
            json.append("\"created\":\"").append(comment.getCreated()).append("\",");
            json.append("\"num\":\"").append(comment.getNum()).append("\"},");
        }
        if (comments.size() > 0) {
            json.deleteCharAt(json.length() - 1); // 마지막 쉼표 제거
        }
        json.append("]");
        return json.toString();
    }
	
}





