package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DButil.ConnectionUtil;

public class BoardDAO {
	
	private static BoardDAO instance = null;
	public BoardDAO(){}
	public static BoardDAO getInstance(){
		if(instance == null){
			synchronized(BoardDAO.class){
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	public int getNum(BoardDTO info) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int fnum = 0;
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "select NUM from BOARD where WRITER=? and PASSWORD=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getWriter());
			pstmt.setString(2, info.getPass());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				fnum = rs.getInt("NUM");
				return fnum;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		
		return -1;
	}
	
	public void insertBoard(BoardDTO info) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "insert into BOARD "
						+ "(NUM,CONTENT,WRITER,PASSWORD,VIEWS,REGDATE)"
						+ "values(BOARD_SEQ.nextval,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getContent());
			pstmt.setString(2, info.getWriter());
			pstmt.setString(3, info.getPass());
			pstmt.setInt(4, info.getViews());
			
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
	}
	
	public List<BoardDTO> getList(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDTO> list = new ArrayList<>(10);
		
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "select * from (select rownum rnum, board.* from\r\n" + 
					"(select * from board order by NUM desc) board)\r\n" + 
					"where rnum >= ? and rnum <=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO(
						rs.getInt("num"),
						rs.getString("content"),
						rs.getString("writer"),
						rs.getString("password"),
						rs.getInt("views"),
						rs.getDate("regdate")
						);
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return list;
	}
	
	public BoardDTO getContent(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "select * from BOARD where num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BoardDTO dto = new BoardDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getDate(6)
						);
				return dto;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		
		return null;
	}
	
	public int updateBoard(int num, String pass, String content) {
		System.out.println("pass=" + pass);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "select PASSWORD from BOARD where NUM=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbpasswd = rs.getString("PASSWORD");
				System.out.println("dbpasswd = "+dbpasswd);
				if(pass.equals(dbpasswd)) {
					SQL = "update BOARD set CONTENT=? where NUM = ?";
					pstmt.close();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, content);
					pstmt.setInt(2, num);
					result = pstmt.executeUpdate();
				}else {
					result = 0;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		System.out.println(result);
		return result;
	}
	
	public int deleteBoard(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "select PASSWORD from BOARD where NUM=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbpass = rs.getString("PASSWORD");
				if(dbpass.equals(pass)) {
					pstmt.close();
					SQL = "delete from BOARD where NUM=?";
					pstmt = conn.prepareStatement(SQL);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1;
				}else {
					result = 0;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return result;
	}
	public void count(int num, int count) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String SQL = "update BOARD set VIEWS=? where NUM=?";
			conn = ConnectionUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, count);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
	}
	
	public int articleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "select count(*) from BOARD";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		return result;
	}
}
