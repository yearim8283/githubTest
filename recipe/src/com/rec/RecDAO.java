package com.rec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecDAO {
	
	private Connection conn;
	
	public RecDAO(Connection conn) {
		this.conn = conn;
	}
	
	public int updateRecommendation(RecDTO dto) {
        // 데이터베이스 연결 및 업데이트 작업 수행
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
    
		try {
			
			sql = "INSERT INTO rec (RCP_SEQ, REC_ID) VALUES (?, ?)";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getRCP_SEQ());
			pstmt.setString(2, dto.getREQ_ID());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
		
		
		
	}
	
	

    public int getRecommendationCount(String RCP_SEQ) {
        int count = 0;
        PreparedStatement pstmt = null;
        String sql;
        // 데이터베이스 연결 및 추천 수 조회 작업 수행
        try {
        	sql = "SELECT COUNT(*) FROM rec WHERE RCP_SEQ = ?";
        
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, RCP_SEQ);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public boolean checkIfUserLikedRecipe(String userId, String RCP_SEQ) {
        boolean alreadyLiked = false;
        PreparedStatement pstmt = null;
        String sql;

        try {
            // 데이터베이스에서 userId와 recipeId를 확인하여 이미 좋아요를 눌렀는지 여부를 판단
            sql = "SELECT COUNT(*) FROM rec WHERE REC_ID = ? AND RCP_SEQ = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, RCP_SEQ);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    alreadyLiked = count > 0; // 이미 좋아요를 눌렀으면 true, 그렇지 않으면 false
                }
            }
            
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return alreadyLiked;
    }
    
    public void removeRecommendation(String RCP_SEQ, String userId) {
        PreparedStatement pstmt = null;
        String sql;

        try {
            // 데이터베이스에서 좋아요를 취소하는 SQL 쿼리 작성
            sql = "DELETE FROM rec WHERE RCP_SEQ = ? AND REC_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, RCP_SEQ);
            pstmt.setString(2, userId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
