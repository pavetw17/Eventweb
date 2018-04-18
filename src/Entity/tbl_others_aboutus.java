package Entity;

public class tbl_others_aboutus {
	private Integer id;
	private String contents;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public tbl_others_aboutus() {
		super();
	}
	public tbl_others_aboutus(Integer id, String contents) {
		super();
		this.id = id;
		this.contents = contents;
	}
	
	
}
