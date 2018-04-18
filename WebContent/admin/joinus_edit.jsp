<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<table style="width: 100%;">
	<tr>
		<td><span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Social Network Manager</span></td>
	</tr>
	<tr>
		<td style="width: 80px" align="right"></td>
		<td style="width: 80px" align="right"></td>
	</tr>
</table>

<table cellspacing='0' cellpadding='3' rules='cols' border='1' id='tbl_banner' 
	   style='color: Black; background-color: silver ; border-color: #999999; border-width: 1px; border-style: Solid; width: 100%; "
	   border-collapse: collapse; ' >
	   <tr	style='color: White; background: url(js/menu/menu_source/images/menu-bg.png) repeat; font-weight: bold; height: 25px'>
	   		<th scope='col' style='width:40%'>Image</th>
	   		<th scope='col' style='width:30%'>Name</th>
			<th scope='col' style='width:30%'>Link</th>
			<th scope='col' style='width:1%'></th>
	   </tr>
	<c:forEach var="list" items="${list_joinus}">
		<form action="JoinUs_SuaJoinUs"  id="bangnhap" method="POST" enctype="multipart/form-data">
			<tr>
				<td align="center">
					<c:if test="${not empty list.image_joinus}">
	  					<input type ="image" disabled  src="${list.image_joinus}" id="image_cu" name="image_cu" style=" width: 50px; height: 50px">
					</c:if>
						<input type="hidden" id="getid_joinus" name="getid_joinus" value="${list.id_joinus}">
                    <input type="hidden" id="txt_hinhanh_cu" name="txt_hinhanh_cu" value="${list.image_joinus}" style="display: none" ></input>
					<INPUT TYPE="file" NAME="txt_hinhanh" id="txt_hinhanh" CLASS="textbox" size="20"  accept="image/x-png, image/gif, image/jpeg"  >
				</td>	
				<td align="center">
					<input type="text" name="txt_name" value="${list.name_joinus}">
				</td>
				<td align="center">
					<input type="text" name="txt_link" value="${list.link_joinus}">
				</td>
				<td >
					<input type="submit" name="btn_ghiCSDL" value="Save" id="btn_ghiCSDL"
								class="button1"   style="font-size: 12pt; width: 105px; "/>
				</td>
			</tr>
		</form>
	</c:forEach>
</table>


	<br>
<input type="button" onclick="history.back();"	 name="btn_quaylai" value="Cancel" id="btn_quaylai"
							class="button1"   style="font-size: 12pt; width: 105px "/>