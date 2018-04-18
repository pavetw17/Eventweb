package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_tt_project;

public class tbl_tt_projectBean {
	private DataSource ds;
	private tbl_tt_project tbl_project;

	public tbl_tt_projectBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_tt_project getTbl_tt_project() {
		return tbl_project;
	}

	public void setTbl_tt_project(tbl_tt_project tbl_tt_project) {
		this.tbl_project = tbl_tt_project;
	}

	/*add news project */
	public void Project_AddNews() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("insert into tbl_tt_projects(name_project,summary,contents,start_date,photo,month,year) "
							+ "values (?,?,?,?,?,?,?)");
			pstmt.setString(1, tbl_project.getName_project());
			pstmt.setString(2, tbl_project.getSummary());
			pstmt.setString(3, tbl_project.getContents());
			pstmt.setInt(4, tbl_project.getPost_start_date());
			pstmt.setString(5, tbl_project.getPhoto());
			pstmt.setInt(6, tbl_project.getMonth());
			pstmt.setInt(7, tbl_project.getYear());
			System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			conn.close();
		}
	}

	/*search new project for guest*/
	public int Project_layTongSoTin(int month, int year) {
		Connection conn = null;
		PreparedStatement pstmt;
		int count = 0;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select count(id_project) from tbl_tt_projects where month=? and year=?");
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
	public ArrayList<tbl_tt_project> Project_HienTin(int month, int year,int limit, int offset) {
		ArrayList<tbl_tt_project> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_project, name_project, summary, start_date, photo from tbl_tt_projects where month=? and year=? "
							+ "order by id_project desc limit ? offset ?");
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			pstmt.setInt(3, limit);
			pstmt.setInt(4, offset);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_tt_project tbl_project = new tbl_tt_project();
				tbl_project.setId_project(rs.getInt("id_project"));
				tbl_project.setName_project(rs.getString("name_project"));
				tbl_project.setSummary(rs.getString("summary"));
				/* tbl_news.setContents(rs.getString("contents")); */
				tbl_project.setPost_start_date(rs.getInt("start_date"));
				tbl_project.setPhoto(rs.getString("photo"));
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

	/* trang Home cua Project lay 10 tin moi nhat */
	public ArrayList<tbl_tt_project> Project_HientrangHome10() {
		ArrayList<tbl_tt_project> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_project, name_project, summary, start_date, photo from tbl_tt_projects order by id_project desc limit 7");
			while (rs.next()) {
				tbl_tt_project tbl_project = new tbl_tt_project();
				tbl_project.setId_project(rs.getInt("id_project"));
				tbl_project.setName_project(rs.getString("name_project"));
				tbl_project.setSummary(rs.getString("summary"));
				/* tbl_news.setContents(rs.getString("contents")); */
				tbl_project.setPost_start_date(rs.getInt("start_date"));
				tbl_project.setPhoto(rs.getString("photo"));
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

	/*hien thi noi dung trang tin va cac tin phu*/
	public ArrayList<tbl_tt_project> Project_XemNoiDungTinTheoMa(String id_tintuc) {
		ArrayList<tbl_tt_project> list = new ArrayList<tbl_tt_project>();
		tbl_tt_project tbl_project = new tbl_tt_project();
		Connection conn = null;
		ResultSet rs_tinchinh = null;
		ResultSet rs_10tinphu = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_project, name_project, summary, contents, start_date from tbl_tt_projects where id_project = ? limit 1");
			pstmt.setInt(1, Integer.parseInt(id_tintuc)); 
			rs_tinchinh = pstmt.executeQuery();
			while (rs_tinchinh.next()) {
				tbl_project.setId_project(rs_tinchinh.getInt("id_project"));
				tbl_project.setName_project(rs_tinchinh.getString("name_project"));
				tbl_project.setSummary(rs_tinchinh.getString("summary"));
				tbl_project.setContents(rs_tinchinh.getString("contents"));
				tbl_project.setPost_start_date(rs_tinchinh.getInt("start_date"));
				list.add(tbl_project); //cho vao vi tri 0 
				
				pstmt = conn
						.prepareStatement("select id_project, name_project, summary, start_date from tbl_tt_projects where id_project NOT IN("+ tbl_project.getId_project() +") order by id_project desc limit 10 ");
				//bỏ record đầu tiên
				//System.out.println(pstmt.toString());
				rs_10tinphu = pstmt.executeQuery();
				while (rs_10tinphu.next()) {
					tbl_tt_project tbl_pro = new tbl_tt_project();
					tbl_pro.setId_project(rs_10tinphu.getInt("id_project"));
					tbl_pro.setName_project(rs_10tinphu.getString("name_project"));
					tbl_pro.setSummary(rs_10tinphu.getString("summary"));
					tbl_pro.setPost_start_date(rs_10tinphu.getInt("start_date"));
					list.add(tbl_pro); //sau vi tri 0
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
	
	/*search project (count) for admin*/
	public int Project_layTongSoDong_coDieuKien( String ten_bai,
			String tu_ngay, String den_ngay) {
		Statement stmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
		String sql = "SELECT count(id_project) FROM tbl_tt_projects where 1=1 ";
	
		if(ten_bai != null){  //so sanh voi doi tuong
			sql += " and name_project like '%" + ten_bai.replace("'", " \\' ") + "%'";
		}
		
		if(!tu_ngay.equals("null")){
			sql += " and start_date >= '" + tu_ngay + "'";
		}
		if(!den_ngay.equals("null")){
			sql += " and start_date <= '" + den_ngay + "'";
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
	
	/*search project for admin*/
	public ArrayList<tbl_tt_project> timkiem(String ten_bai,
			String tu_ngay, String den_ngay, String lmt, String off){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		ArrayList<tbl_tt_project> list = new ArrayList<tbl_tt_project>();
		try {
		String sql =  "select id_project, name_project, summary, start_date from tbl_tt_projects where 1=1 ";
		//initcap : viet hoa chu cai
		
		if(ten_bai != null){ 
			sql += " and lower(name_project) like '%"+ ten_bai.toLowerCase().replace("'", " \\' ") +"%'";
		}
		if (!tu_ngay.equals("null")){
			sql += " and start_date >= " + tu_ngay + "" ;
		}
		if (!den_ngay.equals("null")){ 
			sql += " and start_date <= " + den_ngay + "" ;
		}
		sql += " order by id_project desc limit " + lmt + " offset " + off + "";

	//	System.out.println(sql);
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tbl_tt_project tbl_project = new tbl_tt_project();
			tbl_project.setId_project(rs.getInt("id_project"));
			tbl_project.setName_project(rs.getString("name_project"));
			tbl_project.setPost_start_date(rs.getInt("start_date"));
			tbl_project.setSummary(rs.getString("summary"));
			list.add(tbl_project);
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
	
	
	/*sua tin tim theo ma */
	public tbl_tt_project Project_SuaTin_TimKimTheoMa(int id_tintuc) {
		tbl_tt_project tbl_project = new tbl_tt_project();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_project, name_project, summary, contents, photo from tbl_tt_projects where id_project = ? limit 1");
			pstmt.setInt(1, id_tintuc); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_project.setId_project(rs.getInt("id_project"));
				tbl_project.setName_project(rs.getString("name_project"));
				tbl_project.setSummary(rs.getString("summary"));
				tbl_project.setContents(rs.getString("contents"));
				tbl_project.setPhoto(rs.getString("photo"));
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
		return tbl_project;
	}
	
	/*sua tin cap nhat tin theo ma */
	public void Project_SuaTin_CapNhatTheoMa() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_tt_projects set name_project=? , summary = ?, contents = ?, photo = ? where id_project = ? ");
			pstmt.setString(1, tbl_project.getName_project());
			pstmt.setString(2, tbl_project.getSummary());
			pstmt.setString(3, tbl_project.getContents());
			pstmt.setString(4, tbl_project.getPhoto());
			pstmt.setInt(5, tbl_project.getId_project());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
	
	/*Xoa tin theo ma*/
	public void Project_XoaTheoMa(String id_project) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_tt_projects "
					+ "where id_project in ( select id_project from tbl_tt_projects where id_project = ? order by id_project limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_project));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
}
