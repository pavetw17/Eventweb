package Entity;

import java.sql.Time;


public class tbl_events {
	private Integer id_events;
	private String name_events;
	private String summary;
	private String contents;
	private Integer post_start_date;
	private String  photo;
	private Integer month;
	private	Integer year;
	private Integer start_date_events;
	private Integer end_date_events;
	private Time start_time;
	private Time end_time;
	private String location;
	
	
	public Integer getId_events() {
		return id_events;
	}
	public void setId_events(Integer id_events) {
		this.id_events = id_events;
	}
	public Integer getStart_date_events() {
		return start_date_events;
	}
	public void setStart_date_events(Integer start_date_events) {
		this.start_date_events = start_date_events;
	}
	public Integer getEnd_date_events() {
		return end_date_events;
	}
	public void setEnd_date_events(Integer end_date_events) {
		this.end_date_events = end_date_events;
	}
	public Time getStart_time() {
		return start_time;
	}
	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}
	public Time getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName_events() {
		return name_events;
	}
	public void setName_events(String name_events) {
		this.name_events = name_events;
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
	
	public tbl_events() {
		super();
	}
	public tbl_events(Integer id_events, String name_events, String summary,
			String contents, Integer post_start_date, 
			Integer month, Integer year, Integer start_date_events,
			Integer end_date_events, Time start_time, Time end_time,
			String location) {
		super();
		this.id_events = id_events;
		this.name_events = name_events;
		this.summary = summary;
		this.contents = contents;
		this.post_start_date = post_start_date;
		
		this.month = month;
		this.year = year;
		this.start_date_events = start_date_events;
		this.end_date_events = end_date_events;
		this.start_time = start_time;
		this.end_time = end_time;
		this.location = location;
	}
	public tbl_events(String name_events, String summary, String contents,
			Integer post_start_date,  Integer month, Integer year,
			Integer start_date_events, Integer end_date_events,
			Time start_time, Time end_time, String location, String photo) {
		super();
		this.name_events = name_events;
		this.summary = summary;
		this.contents = contents;
		this.post_start_date = post_start_date;
		this.photo = photo;
		this.month = month;
		this.year = year;
		this.start_date_events = start_date_events;
		this.end_date_events = end_date_events;
		this.start_time = start_time;
		this.end_time = end_time;
		this.location = location;
	}
	
	
	
	
	
	
}
