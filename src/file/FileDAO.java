package file;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	
	public int upload(String fileName, String fileRealName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			String SQL = "insert into FILES VALUES (?,?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
