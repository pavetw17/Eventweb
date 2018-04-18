package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_gallery_image;

public class tbl_gallery_imageBean {
	private DataSource ds;
	private tbl_gallery_image tbl_gallery_image;
	
	public tbl_gallery_imageBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_gallery_image getTbl_gallery_image() {
		return tbl_gallery_image;
	}

	public void setTbl_gallery_image(tbl_gallery_image tbl_gallery_image) {
		this.tbl_gallery_image = tbl_gallery_image;
	}
	
	public void themImage() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("insert into tbl_gallery_image(name_image,link_image,id_thumuc) "
							+ "values (?,?,?) ");
			pstmt.setString(1, tbl_gallery_image.getName_image());
			pstmt.setString(2, tbl_gallery_image.getLink_image());
			pstmt.setInt(3, tbl_gallery_image.getId_thumuc());
			System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			conn.close();
		}
	}
	
	public int demTongSoAnh_TrongAlbum(int id_thumuc){
		PreparedStatement pstmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("SELECT count(id_image) FROM tbl_gallery_image where id_thumuc =?");
			pstmt.setInt(1, id_thumuc);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public ArrayList<tbl_gallery_image> layAnh_TrongAlbum(int id_thumuc, int lmt, int off){
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		tbl_gallery_image tbl_gallery_image = null;
		ArrayList<tbl_gallery_image> list = new ArrayList<tbl_gallery_image>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("SELECT id_image, name_image, link_image FROM tbl_gallery_image where id_thumuc = ? " 
			+ " order by id_image limit ? offset ?");
			pstmt.setInt(1, id_thumuc);
			pstmt.setInt(2, lmt);
			pstmt.setInt(3, off);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_gallery_image = new tbl_gallery_image();
				tbl_gallery_image.setId_image(rs.getInt("id_image"));
				tbl_gallery_image.setName_image(rs.getString("name_image"));
				tbl_gallery_image.setLink_image(rs.getString("link_image"));
				list.add(tbl_gallery_image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void SuaTenImage(String name_image) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("update tbl_gallery_image A set name_image = ? "
						+ " from ( select id_image from tbl_gallery_image " 
						+ "	where id_image = ? and id_thumuc=? limit 1) B " 
						+ "	where A.id_image = B.id_image ");
			pstmt.setString(1, name_image);
			pstmt.setInt(2, tbl_gallery_image.getId_image());
			pstmt.setInt(3, tbl_gallery_image.getId_thumuc());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			conn.close();
		}
	}
	
	public void XoaImage(String id_image) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_gallery_image "
					+ "where id_image in ( select id_image from tbl_gallery_image where id_image = ? order by id_image limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_image));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
	
	public ArrayList<tbl_gallery_image> hienAnhMoiNhat_TrangPhoto(){
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		tbl_gallery_image tbl_gallery_image = null;
		ArrayList<tbl_gallery_image> list = new ArrayList<tbl_gallery_image>();
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_image,name_image,link_image from tbl_gallery_image where id_thumuc in "
					+ "( select id_thumuc from tbl_gallery_image order by id_thumuc desc limit 1) limit 20");
			while (rs.next()) {
				tbl_gallery_image = new tbl_gallery_image();
				tbl_gallery_image.setId_image(rs.getInt("id_image"));
				tbl_gallery_image.setName_image(rs.getString("name_image"));
				tbl_gallery_image.setLink_image(rs.getString("link_image"));
				list.add(tbl_gallery_image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public ArrayList<tbl_gallery_image> hien4Anh_TrangFooter(){
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		tbl_gallery_image tbl_gallery_image = null;
		ArrayList<tbl_gallery_image> list = new ArrayList<tbl_gallery_image>();
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_image,name_image,link_image from tbl_gallery_image order by id_image desc limit 4");
			while (rs.next()) {
				tbl_gallery_image = new tbl_gallery_image();
				tbl_gallery_image.setId_image(rs.getInt("id_image"));
				tbl_gallery_image.setName_image(rs.getString("name_image"));
				tbl_gallery_image.setLink_image(rs.getString("link_image"));
				list.add(tbl_gallery_image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
