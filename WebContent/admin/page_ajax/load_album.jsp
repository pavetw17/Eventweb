<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>	
<%@page language="java" import="BusinessLogic.tbl_gallery_thumucBean"%>
<%@page language="java" import="Entity.tbl_gallery_thumuc"%>
<%@page language="java" import="java.util.ArrayList;"%>	
	
<%!@Resource(name = "EventDB")
	private DataSource ds;%>
<%
	tbl_gallery_thumucBean bean = new tbl_gallery_thumucBean(ds);
	ArrayList<tbl_gallery_thumuc> list =  bean.LayTatCaThuMuc_QuanTri();
	StringBuffer buf = new StringBuffer();
	for(int i= 0 ; i< list.size() ; i++){
		buf.append("<option value='" + list.get(i).getId_thumuc() + "'>" + list.get(i).getTen_thumuc() + "</option>" );
	}
	
	out.println(buf.toString());
%>