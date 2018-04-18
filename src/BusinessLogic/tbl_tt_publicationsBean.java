package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_tt_publications;

public class tbl_tt_publicationsBean {
	private DataSource ds;
	private tbl_tt_publications tbl_tt_publication;

	public tbl_tt_publicationsBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_tt_publications getTbl_tt_publications() {
		return tbl_tt_publication;
	}

	public void setTbl_tt_publications(tbl_tt_publications tbl_tt_publication) {
		this.tbl_tt_publication = tbl_tt_publication;
	}

	/*add news publications */
	public void Publications_AddNews() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("insert into tbl_tt_publications (name_publication,summary,contents,start_date,month,year) "
							+ "values (?,?,?,?,?,?)");
			pstmt.setString(1, tbl_tt_publication.getName_publication());
			pstmt.setString(2, tbl_tt_publication.getSummary());
			pstmt.setString(3, tbl_tt_publication.getContents());
			pstmt.setInt(4, tbl_tt_publication.getPost_start_date());
			pstmt.setInt(5, tbl_tt_publication.getMonth());
			pstmt.setInt(6, tbl_tt_publication.getYear());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			conn.close();
		}
	}

	/*search new publications for guest*/
	public int Publications_layTongSoTin(int month, int year) {
		Connection conn = null;
		PreparedStatement pstmt;
		int count = 0;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select count(id_publication) from tbl_tt_publications where month=? and year=?");
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
	public ArrayList<tbl_tt_publications> Publications_HienTin(int month, int year,int limit, int offset) {
		ArrayList<tbl_tt_publications> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_publication, name_publication, summary, start_date from tbl_tt_publications where month=? and year=? "
							+ "order by id_publication desc limit ? offset ?");
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			pstmt.setInt(3, limit);
			pstmt.setInt(4, offset);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_tt_publications tbl_publications = new tbl_tt_publications();
				tbl_publications.setId_publication(rs.getInt("id_publication"));
				tbl_publications.setName_publication(rs.getString("name_publication"));
				tbl_publications.setSummary(rs.getString("summary"));
				/* tbl_news.setContents(rs.getString("contents")); */
				tbl_publications.setPost_start_date(rs.getInt("start_date"));
				list.add(tbl_publications);
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

	/* trang Home cua publications lay 10 tin moi nhat */
	public ArrayList<tbl_tt_publications> Publications_HientrangHome10() {
		ArrayList<tbl_tt_publications> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_publication, name_publication, summary, start_date from tbl_tt_publications order by id_publication desc limit 7");
			while (rs.next()) {
				tbl_tt_publications tbl_publications = new tbl_tt_publications();
				tbl_publications.setId_publication(rs.getInt("id_publication"));
				tbl_publications.setName_publication(rs.getString("name_publication"));
				tbl_publications.setSummary(rs.getString("summary"));
				/* tbl_news.setContents(rs.getString("contents")); */
				tbl_publications.setPost_start_date(rs.getInt("start_date"));
				list.add(tbl_publications);
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
	public ArrayList<tbl_tt_publications> Publications_XemNoiDungTinTheoMa(String id_tintuc) {
		ArrayList<tbl_tt_publications> list = new ArrayList<tbl_tt_publications>();
		tbl_tt_publications tbl_publication = new tbl_tt_publications();
		Connection conn = null;
		ResultSet rs_tinchinh = null;
		ResultSet rs_10tinphu = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_publication, name_publication, summary, contents, start_date from tbl_tt_publications where id_publication = ? limit 1");
			pstmt.setInt(1, Integer.parseInt(id_tintuc)); 
			rs_tinchinh = pstmt.executeQuery();
			while (rs_tinchinh.next()) {
				tbl_publication.setId_publication(rs_tinchinh.getInt("id_publication"));
				tbl_publication.setName_publication(rs_tinchinh.getString("name_publication"));
				tbl_publication.setSummary(rs_tinchinh.getString("summary"));
				tbl_publication.setContents(rs_tinchinh.getString("contents"));
				tbl_publication.setPost_start_date(rs_tinchinh.getInt("start_date"));
				list.add(tbl_publication); //cho vao vi tri 0 
				
				pstmt = conn
						.prepareStatement("select id_publication, name_publication, summary, start_date from tbl_tt_publications where id_publication NOT IN("+ tbl_publication.getId_publication() +") order by id_publication desc limit 10 ");
				//bỏ record đầu tiên
				//System.out.println(pstmt.toString());
				rs_10tinphu = pstmt.executeQuery();
				while (rs_10tinphu.next()) {
					tbl_tt_publications tbl_pro = new tbl_tt_publications();
					tbl_pro.setId_publication(rs_10tinphu.getInt("id_publication"));
					tbl_pro.setName_publication(rs_10tinphu.getString("name_publication"));
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
	
	/*search publications (count) for admin*/
	public int Publications_layTongSoDong_coDieuKien( String ten_bai,
			String tu_ngay, String den_ngay) {
		Statement stmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
		String sql = "SELECT count(id_publication) FROM tbl_tt_publications where 1=1 ";
	
		if(ten_bai != null){  //so sanh voi doi tuong
			sql += " and name_publication like '%" + ten_bai.replace("'", " \\' ") + "%'";
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
	
	/*search publications for admin*/
	public ArrayList<tbl_tt_publications> timkiem(String ten_bai,
			String tu_ngay, String den_ngay, String lmt, String off){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		ArrayList<tbl_tt_publications> list = new ArrayList<tbl_tt_publications>();
		try {
		String sql =  "select id_publication, name_publication, summary, start_date from tbl_tt_publications where 1=1 ";
		//initcap : viet hoa chu cai
		
		if(ten_bai != null){ 
			sql += " and lower(name_publication) like '%"+ ten_bai.toLowerCase().replace("'", " \\' ") +"%'";
		}
		if (!tu_ngay.equals("null")){
			sql += " and start_date >= " + tu_ngay + "" ;
		}
		if (!den_ngay.equals("null")){ 
			sql += " and start_date <= " + den_ngay + "" ;
		}
		sql += " order by id_publication desc limit " + lmt + " offset " + off + "";

	//	System.out.println(sql);
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tbl_tt_publications tbl_publication = new tbl_tt_publications();
			tbl_publication.setId_publication(rs.getInt("id_publication"));
			tbl_publication.setName_publication(rs.getString("name_publication"));
			tbl_publication.setPost_start_date(rs.getInt("start_date"));
			tbl_publication.setSummary(rs.getString("summary"));
			list.add(tbl_publication);
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
	public tbl_tt_publications Publications_SuaTin_TimKimTheoMa(int id_tintuc) {
		tbl_tt_publications tbl_publications = new tbl_tt_publications();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_publication, name_publication, summary, contents from tbl_tt_publications where id_publication = ? limit 1");
			pstmt.setInt(1, id_tintuc); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_publications.setId_publication(rs.getInt("id_publication"));
				tbl_publications.setName_publication(rs.getString("name_publication"));
				tbl_publications.setSummary(rs.getString("summary"));
				tbl_publications.setContents(rs.getString("contents"));
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
		return tbl_publications;
	}
	
	/*sua tin cap nhat tin theo ma */
	public void Publications_SuaTin_CapNhatTheoMa() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_tt_publications set name_publication=? , summary = ?, contents = ? where id_publication = ? ");
			pstmt.setString(1, tbl_tt_publication.getName_publication());
			pstmt.setString(2, tbl_tt_publication.getSummary());
			pstmt.setString(3, tbl_tt_publication.getContents());
			pstmt.setInt(4, tbl_tt_publication.getId_publication());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
	
	/*Xoa tin theo ma*/
	public void Publications_XoaTheoMa(String id_publications) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_tt_publications "
					+ "where id_publication in ( select id_publication from tbl_tt_publications where id_publication = ? order by id_publication limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_publications));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
}
