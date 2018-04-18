<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="BusinessLogic.tbl_others_project_staffBean"%>
<%@page language="java" import="Entity.tbl_others_project_staff"%>
<%@page language="java" import="BusinessLogic.tbl_others_ourpartnersBean"%>
<%@page language="java" import="Entity.tbl_others_ourpartners"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>	
	
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript">
	ddaccordion.init({
		headerclass : "headerbar", //Shared CSS class name of headers group
		contentclass : "submenu", //Shared CSS class name of contents group
		revealtype : "mouseover", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay : 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev : true, //Collapse previous content (so only one open at any time)? true/false
		defaultexpanded : [ 0 ], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen : true, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault : false, //Should contents open by default be animated into view?
		persiststate : true, //persist state of opened contents within browser session?
		toggleclass : [ "", "selected" ], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml : [ "", "", "" ], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed : "normal", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit : function(headers, expandedindices) { //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose : function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
			//do nothing
		}
	});
</script>

<div class="urbangreymenu">

	<h3 class="headerbar">
		<a href="RedirectPage?action=aboutus">About Us</a>
	</h3>
			<ul class="submenu">
			</ul>
	<h3 class="headerbar">
		<a href="RedirectPage?action=ourpartners">Our Partners</a>
	</h3>
	
	<ul class="submenu">
		<%-- <c:forEach var="tbl_op" items="${list}">
			 <li><a href="${tbl_op.link_partners}"> ${tbl_op.name_partners} </a><li> 
		</c:forEach> --%>
		<%-- <c:out value="${list}"/>  --%>
<%!@Resource(name = "EventDB")
	private DataSource ds;%>
<%
	tbl_others_ourpartnersBean bean = new tbl_others_ourpartnersBean(ds);
	String div_m = "";
	ArrayList<tbl_others_ourpartners> list_op = bean.OurPartner_GetLeft();
	for(int i=0; i<list_op.size(); i++){
		div_m += "<li><a href='RedirectPage?action=ourpartners" + list_op.get(i).getLink_partners() + "'>" + list_op.get(i).getName_partners() +"</a></li>";
	}
%>

<%
	out.println(div_m);
%>
		 
		
	</ul>
	
	
	<!-- <ul class="submenu">
		<li><a href="#ASPE_Training ">ASPE IT Training</a></li>
		<li><a href="#Avaya">Avaya</a></li>
		<li><a href="#Cisco">Cisco</a></li>
	</ul> -->
	
	
	<h3 class="headerbar">
		<a href="#">Our Staff</a>
	</h3>
	<ul class="submenu">
<%
	tbl_others_project_staffBean bean_pro = new tbl_others_project_staffBean(ds);
	String div_pro = "";
	ArrayList<tbl_others_project_staff> list_ps = bean_pro.Staff_GetAllNameProjcet();
	for(int i=0; i<list_ps.size(); i++){
		div_pro += "<li><a href='RedirectPage?action=FindStaff&id_pro_nv=" + list_ps.get(i).getId_pro_nv() + "'>" + list_ps.get(i).getName_pro() +"</a></li>";
	}
%>

<%
	out.println(div_pro);
%>	
	</ul>
	
	
	<!-- <ul class="submenu">
		<li><a href="RedirectPage?action=projectstaff">Project Staffs</a></li>
		<li><a href="#">Project Technical Advisors and Local Staffs</a></li>
		<li><a href="#">Project's PhD Students and MSc Candidates</a></li>
	</ul> -->
	<h3 class="headerbar">
		<a href="RedirectPage?action=contactus">Contact Us</a>
	</h3>


</div>