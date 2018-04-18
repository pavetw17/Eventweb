package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_others_joinus;

public class tbl_others_joinusBean {
	private DataSource ds;
	private tbl_others_joinus tbl_joinus;
	
	public tbl_others_joinusBean(DataSource ds) {
		super();
		this.ds = ds;
	}
	public tbl_others_joinus getTbl_joinus() {
		return tbl_joinus;
	}
	public void setTbl_joinus(tbl_others_joinus tbl_joinus) {
		this.tbl_joinus = tbl_joinus;
	}
	
	public void JoinUs_suaJoinUs() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_others_joinus set image_joinus = ?, link_joinus=?, name_joinus=? where id_joinus = ? ");
			pstmt.setString(1, tbl_joinus.getImage_joinus());
			pstmt.setString(2, tbl_joinus.getLink_joinus());
			pstmt.setString(3, tbl_joinus.getName_joinus());
			pstmt.setInt(4, tbl_joinus.getId_joinus());
			pstmt.executeUpdate();
		}finally{
				conn.close();
		}
	}
	
	public ArrayList<tbl_others_joinus> JoinUs_GetAllList() {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		tbl_others_joinus tbl_joinus = null;
		ArrayList<tbl_others_joinus> list = new ArrayList<tbl_others_joinus>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id_joinus, image_joinus, link_joinus, name_joinus from tbl_others_joinus order by id_joinus");
			while(rs.next()){
				tbl_joinus = new tbl_others_joinus();
				tbl_joinus.setId_joinus(rs.getInt("id_joinus"));
				tbl_joinus.setImage_joinus(rs.getString("image_joinus"));
				tbl_joinus.setLink_joinus(rs.getString("link_joinus"));
				tbl_joinus.setName_joinus(rs.getString("name_joinus"));
				list.add(tbl_joinus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
