<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="admin/template/alert/jquery.alerts.js"></script>
<link rel="stylesheet" href="admin/template/alert/jquery.alerts.css"
	type="text/css" />
	
<style type="text/css">
div.error {
	color: #C00;
	font-size: 14px;
	position: relative;
}

</style>

<script type="text/javascript">
	
		
	
	function kiemtra() {
		var valid = true, errorMessage = "";

		
		if ($('#txt_name').val() == "") {
			errorMessage += "Username is required.\n";
			valid = false;
		} 

		if ($('#txt_password').val() == "" || $('#txt_password').val().length < 5 ) {
			errorMessage += "Password must be greater than 5 charactersd and  and not exceed 10 characters.\n";
			valid = false;
		} else if ($('#txt_password').val() != $('#txt_password_2').val() ) {
			errorMessage += "These passwords don't match. Try again? \n";
			valid = false;
		}
		
		if ($('#txt_question').val() == "") {
			errorMessage += "Question is required.\n";
			valid = false;
		}
		
		if ($('#txt_email').val() == "") {
			errorMessage += "Email is required.\n";
			valid = false;
		}

		
		
		if (!valid && errorMessage.length > 0) {
			alert(errorMessage);
		}
		return valid;
	}
	
	
	$(document).ready(function(){
		
		  $("#btn_submit").click(function(){
			  if(kiemtra()){
				 	 var email =  $('#txt_email').val();
					 var question =  $('#txt_question').val();
					 var password = $('#txt_password').val();
					 var username = $('#txt_name').val();
					 $.post("/eventweb/QuanTri_SuaTin", {
						 txt_email: $.trim(email),
						 txt_question: $.trim(question),
						 txt_password: $.trim(password),
						 txt_username: $.trim(username)
						}, function(j){	 
							if(j=='success'){ 
								jAlert("Successfully.");
							}else{
								jAlert("Failure. Error:  " + j  ,"Alert");
							}
						});
				}
			  });
		});
	
</script>

<span ID="lblTK" style="font-weight: 700; color: blue; font-size: 16pt">Account
	Settings</span>
<br>
<br>

<table>
	<tr>
		<td>Username:</td>
		<td>&nbsp;</td>
		<td><input class="textbox" type="text" id="txt_name"
			name="txt_name" value="${tbl_qt.username}" /></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td>&nbsp;</td>
		<td><input class="textbox" type="password" name="txt_password"
			 id="txt_password" maxlength="10"  /></td>
	</tr>
	<tr>
		<td>Confirm Password:</td>
		<td>&nbsp;</td>
		<td><input class="textbox" type="password" name="txt_password_2"
			 id="txt_password_2" maxlength="10"  /></td>
	</tr>
	<tr>
		<td>Who was my first teacher?:</td>
		<td>&nbsp;</td>
		<td><input class="textbox" type="password" name="txt_question"
			value="${tbl_qt.question}" id="txt_question" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td>&nbsp;</td>
		<td><input class="textbox" type="text" name="txt_email"
			value="${tbl_qt.email_quantri}" id="txt_email"  /></td>
	</tr>
	<tr>
		<td></td>
		<td>&nbsp;</td>
		<td align="left"><div class="buttom"
				style="float: left !important;">
				<input type="button" name="btn_submit" value=" Save "
					id="btn_submit" class="button1"
					style="font-size: 12pt; width: 70px;" />
			</div></td>
	</tr>
</table>
