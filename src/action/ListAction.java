package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardDTO;

public class ListAction extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<BoardDTO> list = null;
		BoardDAO dao = new BoardDAO();
		list = dao.getList();
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
	}
}
