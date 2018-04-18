package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_events;

public class tbl_eventsBean {
	private DataSource ds;
	private tbl_events tbl_events;
	
	public tbl_eventsBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_events getTbl_events() {
		return tbl_events;
	}

	public void setTbl_events(tbl_events tbl_events) {
		this.tbl_events = tbl_events;
	}

	/*them new*/
	public void Events_them() throws SQLException  {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("insert into tbl_tt_events(name_events,summary,contents,post_start_date,"
							+ "month,year,location,start_time,end_time,start_date_events,end_date_events,photo) "
							+ "values (?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, tbl_events.getName_events());
			pstmt.setString(2, tbl_events.getSummary());
			pstmt.setString(3, tbl_events.getContents());
			pstmt.setInt(4, tbl_events.getPost_start_date());
			pstmt.setInt(5, tbl_events.getMonth());
			pstmt.setInt(6, tbl_events.getYear());
			pstmt.setString(7, tbl_events.getLocation());
			pstmt.setTime(8, tbl_events.getStart_time());
			pstmt.setTime(9, tbl_events.getEnd_time());
			pstmt.setInt(10, tbl_events.getStart_date_events());
			pstmt.setInt(11, tbl_events.getEnd_date_events());
			pstmt.setString(12, tbl_events.getPhoto());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*search event (count) for admin*/
	public int layTongSoDongEvents_coDieuKien( String ten_bai,
			String tu_ngay, String den_ngay) {
		Statement stmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		
		try {
		String sql = "SELECT count(id_events) FROM tbl_tt_events where 1=1 ";
	
		if(ten_bai != null){  //so sanh voi doi tuong
			sql += " and name_events like '%" + ten_bai.replace("'", " \\' ") + "%'";
		}
		
		//do gia tri ban dau la null, nen String.valueOf(tu_ngay) in ra là "null"
		//String ten_bai = null;
		//Integer tu_ngay = null ;
		//Integer den_ngay = null;
		
		if(!tu_ngay.equals("null")){
			sql += " and post_start_date  >= '" + tu_ngay + "'";
		}
		if(!den_ngay.equals("null")){
			sql += " and post_start_date <= '" + den_ngay + "'";
		}
		
	//		System.out.println("Cau lenh: " + sql);
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
	
	/*search event for admin*/
	public ArrayList<tbl_events> timkiem(String ten_bai,
			String tu_ngay, String den_ngay, String lmt, String off){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		tbl_events tbl_events = null;
		ArrayList<tbl_events> list = new ArrayList<tbl_events>();
		try {
		String sql =  "select id_events, name_events, summary, post_start_date from tbl_tt_events where 1=1 ";
		//initcap : viet hoa chu cai
		
		if(ten_bai != null){ 
			sql += " and lower(name_events) like '%"+ ten_bai.toLowerCase().replace("'", " \\' ") +"%'";
		}
		if (!tu_ngay.equals("null")){
			sql += " and post_start_date >= " + tu_ngay + "" ;
		}
		if (!den_ngay.equals("null")){ 
			sql += " and post_start_date <= " + den_ngay + "" ;
		}
		sql += " order by id_events desc limit " + lmt + " offset " + off + "";

	//	System.out.println(sql);
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tbl_events = new tbl_events();
			tbl_events.setId_events(rs.getInt("id_events"));
			tbl_events.setName_events(rs.getString("name_events"));
			tbl_events.setPost_start_date(rs.getInt("post_start_date"));
			tbl_events.setSummary(rs.getString("summary"));
			list.add(tbl_events);
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
	
	
	/* trang Home cua Events lay 10 tin moi nhat */
	public ArrayList<tbl_events> Events_HientrangHome10() {
		ArrayList<tbl_events> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_events, name_events, summary, location, start_time, end_time, start_date_events, end_date_events, post_start_date, photo from tbl_tt_events order by id_events desc limit 10");
			while (rs.next()) {
				tbl_events tbl_event = new tbl_events();
				tbl_event.setId_events(rs.getInt("id_events"));
				tbl_event.setName_events(rs.getString("name_events"));
				tbl_event.setSummary(rs.getString("summary"));
				tbl_event.setLocation(rs.getString("location"));
				tbl_event.setStart_time(rs.getTime("start_time"));
				tbl_event.setEnd_time(rs.getTime("end_time"));
				tbl_event.setStart_date_events(rs.getInt("start_date_events"));
				tbl_event.setEnd_date_events(rs.getInt("end_date_events"));
				tbl_event.setPost_start_date(rs.getInt("post_start_date"));
				tbl_event.setPhoto(rs.getString("photo"));
				list.add(tbl_event);
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
	
	
	/*search event for guest*/
	public int Events_Laytongsotin(int month, int year) {
		Connection conn = null;
		PreparedStatement pstmt;
		int count = 0;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select count(id_events) from tbl_tt_events where month=? and year=?");
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	/* chon theo thang nam hien ra tin */
	public ArrayList<tbl_events> Events_Hientin(int month, int year,int limit, int offset) {
		ArrayList<tbl_events> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_events, name_events, summary, location, start_time, end_time, start_date_events, end_date_events, post_start_date, photo from tbl_tt_events where month=? and year=? "
							+ "order by id_events desc limit ? offset ?");
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			pstmt.setInt(3, limit);
			pstmt.setInt(4, offset);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_events tbl_event = new tbl_events();
				tbl_event.setId_events(rs.getInt("id_events"));
				tbl_event.setName_events(rs.getString("name_events"));
				tbl_event.setSummary(rs.getString("summary"));
				tbl_event.setLocation(rs.getString("location"));
				tbl_event.setStart_time(rs.getTime("start_time"));
				tbl_event.setEnd_time(rs.getTime("end_time"));
				tbl_event.setStart_date_events(rs.getInt("start_date_events"));
				tbl_event.setEnd_date_events(rs.getInt("end_date_events"));
				tbl_event.setPost_start_date(rs.getInt("post_start_date"));
				tbl_event.setPhoto(rs.getString("photo"));
				list.add(tbl_event);
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
	
	/*hien thi noi dung trang tin va cac tin phu*/
	public ArrayList<tbl_events> Events_XemNoiDungTinTheoMa(String id_tintuc) {
		ArrayList<tbl_events> list = new ArrayList<tbl_events>();
		tbl_events tbl_event = new tbl_events();
		Connection conn = null;
		ResultSet rs_tinchinh = null;
		ResultSet rs_10tinphu = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_events, name_events, summary, contents, post_start_date, location, start_time, end_time, start_date_events, end_date_events from tbl_tt_events where id_events = ? limit 1");
			pstmt.setInt(1, Integer.parseInt(id_tintuc)); 
			rs_tinchinh = pstmt.executeQuery();
			while (rs_tinchinh.next()) {
				tbl_event.setId_events(rs_tinchinh.getInt("id_events"));
				tbl_event.setName_events(rs_tinchinh.getString("name_events"));
				tbl_event.setSummary(rs_tinchinh.getString("summary"));
				tbl_event.setContents(rs_tinchinh.getString("contents"));
				tbl_event.setPost_start_date(rs_tinchinh.getInt("post_start_date"));
				tbl_event.setLocation(rs_tinchinh.getString("location"));
				tbl_event.setStart_time(rs_tinchinh.getTime("start_time"));
				tbl_event.setEnd_time(rs_tinchinh.getTime("end_time"));
				tbl_event.setStart_date_events(rs_tinchinh.getInt("start_date_events"));
				tbl_event.setEnd_date_events(rs_tinchinh.getInt("end_date_events"));
				list.add(tbl_event); //cho vao vi tri 0 
				
				pstmt = conn
						.prepareStatement("select id_events, name_events, summary, post_start_date from tbl_tt_events where id_events NOT IN("+ tbl_event.getId_events() +") order by id_events desc limit 10 ");
				//bỏ record đầu tiên
				//System.out.println(pstmt.toString());
				rs_10tinphu = pstmt.executeQuery();
				while (rs_10tinphu.next()) {
					tbl_events tbl_event_phu = new tbl_events();
					tbl_event_phu.setId_events(rs_10tinphu.getInt("id_events"));
					tbl_event_phu.setName_events(rs_10tinphu.getString("name_events"));
					tbl_event_phu.setSummary(rs_10tinphu.getString("summary"));
					tbl_event_phu.setPost_start_date(rs_10tinphu.getInt("post_start_date"));
					list.add(tbl_event_phu); //sau vi tri 0
				}
				rs_10tinphu.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs_tinchinh.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/*sua tin tim theo ma */
	public tbl_events Events_SuaTin_TimKimTheoMa(int id_tintuc) {
		tbl_events tbl_event = new tbl_events();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_events, name_events, summary, contents, location, start_time, end_time, start_date_events, end_date_events, photo from tbl_tt_events where id_events = ? limit 1");
			pstmt.setInt(1, id_tintuc); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_event.setId_events(rs.getInt("id_events"));
				tbl_event.setName_events(rs.getString("name_events"));
				tbl_event.setSummary(rs.getString("summary"));
				tbl_event.setContents(rs.getString("contents"));
				tbl_event.setLocation(rs.getString("location"));
				tbl_event.setStart_time(rs.getTime("start_time"));
				tbl_event.setEnd_time(rs.getTime("end_time"));
				tbl_event.setStart_date_events(rs.getInt("start_date_events"));
				tbl_event.setEnd_date_events(rs.getInt("end_date_events"));
				tbl_event.setPhoto(rs.getString("photo"));
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
		return tbl_event;
	}
	
	/*sua tin cap nhat tin theo ma */
	public void Events_SuaTin_CapNhatTheoMa() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_tt_events set name_events=? ,summary = ?, contents = ?, location=?, start_time=?, end_time=?, start_date_events =?, end_date_events=?, photo = ? where id_events = ? ");
			pstmt.setString(1, tbl_events.getName_events());
			pstmt.setString(2, tbl_events.getSummary());
			pstmt.setString(3, tbl_events.getContents());
			pstmt.setString(4, tbl_events.getLocation());
			pstmt.setTime(5, tbl_events.getStart_time());
			pstmt.setTime(6, tbl_events.getEnd_time());
			pstmt.setInt(7, tbl_events.getStart_date_events());
			pstmt.setInt(8, tbl_events.getEnd_date_events());
			pstmt.setString(9, tbl_events.getPhoto());
			pstmt.setInt(10, tbl_events.getId_events());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
	
	/*Xoa tin theo ma*/
	public void Events_XoaTheoMa(String id_news) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_tt_events "
					+ "where id_events in ( select id_events from tbl_tt_events where id_events = ? order by id_events limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_news));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
}
