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

<div class="events">
	<div class="project_collection">
		<h2>Upcoming Events</h2>
	</div>
	<div id="cactin_events">
		<%
			tbl_eventsBean bean = new tbl_eventsBean(ds);
			FunctionAll func = new  FunctionAll();
			ArrayList<tbl_events> list = bean.Events_HientrangHome10();
			StringBuffer buf = new StringBuffer();
		    
			for(int i=0 ; i < list.size(); i++){	
				buf.append("<div class='content_events'>");
				buf.append("<div class='title_events'>");
				buf.append("<a href='RedirectPage?action=xemnoidungEvents&id_tin_tuc="+ list.get(i).getId_events() +"' style='font-size: medium;'>"+ list.get(i).getName_events()+ "</a><br><span class='date'>("+ func.DoiSoNguyenRaNgayThang(list.get(i).getPost_start_date()) +")</span>");
				buf.append("</div>");
				buf.append("<div class='details_events'>");
				if(list.get(i).getPhoto() != null){
					buf.append("<div class='details_left_events'> <img src='" + list.get(i).getPhoto() +"' align='left'style='width:150px; height:100px; margin-right: 10px;' >" + list.get(i).getSummary() +"</div>");
				}else {
					buf.append("<div class='details_left_events'>" + list.get(i).getSummary() +"</div>");
				}
				buf.append("<div class='details_right_events'><p><b>Date</b>");
				buf.append("<br> From: "+ func.DoiSoNguyenRaNgayThang(list.get(i).getStart_date_events()) +"<br> To: "+ func.DoiSoNguyenRaNgayThang(list.get(i).getEnd_date_events()));
				buf.append("<br> Event time: " + func.formatTimestampAsTwelveHour_string(list.get(i).getStart_time()) + " - " + func.formatTimestampAsTwelveHour_string(list.get(i).getEnd_time()) + "</p>");
				buf.append("<p>	<b>Location</b><br>" + list.get(i).getLocation() +"</p></div></div></div>");			
			}
			
			out.print(buf.toString());
		%>
		
		<!-- <div class="content_events">
		  <div class="title_events">
			<a href=""> Resiliency: A BIG IDEA Program </a>
		</div>
		<div class="details_events">
			<div class="details_left_events">The 4th program in our 10 BIG
				IDEAS Series will be held aboard the Living Classrooms Skipjack,
				Sigsbee, as we tour Baltimore's Inner Harbor. Dr. Cindy Parker and
				Kristin Baja will present on what resiliency means for cities in the
				Chesapeake region. Join us for this fun and interactive event!



				Resiliency has gained increasing momentum as a concept in public
				policy and urban design as we face the need to respond to climate
				change and associated impacts- extreme weather events, energy
				shortages, and health ramifications. During this harbor tour, our
				speakers will examine resiliency planning through the lens of
				infrastructure and public health.</div>
			<div class="details_right_events">
				<p>
					<b>When</b> <br> 23 Jul 2014 <br> 3:30 PM - 6:00 PM
				</p>
				<p>
					<b>Location</b> <br>Frederick Douglass Building Pier (1417
					Thames Street Baltimore, MD 21231)
				</p>
			</div>
		</div>  -->
		
		</div>
	</div>
	
	<div id="Pagination" class="pagination"></div>  

