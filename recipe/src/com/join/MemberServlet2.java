package com.join;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBConn;

public class MemberServlet2 extends HttpServlet{

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
		
		MemberDAO dao = new MemberDAO(DBConn.getConnection());
		
		String uri = req.getRequestURI();
		String url;
		
//		if(uri.indexOf("index2.do")!=-1) {
//			
//			url = "/index2.jsp";
//			forward(req, resp, url);
//			
//		}else if(uri.indexOf("created.do")!=-1) {
//			
//			url = "/member/created.jsp";
//			forward(req, resp, url);
//			
//		}else if(uri.indexOf("created_ok.do")!=-1) {
		if(uri.indexOf("created_ok.do")!=-1) {
			
			MemberDTO dto = new MemberDTO();
			
			dto.setUserId(req.getParameter("userId"));
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserName(req.getParameter("userName"));
			dto.setUserTel(req.getParameter("userTel"));
			
			System.out.println(req.getParameter("userId"));
			System.out.println(req.getParameter("userPwd"));
			System.out.println(req.getParameter("userName"));
			System.out.println(req.getParameter("userTel"));
			
			
			dao.insertData(dto);
			
			url = cp + "/rcp/main.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("login.do")!=-1) {
			
			url = "/member/login.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("login_ok.do")!=-1) {
			
			String userId = req.getParameter("userId");
			String userPwd = req.getParameter("userPwd");
			
			MemberDTO dto = dao.getReadData(userId);
			
			if(dto==null || !(dto.getUserPwd().equals(userPwd))) {				
				req.setAttribute("message", "Please enter your username or password correctly.");
//				req.setAttribute("message2", "비밀번호 찾기");
				
			
				url = "/member/login.jsp";
				forward(req, resp, url);
				return;
				
			}
			
			HttpSession session = req.getSession();
			
			CustomInfo info = new CustomInfo();
			
			info.setUserId(dto.getUserId());
			info.setUserName(dto.getUserName());
			
			System.out.println(info.getUserId());
			System.out.println(info.getUserName());
			
			session.setAttribute("customInfo", info);
		
			url = cp + "/rcp/main.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("logout.do")!=-1) {
			
			HttpSession session = req.getSession();
			
			session.removeAttribute("customInfo");
			session.invalidate();
			
//			url = cp;
//			resp.sendRedirect(url);
			
			url = cp + "/rcp/main.do";
			resp.sendRedirect(url);
			
		}/*else if(uri.indexOf("searchpw.do")!=-1) {
			
		
			url = "/member/searchpw.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("searchpw_ok.do")!=-1) {
			
			String userId = req.getParameter("userId");
			String userTel = req.getParameter("userTel");
			
			MemberDTO dto = dao.getReadData(userId);
			
			if(dto==null || !(dto.getUserTel().equals(userTel))) {				
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");
				req.setAttribute("message2", "비밀번호 찾기");
				
			
				url = "/member/login.jsp";
				forward(req, resp, url);
				return;
				
			}
			
			req.setAttribute("message", "비밀번호는"+ dto.getUserPwd() +" 입니다");
			req.setAttribute("message2", "비밀번호 찾기");
			
		
			url = "/member/login.jsp";
			forward(req, resp, url);
			return;
			
			
			
		}else if(uri.indexOf("updated.do")!=-1) {
			
			url = "/member/updated.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("updated_ok.do")!=-1) {
			
			MemberDTO dto = new MemberDTO();
			
			dto.setUserId(req.getParameter("userId"));
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserName(req.getParameter("userName"));
			dto.setUserTel(req.getParameter("userTel"));
			
			dao.updateData(dto);
			
			
			url = cp;
			resp.sendRedirect(url);
		}
		*/	
		
	}
	
}
