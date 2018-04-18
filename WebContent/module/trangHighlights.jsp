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

<div class="details_highlights">
	<div class="project_collection">
		<h2>News Highlights</h2>
	</div>

	<%
			tbl_newsBean bean = new tbl_newsBean(ds);
			FunctionAll func = new  FunctionAll();
    		ArrayList<tbl_news> list = bean.HientrangHome10_Hightlights();
    		StringBuffer buf = new StringBuffer();
    		if(list.get(0).getPhoto() != null){
	    		buf.append("<div class='img_highlights'>");
	    		buf.append("<img src='"+ list.get(0).getPhoto() +"' style='width: 200px; height: 200px' >");
	    		buf.append("</div>");
    		}
    		buf.append("<div class='title_highlights'><a href='RedirectPage?action=xemnoidungHighlights&id_tin_tuc=" +  list.get(0).getId_news() +"' >" + list.get(0).getName_news() +"</a>");
    		buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(0).getStart_date()) + ")</span></div>");
    		buf.append("<div class='text_highlights'>" + list.get(0).getSummary() +"");
    		buf.append("<a href='RedirectPage?action=xemnoidungHighlights&id_tin_tuc=" +  list.get(0).getId_news() +"'> Read more...</a>");
    		buf.append("</div>");
	%>

	<div id="cactin_highlights">
		<div class="content_highlights"
			style="border-bottom: none; margin-bottom: 0px;">
			<% 		out.print(buf.toString());
				buf.setLength(0);
				
				for(int i= 1;i<list.size();i++){
					buf.append("<li><a href='RedirectPage?action=xemnoidungHighlights&id_tin_tuc=" +  list.get(i).getId_news() +"' title='"+ list.get(i).getSummary() +"'>" + list.get(i).getName_news() + "</a>");
					buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(i).getStart_date()) + ")</span></li>");
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