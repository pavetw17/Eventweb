<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
 	<jsp:forward page="../../403.jsp"></jsp:forward>
 </c:if>  
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Trang quản trị</title>
<link rel="stylesheet" type="text/css" href="./admin/template/style.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
</head>

<body>
	<div id="header">
		<div class="banner">
			<a href="showTrangChu">
				<h1 style="color: White; padding-top: 15px; font-size: 30px;">Admin
					Control Panel</h1>
			</a> <span class="flash"></span>
		</div>
		<!--End .banner-->
		<div id="wrapper">
			<div class="menu" style="padding-bottom: 70px; padding-top: 18px;">
				<tiles:insertAttribute name="menu" />
				<div class="info">
					Hello : ${sessionScope.tbl_qt.username} <a ID="LinkButton1"
						href="thoat">Exit</a>

				</div>
				<!-- End .info -->
				<div class="clear"></div>
			</div>
			<!--End .menu-->
		</div>
		<!--End #header-->

		<div id="content">
			<div class="clear"></div>
			<tiles:insertAttribute name="content" />
		</div>
		<!--End #content-->
	</div>
	<!--End #wrapper-->
	 
	<div id="footer">
			<div class="main_footer">
				<b>COPYRIGHT (C) 2014</b>
			</div>
    </div> <!--End #footer-->
	
</body>
</html>