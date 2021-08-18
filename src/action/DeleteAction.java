package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;

public class DeleteAction extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		int num = Integer.parseInt(req.getParameter("num"));
		String pass = req.getParameter("pass");
		
		BoardDAO dao = new BoardDAO();
		
		try {
			if(dao.deleteBoard(num, pass) == 1) {
				PrintWriter script = resp.getWriter();
				script.println("<script>");
				script.println("alert ('삭제되었습니다.');");
				script.println("location.href='list'");
				script.println("</script>");
				script.close();
			}else {
				PrintWriter script = resp.getWriter();
				script.println("<script>");
				script.println("alert ('비밀번호가 다릅니다.');");
				script.println("history.back()");
				script.println("</script>");
				script.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
