package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;

public class UpdateAction extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(req.getParameter("num"));
		String content = req.getParameter("content");
		String pass = req.getParameter("pass");
		
		BoardDAO dao = new BoardDAO();
		
		try {
			if(dao.updateBoard(num, pass, content) == 1) {
				resp.sendRedirect(req.getContextPath()+"/list");
			}else {
				resp.sendRedirect(req.getContextPath()+"/updateForm?num="+num);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
