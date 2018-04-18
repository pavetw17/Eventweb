<%@page import="BusinessLogic.tbl_workplanBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>	
<%@page language="java" import="Entity.tbl_workplan"%>
<%@page language="java" import="java.util.ArrayList;"%>	
	
<%!@Resource(name = "EventDB")
	private DataSource ds;%>
<%
	tbl_workplanBean bean = new tbl_workplanBean(ds);
	StringBuffer buf = new StringBuffer();
	 ArrayList<tbl_workplan> list = bean.Workplan_GetNameParent(Integer.parseInt(request.getParameter("year")));
	for(int i=0; i<list.size(); i++){
		buf.append("<option value='" + list.get(i).getId_workplan() + "'>");
		buf.append(list.get(i).getName_parent() + "</option>");
	} 
	
	out.println(buf.toString());
%>