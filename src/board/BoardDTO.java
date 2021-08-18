package board;

import java.util.Date;

public class BoardDTO {
	private int num;
	private String content;
	private String writer;
	private String pass;
	private int views;
	private Date regdate;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	public BoardDTO() {
		
	}
	
	public BoardDTO(String content, String writer, String pass, int views) {
		this.content = content;
		this.writer = writer;
		this.pass = pass;
		this.views = views;
	}
	public BoardDTO(int num, String content, String writer, String pass, int views, Date regdate) {
		this.num = num;
		this.content = content;
		this.writer = writer;
		this.pass = pass;
		this.views = views;
		this.regdate = regdate;
	}
	
	
	
}
