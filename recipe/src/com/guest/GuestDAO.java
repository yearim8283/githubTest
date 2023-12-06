package com.guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;




public class GuestDAO {
	
	private Connection conn = null;
	
	public GuestDAO(Connection conn) {
		this.conn = conn;
	}
	
	//num컬럼의 마지막 값
	public int getMaxNum() {
		
		int maxMum = 0;
		
		ResultSet rs = null;
		String sql;
		PreparedStatement pstmt = null;
		
		try {
			sql = "select nvl(max(num),0) from guests";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxMum = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return maxMum;
	}
	
	
	
	public int insertData(GuestDTO dto) {
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		
		try {
			sql= "insert into guests (num,GUEST_NAME,GUEST_ID,RCP_SEQ,content,";
			sql += "created) values(?,?,?,?,?,sysdate)";
		
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getGUEST_NAME());
			pstmt.setString(3, dto.getGUEST_ID());
			pstmt.setString(4, dto.getRCP_SEQ());
			pstmt.setString(5, dto.getContent());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
		

		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//전체 데이터 개수
	public int getDataCount() {
		
		int totalCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from guests";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt(1);
				
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return totalCount;
	}
	
	
	//전체 데이터 읽어오기
	public List<GuestDTO> getLists(String RCP_SEQ){
		
		List<GuestDTO> lists = new ArrayList<GuestDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			
			
			sql= "select num,GUEST_NAME,GUEST_ID,RCP_SEQ,content,";
			sql+= "created from guests WHERE RCP_SEQ = ? order by num desc";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, RCP_SEQ);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestDTO dto = new GuestDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setGUEST_NAME(rs.getString("GUEST_NAME"));
				dto.setGUEST_ID(rs.getString("GUEST_ID"));
				dto.setRCP_SEQ(rs.getString("RCP_SEQ"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				
				lists.add(dto);
				
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
	}
	
	
	//삭제
	public int deleteData(int num) {
		
		int result=0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "delete from guests where num=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
