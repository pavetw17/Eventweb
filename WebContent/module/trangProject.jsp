<%@page import="BusinessLogic.tbl_tt_projectBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="Entity.tbl_tt_project"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.FunctionAll"%>

	<%!@Resource(name = "EventDB")
	   private DataSource ds;%>

<div class="details_highlights">
	<div class="project_collection">
		<h2>Project</h2>
	</div>

	<%
			tbl_tt_projectBean bean = new tbl_tt_projectBean(ds);
			FunctionAll func = new  FunctionAll();
    		ArrayList<tbl_tt_project> list = bean.Project_HientrangHome10();
    		StringBuffer buf = new StringBuffer();
    		if(list.get(0).getPhoto() != null){
	    		buf.append("<div class='img_highlights'>");
	    		buf.append("<img src='"+ list.get(0).getPhoto() +"' style='width: 200px; height: 200px' >");
	    		buf.append("</div>");
    		}
    		buf.append("<div class='title_highlights'><a href='RedirectPage?action=xemnoidungProject&s&id_tin_tuc=" +  list.get(0).getId_project() +"' >" + list.get(0).getName_project() +"</a>");
    		buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(0).getPost_start_date()) + ")</span></div>");
    		buf.append("<div class='text_highlights'>" + list.get(0).getSummary() +"");
    		buf.append("<a href='RedirectPage?action=xemnoidungProject&id_tin_tuc=" +  list.get(0).getId_project() +"'> Read more...</a>");
    		buf.append("</div>");
	%>

	<div id="cactin_highlights">
		<div class="content_highlights"
			style="border-bottom: none; margin-bottom: 0px;">
			<% 		out.print(buf.toString());
				buf.setLength(0);
				
				for(int i= 1;i<list.size();i++){
					buf.append("<li><a href='RedirectPage?action=xemnoidungProject&id_tin_tuc=" +  list.get(i).getId_project() +"' title='"+ list.get(i).getSummary() +"'>" + list.get(i).getName_project() + "</a>");
					buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(i).getPost_start_date()) + ")</span></li>");
				}
			%>
		</div>
		<div class="tinkhac">News other</div>
		<div class="list_events">
			<ol class="list_ol">
				<%
					out.print(buf.toString());
				%>
			</ol>
		</div>
	</div>

	<div id="Pagination" class="pagination"></div>

</div>