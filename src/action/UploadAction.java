package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardDTO;
import file.FileDAO;

public class UploadAction extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		req.setCharacterEncoding("UTF-8");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String pass = req.getParameter("pass");
		int views = 0;
		
		BoardDAO dao = new BoardDAO();
		BoardDTO info = new BoardDTO();
		info.setContent(content);
		info.setWriter(writer);
		info.setPass(pass);
		info.setViews(views);
		dao.insertBoard(info);
		
		resp.sendRedirect(req.getContextPath()+"/list");
	}
}
