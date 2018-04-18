package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_others_project_staff;
import Entity.tbl_others_staff;

public class tbl_others_project_staffBean {
	private DataSource ds;
	private  tbl_others_project_staff tbl_pro_staff;
	
	public tbl_others_project_staffBean(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	public tbl_others_project_staff getTbl_pro_staff() {
		return tbl_pro_staff;
	}
	
	public void setTbl_pro_staff(tbl_others_project_staff tbl_pro_staff) {
		this.tbl_pro_staff = tbl_pro_staff;
	}
	
	//insert data project staff into Database
	public int Staff_taoProject() throws SQLException {
		//kiem tra ten project co ton tai khong neu co thi temp = 1 , con khong thi temp = 0
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int temp = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_pro_nv from tbl_others_project_staff where name_pro = ? limit 1");
			pstmt.setString(1, tbl_pro_staff.getName_pro());
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp = rs.getInt(1);
			}
			
			if(temp == 0){
				pstmt = conn
						.prepareStatement("insert into tbl_others_project_staff(name_pro) "
							+ "values (?)");
			pstmt.setString(1, tbl_pro_staff.getName_pro());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			}
			
		} finally {
			conn.close();
		    rs.close();
		}
		return temp;
	}
	
	//get all project
	public ArrayList<tbl_others_project_staff> Staff_GetAllNameProjcet(){
		ArrayList<tbl_others_project_staff> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_pro_nv, name_pro from tbl_others_project_staff order by name_pro ");
			while (rs.next()) {
				tbl_others_project_staff tbl_project = new tbl_others_project_staff();
				tbl_project.setId_pro_nv(rs.getInt("id_pro_nv"));
				tbl_project.setName_pro(rs.getString("name_pro"));
				list.add(tbl_project);
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
		return list;
	}
	
	/*Delete project according to the code*/
	public void Staff_XoaTheoMaProject(String id_project) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_others_staff where id_pro_nv "
					+ " in ( select distinct(id_pro_nv) from tbl_others_staff where id_pro_nv = ? order by id_pro_nv)");
			pstmt.setInt(1, Integer.parseInt(id_project));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("delete from tbl_others_project_staff where id_pro_nv "
					+ " in ( select id_pro_nv from tbl_others_project_staff where id_pro_nv = ? order by id_pro_nv limit 1) ");
			pstmt.setInt(1, Integer.parseInt(id_project));
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
	
	//reanme work plan
	public int Staff_SuaTenProject() throws SQLException {
		//kiem tra ten Project co ton tai khong neu co thi temp = 1 , con khong thi temp = 0
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int temp = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_pro_nv from tbl_others_project_staff where name_pro = ? limit 1");
			pstmt.setString(1, tbl_pro_staff.getName_pro());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp = rs.getInt(1);
			}
			
			if(temp == 0){
				pstmt = conn
						.prepareStatement("update tbl_others_project_staff set name_pro =? where id_pro_nv =? ");
			pstmt.setString(1, tbl_pro_staff.getName_pro());
			pstmt.setInt(2, tbl_pro_staff.getId_pro_nv()); 
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			}
			
		} finally {
			conn.close();
		    rs.close();
		}
		return temp;
	}
	
	//Get staff according to project
	public ArrayList<tbl_others_staff> Staff_GetStaffaccordingProject(int id_pro_nv) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		ArrayList<tbl_others_staff> list = new ArrayList<>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(" select name_nv, photo_nv, job_nv, task_nv, email_nv from tbl_others_staff where id_pro_nv = ? ");
			pstmt.setInt(1, id_pro_nv);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_others_staff tbl_staff = new tbl_others_staff();
				tbl_staff.setName_nv(rs.getString("name_nv"));
				tbl_staff.setPhoto_nv(rs.getString("photo_nv"));
				tbl_staff.setJob_nv(rs.getString("job_nv"));
				tbl_staff.setEmail_nv(rs.getString("email_nv"));
				tbl_staff.setTask_nv(rs.getString("task_nv"));
				list.add(tbl_staff);
			}
		}  finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
