<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:if test="${empty sessionScope.tbl_qt.username}">
 	<jsp:forward page="../../403.jsp"></jsp:forward>
 </c:if>  
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="BusinessLogic.menuBean"%>
<%@page language="java" import="javax.sql.DataSource"%>

<script type="text/javascript" src="admin/template/quanly_css_js_menu/jquerycssmenu.js"></script>
<link rel="stylesheet" type="text/css"	href="admin/template/quanly_css_js_menu/jquerycssmenu.css" />
<%!@Resource(name = "EventDB")
	private DataSource ds;%>
<%
	menuBean bean = new menuBean(ds);
	String div_m = bean.quanly_getMainMenu(ds, 1,-1);
/* 	System.out.print(div_m); */
%>

<%
	out.println(div_m);
%>