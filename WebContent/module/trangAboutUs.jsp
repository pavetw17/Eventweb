<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.tbl_others_aboutusBean"%>
<%@page language="java" import="Entity.tbl_others_aboutus"%>


<div class="details_aboutus">
	<div class="project_collection">
		<h2>About Us</h2>
	</div>

	<div class="content_aboutus">

		<div class="text_aboutus">
		<%!@Resource(name = "EventDB")
			private DataSource ds;%>
			
			<%
				tbl_others_aboutusBean bean = new tbl_others_aboutusBean(ds);
				tbl_others_aboutus tbl_about = bean.AboutUs_GetContents(1);
				
				StringBuffer buf = new StringBuffer();
				buf.append(tbl_about.getContents());
				
				out.print(buf.toString());
			%>
		
		</div>
	</div>

</div>