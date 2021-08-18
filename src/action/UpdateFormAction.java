package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardDTO;

public class UpdateFormAction extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		dto = dao.getContent(num);
		
		req.setAttribute("writer", dto.getWriter());
		req.setAttribute("num", num);
		req.getRequestDispatcher("/board/update.jsp").forward(req, resp);
	}
}
