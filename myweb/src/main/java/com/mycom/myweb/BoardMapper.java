package com.mycom.myweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardMapper {

    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String uid = "myuser"; 
    private String upw = "1234";

    
    public List<BoardVO> getListWithPaging(Criteria cri) {
        List<BoardVO> list = new ArrayList<>();
        String sql = "SELECT * FROM ("
                   + "  SELECT rownum rn, bno, title, content, writer, regdate "
                   + "  FROM (SELECT * FROM board ORDER BY bno DESC) "
                   + "  WHERE rownum <= ? * ?"
                   + ") WHERE rn > (? - 1) * ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection(url, uid, upw);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, cri.getPageNum());
                pstmt.setInt(2, cri.getAmount());
                pstmt.setInt(3, cri.getPageNum());
                pstmt.setInt(4, cri.getAmount());

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    BoardVO vo = new BoardVO();
                    vo.setBno(rs.getInt("bno"));
                    vo.setTitle(rs.getString("title"));
                    vo.setWriter(rs.getString("writer"));
                    vo.setRegdate(rs.getTimestamp("regdate"));
                    list.add(vo); 
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        System.out.println(cri.getPageNum() + "페이지 데이터를 DB에서 성공적으로 가져왔습니다!");
        return list;
    } 

    
    public BoardVO get(int bno) {
        BoardVO vo = new BoardVO();
        String sql = "SELECT * FROM board WHERE bno = ?";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection(url, uid, upw);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, bno);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    vo.setBno(rs.getInt("bno"));
                    vo.setTitle(rs.getString("title"));
                    vo.setContent(rs.getString("content"));
                    vo.setWriter(rs.getString("writer"));
                    vo.setRegdate(rs.getTimestamp("regdate"));
                    vo.setUpdatedate(rs.getTimestamp("updatedate"));
                }
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return vo;
    }
    
    
 
    public void update(BoardVO vo) {
        String sql = "UPDATE board SET title = ?, content = ?, updatedate = sysdate WHERE bno = ?";
        try (Connection conn = DriverManager.getConnection(url, uid, upw);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setInt(3, vo.getBno());
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

  
    public void delete(int bno) {
        String sql = "DELETE FROM board WHERE bno = ?";
        try (Connection conn = DriverManager.getConnection(url, uid, upw);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bno);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
 
    public void insert(BoardVO vo) {
        
        String sql = "INSERT INTO board (bno, title, content, writer) "
                   + "VALUES (board_seq.nextval, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url, uid, upw);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setString(3, vo.getWriter());
            
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public int getTotalCount() {
    	int total = 0;
    	String sql = "SELECT COUNT(*) FROM board";
    	try { total = 131;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return total;
    }
    
    	
    }
    
 