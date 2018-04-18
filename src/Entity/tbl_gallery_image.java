package Entity;

public class tbl_gallery_image {
	private Integer id_image ;
	private String name_image ;
	private Integer id_thumuc ;
	private String link_image;
	
	public String getLink_image() {
		return link_image;
	}
	public void setLink_image(String link_image) {
		this.link_image = link_image;
	}
	public Integer getId_image() {
		return id_image;
	}
	public void setId_image(Integer id_image) {
		this.id_image = id_image;
	}
	public String getName_image() {
		return name_image;
	}
	public void setName_image(String name_image) {
		this.name_image = name_image;
	}
	public Integer getId_thumuc() {
		return id_thumuc;
	}
	public void setId_thumuc(Integer id_thumuc) {
		this.id_thumuc = id_thumuc;
	}
	public tbl_gallery_image() {
		super();
	}
	public tbl_gallery_image(Integer id_image, String name_image,
			Integer id_thumuc) {
		super();
		this.id_image = id_image;
		this.name_image = name_image;
		this.id_thumuc = id_thumuc;
	}
	
	
}
