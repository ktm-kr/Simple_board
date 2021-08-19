package file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DButil.ConnectionUtil;
import board.BoardDAO;

public class FileDAO {
	
	private static FileDAO instance = null;
	public FileDAO(){}
	public static FileDAO getInstance(){
		if(instance == null){
			synchronized(BoardDAO.class){
				instance = new FileDAO();
			}
		}
		return instance;
	}
	
	public int upload(int fnum , String fileName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "insert into FILES (FNUM, FILENAME) VALUES  (?,?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, fnum);
			pstmt.setString(2, fileName);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String getName(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "select FILENAME from FILES where FNUM=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("FILENAME");
				return result;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e){}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e){}
			if(conn != null) try { conn.close(); } catch (SQLException e){}
		}
		
		return result;
	}
}
