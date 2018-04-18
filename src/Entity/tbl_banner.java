package Entity;

public class tbl_banner {
	private int id_banner;
	private String title_banner;
	private String images;
	private String contents;
	private String link;
	private int pos;
	private boolean status;
	
	public int getId_banner() {
		return id_banner;
	}
	public void setId_banner(int id_banner) {
		this.id_banner = id_banner;
	}
	public String getTitle_banner() {
		return title_banner;
	}
	public void setTitle_banner(String title_banner) {
		this.title_banner = title_banner;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public tbl_banner() {
		super();
	}
	public tbl_banner(int id_banner, String title_banner, String images,
			String contents, String link, int pos, boolean status) {
		super();
		this.id_banner = id_banner;
		this.title_banner = title_banner;
		this.images = images;
		this.contents = contents;
		this.link = link;
		this.pos = pos;
		this.status = status;
	}
	
	
	 
	 
}
