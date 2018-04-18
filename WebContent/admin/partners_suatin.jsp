<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			style="font-weight: 700; color: blue; font-size: 16pt">Edit Partners </span>
<!-- Highlights_SuaTin -->
<form action="Partners_Edit?action=ghivaoCSDL"  id="bangnhap" method="POST" enctype="multipart/form-data">

<table style="width: 100%;">
	<tr>
                <td width="17%" >
                    <p align="right">Name partner:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000">*</font></td>
                <td width="83%" >
               <textarea id="txt_name" name="txt_name" style=" width: 400px;  " >${tb_partners.name_partners}</textarea>
    </tr>
     <tr>	  
                <td width="17%"  align="right">Image:</td>
                <td width="1%"  align="right"><font color="#FF0000"></font></td>
                <td width="83%" >
                
                
               <%-- <c:choose>
					<c:when test="${not empty tb_news.photo}">
						<input type ="image" src="${tb_news.photo}" id="image_cu" style=" width: 100px; height: 100px">
					</c:when>
				</c:choose> --%>
                
                <c:if test="${not empty tb_partners.link_logo}">
  					<input type ="image" disabled src="${tb_partners.link_logo}" id="image_cu" name="image_cu" style=" width: 100px; height: 100px">
				</c:if>
                
               		<input type="hidden" id="getid_tintuc" name="getid_tintuc" value="${tb_partners.id_partners}">
                    <input type="hidden" id="txt_hinhanh_cu" name="txt_hinhanh_cu" value="${tb_partners.link_logo}" style="display: none" ></input>
                    <INPUT TYPE="file" NAME="txt_hinhanh" id="txt_hinhanh" CLASS="textbox" size="20"  accept="image/x-png, image/gif, image/jpeg"  ></td>
            </tr>
    
</table>
<br>

	<textarea name="div_text" id="div_text" style="margin-bottom: 10px; width: 1000px; height: 119px;">${tb_partners.introduce}</textarea>
	<br>
	<input type="submit" name="btn_ghiCSDL" value="Save" id="btn_ghiCSDL"
							class="button1"   style="font-size: 12pt; width: 105px; margin-right: 190px; "/>
	
	<input type="button" onclick="history.back();" name="btn_quaylai" value="Cancel" id="btn_quaylai"
							class="button1"   style="font-size: 12pt; width: 105px "/>
</form>