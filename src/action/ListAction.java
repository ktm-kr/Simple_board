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
		int articleCount = dao.articleCount(); // 총 글 갯수
		
		int pageN;
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageN = 1;
		}else {
			pageN = Integer.parseInt(pageNum);
			if(pageN <= 0 ) {
				pageN = 1;
			}
		}
		int currentPage = pageN; // 현재 페이지 넘버
		int countList = 5; // 한 페이지에 보여줄 글 갯수
		int countPage = 3; // 페이지 갯수 ex ) [1] [2] [3] 다음
		int block = articleCount / countList ;
		if(articleCount % countList != 0){
			block++;
		}
		int startPage = (currentPage-1) / countPage * countPage + 1; // 시작 페이지
		int endPage = startPage + countPage - 1; // 끝 페이지
		if (endPage > block) {
			endPage = block;
		}
		int start = pageN*5 - 4;
		int end = pageN*5;
		list = dao.getList(start, end); // 해당 페이지에 맞는 게시물 불러오는 메서드
		
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("block", block);
		req.setAttribute("list", list);
		req.setAttribute("articleCount", articleCount);
		req.setAttribute("currentPage", currentPage);
		
		req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
	}
}
