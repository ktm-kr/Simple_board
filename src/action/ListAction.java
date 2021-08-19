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
		int pageN;
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageN = 1;
		}else {
			pageN = Integer.parseInt(pageNum);
		}
		
		int start = pageN*10 - 9;
		int end = pageN*10;
		
		List<BoardDTO> list = null;
		BoardDAO dao = new BoardDAO();
		list = dao.getList(start, end);
		int articleCount = dao.articleCount();
		
		req.setAttribute("list", list);
		req.setAttribute("articleCount", articleCount);
		
		req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
	}
}
