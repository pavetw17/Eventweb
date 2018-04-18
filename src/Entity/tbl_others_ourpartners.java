package Entity;

public class tbl_others_ourpartners {
	private Integer id_partners;
	private String name_partners;
	private String link_partners;
	private String introduce;
	private String link_logo;
	public Integer getId_partners() {
		return id_partners;
	}
	public void setId_partners(Integer id_partners) {
		this.id_partners = id_partners;
	}
	public String getName_partners() {
		return name_partners;
	}
	public void setName_partners(String name_partners) {
		this.name_partners = name_partners;
	}
	public String getLink_partners() {
		return link_partners;
	}
	public void setLink_partners(String link_partners) {
		this.link_partners = link_partners;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getLink_logo() {
		return link_logo;
	}
	public void setLink_logo(String link_logo) {
		this.link_logo = link_logo;
	}
	public tbl_others_ourpartners() {
		super();
	}
	public tbl_others_ourpartners(Integer id_partners, String name_partners,
			String link_partners, String introduce, String link_logo) {
		super();
		this.id_partners = id_partners;
		this.name_partners = name_partners;
		this.link_partners = link_partners;
		this.introduce = introduce;
		this.link_logo = link_logo;
	}
	
}
