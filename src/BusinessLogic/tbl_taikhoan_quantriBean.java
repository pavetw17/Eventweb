package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import Entity.tbl_taikhoan_quantri;

public class tbl_taikhoan_quantriBean {
	private DataSource ds;
	private tbl_taikhoan_quantri tbl_quantri;
	
	
	public tbl_taikhoan_quantri getTbl_quantri() {
		return tbl_quantri;
	}

	public void setTbl_quantri(tbl_taikhoan_quantri tbl_quantri) {
		this.tbl_quantri = tbl_quantri;
	}

	public tbl_taikhoan_quantriBean(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public tbl_taikhoan_quantri dangNhap(String username, String password)
			throws SQLException {
		tbl_taikhoan_quantri tbl_qt = null;
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_account, username,password from tbl_taikhoan_quantri where username =? and password=? limit 1");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_qt = new tbl_taikhoan_quantri(rs.getString("username"),
						rs.getString("password"), rs.getInt("id_account"));
			}
		} finally {
			rs.close();
			conn.close();
		}
		return tbl_qt;
	}

	
	public Integer QuanTri_CheckEmail(String email,String question) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int id_account = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_account from tbl_taikhoan_quantri where email_quantri = ? and question = ?  limit 1");
			pstmt.setString(1, email);
			pstmt.setString(2, question);
			rs = pstmt.executeQuery();
			while(rs.next()){
				id_account = rs.getInt(1);
			}
		} finally {
			rs.close();
			conn.close();
		}
		return id_account;
	}
	
	public Boolean QuanTri_SetUpdateForgotPassword(String password, int id_account) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		Boolean check = false;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("update tbl_taikhoan_quantri set password = ? where id_account = ?");
			pstmt.setString(1, password);
			pstmt.setInt(2, id_account);
			int i = pstmt.executeUpdate();
			/*if( i == -1){
				//return msg error
			} else {
				//return msg success
				check = true;
			}*/
			if(i > 0){
				check = true;
			}
			
		} finally {
			conn.close();
		}
		return check;
	}
	
	public tbl_taikhoan_quantri QuanTri_GetAccountAccordingID(int id_account) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		tbl_taikhoan_quantri tbl_quantri =null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select id_account, username, password, question, email_quantri from tbl_taikhoan_quantri where id_account = ? limit 1");
			pstmt.setInt(1, id_account);
			rs = pstmt.executeQuery();
			while(rs.next()){
				tbl_quantri = new tbl_taikhoan_quantri();
				tbl_quantri.setUsername(rs.getString("username"));
				tbl_quantri.setId_account(rs.getInt("id_account"));
				tbl_quantri.setPassword(rs.getString("password"));
				tbl_quantri.setQuestion(rs.getString("question"));
				tbl_quantri.setEmail_quantri(rs.getString("email_quantri"));
			}
		} finally {
			rs.close();
			conn.close();
		}
		return tbl_quantri;
	}
	
	
	public Boolean QuanTri_UpdateAccount() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		Boolean check = false;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("update tbl_taikhoan_quantri set username=?, password=?, email_quantri=?, question=?  where id_account = ?");
			pstmt.setString(1, tbl_quantri.getUsername());
			pstmt.setString(2, tbl_quantri.getPassword());
			pstmt.setString(3, tbl_quantri.getEmail_quantri());
			pstmt.setString(4, tbl_quantri.getQuestion());
			pstmt.setInt(5, tbl_quantri.getId_account());
			int i = pstmt.executeUpdate();
			if(i > 0){
				check = true;
			}
			
		} finally {
			conn.close();
		}
		return check;
	}
}
