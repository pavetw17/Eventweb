package Entity;

public class tbl_news {
	private Integer id_news;
	private String name_news;
	private String summary;
	private String contents;
	private Integer start_date;
	private String  photo;
	private Integer month;
	private	Integer year;
	
	public tbl_news() {
		super();
	}

	public tbl_news(Integer id_news, String name_news, String summary,
			String contents, Integer start_date, String photo, Integer month,
			Integer year) {
		super();
		this.id_news = id_news;
		this.name_news = name_news;
		this.summary = summary;
		this.contents = contents;
		this.start_date = start_date;
		this.photo = photo;
		this.month = month;
		this.year = year;
	}

	public tbl_news(String name_news, String summary, String contents,
			Integer start_date, String photo, Integer month, Integer year) {
		super();
		this.name_news = name_news;
		this.summary = summary;
		this.contents = contents;
		this.start_date = start_date;
		this.photo = photo;
		this.month = month;
		this.year = year;
	}

	public Integer getId_news() {
		return id_news;
	}

	public void setId_news(Integer id_news) {
		this.id_news = id_news;
	}

	public String getName_news() {
		return name_news;
	}

	public void setName_news(String name_news) {
		this.name_news = name_news;
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

	public Integer getStart_date() {
		return start_date;
	}

	public void setStart_date(Integer start_date) {
		this.start_date = start_date;
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
	
	
}
