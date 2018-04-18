<%@page import="Entity.tbl_tt_project"%>
<%@page import="BusinessLogic.tbl_tt_projectBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="BusinessLogic.tbl_eventsBean"%>
<%@page language="java" import="Entity.tbl_events"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.FunctionAll"%>


<%!@Resource(name = "EventDB")
	private DataSource ds;%>

<div class="project_collection">
	<h2>Upcoming Events</h2>
	<div class="upcoming_events">
	
	
	<%
			tbl_eventsBean bean = new tbl_eventsBean(ds);
			FunctionAll func = new  FunctionAll();
    		ArrayList<tbl_events> list = bean.Events_HientrangHome10();
    		StringBuffer buf = new StringBuffer();
    	
    		buf.append("<div class='contents'>");
    		if(list.get(0).getPhoto() != null){
	    		buf.append("<div class='img' style='margin-right: 10px;'>");
	    		buf.append("<img src='"+ list.get(0).getPhoto() +"' style='width: 200px; height: 200px;' >");
	    		buf.append("</div>");
    		}
    		buf.append("<div ><a href='RedirectPage?action=xemnoidungEvents&id_tin_tuc=" +  list.get(0).getId_events() +"' class='title_contents' >" + list.get(0).getName_events() +"</a></div>");
    		buf.append("<p>" + list.get(0).getSummary() +"</p>");
    		buf.append("<a href='RedirectPage?action=xemnoidungEvents&id_tin_tuc=" +  list.get(0).getId_events() +"'> Read more...</a>");
    		buf.append("</div>");
    		
    		out.print(buf.toString());
	%>
	
		<!-- <div class="img">
			<img src="uploads/Mariana_Azaola.jpg"
				style="width: 200px; height: 200px;">
		</div> -->
		<!-- <div class="contents">
			<div class="title_contents">Interview: Suzanne McKechnie Klahr
				on social entrepreneurship from high school to law school</div>
			<p>
				Suzanne McKechnie Klahr is the CEO and founder of BUILD. BUILD’s
				mission is to use entrepreneurship to excite and propel disengaged,
				low-income students through high school to college and career
				success. By helping students develop and run their own small
				businesses, BUILD blends academic instruction with real-world
				business experiences and critical skill-building for the future. <a
					href="javascript:void(0)">Read more...</a>
			</p>
		</div> -->
		<div class="list_events">
		<% 		buf.setLength(0);
				buf.append("<ol class='list_ol'>");
				for(int i= 1;i<list.size();i++){
					buf.append("<li><a href='RedirectPage?action=xemnoidungEvents&id_tin_tuc=" +  list.get(i).getId_events() +"' title='"+ list.get(i).getSummary() +"'>" + list.get(i).getName_events() + "</a>");
					buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(i).getPost_start_date()) + ")</span></li>");
				}
				out.print(buf.toString());
		%>
		</div>
		<!-- <div class="list_events">
			<ol class="list_ol">
				<li><a href="#">NULL</a></li>
			</ol>
		</div> -->
		<a href="RedirectPage?action=events" class="more float_r">View All</a>
	</div>
	<div class="cleared"></div>
</div>


<div class="project_collection">
	<h2>Project Information</h2>
	<div class="project_infomation">
	
	<%
			tbl_tt_projectBean bean_pr = new tbl_tt_projectBean(ds);
    		ArrayList<tbl_tt_project> list_pr = bean_pr.Project_HientrangHome10();
    		buf.setLength(0);
    		
    		buf.append("<div class='contents'>");
    		if(list_pr.get(0).getPhoto() != null){
	    		buf.append("<div class='img'> style='margin-right: 10px;'");
	    		buf.append("<img src='"+ list_pr.get(0).getPhoto() +"' style='width: 200px; height: 200px' >");
	    		buf.append("</div>");
    		}
    		buf.append("<div class='title_contents'><a href='RedirectPage?action=xemnoidungProject&id_tin_tuc=" +  list_pr.get(0).getId_project() +"' class='title_contents' >" + list_pr.get(0).getName_project() +"</a></div>");
    		buf.append("<p>" + list_pr.get(0).getSummary() +"</p>");
    		buf.append("<a href='RedirectPage?action=xemnoidungProject&id_tin_tuc=" +  list_pr.get(0).getId_project() +"'> Read more...</a>");
    		buf.append("</div>");
    		
    		out.print(buf.toString());
	%>
	
		<!-- <div class="img">
			<img src="uploads/a1-200x200.jpg">
		</div>
		<div class="contents">
			<div class="title_contents">Architecture</div>
			<p>
				Architecture is both the process and the product of planning,
				designing, and constructing buildings and other physical structures.
				Architectural works, in the material form of buildings, are often
				perceived as cultural symbols and as works of art. Historical
				civilizations are often identified with their surviving
				architectural achievements <a href="javascript:void(0)">Read
					more...</a>
			</p>
		</div> -->
		<div class="list_events">
		
			<% 		buf.setLength(0);
				buf.append("<ol class='list_ol'>");
				for(int i= 1;i<list_pr.size();i++){
					buf.append("<li><a href='RedirectPage?action=xemnoidungProject&id_tin_tuc=" +  list_pr.get(i).getId_project() +"' title='"+ list_pr.get(i).getSummary() +"'>" + list_pr.get(i).getName_project() + "</a>");
					buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list_pr.get(i).getPost_start_date()) + ")</span></li>");
				}
				out.print(buf.toString());
			%>
		
			<!-- <ol class="list_ol">
				<li><a href="#">NULL</a></li>
			</ol> -->
		</div>
		<a href="RedirectPage?action=project" class="more float_r">View All</a>
	</div>
	<div class="cleared"></div>
</div>