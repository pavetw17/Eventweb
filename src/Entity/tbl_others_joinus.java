package Entity;

public class tbl_others_joinus {
	private Integer id_joinus;
	private String image_joinus;
	private String link_joinus;
	private String name_joinus;
	
	public Integer getId_joinus() {
		return id_joinus;
	}
	public void setId_joinus(Integer id_joinus) {
		this.id_joinus = id_joinus;
	}
	public String getImage_joinus() {
		return image_joinus;
	}
	public void setImage_joinus(String image_joinus) {
		this.image_joinus = image_joinus;
	}
	public String getLink_joinus() {
		return link_joinus;
	}
	public void setLink_joinus(String link_joinus) {
		this.link_joinus = link_joinus;
	}
	public String getName_joinus() {
		return name_joinus;
	}
	public void setName_joinus(String name_joinus) {
		this.name_joinus = name_joinus;
	}
	public tbl_others_joinus(Integer id_joinus, String image_joinus,
			String link_joinus, String name_joinus) {
		super();
		this.id_joinus = id_joinus;
		this.image_joinus = image_joinus;
		this.link_joinus = link_joinus;
		this.name_joinus = name_joinus;
	}
	public tbl_others_joinus() {
		super();
	}
	  
	  
}
