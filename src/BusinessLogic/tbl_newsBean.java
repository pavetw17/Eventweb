package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_news;

public class tbl_newsBean {
	private DataSource ds;
	private tbl_news tbl_news;

	public tbl_newsBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_news getTbl_news() {
		return tbl_news;
	}

	public void setTbl_news(tbl_news tbl_news) {
		this.tbl_news = tbl_news;
	}

	/*them new*/
	public void themNews() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("insert into tbl_tt_news(name_news,summary,contents,start_date,photo,month,year) "
							+ "values (?,?,?,?,?,?,?)");
			pstmt.setString(1, tbl_news.getName_news());
			pstmt.setString(2, tbl_news.getSummary());
			pstmt.setString(3, tbl_news.getContents());
			pstmt.setInt(4, tbl_news.getStart_date());
			pstmt.setString(5, tbl_news.getPhoto());
			pstmt.setInt(6, tbl_news.getMonth());
			pstmt.setInt(7, tbl_news.getYear());
			 System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			conn.close();
		}
	}

	/*search new for guest*/
	public int layTongSoTin_Highlights(int month, int year) {
		Connection conn = null;
		PreparedStatement pstmt;
		int count = 0;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select count(id_news) from tbl_tt_news where month=? and year=?");
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
	public ArrayList<tbl_news> HienTin_Highlights(int month, int year,int limit, int offset) {
		ArrayList<tbl_news> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_news, name_news, summary, start_date, photo from tbl_tt_news where month=? and year=? "
							+ "order by id_news desc limit ? offset ?");
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			pstmt.setInt(3, limit);
			pstmt.setInt(4, offset);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_news tbl_news = new tbl_news();
				tbl_news.setId_news(rs.getInt("id_news"));
				tbl_news.setName_news(rs.getString("name_news"));
				tbl_news.setSummary(rs.getString("summary"));
				/* tbl_news.setContents(rs.getString("contents")); */
				tbl_news.setStart_date(rs.getInt("start_date"));
				tbl_news.setPhoto(rs.getString("photo"));
				list.add(tbl_news);
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

	/* trang Home cua Highlights lay 10 tin moi nhat */
	public ArrayList<tbl_news> HientrangHome10_Hightlights() {
		ArrayList<tbl_news> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_news, name_news, summary, start_date, photo from tbl_tt_news order by id_news desc limit 7");
			while (rs.next()) {
				tbl_news tbl_news = new tbl_news();
				tbl_news.setId_news(rs.getInt("id_news"));
				tbl_news.setName_news(rs.getString("name_news"));
				tbl_news.setSummary(rs.getString("summary"));
				/* tbl_news.setContents(rs.getString("contents")); */
				tbl_news.setStart_date(rs.getInt("start_date"));
				tbl_news.setPhoto(rs.getString("photo"));
				list.add(tbl_news);
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
	public ArrayList<tbl_news> XemNoiDungTinTheoMa_Highlights(String id_tintuc) {
		ArrayList<tbl_news> list = new ArrayList<tbl_news>();
		tbl_news tbl_n = new tbl_news();
		Connection conn = null;
		ResultSet rs_tinchinh = null;
		ResultSet rs_10tinphu = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_news, name_news, summary, contents, start_date from tbl_tt_news where id_news = ? limit 1");
			pstmt.setInt(1, Integer.parseInt(id_tintuc)); 
			rs_tinchinh = pstmt.executeQuery();
			while (rs_tinchinh.next()) {
				tbl_n.setId_news(rs_tinchinh.getInt("id_news"));
				tbl_n.setName_news(rs_tinchinh.getString("name_news"));
				tbl_n.setSummary(rs_tinchinh.getString("summary"));
				tbl_n.setContents(rs_tinchinh.getString("contents"));
				tbl_n.setStart_date(rs_tinchinh.getInt("start_date"));
				list.add(tbl_n); //cho vao vi tri 0 
				
				pstmt = conn
						.prepareStatement("select id_news, name_news, summary, start_date from tbl_tt_news where id_news NOT IN("+ tbl_n.getId_news() +") order by id_news desc limit 10 ");
				//bỏ record đầu tiên
				//System.out.println(pstmt.toString());
				rs_10tinphu = pstmt.executeQuery();
				while (rs_10tinphu.next()) {
					tbl_news tbl_news = new tbl_news();
					tbl_news.setId_news(rs_10tinphu.getInt("id_news"));
					tbl_news.setName_news(rs_10tinphu.getString("name_news"));
					tbl_news.setSummary(rs_10tinphu.getString("summary"));
					tbl_news.setStart_date(rs_10tinphu.getInt("start_date"));
					list.add(tbl_news); //sau vi tri 0
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
	
	/*search new (count) for admin*/
	public int layTongSoDongHighlights_coDieuKien( String ten_bai,
			String tu_ngay, String den_ngay) {
		Statement stmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
		String sql = "SELECT count(id_news) FROM tbl_tt_news where 1=1 ";
	
		if(ten_bai != null){  //so sanh voi doi tuong
			sql += " and name_news like '%" + ten_bai.replace("'", " \\' ") + "%'";
		}
		
		//do gia tri ban dau la null, nen String.valueOf(tu_ngay) in ra là "null"
		//String ten_bai = null;
		//Integer tu_ngay = null ;
		//Integer den_ngay = null;
		
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
	
	/*search new for admin*/
	public ArrayList<tbl_news> timkiem(String ten_bai,
			String tu_ngay, String den_ngay, String lmt, String off){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		tbl_news tbl_new = null;
		ArrayList<tbl_news> list = new ArrayList<tbl_news>();
		try {
		String sql =  "select id_news, name_news, summary, start_date from tbl_tt_news where 1=1 ";
		//initcap : viet hoa chu cai
		
		if(ten_bai != null){ 
			sql += " and lower(name_news) like '%"+ ten_bai.toLowerCase().replace("'", " \\' ") +"%'";
		}
		if (!tu_ngay.equals("null")){
			sql += " and start_date >= " + tu_ngay + "" ;
		}
		if (!den_ngay.equals("null")){ 
			sql += " and start_date <= " + den_ngay + "" ;
		}
		sql += " order by id_news desc limit " + lmt + " offset " + off + "";

	//	System.out.println(sql);
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tbl_new = new tbl_news();
			tbl_new.setId_news(rs.getInt("id_news"));
			tbl_new.setName_news(rs.getString("name_news"));
			tbl_new.setStart_date(rs.getInt("start_date"));
			tbl_new.setSummary(rs.getString("summary"));
			list.add(tbl_new);
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
	public tbl_news SuaTin_TimKimTheoMa_Highlights(int id_tintuc) {
		tbl_news tbl_n = new tbl_news();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_news, name_news, summary, contents, photo from tbl_tt_news where id_news = ? limit 1");
			pstmt.setInt(1, id_tintuc); 
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_n.setId_news(rs.getInt("id_news"));
				tbl_n.setName_news(rs.getString("name_news"));
				tbl_n.setSummary(rs.getString("summary"));
				tbl_n.setContents(rs.getString("contents"));
				tbl_n.setPhoto(rs.getString("photo"));
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
		return tbl_n;
	}
	
	/*sua tin cap nhat tin theo ma */
	public void SuaTin_CapNhatTheoMa_Highlights() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_tt_news set name_news=? , summary = ?, contents = ?, photo = ? where id_news = ? ");
			pstmt.setString(1, tbl_news.getName_news());
			pstmt.setString(2, tbl_news.getSummary());
			pstmt.setString(3, tbl_news.getContents());
			pstmt.setString(4, tbl_news.getPhoto());
			pstmt.setInt(5, tbl_news.getId_news());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
	
	/*Xoa tin theo ma*/
	public void XoaTheoMa_Highlights(String id_news) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_tt_news "
					+ "where id_news in ( select id_news from tbl_tt_news where id_news = ? order by id_news limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_news));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
}
