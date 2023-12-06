package com.join;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MemberDAO {
	
	private Connection conn;
	
	public MemberDAO(Connection conn) {
		this.conn = conn;
	}
	
	public int insertData(MemberDTO dto) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into users (userId,userPwd,userName,userTel) values(?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPwd());
			pstmt.setString(3, dto.getUserName());
			pstmt.setString(4, dto.getUserTel());
			
			
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public MemberDTO getReadData(String userId) {
		
		MemberDTO dto = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select userId,userPwd,userName,";
			sql += "userTel from users where userId=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto = new MemberDTO();
				
				dto.setUserId(rs.getString("userId"));
				dto.setUserPwd(rs.getString("userPwd"));
				dto.setUserName(rs.getString("userName"));
				dto.setUserTel(rs.getString("userTel"));
			}
			
			rs.close();
			pstmt.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	
		
	}
	
	//수정
		public int updateData(MemberDTO dto) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			String sql;
			
			try {
				
				sql = "update users set userPwd=?,";
				sql+= "userTel=? where userId=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dto.getUserPwd());
				pstmt.setString(3, dto.getUserTel());
				pstmt.setString(4, dto.getUserId());

				
				result = pstmt.executeUpdate();
				
				pstmt.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return result;
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
