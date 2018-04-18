<%@page import="BusinessLogic.tbl_tt_publicationsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="Entity.tbl_tt_publications"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.FunctionAll"%>

	<%!@Resource(name = "EventDB")
	   private DataSource ds;%>

<div class="details_publications">
	<div class="project_collection">
		<h2>Publications</h2>
	</div>
	<%
			tbl_tt_publicationsBean bean = new tbl_tt_publicationsBean(ds);
			FunctionAll func = new  FunctionAll();
    		ArrayList<tbl_tt_publications> list = bean.Publications_HientrangHome10();
    		StringBuffer buf = new StringBuffer();
    		buf.append("<div class='title_publications'><a href='RedirectPage?action=xemnoidungPublications&id_tin_tuc=" +  list.get(0).getId_publication() +"' >" + list.get(0).getName_publication() +"</a>");
    		buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(0).getPost_start_date()) + ")</span></div>");
    		buf.append("<div class='text_publications'>" + list.get(0).getSummary() +"");
    		buf.append("<a href='RedirectPage?action=xemnoidungPublications&id_tin_tuc=" +  list.get(0).getId_publication() +"'> Read more...</a>");
    		buf.append("</div>");
	%>

	<div id="cactin_publications">
		<div class="content_publications"
			style="border-bottom: none; margin-bottom: 0px;">
			<% 		out.print(buf.toString());
				buf.setLength(0);
				
				for(int i= 1;i<list.size();i++){
					buf.append("<li><a href='RedirectPage?action=xemnoidungPublications&id_tin_tuc=" +  list.get(i).getId_publication() +"' title='"+ list.get(i).getSummary() +"'>" + list.get(i).getName_publication() + "</a>");
					buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(i).getPost_start_date()) + ")</span></li>");
				}
			%>
		</div>
		<div class="tinkhac_publications">News other</div>
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




<!-- <div class="details_publications">
	<div class="project_collection">
		<h2>Publication</h2>
	</div>

	<div class="content_publications ">
		<div class="title_publications">Credential Maintenance
		</div>

		<div class="text_publications">We work to include GBCI CE hours and AIA LU hours for programs and events whenever we can. This information will always be listed on the event or program registration page.<a href="javascript:void(0)">Read
				more...</a>
		</div>
	</div>

	<div class="content_publications last">
		<div class="title_publications">Educational Events</div>

		<div class="text_publications">USGBC MD is committed to providing credential maintenance for GBCI, LEED AP’s and AIA. We are currently working to build in half or full day workshops, but for now provide at least 3 hours of LEED specific and AIA continuing education hours per month..<a href="javascript:void(0)">Read
				more...</a></div>
	</div>

</div> -->