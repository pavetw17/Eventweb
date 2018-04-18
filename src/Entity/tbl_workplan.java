package Entity;


public class tbl_workplan {
	private Integer id_workplan;
	private Integer id_parent;
	private String content;
	private String objectives;
	private String status;
	private Integer year;
	private Integer post_workplan_date;
	private Integer time_begin;
	private Integer time_end;
	private String name_parent;

	public Integer getId_workplan() {
		return id_workplan;
	}

	public void setId_workplan(Integer id_workplan) {
		this.id_workplan = id_workplan;
	}

	public Integer getId_parent() {
		return id_parent;
	}

	public void setId_parent(Integer id_parent) {
		this.id_parent = id_parent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPost_workplan_date() {
		return post_workplan_date;
	}

	public void setPost_workplan_date(Integer post_workplan_date) {
		this.post_workplan_date = post_workplan_date;
	}

	public Integer getTime_begin() {
		return time_begin;
	}

	public void setTime_begin(Integer time_begin) {
		this.time_begin = time_begin;
	}

	public Integer getTime_end() {
		return time_end;
	}

	public void setTime_end(Integer time_end) {
		this.time_end = time_end;
	}

	public String getName_parent() {
		return name_parent;
	}

	public void setName_parent(String name_parent) {
		this.name_parent = name_parent;
	}

	public tbl_workplan() {
		super();
	}
	

	public tbl_workplan(Integer id_workplan, Integer id_parent,
			String content, String objectives, String status, Integer year,
			Integer post_workplan_date, Integer time_begin, Integer time_end) {
		super();
		this.id_workplan = id_workplan;
		this.id_parent = id_parent;
		this.content = content;
		this.objectives = objectives;
		this.status = status;
		this.year = year;
		this.post_workplan_date = post_workplan_date;
		this.time_begin = time_begin;
		this.time_end = time_end;
	}

	
}
