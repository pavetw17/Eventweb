<%@page import="BusinessLogic.tbl_bannerBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="BusinessLogic.tbl_bannerBean"%>
<%@page language="java" import="Entity.tbl_banner"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="javax.sql.DataSource"%>

<%!@Resource(name = "EventDB")
	private DataSource ds;%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="js/slide/preview.css" />
<link rel="stylesheet" type="text/css" href="js/slide/wt-rotator.css" />
<script type="text/javascript" src="js/slide/jquery.wt-rotator.min.js"></script>
<script type="text/javascript" src="js/slide/preview.js"></script>

<div class="panel">
	<div class="container">
		<div class="wt-rotator">
			<a href="#"></a>
			<div class="desc"></div>
			<div class="preloader"></div>
			<div class="c-panel">
				<!-- <div class="buttons">
					<div class="prev-btn"></div>
					<div class="play-btn"></div>
					<div class="next-btn"></div>
				</div> -->
				<div class="thumbnails">
					<%-- <ul>
						<c:forEach var="banner" items="${requestScope.results}">
							<LI> <a href='<c:out value="${banner.images}" />'></a>
								<a href='<c:out value="${banner.link}" />'></a>
							<div style="left: 100px; top: 130px; width: 306px;">
							<span class="cap-title"><c:out value="${banner.title_banner}" /> </span><br />
							<c:out value="${banner.contents}" />
							</div>
						</c:forEach>
					</ul> --%>
						<ul>
							<%
								StringBuffer bf = new StringBuffer();
								tbl_bannerBean bean = new tbl_bannerBean(ds);
								ArrayList<tbl_banner> list = bean.HienBanner_TrangChu();
								for(int i=0;i<list.size();i++){
									bf.append("<li><a href='" + list.get(i).getImages() + "' title=''></a>");
									bf.append("<a href='" + list.get(i).getLink() + "'></a>");
									bf.append("<div style='left: 100px; top: 130px; width: 306px;'><a href='" + list.get(i).getLink() + "'>  <span class='cap-title'>"+ list.get(i).getTitle_banner() + "</a></span><br />");
									bf.append(list.get(i).getContents() +  "</div></li>");
								}
								out.println(bf.toString());
								
							%>
						</ul>
					<!-- 	<li><a href="./images/banner/a.jpg" title=""></a>
							<a href="index.jsp"></a>
							<div style="left: 100px; top: 130px; width: 306px;">
								<span class="cap-title">Resiliency: A BIG IDEA Program</span><br />
								23 Jul 2014 3:30 PM • Frederick Douglass Building Pier (1417
								Thames Street Baltimore, MD 21231)
							</div></li>
						 -->
					
				</div>
			</div>
		</div>
	</div>
</div>

