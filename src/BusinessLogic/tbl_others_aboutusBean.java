package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import Entity.tbl_others_aboutus;

public class tbl_others_aboutusBean {
	private tbl_others_aboutus tbl_aboutus;
	private DataSource ds;
	public tbl_others_aboutusBean(DataSource ds) {
		super();
		this.ds = ds;
	}
	public tbl_others_aboutus getTbl_aboutus() {
		return tbl_aboutus;
	}
	public void setTbl_aboutus(tbl_others_aboutus tbl_aboutus) {
		this.tbl_aboutus = tbl_aboutus;
	}
	
	public tbl_others_aboutus AboutUs_GetContents(int id){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt;
		tbl_others_aboutus tbl_aboutus = new tbl_others_aboutus();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select id, contents from tbl_others_aboutus where id = ? limit 1 ");
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_aboutus.setId(rs.getInt("id"));
				tbl_aboutus.setContents(rs.getString("contents"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tbl_aboutus;
	}
	
	/*edit contents according to id */
	public void AboutUs_EditContents() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_others_aboutus set contents=? where id = ? ");
			pstmt.setString(1, tbl_aboutus.getContents());
			pstmt.setInt(2, tbl_aboutus.getId());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
	
	
}
