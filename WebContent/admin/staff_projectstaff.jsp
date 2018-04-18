<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<div class="staff_div">
	<div class="project_collection">
		<h2>Project Staff</h2>
	</div>

	<c:forEach var="staff" items="${list_staff}"> 
	<div class="staff_detail">
		<div class="staff_contents">
			<span style="font-family:  Tahoma, Geneva, sans-serif; font-size: 15px">
			<strong> ${staff.name_nv}</strong> <br> 
			
			<strong>${staff.job_nv}<br>	</strong> 
			<strong>Tasks:<br></strong> ${staff.task_nv}<br> 
			
			<strong>Email</strong>: <a href="mailto:${staff.email_nv}"> 
				${staff.email_nv}
				</a> 
				
			</span> 
		</div>
		<div class="staff_img">
			<img src="${staff.photo_nv}" style="width:148px; height:180px"/>
		</div>
	</div>
	</c:forEach>

</div>