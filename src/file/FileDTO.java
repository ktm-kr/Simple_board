package file;

public class FileDTO {
	private int fnum;
	private String fileName;
	
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public FileDTO(int fnum, String fileName) {
		this.fnum = fnum;
		this.fileName = fileName;
	}
	
}
