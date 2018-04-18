package BusinessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Entity.tbl_gallery_thumuc;

public class menuBean {
	private DataSource ds;

	public menuBean(DataSource ds) {
		super();
		this.ds = ds;
	}

	public String getMainMenu(DataSource ds,int parent_id) throws SQLException{
	    	String data = null;	
	    	Connection conn = null;
	    	Statement stm;
	    	ResultSet rs = null;
	    	
	    	if(parent_id==-1)
	    	{
	    		try
	    		{
	    			conn = ds.getConnection();
	    			stm = conn.createStatement();
	    			rs = stm.executeQuery("SELECT id,menu_name,menu_link  FROM  tbl_menu WHERE parent_id is null ORDER BY id");
	    			List<Integer> lstID = new ArrayList<Integer>();
	    	   		List<String> lstName = new ArrayList<String>();
	    	   		List<String> lstHref = new ArrayList<String>();
	    	   		while(rs.next())
	    			{
	    				lstID.add(rs.getInt(1));
	    				lstName.add(rs.getString(2));
	    				lstHref.add(rs.getString(3));
	    			}    	
	    	   		data = "<div id=\"myslidemenu\" class=\"jqueryslidemenu\">";
	    	   		//data = "<div id=\"cssmenu\">";
	    	   		data += "<ul>";
	    	   		
	    	   		for(int i=0;i<lstID.size();i++){
	    	   			data += "<li><a href=\""+lstHref.get(i)+"\">"+lstName.get(i)+"</a>";
	    	   			data = data + getMainMenu(ds, lstID.get(i));
	    	   			data += "</li>";
	    	   		}
	    	   		data += "</ul>";
	    	   		data += "</div>";
	    	   		
	    	   		return data;
	    		}
	    		finally
	    		{
	    			rs.close();
	    			conn.close();
	    		}
	    	} else if(parent_id>0){
	    		try{
	    			conn = ds.getConnection();
	    			stm = conn.createStatement();
	    			rs = stm.executeQuery("SELECT id,menu_name,menu_link FROM  tbl_menu WHERE parent_id=" + parent_id +" ORDER BY id");
	    			List<Integer> lstID = new ArrayList<Integer>();
			   		List<String> lstName = new ArrayList<String>();
			   		List<String> lstHref = new ArrayList<String>();
			   		while(rs.next())
	    			{
	    				lstID.add(rs.getInt(1));
	    				lstName.add(rs.getString(2));
	    				lstHref.add(rs.getString(3));
	    			}   
			   		
			   		if(lstID.size()==0) {
			   			rs.close();
		    			conn.close();
			   			return ""; //khong co menu con
			   		}
			   		data = "<ul>";
					for(int i=0;i<lstID.size();i++){
	    				data += "<li><a href=\""+lstHref.get(i)+"\">"+lstName.get(i)+"</a>";
	    				data += getMainMenu(ds,lstID.get(i));
						data += "</li>";
					}
					data += "</ul>";    
	    		}finally{
	    			rs.close();
	    			conn.close();
	    		}
	    	}
	    	return data;
	    }
	
