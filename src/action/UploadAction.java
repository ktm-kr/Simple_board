package action;

import java.io.File;
import java.io.IOException;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String savePath = "board/upload";
		int uploadFileSizeLimit = 30 * 1024 * 1024;
		String encType = "UTF-8";
		String fileName = null;
		MultipartRequest multi = null;
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉터리");
		System.out.println(uploadFilePath);
		
		try {
			multi = new MultipartRequest(req, uploadFilePath, uploadFileSizeLimit, encType,
			new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("uploadFile");
			System.out.println(fileName);
		} catch (Exception e) {
			System.out.print("예외 발생 : " + e);
			e.printStackTrace();
		}
		
		
		
		req.setCharacterEncoding("UTF-8");
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		String pass = multi.getParameter("pass");
		int views = 0;
		System.out.println(content + " " + writer + " " +pass);
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
		System.out.println(fnum +" " + fileName);
		resp.sendRedirect(req.getContextPath()+"/list");
	}
}
