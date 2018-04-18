<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="right_ourpartners">
		<div class="project_collection">
		<h2>Our Partners</h2>
	</div>


	<c:forEach var="tbl_op" items="${list}">
		<div class="content_ourpartners">
			<div class="img_ourpartners">
				<img src="${tbl_op.link_logo}" style="width: 104px; height: 104px">
			</div>
			<div class="title_ourpartners">
				<a name="${tbl_op.link_partners}">${tbl_op.name_partners}</a>
			</div>
	
		<div class="text_ourpartners">${tbl_op.introduce}</div>
	</div>
	
	</c:forEach>

	<!-- <div class="content_ourpartners">
		<div class="img_ourpartners">
			<img src="uploads/logo_ourpartners/ASPE-web-logo-2012.png">
		</div>
		<div class="title_ourpartners">
			<a name="ASPE_Training">ASPE-IT Training</a>
		</div>

		<div class="text_ourpartners">ASPE, Inc. is a leading North
			American provider of Agile Software Development training. Global
			Knowledge has joined with ASPE to offer a complete curriculum of
			Agile courses to our customer.</div>
	</div> -->

	
</div>