package Entity;

public class tbl_others_staff {
	private Integer  id_nv;
	private String  name_nv;
	private String  photo_nv;
	private String job_nv;
	private String task_nv;
	private String  email_nv;
	private Integer id_pro_nv;
	public Integer getId_nv() {
		return id_nv;
	}
	public void setId_nv(Integer id_nv) {
		this.id_nv = id_nv;
	}
	public String getName_nv() {
		return name_nv;
	}
	public void setName_nv(String name_nv) {
		this.name_nv = name_nv;
	}
	public String getPhoto_nv() {
		return photo_nv;
	}
	public void setPhoto_nv(String photo_nv) {
		this.photo_nv = photo_nv;
	}
	public String getJob_nv() {
		return job_nv;
	}
	public void setJob_nv(String job_nv) {
		this.job_nv = job_nv;
	}
	public String getTask_nv() {
		return task_nv;
	}
	public void setTask_nv(String task_nv) {
		this.task_nv = task_nv;
	}
	public String getEmail_nv() {
		return email_nv;
	}
	public void setEmail_nv(String email_nv) {
		this.email_nv = email_nv;
	}
	public Integer getId_pro_nv() {
		return id_pro_nv;
	}
	public void setId_pro_nv(Integer id_pro_nv) {
		this.id_pro_nv = id_pro_nv;
	}
	public tbl_others_staff(Integer id_nv, String name_nv, String photo_nv,
			String job_nv, String task_nv, String email_nv, Integer id_pro_nv) {
		super();
		this.id_nv = id_nv;
		this.name_nv = name_nv;
		this.photo_nv = photo_nv;
		this.job_nv = job_nv;
		this.task_nv = task_nv;
		this.email_nv = email_nv;
		this.id_pro_nv = id_pro_nv;
	}
	public tbl_others_staff() {
		super();
	}
	
	
}
