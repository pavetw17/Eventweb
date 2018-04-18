<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		$('#hinh a').lightBox();
	});
</script>

<div class="div_gallery">
	<div class="project_collection">
		<h2>Gallery</h2>
	</div>

	<div class="photo_gallery" id="photo_gallery">
		<!-- <ul class="ulphoto">
			<li id="hinh"><a href="uploads/lamviec/IMAGE1662.jpg"
				title="Hội nghị tổng kết"> <img class="imgpic"
					src="uploads/lamviec/IMAGE1662.jpg"
					style="width: 206px; height: 150px"></a></li>
			<li id="hinh" class="licenter"><a
				href="uploads/Mariana_Azaola.jpg" title="Hội nghị tổng kết">
					<img class="imgpic" src="uploads/Mariana_Azaola.jpg"
					style="width: 206px; height: 150px">
			</a></li>
			<li id="hinh"><a href="uploads/Resiliency_06.JPG"
				title="Hội nghị tổng kết"> <img class="imgpic"
					src="uploads/Resiliency_06.JPG"
					style="width: 206px; height: 150px"></a></li>
		</ul>
		<div style="clear: both"></div>
		-->
		
		<ul class="ulphoto">
			<%  int k = 2; int i = 0;   %>  
			<c:forEach var="anh" items="${requestScope.list}" >
					<% out.print("<li id='hinh' ");
						if( (i+1) % k == 0 ){
						out.print(" class='licenter' ");
						k = k + 3;
					}
					%>
					<%   out.print(">");%>
					<a href="${anh.link_image}"
					title="${anh.name_image}"> <img class="imgpic"
						src="${anh.link_image}"
						style="width: 206px; height: 150px"></a></li>
					
					<%  if((i +1) % 3 == 0){ 
					
						out.print("</ul> <div style='clear: both'></div> <ul class='ulphoto'>");
					
						}	
						
					 i++; %>
			</c:forEach>
		
	</div>
</div>
	<div id="Pagination" class="pagination"></div>





