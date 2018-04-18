package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Entity.tbl_workplan;

public class tbl_workplanBean {
	private DataSource ds;
	private  tbl_workplan tbl_workplan;
	
	public tbl_workplanBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_workplan getTbl_workplan() {
		return tbl_workplan;
	}

	public void setTbl_workplan(tbl_workplan tbl_workplan) {
		this.tbl_workplan = tbl_workplan;
	}
	
	//Get year in database
	public ArrayList<Integer> Workplan_GetDistinctYear(){
		ArrayList<Integer> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select distinct year from tbl_tt_workplans where id_parent is null order by year desc limit 10");
			while (rs.next()) {
				list.add(rs.getInt(1));
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
	
	
	//Get work plan according to year
	public ArrayList<tbl_workplan> Workplan_GetNameParent(int year){
		ArrayList<tbl_workplan> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select id_workplan, name_parent from tbl_tt_workplans where year=? and id_parent is null order by name_parent");
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			while(rs.next()){
				tbl_workplan tbl_wp = new tbl_workplan();
				tbl_wp.setId_workplan(rs.getInt("id_workplan"));
				tbl_wp.setName_parent(rs.getString("name_parent"));
				list.add(tbl_wp);
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
	
	//Get data parent in database and add data to dropdownlist
	public ArrayList<tbl_workplan> LayTatCaWorkplans_TheoNam_QuanTri(int year){
		ArrayList<tbl_workplan> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_workplan, name_parent from tbl_tt_workplans where year = ? and id_parent is null order by name_parent ");
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_workplan tbl_wp = new tbl_workplan();
				tbl_wp.setId_workplan(rs.getInt("id_workplan"));
				tbl_wp.setName_parent(rs.getString("name_parent"));
				list.add(tbl_wp);
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
	
	
	//insert data work plan into Database
	public int taoWorkplan() throws SQLException {
		//kiem tra ten workplan co ton tai khong neu co thi temp = 1 , con khong thi temp = 0
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int temp = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_workplan from tbl_tt_workplans where name_parent = ? limit 1");
			pstmt.setString(1, tbl_workplan.getName_parent());
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp = rs.getInt(1);
			}
			
			if(temp == 0){
				pstmt = conn
						.prepareStatement("insert into tbl_tt_workplans(name_parent,year) "
							+ "values (?,?)");
			pstmt.setString(1, tbl_workplan.getName_parent());
			pstmt.setInt(2, tbl_workplan.getYear()); 
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			}
			
		} finally {
			conn.close();
		    rs.close();
		}
		return temp;
	}
	
	//reanme work plan
	public int SuaTenWorkplan() throws SQLException {
		//kiem tra ten Workplan co ton tai khong neu co thi temp = 1 , con khong thi temp = 0
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int temp = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_workplan from tbl_tt_workplans where name_parent = ? limit 1");
			pstmt.setString(1, tbl_workplan.getName_parent());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp = rs.getInt(1);
			}
			
			if(temp == 0){
				pstmt = conn
						.prepareStatement("update tbl_tt_workplans set name_parent =? where id_workplan =? ");
			pstmt.setString(1, tbl_workplan.getName_parent());
			pstmt.setInt(2, tbl_workplan.getId_workplan()); 
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			}
			
		} finally {
			conn.close();
		    rs.close();
		}
		return temp;
	}
	
	//delete work plan
	public void XoaWorkplan(String id_workplan) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_tt_workplans where id_workplan "
					+ " in ( select distinct(id_workplan) from tbl_tt_workplans where id_parent = ? order by id_workplan)");
			pstmt.setInt(1, Integer.parseInt(id_workplan));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("delete from tbl_tt_workplans where id_workplan "
					+ " in ( select id_workplan from tbl_tt_workplans where id_workplan = ? order by id_workplan limit 1) ");
			pstmt.setInt(1, Integer.parseInt(id_workplan));
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
	
