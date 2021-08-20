package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardDTO;
import file.FileDAO;

public class ContentAction extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		dto = dao.getContent(num);
		FileDAO fdao = new FileDAO();
		
		req.setAttribute("num", dto.getNum());
		req.setAttribute("writer", dto.getWriter());
		req.setAttribute("views", dto.getViews() );
		req.setAttribute("content", dto.getContent());
		req.setAttribute("fileName", fdao.getName(num));
		
		dao.count(dto.getNum(), dto.getViews()+1);
		
		req.getRequestDispatcher("/board/content.jsp").forward(req, resp);
	}
}
