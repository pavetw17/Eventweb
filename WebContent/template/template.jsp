<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%--Title--%> <tiles:insertAttribute name="title" />
</title>
<link rel="stylesheet" href="css/style_layout.css" type="text/css" />
<link rel="stylesheet" href="css/templatemo_style.css" type="text/css" />
<link rel="stylesheet" href="css/jquery.lightbox-0.5.css" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type='text/javascript' src='js/jquery-1.10.2.js'></script>
<script type='text/javascript' src='js/jquery.lightbox-0.5.js'></script>
<style type='text/css'>
#bttop{border:1px solid #4adcff;background:#24bde2;text-align:center;padding:5px;position:fixed;bottom:35px;right:10px;cursor:pointer;display:none;color:#fff;font-size:11px;font-weight:900;}
#bttop:hover{border:1px solid #ffa789;background:#ff6734;}
</style>
<script type='text/javascript'>$(function(){$(window).scroll(function(){if($(this).scrollTop()!=0){$('#bttop').fadeIn();}else{$('#bttop').fadeOut();}});$('#bttop').click(function(){$('body,html').animate({scrollTop:0},300);});});</script>
	
</head>

<body>
	<div id="templatemo_wrapper">
		<!--Header Start-->
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		
		<div id="header_banner">
			<tiles:insertAttribute name="header_banner" />
		</div>
		<!--Header End-->
		
		<!-- Content Start -->
		<div id="content">
			<div id="left" style="margin-top: 20px; margin-left: 14px; padding-right: 10px; ">
				<tiles:insertAttribute name="left" />
			</div>
			
			<div id="container" style="margin-top: 20px; ">
				<tiles:insertAttribute name="content" />
			</div>

				<%-- <div id="right">
						<tiles:insertAttribute name="right" />
				</div> --%>
		</div>
		<!-- Content End -->
	</div>
	
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
		

<div id='bttop'>BACK TO TOP</div>
	
</body>
</html>