	/*Add new for work plan*/
	public void Workplan_themtin() throws SQLException  {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement(" insert into tbl_tt_workplans(id_parent, content, objectives, status, year, post_workplan_date, "
							+ " time_begin, time_end) values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, tbl_workplan.getId_parent());
			pstmt.setString(2, tbl_workplan.getContent());
			pstmt.setString(3, tbl_workplan.getObjectives());
			pstmt.setString(4, tbl_workplan.getStatus());
			pstmt.setInt(5, tbl_workplan.getYear());
			pstmt.setInt(6, tbl_workplan.getPost_workplan_date());
			pstmt.setInt(7, tbl_workplan.getTime_begin());
			pstmt.setInt(8, tbl_workplan.getTime_end());
			pstmt.executeUpdate();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*search work plan (count) for admin*/
	public int Workplan_layTongSoDong_coDieuKien(String id_parent, String ten_bai,
			String tu_ngay, String den_ngay) {
		Statement stmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
		String sql = "SELECT count(id_workplan) FROM tbl_tt_workplans where 1=1 ";
	
		if(ten_bai != null){  //so sanh voi doi tuong
			sql += " and content like '%" + ten_bai.replace("'", " \\' ") + "%'";
		}
		
		//do gia tri ban dau la null, nen String.valueOf(tu_ngay) in ra là "null"
		//String ten_bai = null;
		//Integer tu_ngay = null ;
		//Integer den_ngay = null;
		
		if(!tu_ngay.equals("null")){
			sql += " and post_workplan_date  >= '" + tu_ngay + "'";
		}
		if(!den_ngay.equals("null")){
			sql += " and post_workplan_date <= '" + den_ngay + "'";
		}
		sql += " and id_parent = '" + id_parent.replace("'", " \\' ") + "'";
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
	
	
	/*search work plan for admin*/
	public ArrayList<tbl_workplan> Workplan_timkiem(String id_parent, String ten_bai,
			String tu_ngay, String den_ngay, String lmt, String off){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		 
		ArrayList<tbl_workplan> list = new ArrayList<tbl_workplan>();
		try {
		String sql =  "select id_workplan, content, objectives, post_workplan_date from tbl_tt_workplans where 1=1 ";
		//initcap : viet hoa chu cai
		
		if(ten_bai != null){ 
			sql += " and lower(content) like '%"+ ten_bai.toLowerCase().replace("'", " \\' ") +"%'";
		}
		if (!tu_ngay.equals("null")){
			sql += " and post_workplan_date >= " + tu_ngay + "" ;
		}
		if (!den_ngay.equals("null")){ 
			sql += " and post_workplan_date <= " + den_ngay + "" ;
		}
		sql += " and id_parent = '" + id_parent + "' order by id_workplan desc limit " + lmt + " offset " + off + "";

		//System.out.println(sql);
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tbl_workplan tbl_wp = new tbl_workplan();
			tbl_wp.setId_workplan(rs.getInt("id_workplan"));
			tbl_wp.setContent(rs.getString("content"));
			tbl_wp.setObjectives(rs.getString("objectives"));
			tbl_wp.setPost_workplan_date(rs.getInt("post_workplan_date"));
			list.add(tbl_wp);
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
	
	
	/*Delete news according to the code*/
	public void Workplan_XoaTheoMa(String id_news) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_tt_workplans "
					+ "where id_workplan in ( select id_workplan from tbl_tt_workplans where id_workplan = ? order by id_workplan limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_news));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
	
	/*Edit news (show content) according to the code */
	public tbl_workplan Workplan_SuaTin_TimKiemTheoMa(int id_tintuc) {
		tbl_workplan tbl_wp = new tbl_workplan();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_parent, id_workplan, content, objectives, status, time_begin, time_end, year from tbl_tt_workplans where id_workplan = ? limit 1");
			pstmt.setInt(1, id_tintuc); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_wp.setId_workplan(rs.getInt("id_workplan"));
				tbl_wp.setId_parent(rs.getInt("id_parent"));
				tbl_wp.setContent(rs.getString("content"));
				tbl_wp.setObjectives(rs.getString("objectives"));
				tbl_wp.setStatus(rs.getString("status"));
				tbl_wp.setTime_begin(rs.getInt("time_begin"));
				tbl_wp.setTime_end(rs.getInt("time_end"));
				tbl_wp.setYear(rs.getInt("year"));
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
	
	/*Edit news (update database) according to the id_workplan */
	public void Workplan_SuaTin_CapNhatTheoMa() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_tt_workplans set id_parent=? ,content = ?, objectives = ?, status=?, time_begin=?, time_end=? where id_workplan = ? ");
			pstmt.setInt(1, tbl_workplan.getId_parent());
			pstmt.setString(2, tbl_workplan.getContent());
			pstmt.setString(3, tbl_workplan.getObjectives());
			pstmt.setString(4, tbl_workplan.getStatus());
			pstmt.setInt(5, tbl_workplan.getTime_begin());
			pstmt.setInt(6, tbl_workplan.getTime_end());
			pstmt.setInt(7, tbl_workplan.getId_workplan());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
	
	public String Workplan_showWorkingSchedule(int year) throws SQLException {
		String data = "";	
    	Connection conn = null;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	FunctionAll func = new FunctionAll();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select id_workplan, name_parent from tbl_tt_workplans where year=? and id_parent is null");
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			List<tbl_workplan> list = new ArrayList<tbl_workplan>();
			while(rs.next()){
				tbl_workplan tbl_wp = new tbl_workplan();
				tbl_wp.setId_workplan(rs.getInt("id_workplan"));
				tbl_wp.setName_parent(rs.getString("name_parent"));
				list.add(tbl_wp);
			}
			
			data += "<script>";
			data += " $(document).ready(function() {" ;
			data += " $('div.menu_head').click( ";
			data += "function() { ";
			data += "	$(this).next(\"div.menu_body\").slideToggle(300).siblings(\"div.menu_body\").slideUp(\"slow\"); ";
			data += "});";
			data += "});";
			data += "</script>";
			
			for(int i=0;i<list.size();i++){
				data += "<div class=\"menu_head\">";
				data += "<div id=\"divcthd\">";
				data += "<table class=\"table_results_content\">";
				data += "<tr class=\"trtitile\">";
				data +=	"<td colspan=\"3\" class=\"td6\"> <div class=\"divtitle\">";
				data += "<span id=\"id0\" class=\"h2title_results\">" + list.get(i).getName_parent() + "</span> </div></td>";
				data += "<td class=\"td7\"><span class=\"date1\"></span> <span class=\"date2\"></span></td>";
				data += "</tr></table></div>"; //end divcthd
				data += "</div>";	//end div.menu_head

				pstmt = conn.prepareStatement("select id_workplan, content, objectives, status, time_begin, time_end from tbl_tt_workplans where id_parent=?");
				pstmt.setInt(1, list.get(i).getId_workplan());
				rs = pstmt.executeQuery();
				data += "<div class=\"menu_body\">";
				data += "<table class=\"table_results\">";
				while(rs.next()){
					data += "<tr>";
					data += "<td class=\"td2\"><span>" + rs.getString("content") + "</span></td>";
					data += "<td class=\"td3\"><span>" + rs.getString("objectives") + "</span></td>";
					data += "<td class=\"td4\"><span>" + rs.getString("status") + "</span></td>";
					data += "<td class=\"td5\"><span class=\"date1\"> From: " + func.DoiSoNguyenRaNgayThang(rs.getInt("time_begin")) + "</span> <br> <span class=\"date2\"> To: " + func.DoiSoNguyenRaNgayThang(rs.getInt("time_end")) + "</span></td>";
					data += "</tr>";
				}
				data += "</table></div>"; //end table_results
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
			rs.close();
		}
    	return data;
	}
	
	public String Workplan_showHome() throws SQLException {
		String data = "";	
    	Connection conn = null;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	FunctionAll func = new FunctionAll();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select id_workplan, name_parent from tbl_tt_workplans where id_parent is null order by year desc limit 1");
			rs = pstmt.executeQuery();
			List<tbl_workplan> list = new ArrayList<tbl_workplan>();
			while(rs.next()){
				tbl_workplan tbl_wp = new tbl_workplan();
				tbl_wp.setId_workplan(rs.getInt("id_workplan"));
				tbl_wp.setName_parent(rs.getString("name_parent"));
				list.add(tbl_wp);
			}
			
			for(int i=0;i<list.size();i++){
				data += "<div class=\"menu_head\">";
				data += "<div id=\"divcthd\">";
				data += "<table class=\"table_results_content\">";
				data += "<tr class=\"trtitile\">";
				data +=	"<td colspan=\"3\" class=\"td6\"> <div class=\"divtitle\">";
				data += "<span id=\"id0\" class=\"h2title_results\">" + list.get(i).getName_parent() + "</span> </div></td>";
				data += "<td class=\"td7\"><span class=\"date1\"></span> <span class=\"date2\"></span></td>";
				data += "</tr></table></div>"; //end divcthd
				data += "</div>";	//end div.menu_head

				pstmt = conn.prepareStatement("select id_workplan, content, objectives, status, time_begin, time_end from tbl_tt_workplans where id_parent=?");
				pstmt.setInt(1, list.get(i).getId_workplan());
				rs = pstmt.executeQuery();
				data += "<div class=\"menu_body\">";
				data += "<table class=\"table_results\">";
				while(rs.next()){
					data += "<tr>";
					data += "<td class=\"td2\"><span>" + rs.getString("content") + "</span></td>";
					data += "<td class=\"td3\"><span>" + rs.getString("objectives") + "</span></td>";
					data += "<td class=\"td4\"><span>" + rs.getString("status") + "</span></td>";
					data += "<td class=\"td5\"><span class=\"date1\"> From: " + func.DoiSoNguyenRaNgayThang(rs.getInt("time_begin")) + "</span> <br> <span class=\"date2\"> To: " + func.DoiSoNguyenRaNgayThang(rs.getInt("time_end")) + "</span></td>";
					data += "</tr>";
				}
				data += "</table></div>"; //end table_results
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
			rs.close();
		}
    	
    	return data;
	}
	
}
