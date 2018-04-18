package Entity;



public class tbl_tt_project {
	private Integer id_project;
	private String name_project;
	private String summary;
	private String contents;
	private Integer post_start_date;
	private String  photo;
	private Integer month;
	private	Integer year;
	public Integer getId_project() {
		return id_project;
	}
	public void setId_project(Integer id_project) {
		this.id_project = id_project;
	}
	public String getName_project() {
		return name_project;
	}
	public void setName_project(String name_project) {
		this.name_project = name_project;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getPost_start_date() {
		return post_start_date;
	}
	public void setPost_start_date(Integer post_start_date) {
		this.post_start_date = post_start_date;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public tbl_tt_project(Integer id_project, String name_project,
			String summary, String contents, Integer post_start_date,
			String photo, Integer month, Integer year) {
		super();
		this.id_project = id_project;
		this.name_project = name_project;
		this.summary = summary;
		this.contents = contents;
		this.post_start_date = post_start_date;
		this.photo = photo;
		this.month = month;
		this.year = year;
	}
	
	
	
	public tbl_tt_project(String name_project, String summary, String contents,
			Integer post_start_date, String photo, Integer month, Integer year) {
		super();
		this.name_project = name_project;
		this.summary = summary;
		this.contents = contents;
		this.post_start_date = post_start_date;
		this.photo = photo;
		this.month = month;
		this.year = year;
	}
	public tbl_tt_project() {
		super();
	}

	
}