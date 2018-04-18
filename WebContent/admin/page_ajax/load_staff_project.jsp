<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="Entity.tbl_others_project_staff"%>
<%@page import="BusinessLogic.tbl_others_project_staffBean"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>	
<%@page language="java" import="java.util.ArrayList;"%>	
	
<%!@Resource(name = "EventDB")
	private DataSource ds;%>
<%
	tbl_others_project_staffBean bean = new tbl_others_project_staffBean(ds);
	ArrayList<tbl_others_project_staff> list =  bean.Staff_GetAllNameProjcet();
	StringBuffer buf = new StringBuffer();
	for(int i= 0 ; i< list.size() ; i++){
		buf.append("<option value='" + list.get(i).getId_pro_nv() + "'>" + list.get(i).getName_pro() + "</option>" );
	}
	
	out.println(buf.toString());
%>