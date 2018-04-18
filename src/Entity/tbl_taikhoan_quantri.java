package Entity;

public class tbl_taikhoan_quantri {
	private String username;
	private String password;
	private Integer id_account;
	private String question;
	private String email_quantri;
	
	public tbl_taikhoan_quantri() {
		super();
	}

	public tbl_taikhoan_quantri(String username, String password, Integer id_account) {
		super();
		this.username = username;
		this.password = password;
		this.id_account = id_account;
	}
	
	
	
	public String getEmail_quantri() {
		return email_quantri;
	}

	public void setEmail_quantri(String email_quantri) {
		this.email_quantri = email_quantri;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getId_account() {
		return id_account;
	}

	public void setId_account(Integer id_account) {
		this.id_account = id_account;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
