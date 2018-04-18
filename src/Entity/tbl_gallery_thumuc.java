package Entity;

public class tbl_gallery_thumuc {
	private Integer id_thumuc;
	private String ten_thumuc;
	private Integer year;
	
	public Integer getId_thumuc() {
		return id_thumuc;
	}
	public void setId_thumuc(Integer id_thumuc) {
		this.id_thumuc = id_thumuc;
	}
	public String getTen_thumuc() {
		return ten_thumuc;
	}
	public void setTen_thumuc(String ten_thumuc) {
		this.ten_thumuc = ten_thumuc;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public tbl_gallery_thumuc() {
		super();
	}
	public tbl_gallery_thumuc(Integer id_thumuc, String ten_thumuc, Integer year) {
		super();
		this.id_thumuc = id_thumuc;
		this.ten_thumuc = ten_thumuc;
		this.year = year;
	}
	
	
}
