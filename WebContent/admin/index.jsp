<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="../css/style_layout_admin.css" />
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<script type="text/javascript">
function kiemtra_dangnhap() {
		var valid = true, errorMessage="";;
		if ($('#txt_username').val() == "") {
			errorMessage += "Username field is required.\n";
			valid = false;
		} 
		
		if ($('#txt_password').val() == "") {
			errorMessage += "Password field is required.\n";
			valid = false;
		} 
		
		if(!valid && errorMessage.length>0){
			alert(errorMessage);
		}
	    return valid;

	}
</script>
</head>
<body>
	<form method="post" action="../DangNhap" onsubmit="return kiemtra_dangnhap();">
		<div id="background">
			<div class="header">
				<p class="nCompany"></p>
					<%
						if (request.getParameter("kq") != null
								&& request.getParameter("kq") != "") {
					%>
				<p class="iCompany">Sorry, unrecognized username or password!</p>
					<%
						}
					%>
			</div>
			
			<div id="MainBG"></div>
			<div id="BottonDivider"></div>
			<div id="TitleBG"></div>
			<div id="Username">Username</div>
			<div id="Usernameinput">
				<input class="textbox" type="text" name="txt_username" value="admin"
					id="txt_username" />
			</div>
			<div id="Password">Password</div>
			<div id="Passwordinput">
				<input class="textbox" type="password" name="txt_password" value="admin"
					id="txt_password" />
			</div>
			<div id="ForgotPass"> <a href="ForgotPassword.jsp"> Forgot Password? </a></div>
			<div id="Shadow"></div>
			<div id="MemberLogin"></div>
			<div id="Enteryourusernameand"></div>
			<div id="LockIcon"></div>
			<div id="Loginbutton">
				<input type="submit" name="btn_login" id="btn_login"
					class="myButton" value="Login" />
			</div>
			<div id="Groupbg2"></div>
			<div id="Groupbg1"></div>
			<div id="GroupIcon"></div>
			</div>
	</form>
</body>
</html>