	public String quanly_getMainMenu(DataSource ds,int role,int parent_id) throws SQLException{
		String data = null;
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	if(parent_id==-1)
    	{
    		try
    		{
    			conn = ds.getConnection();
    			pstmt = conn
    					.prepareStatement("SELECT id,menu_name,menu_link FROM tbl_menu_quantri  WHERE parent_id is null and ma_quan_ly=? ORDER BY position");
    			pstmt.setInt(1, role);
    	    	List<Integer> lstID = new ArrayList<Integer>();
    	   		List<String> lstName = new ArrayList<String>();
    	   		List<String> lstHref = new ArrayList<String>();
    	   		rs = pstmt.executeQuery();
    	   		while(rs.next())
    			{
    				lstID.add(rs.getInt(1));
    				lstName.add(rs.getString(2));
    				lstHref.add(rs.getString(3));
    			}    	
    	   		
    	   		//data = "<ul class=\"nav_menu\">";
    	   		data = "<div id=\"myjquerymenu\" class=\"jquerycssmenu\">";
    	   		data += "<ul>";
    	   		for(int i=0;i<lstID.size();i++){
    	   			data += "<li><a href=\""+lstHref.get(i)+"\">"+lstName.get(i)+"</a>";
    	   			data = data + quanly_getMainMenu(ds,role, lstID.get(i));
    	   			data += "</li>";
    	   		}
    	   		data += "</ul>";
    	   		
    	   		
    	   		return data;
    		}
    		finally
    		{
    			rs.close();
    			conn.close();
    		}
    	} else if(parent_id>0){
    		try{
/*    			conn = ds.getConnection();
    			stm = conn.createStatement();
    			rs = stm.executeQuery("SELECT * FROM  tbl_menu_quantri WHERE parent_id=" + parent_id +" ORDER BY id");
    			List<Integer> lstID = new ArrayList<Integer>();
		   		List<String> lstName = new ArrayList<String>();
		   		List<String> lstHref = new ArrayList<String>();
*/		   		
    			conn = ds.getConnection();
		   		pstmt = conn
    					.prepareStatement("SELECT id,menu_name,menu_link FROM  tbl_menu_quantri WHERE parent_id=? and ma_quan_ly=? ORDER BY menu_name");
		   		pstmt.setInt(1, parent_id);
		   		pstmt.setInt(2, role);
    	    	List<Integer> lstID = new ArrayList<Integer>();
    	   		List<String> lstName = new ArrayList<String>();
    	   		List<String> lstHref = new ArrayList<String>();
    	   		rs = pstmt.executeQuery();
		   		while(rs.next()) 
    			{
    				lstID.add(rs.getInt(1));
    				lstName.add(rs.getString(2));
    				lstHref.add(rs.getString(3));
    			}    	
		   		if(lstID.size()==0){
		   			rs.close();
	    			conn.close();
		   			return ""; //khong co menu con
		   		}
		   		//data = "<ul class=\"nav_2\">";
		  		data = "<ul>";
				for(int i=0;i<lstID.size();i++){
    				data += "<li><a href=\""+lstHref.get(i)+"\">&bull;"+lstName.get(i)+"</a>";
    				data += quanly_getMainMenu(ds,role,lstID.get(i));
					data += "</li>";
				}
				data += "</ul>";    
    		}finally{
    			rs.close();
    			conn.close();
    		}
    	}
    	return data;
	}
	
