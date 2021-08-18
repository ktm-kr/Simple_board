package file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadAction extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String fileName = req.getParameter("file");
		
		String directory = this.getServletContext().getRealPath("/upload/");
		File file = new File(directory + "/" + fileName);
		
		String mimeType = getServletContext().getMimeType(file.toString());
		if(mimeType == null) {
			resp.setContentType("appliciation/octet-stream");
		}
		
		resp.setContentType(mimeType + "; charset=utf-8");
		resp.setHeader("Content-Disposition", "attatchment; filename="+fileName);
		
		byte[] buffer = new byte[1024];
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = resp.getOutputStream();
		
		int readCnt = 0;
		while((readCnt = in.read(buffer, 0, buffer.length)) != -1) {
			out.write(buffer, 0, readCnt);
		}
		 
		out.flush();
		out.close();
		in.close();
	}
}
