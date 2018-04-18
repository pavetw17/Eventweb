package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_gallery_thumuc;

public class tbl_gallery_thumucBean {
	private DataSource ds;
	private tbl_gallery_thumuc tbl_gallery_thumuc;
	
	public tbl_gallery_thumucBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public tbl_gallery_thumuc getTbl_gallery_image() {
		return tbl_gallery_thumuc;
	}

	public void setTbl_gallery_thumuc(tbl_gallery_thumuc tbl_gallery_thumuc) {
		this.tbl_gallery_thumuc = tbl_gallery_thumuc;
	}
	
	public ArrayList<tbl_gallery_thumuc> LayTatCaThuMuc_QuanTri(){
		ArrayList<tbl_gallery_thumuc> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stm;
		try {
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select id_thumuc, ten_thumuc from tbl_gallery_thumuc order by ten_thumuc");
			while (rs.next()) {
				tbl_gallery_thumuc tbl_thumuc = new tbl_gallery_thumuc();
				tbl_thumuc.setId_thumuc(rs.getInt("id_thumuc"));
				tbl_thumuc.setTen_thumuc(rs.getString("ten_thumuc"));
				list.add(tbl_thumuc);
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
	
	
	public int taoAlbum() throws SQLException {
		//kiem tra ten album co ton tai khong neu co thi temp = 1 , con khong thi temp = 0
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int temp = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_thumuc from tbl_gallery_thumuc where ten_thumuc = ? limit 1");
			pstmt.setString(1, tbl_gallery_thumuc.getTen_thumuc());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp = rs.getInt(1);
			}
			
			if(temp == 0){
				pstmt = conn
						.prepareStatement("insert into tbl_gallery_thumuc(ten_thumuc,year) "
							+ "values (?,?)");
			pstmt.setString(1, tbl_gallery_thumuc.getTen_thumuc());
			pstmt.setInt(2, tbl_gallery_thumuc.getYear()); 
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			}
			
		} finally {
			conn.close();
		    rs.close();
		}
		return temp;
	}
	
	
	public int SuaTenAlbum() throws SQLException {
		//kiem tra ten album co ton tai khong neu co thi temp = 1 , con khong thi temp = 0
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int temp = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_thumuc from tbl_gallery_thumuc where ten_thumuc = ? limit 1");
			pstmt.setString(1, tbl_gallery_thumuc.getTen_thumuc());
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp = rs.getInt(1);
			}
			
			if(temp == 0){
				pstmt = conn
						.prepareStatement("update tbl_gallery_thumuc set ten_thumuc =? where id_thumuc =? ");
			pstmt.setString(1, tbl_gallery_thumuc.getTen_thumuc());
			pstmt.setInt(2, tbl_gallery_thumuc.getId_thumuc()); 
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			}
			
		} finally {
			conn.close();
		    rs.close();
		}
		return temp;
	}
	
	public void XoaAlbum(String id_thumuc) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_gallery_image where id_image "
					+ " in ( select id_image from tbl_gallery_image where id_thumuc = ? order by id_thumuc)");
			pstmt.setInt(1, Integer.parseInt(id_thumuc));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("delete from tbl_gallery_thumuc where id_thumuc "
					+ " in ( select id_thumuc from tbl_gallery_thumuc where id_thumuc = ? order by id_thumuc limit 1) ");
			pstmt.setInt(1, Integer.parseInt(id_thumuc));
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
}
