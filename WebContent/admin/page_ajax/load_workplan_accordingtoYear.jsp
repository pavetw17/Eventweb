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
	//System.out.println(request.getParameter("year"));
	ArrayList<tbl_workplan> list =  bean.Workplan_GetNameParent(Integer.parseInt(request.getParameter("year")));
	StringBuffer buf = new StringBuffer();
	for(int i= 0 ; i< list.size() ; i++){
		buf.append("<option value='" + list.get(i).getId_workplan() + "'>" + list.get(i).getName_parent() + "</option>" );
	}
	
	out.print(buf.toString());
%>