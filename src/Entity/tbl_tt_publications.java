package Entity;



public class tbl_tt_publications {
	private Integer id_publication;
	private String name_publication;
	private String summary;
	private String contents;
	private Integer post_start_date;
	private Integer month;
	private	Integer year;
	public Integer getId_publication() {
		return id_publication;
	}
	public void setId_publication(Integer id_publication) {
		this.id_publication = id_publication;
	}
	public String getName_publication() {
		return name_publication;
	}
	public void setName_publication(String name_publication) {
		this.name_publication = name_publication;
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
	public tbl_tt_publications() {
		super();
	}
	
	public tbl_tt_publications(Integer id_publication, String name_publication,
			String summary, String contents, Integer post_start_date,
			Integer month, Integer year) {
		super();
		this.id_publication = id_publication;
		this.name_publication = name_publication;
		this.summary = summary;
		this.contents = contents;
		this.post_start_date = post_start_date;
		this.month = month;
		this.year = year;
	}
	
	public tbl_tt_publications(String name_publication, String summary,
			String contents, Integer post_start_date, Integer month,
			Integer year) {
		super();
		this.name_publication = name_publication;
		this.summary = summary;
		this.contents = contents;
		this.post_start_date = post_start_date;
		this.month = month;
		this.year = year;
	}
	
	
	
}