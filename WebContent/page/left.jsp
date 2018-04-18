<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="BusinessLogic.tbl_newsBean"%>
<%@page language="java" import="Entity.tbl_news"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.FunctionAll"%>


<%!@Resource(name = "EventDB")
	private DataSource ds;%>
	
	
<div class="project_collection">
	<h2>News Highlights</h2>

<%
			tbl_newsBean bean = new tbl_newsBean(ds);
			FunctionAll func = new  FunctionAll(); 
			ArrayList<tbl_news> list = bean.HientrangHome10_Hightlights();
			StringBuffer buf = new StringBuffer();
			buf.append("<div class='div_highlights'>");
			for(int i=0;i<list.size();i++){
				buf.append("<p>");
				buf.append("<a href='RedirectPage?action=xemnoidungHighlights&id_tin_tuc=" +  list.get(i).getId_news() +"' title='"+ list.get(i).getSummary() +"' >" + list.get(i).getName_news() +"</a>");
				buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(0).getStart_date()) + ")</span>");
				buf.append("</p>");
			}
			buf.append("</div>");
			out.print(buf.toString());
%>

	<a href="RedirectPage?action=highlights" class="more float_r">View All</a>
</div>
