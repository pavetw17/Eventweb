package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_banner;

public class tbl_bannerBean {
	private DataSource ds;
	private tbl_banner tbl_banner;
	
	public tbl_bannerBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_banner getTbl_banner() {
		return tbl_banner;
	}

	public void setTbl_banner(tbl_banner tbl_banner) {
		this.tbl_banner = tbl_banner;
	}
	
	public ArrayList<tbl_banner> LayTatCaBanner_QuanTri(){
		ArrayList<tbl_banner> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_banner, title_banner, images, contents, link, pos, status from tbl_banner order by id_banner limit 6");
			while (rs.next()) {
				tbl_banner tbl_banner = new tbl_banner();
				tbl_banner.setId_banner(rs.getInt("id_banner"));
				tbl_banner.setTitle_banner(rs.getString("title_banner"));
				tbl_banner.setImages(rs.getString("images"));
				tbl_banner.setContents(rs.getString("contents"));
				tbl_banner.setLink(rs.getString("link"));
				tbl_banner.setPos(rs.getInt("pos"));
				tbl_banner.setStatus(rs.getBoolean("status"));
				list.add(tbl_banner);
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
	
	public void suaBanner() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_banner set title_banner = ?, images=?, contents=?, link = ?, pos=?, status=? where id_banner = ? ");
			pstmt.setString(1, tbl_banner.getTitle_banner());
			pstmt.setString(2, tbl_banner.getImages());
			pstmt.setString(3, tbl_banner.getContents());
			pstmt.setString(4, tbl_banner.getLink());
			pstmt.setInt(5, tbl_banner.getPos());
			pstmt.setBoolean(6, tbl_banner.isStatus());
			pstmt.setInt(7, tbl_banner.getId_banner());
			pstmt.executeUpdate();
		}finally{
				conn.close();
		}
	}
	
	public ArrayList<tbl_banner> HienBanner_TrangChu(){
		ArrayList<tbl_banner> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_banner, title_banner, images, contents, link, pos, status from tbl_banner where status is true order by pos limit 6 ");
			while (rs.next()) {
				tbl_banner tbl_banner = new tbl_banner();
				tbl_banner.setId_banner(rs.getInt("id_banner"));
				tbl_banner.setTitle_banner(rs.getString("title_banner"));
				tbl_banner.setImages(rs.getString("images"));
				tbl_banner.setContents(rs.getString("contents"));
				tbl_banner.setLink(rs.getString("link"));
				tbl_banner.setPos(rs.getInt("pos"));
				tbl_banner.setStatus(rs.getBoolean("status"));
				list.add(tbl_banner);
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
}
