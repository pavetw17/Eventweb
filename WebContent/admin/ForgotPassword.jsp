<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"	src="../admin/template/alert/jquery.alerts.js"></script>
<link rel="stylesheet" href="../admin/template/alert/jquery.alerts.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/style_layout_admin.css" />	


<script  type="text/javascript">

	function validateEmail(email) { 
	  // http://stackoverflow.com/a/46181/11236
	    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(email);
	}

	function kiemtra(){
		var valid = true, errorMessage="";;
		if ($('#txt_email').val() == "") {
			errorMessage += "Email is required.\n";
			valid = false;
		} else {
			if (!validateEmail($('#txt_email').val())) {
				errorMessage += "Email is not valid.\n";
				valid = false;
			}
		}
		
		if ($('#txt_teacher').val() == "") {
			errorMessage += "Question is required.\n";
			valid = false;
		}
		
		if(!valid && errorMessage.length>0){
			alert(errorMessage);
		}
	    return valid;
	}
	
	function SendMail(){
		 var email =  $('#txt_email').val();
		 var teacher =  $('#txt_teacher').val();
		 
		 $.post("/eventweb/MailClient", {
			
			 txt_email: $.trim(email),
			 txt_teacher: $.trim(teacher)
			}, function(j){	 
				if(j=='success'){ 
					jAlert("Email has been sent successfully.");
					setTimeout("location.href = '<%=request.getContextPath()%>/admin';",1000);
				}else{
					jAlert("Failure. Error:  " + j  ,"Alert");
				}
			});
	}
	
	$(document).ready(function(){
		  $("#btn_submit").click(function(){
			  if(kiemtra()){
				  SendMail();
			  }
	      });
		
	});
	
	$(document).keypress(function(event){
	    var keycode = (event.keyCode ? event.keyCode : event.which);
	    if(keycode == '13'){
	    	if(kiemtra()){
	    		 SendMail();
			}
	    }
	});

</script>


<body>
<div id="MainForgotPass"> 
	<div id="divtrong">
		<b>Can't sign in? Forgot your password?</b>
		<br>
		Enter your email address below and we'll send you password reset instructions.
		
		<br><br>
		<b>Who was my first teacher?</b>
		<br>
		<input class="textbox"  name="txt_teacher" id="txt_teacher" />
		<br><br>
		<b>Enter your email address</b>
		<br>
		<input class="textbox"  name="txt_email" id="txt_email" />
		<input type="button" name="btn_submit" id="btn_submit" class="myButton" value="Send Me" />
	</div>
</div>
</body>

