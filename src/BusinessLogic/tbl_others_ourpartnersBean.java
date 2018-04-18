package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import Entity.tbl_others_ourpartners;

public class tbl_others_ourpartnersBean {
	private tbl_others_ourpartners tbl_ourpartners;
	private DataSource ds;
	public tbl_others_ourpartners getTbl_ourpartners() {
		return tbl_ourpartners;
	}
	public void setTbl_ourpartners(tbl_others_ourpartners tbl_ourpartners) {
		this.tbl_ourpartners = tbl_ourpartners;
	}
	public tbl_others_ourpartnersBean(DataSource ds) {
		super();
		this.ds = ds;
	}
	
	/*Add Partners*/
	public void OurPartners_Add() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("insert into tbl_others_ourpartners(name_partners,link_partners,introduce,link_logo) "
							+ "values (?,?,?,?)");
			pstmt.setString(1, tbl_ourpartners.getName_partners());
			pstmt.setString(2, tbl_ourpartners.getLink_partners());
			pstmt.setString(3, tbl_ourpartners.getIntroduce());
			pstmt.setString(4, tbl_ourpartners.getLink_logo());
		//	 System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		} finally {
			conn.close();
		}
	}
	
	/*search our partners (count) for admin*/
	public int layTongSoDongOurPartners_coDieuKien( String name_partners) {
		Statement stmt;
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
		String sql = "SELECT count(id_partners) FROM tbl_others_ourpartners where 1=1 ";
	
		if(name_partners != null){  //so sanh voi doi tuong
			sql += " and name_partners like '%" + name_partners.replace("'", " \\' ") + "%'"; //  "\'" chong loai sql injection
		}
		
		//	System.out.println("Cau lenh: " + sql);
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
	
	/*search our partners for admin*/
	public ArrayList<tbl_others_ourpartners> timkiem(String ten_bai,
			 String lmt, String off){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		tbl_others_ourpartners tbl_ourpartners = null;
		ArrayList<tbl_others_ourpartners> list = new ArrayList<tbl_others_ourpartners>();
		try {
		String sql =  "select id_partners, name_partners, introduce, link_partners from tbl_others_ourpartners where 1=1 ";
		//initcap : viet hoa chu cai
		
		if(ten_bai != null){ 
			sql += " and lower(name_partners) like '%"+ ten_bai.toLowerCase().replace("'", " \\' ") +"%'";
		}
		sql += " order by id_partners desc limit " + lmt + " offset " + off + "";

	//	System.out.println(sql);
		
		conn = ds.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			tbl_ourpartners = new tbl_others_ourpartners();
			tbl_ourpartners.setId_partners(rs.getInt("id_partners"));
			tbl_ourpartners.setName_partners(rs.getString("name_partners"));
			tbl_ourpartners.setIntroduce(rs.getString("introduce"));
			tbl_ourpartners.setLink_partners(rs.getString("link_partners"));
			list.add(tbl_ourpartners);
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
	
	
	/*Delete our partner */
	public void OurPartners_Delete(String id_partners) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("delete from tbl_others_ourpartners "
					+ "where id_partners in ( select id_partners from tbl_others_ourpartners where id_partners = ? order by id_partners limit 1)");
			pstmt.setInt(1, Integer.parseInt(id_partners));
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
			
		}finally{
			conn.close();
		}
	}
	
	
	/*Edit our partner according to the id_partners */
	public tbl_others_ourpartners OurPartner_SuaTin_TimKimTheoMa(int id_partners) {
		tbl_others_ourpartners tbl_op = new tbl_others_ourpartners();
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn
					.prepareStatement("select id_partners, name_partners, introduce, link_logo from tbl_others_ourpartners where id_partners = ? limit 1");
			pstmt.setInt(1, id_partners); 
			//System.out.print("Lenh SQL: "+ pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbl_op.setId_partners(rs.getInt("id_partners"));
				tbl_op.setName_partners(rs.getString("name_partners"));
				tbl_op.setIntroduce(rs.getString("introduce"));
				tbl_op.setLink_logo(rs.getString("link_logo"));
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
		return tbl_op;
	}
	
	/*Edit Partners*/
	public void OurPartners_Edit() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("UPDATE tbl_others_ourpartners set name_partners=?, link_partners =?, introduce=?,link_logo=?  where id_partners = ? ");
			pstmt.setString(1, tbl_ourpartners.getName_partners());
			pstmt.setString(2, tbl_ourpartners.getLink_partners());
			pstmt.setString(3, tbl_ourpartners.getIntroduce());
			pstmt.setString(4, tbl_ourpartners.getLink_logo());
			pstmt.setInt(5, tbl_ourpartners.getId_partners());
		//	System.out.print("Lenh SQL: "+ pstmt.toString());
			pstmt.executeUpdate();
		}finally{
			conn.close();
		}
	}
	
	public ArrayList<tbl_others_ourpartners> OurPartner_GetLeft(){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		ArrayList<tbl_others_ourpartners> list = new ArrayList<tbl_others_ourpartners>();
		tbl_others_ourpartners tbl_ourpartners = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select ('#'|| link_partners) as link_partners, name_partners from tbl_others_ourpartners order by name_partners");
			while(rs.next()){
				tbl_ourpartners = new tbl_others_ourpartners();
				tbl_ourpartners.setLink_partners(rs.getString("link_partners"));
				tbl_ourpartners.setName_partners(rs.getString("name_partners"));
				list.add(tbl_ourpartners);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<tbl_others_ourpartners> OurPartner_SelectAll(){
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt;
		ArrayList<tbl_others_ourpartners> list = new ArrayList<tbl_others_ourpartners>();
		tbl_others_ourpartners tbl_ourpartners = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select name_partners, link_partners, introduce, link_logo from tbl_others_ourpartners order by name_partners");
			while(rs.next()){
				tbl_ourpartners = new tbl_others_ourpartners();
				tbl_ourpartners.setLink_partners(rs.getString("link_partners"));
				tbl_ourpartners.setName_partners(rs.getString("name_partners"));
				tbl_ourpartners.setIntroduce(rs.getString("introduce"));
				tbl_ourpartners.setLink_logo(rs.getString("link_logo"));
				list.add(tbl_ourpartners);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
