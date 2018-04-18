<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type='text/javascript' src='js/jquery-1.10.2.js'></script>

<script type="text/javascript">
	    $(document).on('submit', '#bangnhap', function(e) { 
	
	 		    var valid = true, errorMessage="";
				var fileName = $('#txt_hinhanh').val();
				
				if(fileName !== ''){
					if(!validate_fileupload(fileName)){
		 		    	errorMessage += "Invalid image file\n\nYou may only upload files with the following extensions: JPG, PNG, GIF ";
		 		    	valid = false;
					}
				}
	 		    
				if ($('#txt_name').val() === '' || $('#txt_name').val().length > 50) {//lay id
					errorMessage += "The name partner must be less than 50 characters.\n";
					valid = false;
				}
		
				if ($('#div_text').val() === '' || $('#div_text').val().length < 200) {//lay id
					errorMessage += "The introduce must be greater than 200 characters.\n";
					valid = false;
				}
				
				if (!valid && errorMessage.length > 0) {
					alert(errorMessage);
					e.preventDefault(); //the default action of the event will not be triggered.
				}
				
		 }); 

	/* $(document).ready(function(){ 
		 $('#bangnhap').submit(function(e){
		 });
	}); */
	
	function validate_fileupload(fileName)
	{
	    var allowed_extensions = new Array("jpg","png","gif","jpeg");
	    var file_extension = fileName.split('.').pop().toLowerCase(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

	    for(var i = 0; i <= allowed_extensions.length; i++)
	    {
	        if(allowed_extensions[i]==file_extension)
	        {
	            return true; // valid file extension
	        }
	    }
	    return false;
	}
</script>

<span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Add Partner</span>
<!-- Partners_Add -->
<form action="Partners_Add"  id="bangnhap" method="POST" enctype="multipart/form-data">

<table style="width: 100%;">
	<tr>
                <td width="17%" >
                    <p align="right">Name partner:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000">*</font></td>
                <td width="83%" >
               <textarea id="txt_name" name="txt_name" style=" width: 400px; " ></textarea>
    </tr>
     <tr>	  
                <td width="17%"  align="right">Image:</td>
                <td width="1%"  align="right"><font color="#FF0000"></font></td>
                <td width="83%" >
                    <INPUT TYPE="file" NAME="txt_hinhanh" id="txt_hinhanh" CLASS="textbox" size="20"  accept="image/x-png, image/gif, image/jpeg" ></td>
            </tr>
    
</table>
<br>

	<textarea name="div_text" id="div_text" style="margin-bottom: 10px; width: 1000px; height: 119px;"> </textarea>
	<br>
	<input type="submit" name="btn_ghiCSDL" value="Save" id="btn_ghiCSDL"
							class="button1"   style="font-size: 12pt; width: 105px; margin-right: 190px; "/>
	
	<input type="button" onclick="history.back();"	 name="btn_quaylai" value="Cancel" id="btn_quaylai"
							class="button1"   style="font-size: 12pt; width: 105px "/>
</form>