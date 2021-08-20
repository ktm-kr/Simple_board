package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.BoardDAO;
import board.BoardDTO;
import file.FileDAO;

public class UploadAction extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/board/upload.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String savePath = "board/upload";
		int uploadFileSizeLimit = 30 * 1024 * 1024; // 파일 크기 제한
		String encType = "UTF-8"; //인코딩 타입
		String fileName = null;
		MultipartRequest multi = null;
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath); // 파일 경로
		
		
		try {
			multi = new MultipartRequest(req, uploadFilePath, uploadFileSizeLimit, encType,
			new DefaultFileRenamePolicy()); // 파일명을 바꾸는 규칙이 들어있는 클래스로 업로드시 그 규칙에 따라 파일명이 변경됨
			fileName = multi.getFilesystemName("uploadFile");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setCharacterEncoding("UTF-8");
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		String pass = multi.getParameter("pass");
		
		try {
			if(content.equals("")  || writer.equals("") || pass.equals("")) {
				PrintWriter script = resp.getWriter();
				script.println("<script>");
				script.println("alert ('정보를 모두 입력해 주세요.');");
				script.println("location.href='upload'");
				script.println("</script>");
				script.close();
			}else {
				int views = 0;
				BoardDAO dao = new BoardDAO();
				BoardDTO info = new BoardDTO(
						content,
						writer,
						pass,
						views
						);
				dao.insertBoard(info);
				int fnum = dao.getNum(info);
				
				FileDAO fdao = new FileDAO();	
				fdao.upload(fnum, fileName);
				resp.sendRedirect(req.getContextPath()+"/list");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
