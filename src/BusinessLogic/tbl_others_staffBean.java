package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_others_staff;

public class tbl_others_staffBean {
	private DataSource ds;
	private tbl_others_staff tbl_staff;
	
	public tbl_others_staffBean(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public tbl_others_staff getTbl_staff() {
		return tbl_staff;
	}
	
	public void setTbl_staff(tbl_others_staff tbl_staff) {
		this.tbl_staff = tbl_staff;
	}
	
	/*add new staff*/
	public void Staff_themStaff() throws SQLException  {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("insert into tbl_others_staff(name_nv,photo_nv,job_nv,task_nv,email_nv,id_pro_nv)"
							+ " values (?,?,?,?,?,?)");
			pstmt.setString(1, tbl_staff.getName_nv());
			pstmt.setString(2, tbl_staff.getPhoto_nv());
			pstmt.setString(3, tbl_staff.getJob_nv());
			pstmt.setString(4, tbl_staff.getTask_nv());
			pstmt.setString(5, tbl_staff.getEmail_nv());
			pstmt.setInt(6, tbl_staff.getId_pro_nv());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*search staff (count) for admin*/
	public int Staff_layTongSoDongStaff_coDieuKien(String name_nv, int id_pro_nv) {
		Statement stmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
		String sql = "SELECT count(id_nv) FROM tbl_others_staff where 1=1 and id_pro_nv=" + id_pro_nv;
	
		if(name_nv != null){  //so sanh voi doi tuong
			sql += " and name_nv like '%" + name_nv.replace("'", " \\' ") + "%'";
		}
		
			//System.out.println("Cau lenh: " + sql);
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	/*search staff for admin*/
	public ArrayList<tbl_others_staff> Staff_timkiem(String name_nv, int id_pro_nv,String lmt, String off){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		tbl_others_staff tbl_staff = null;
		ArrayList<tbl_others_staff> list = new ArrayList<tbl_others_staff>();
		try {
		String sql =  "select id_nv, name_nv, photo_nv, job_nv from tbl_others_staff where 1=1 and id_pro_nv = "+ id_pro_nv;
		
		//initcap : viet hoa chu cai
		
		if(name_nv != null){ 
			sql += " and lower(name_nv) like '%"+ name_nv.toLowerCase().replace("'", " \\' ") +"%'";
		}
		sql += " order by id_nv desc limit " + lmt + " offset " + off + "";

	//	System.out.println(sql);
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tbl_staff = new tbl_others_staff();
			tbl_staff.setId_nv(rs.getInt("id_nv"));
			tbl_staff.setName_nv(rs.getString("name_nv"));
			tbl_staff.setPhoto_nv(rs.getString("photo_nv"));
			tbl_staff.setJob_nv(rs.getString("job_nv"));
			list.add(tbl_staff);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	//delete a staff
	public void Staff_deleteStaff(String id_nv) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_others_staff where id_nv "
					+ " in ( select distinct(id_nv) from tbl_others_staff where id_nv = ? order by id_nv limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_nv));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
	
	/*Edit staff according to the code */
	public tbl_others_staff Staff_SuaStaff_TimKiemTheoMa(int id_nv) {
		tbl_others_staff tbl_wp = new tbl_others_staff();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_nv, name_nv, photo_nv, job_nv, task_nv, email_nv, id_pro_nv from tbl_others_staff where id_nv = ? limit 1");
			pstmt.setInt(1, id_nv); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_wp.setId_nv(rs.getInt("id_nv"));
				tbl_wp.setName_nv(rs.getString("name_nv"));
				tbl_wp.setPhoto_nv(rs.getString("photo_nv"));
				tbl_wp.setJob_nv(rs.getString("job_nv"));
				tbl_wp.setTask_nv(rs.getString("task_nv"));
				tbl_wp.setEmail_nv(rs.getString("email_nv"));
				tbl_wp.setId_pro_nv(rs.getInt("id_pro_nv"));
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
		return tbl_wp;
	}
	
	
	/*sua tin cap nhat tin theo ma */
	public void Staff_SuaStaff() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_others_staff set name_nv=? ,photo_nv = ?, job_nv = ?, task_nv=?, email_nv=?, id_pro_nv=? where id_nv = ? ");
			pstmt.setString(1, tbl_staff.getName_nv());
			pstmt.setString(2, tbl_staff.getPhoto_nv());
			pstmt.setString(3, tbl_staff.getJob_nv());
			pstmt.setString(4, tbl_staff.getTask_nv());
			pstmt.setString(5, tbl_staff.getEmail_nv());
			pstmt.setInt(6, tbl_staff.getId_pro_nv());
			pstmt.setInt(7, tbl_staff.getId_nv());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
}
