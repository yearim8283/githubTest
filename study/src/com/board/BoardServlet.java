package com.board;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.join.CustomInfo;
import com.util.DBConn;
import com.util.MyUtil;

public class BoardServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		
	}
	
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		
		rd.forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String cp = req.getContextPath();
		
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAO(conn);
		
		MyUtil myUtil = new MyUtil();
		
		String uri = req.getRequestURI();
		
		String url;
		
		if(uri.indexOf("created.do")!=-1) {
			
			HttpSession session = req.getSession();
			
			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
			
			if(info==null) {
				url = "/member/login.jsp";
				forward(req, resp, url);
				return;
			}
			
			url = "/boardTest/created.jsp";
			forward(req, resp, url);
					
		}else if(uri.indexOf("created_ok.do")!=-1) {
			
			BoardDTO dto = new BoardDTO();
			
			int maxNum = dao.getMaxNum();
			
			dto.setNum(maxNum + 1);
			dto.setSubject(req.getParameter("subject"));
			dto.setName(req.getParameter("name"));
			dto.setEmail(req.getParameter("email"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setContent(req.getParameter("content"));
			dto.setIpAddr(req.getRemoteAddr());
			
			dao.insertData(dto);
			
			url = cp + "/bbs/list.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("list.do")!=-1) {
			
			String pageNum = req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null) {
				currentPage = Integer.parseInt(pageNum);
			}
			
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue==null) {
				
				searchKey	= "subject";
				searchValue = "";
				
			}else {
				
				if(req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
				
			}
			
			int dataCount = dao.getDataCount(searchKey, searchValue);
			
			int numPerPage = 5;
			int totalPage  = myUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage) {
				currentPage = totalPage;
			}
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			List<BoardDTO> lists = dao.getLists(start, end, searchKey, searchValue);
			
			String param = "";
			if(searchValue!=null && !searchValue.equals("")) {
				param = "searchKey=" + searchKey;
				param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}
			
			String listUrl = cp + "/bbs/list.do";
			
			if(!param.equals("")) {
				listUrl += "?" + param;
			}
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			String articleUrl = cp + "/bbs/article.do?pageNum=" + currentPage;
			
			if(!param.equals("")) {
				articleUrl += "&" + param;
			}
			
			//포워딩할 데이터
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("articleUrl", articleUrl);
			
			//포워딩
			url = "/boardTest/list.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("article.do")!=-1) {
			
			int num = Integer.parseInt(req.getParameter("num"));
			String pageNum = req.getParameter("pageNum");
			
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue!=null) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}else {
				searchKey 	= "subject";
				searchValue = "";
			}
			
			dao.updateHitCount(num);
			
			BoardDTO dto = dao.getReadData(num);
			
			if(dto==null) {
				url = cp + "/bbs/list.do";
				resp.sendRedirect(url);
			}
			
			int lineSu = dto.getContent().split("\n").length;
			
			dto.setContent(dto.getContent().replaceAll("\n", "<br/>"));
			
			String param = "pageNum=" + pageNum;
			System.out.println(pageNum);
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("lineSu", lineSu);
			req.setAttribute("params", param);
			req.setAttribute("pageNum", pageNum);
			
			url = "/boardTest/article.jsp";
			forward(req, resp, url);			
			
		}else if(uri.indexOf("updated.do")!=-1) {
			
			int num = Integer.parseInt(req.getParameter("num"));
			String pageNum = req.getParameter("pageNum");
			
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue!=null) {
				searchValue = URLDecoder.decode(searchValue,"UTF-8");
			}
			
			BoardDTO dto = dao.getReadData(num);
			
			if(dto==null) {
				url = cp + "/bbs/list.do";
				resp.sendRedirect(url);
			}
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", param);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			
			
			url = "/boardTest/updated.jsp";
			forward(req, resp, url);			
			
		}else if(uri.indexOf("updated_ok.do")!=-1) {
			
			String pageNum = req.getParameter("pageNum");
			
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			BoardDTO dto = new BoardDTO();
			
			dto.setNum(Integer.parseInt(req.getParameter("num")));
			dto.setSubject(req.getParameter("subject"));
			dto.setName(req.getParameter("name"));
			dto.setEmail(req.getParameter("email"));
			dto.setPwd(req.getParameter("pwd"));
			dto.setContent(req.getParameter("content"));
			
			dao.updateData(dto);
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}
			
			url = cp + "/bbs/list.do?" + param;
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("deleted.do")!=-1) {
			
			int num = Integer.parseInt(req.getParameter("num"));
			String pageNum = req.getParameter("pageNum");
			
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			dao.deleteData(num);
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue!=null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}			
			
			url = cp + "/bbs/list.do?" + param;
			resp.sendRedirect(url);			

		}
	}	

}
