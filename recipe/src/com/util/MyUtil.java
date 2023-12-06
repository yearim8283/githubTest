package com.util;

public class MyUtil {
	
	
	//전체 페이지 수 구하기
	public int getPageCount(int numPerPage, int dataCount) {
		
		int pageCount = 0;
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount++;
		}
		
		return pageCount;
	}
	
	
	
	
	//페이지 처리 메소드
	public String pageIndexList(int currentPage,int totalPage,String listUrl) {
		
		int numPerBlock = 5;
		int currentPageSetup; //이전 페이지(표시할 첫 페이지 - 1)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage ==0 || totalPage == 0) {
			return "";
		}
		
		//list.jsp ?pageNum=2
		//list.jsp?searchKey=subject&searchValue=suzi &pageNum=2
		
		if(listUrl.indexOf("?")!=-1) {
			listUrl = listUrl + "&";
		}else {
			listUrl = listUrl + "?";
		}
		
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12 13 14 15 다음▶
		//이전에 들어가는 페이지 번호 구하는 공식
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		//      5               7           5             5
		//      10(9)          10           5             5
		
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		//◀이전
		if(totalPage > numPerBlock && currentPageSetup > 0) {
		//   12             5	             5
			//sb.append("<a href=\""+ listUrl + "pageNum=" +
					//currentPageSetup + "\">◀이전</a>&nbsp;");
			sb.append("<li class=\"page-item\"><a class=\"page-link\" aria-disabled=\"true\" href=\""+ listUrl + "pageNum=" +
			currentPageSetup + "\">Prev</a></li>");
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp;
		}
		
		
	
		
		
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12 13 14 15 다음▶
		//바로가기 페이지
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup+numPerBlock)) {
		//      6         12         6             5               5
		//     11         12        11            10               5
			
			if(page==currentPage) {
				sb.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + page +
						"\">" + page + "</a></li>");
				//sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
				//<font color="Fuchsia">9</font>&nbsp;
			}else {
				sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + page +
						"\">" + page + "</a></li>");
				//<a href="list.jsp?pageNum=10">10</a>&nbsp;
			}
			
			page++;
			
		}
		
		
		
		
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12
		//다음▶
		if(totalPage - currentPageSetup > numPerBlock) {
		//    12              5              5
			
			sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + page +
					"\">Next</a></li>");
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
			
			
		}
		
		return sb.toString();
	}
	
	
	
	
}



















