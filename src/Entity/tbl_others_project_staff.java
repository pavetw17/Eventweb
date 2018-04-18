package Entity;

public class tbl_others_project_staff {
	private Integer id_pro_nv;
	private String name_pro;
	public Integer getId_pro_nv() {
		return id_pro_nv;
	}
	public void setId_pro_nv(Integer id_pro_nv) {
		this.id_pro_nv = id_pro_nv;
	}
	public String getName_pro() {
		return name_pro;
	}
	public void setName_pro(String name_pro) {
		this.name_pro = name_pro;
	}
	public tbl_others_project_staff(Integer id_pro_nv, String name_pro) {
		super();
		this.id_pro_nv = id_pro_nv;
		this.name_pro = name_pro;
	}
	public tbl_others_project_staff() {
		super();
	}
	
	
	
}