	/*Left highlights*/
	public String getMenuLeft(DataSource ds) throws SQLException{
		String data = "";
		Connection conn = null;
		Statement stm ;
		ResultSet rs = null;
		
		String lstMonthEnglish[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		try
		{
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select distinct year from tbl_tt_news order by year desc");
	    	List<Integer> lstYear = new ArrayList<Integer>();
	    	List<Integer> lstMonth = null;
	   		
	   		while(rs.next())
			{
				lstYear.add(rs.getInt(1));
			}    	
	   		
	   		for(int i=0;i<lstYear.size();i++){
	   			data += "<h3 class=\"headerbar\">";
	   			data += "<a href=\"#\">" + lstYear.get(i) + "</a></h3>";
	   			data += "<ul class=\"submenu\">";
	   			
	   			rs = stm.executeQuery("select distinct month from tbl_tt_news where year =" + lstYear.get(i) + " order by month desc");
	   			lstMonth = new ArrayList<Integer>();
	   			while(rs.next())
				{
	   				lstMonth.add(rs.getInt(1));
				}
	   			
	   			for(int j=0;j<lstMonth.size();j++){
	   				for(int k=0; k < lstMonth.get(j); k++){
	   					if( (lstMonth.get(j) - 1) == k){
	   						data += "<li><a href=\"" + lstMonth.get(j) + "_" + lstYear.get(i)  + "\" class=\"basic\" >" + lstMonthEnglish[k] + "</a></li>";
	   					}
	   				}
	   			}
	   			data += "</ul>";
	   		}
		}
		finally
		{
			rs.close(); 
			conn.close();
		}
		return data;
	}
	
	/*Left Project*/
	public String Project_getMenuLeft(DataSource ds) throws SQLException{
		String data = "";
		Connection conn = null;
		Statement stm ;
		ResultSet rs = null;
		
		String lstMonthEnglish[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		try
		{
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select distinct year from tbl_tt_projects order by year desc");
	    	List<Integer> lstYear = new ArrayList<Integer>();
	    	List<Integer> lstMonth = null;
	   		
	   		while(rs.next())
			{
				lstYear.add(rs.getInt(1));
			}    	
	   		
	   		for(int i=0;i<lstYear.size();i++){
	   			data += "<h3 class=\"headerbar\">";
	   			data += "<a href=\"#\">" + lstYear.get(i) + "</a></h3>";
	   			data += "<ul class=\"submenu\">";
	   			
	   			rs = stm.executeQuery("select distinct month from tbl_tt_projects where year =" + lstYear.get(i) + " order by month desc");
	   			lstMonth = new ArrayList<Integer>();
	   			while(rs.next())
				{
	   				lstMonth.add(rs.getInt(1));
				}
	   			
	   			for(int j=0;j<lstMonth.size();j++){
	   				for(int k=0; k < lstMonth.get(j); k++){
	   					if( (lstMonth.get(j) - 1) == k){
	   						data += "<li><a href=\"" + lstMonth.get(j) + "_" + lstYear.get(i)  + "\" class=\"basic\" >" + lstMonthEnglish[k] + "</a></li>";
	   					}
	   				}
	   			}
	   			data += "</ul>";
	   		}
		}
		finally
		{
			rs.close(); 
			conn.close();
		}
		return data;
	}
	
	/*Left Gallery*/
	public String Menu_LeftGallery(DataSource ds) throws SQLException{
		String data = "";
		Connection conn = null;
		Statement stm ;
		ResultSet rs = null;
		
		try{	
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select distinct year from tbl_gallery_thumuc order by year desc");
	    	List<Integer> lstYear = new ArrayList<Integer>();
	    	ArrayList<tbl_gallery_thumuc> list = new ArrayList<>();
	   		
	   		while(rs.next())
			{
				lstYear.add(rs.getInt(1));
			}    	
	   		
	   		for(int i=0;i<lstYear.size();i++){
	   			data += "<h3 class=\"headerbar\">";
	   			data += "<a href=\"#\">" + lstYear.get(i) + "</a></h3>";
	   			data += "<ul class=\"submenu\">";
	   			
	   			rs = stm.executeQuery("select ten_thumuc,id_thumuc from tbl_gallery_thumuc where year =" + lstYear.get(i) + " order by id_thumuc desc");
	   			while(rs.next())
				{	tbl_gallery_thumuc tbl_thumuc = new tbl_gallery_thumuc();
	   				tbl_thumuc.setTen_thumuc(rs.getString("ten_thumuc"));
	   				tbl_thumuc.setId_thumuc(rs.getInt("id_thumuc"));
	   				list.add(tbl_thumuc);
				}
	   			
	   			for(int j=0;j<list.size();j++){
	   						data += "<li><a href=\"" + list.get(j).getId_thumuc() + "\" class=\"basic\" >" + list.get(j).getTen_thumuc() + "</a></li>";
	   			}
	   			data += "</ul>";
	   		}
		}
		finally{
			rs.close(); 
			conn.close();
		}
		return data;
	}

	/*Left Events*/
	public String Menu_LeftEvents(DataSource ds) throws SQLException{
		String data = "";
		Connection conn = null;
		Statement stm ;
		ResultSet rs = null;
		
		String lstMonthEnglish[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		try
		{
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select distinct year from tbl_tt_events order by year desc");
	    	List<Integer> lstYear = new ArrayList<Integer>();
	    	List<Integer> lstMonth = null;
	   		
	   		while(rs.next())
			{
				lstYear.add(rs.getInt(1));
			}    	
	   		
	   		for(int i=0;i<lstYear.size();i++){
	   			data += "<h3 class=\"headerbar\">";
	   			data += "<a href=\"#\">" + lstYear.get(i) + "</a></h3>";
	   			data += "<ul class=\"submenu\">";
	   			
	   			rs = stm.executeQuery("select distinct month from tbl_tt_events where year =" + lstYear.get(i) + " order by month desc");
	   			lstMonth = new ArrayList<Integer>();
	   			while(rs.next())
				{
	   				lstMonth.add(rs.getInt(1));
				}
	   			
	   			for(int j=0;j<lstMonth.size();j++){
	   				for(int k=0; k < lstMonth.get(j); k++){
	   					if( (lstMonth.get(j) - 1) == k){
	   						data += "<li><a href=\"" + lstMonth.get(j) + "_" + lstYear.get(i)  + "\" class=\"basic\" >" + lstMonthEnglish[k] + "</a></li>";
	   					}
	   				}
	   			}
	   			data += "</ul>";
	   		}
		}
		finally
		{
			rs.close(); 
			conn.close();
		}
		return data;
	}
	
	/*Left Workplan*/
	public String Menu_LeftWorkplan(DataSource ds) throws SQLException{
		String data = "";
		Connection conn = null;
		Statement stm ;
		ResultSet rs = null;
		
		try{	
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select distinct year from tbl_tt_workplans order by year desc");
	    	List<Integer> lstYear = new ArrayList<Integer>();
	   		
	   		while(rs.next())
			{
				lstYear.add(rs.getInt(1));
			}    	
	   		
	   		for(int i=0;i<lstYear.size();i++){
	   			data += "<h3 class=\"headerbar\">";
	   			data += "<a href=\"" + lstYear.get(i) +"\" class=\"basic\">" + lstYear.get(i) + "</a></h3>";
	   		}
		}
		finally{
			rs.close(); 
			conn.close();
		}
		return data;
	}
	
	/*Left Publications*/
	public String Publication_getMenuLeft(DataSource ds) throws SQLException{
		String data = "";
		Connection conn = null;
		Statement stm ;
		ResultSet rs = null;
		
		String lstMonthEnglish[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		try
		{
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select distinct year from tbl_tt_publications order by year desc");
	    	List<Integer> lstYear = new ArrayList<Integer>();
	    	List<Integer> lstMonth = null;
	   		
	   		while(rs.next())
			{
				lstYear.add(rs.getInt(1));
			}    	
	   		
	   		for(int i=0;i<lstYear.size();i++){
	   			data += "<h3 class=\"headerbar\">";
	   			data += "<a href=\"#\">" + lstYear.get(i) + "</a></h3>";
	   			data += "<ul class=\"submenu\">";
	   			
	   			rs = stm.executeQuery("select distinct month from tbl_tt_publications where year =" + lstYear.get(i) + " order by month desc");
	   			lstMonth = new ArrayList<Integer>();
	   			while(rs.next())
				{
	   				lstMonth.add(rs.getInt(1));
				}
	   			
	   			for(int j=0;j<lstMonth.size();j++){
	   				for(int k=0; k < lstMonth.get(j); k++){
	   					if( (lstMonth.get(j) - 1) == k){
	   						data += "<li><a href=\"" + lstMonth.get(j) + "_" + lstYear.get(i)  + "\" class=\"basic\" >" + lstMonthEnglish[k] + "</a></li>";
	   					}
	   				}
	   			}
	   			data += "</ul>";
	   		}
		}
		finally
		{
			rs.close(); 
			conn.close();
		}
		return data;
	}
}
