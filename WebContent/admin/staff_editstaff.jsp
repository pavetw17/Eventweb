<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"	src="admin/template/alert/jquery.alerts.js"></script>
<link rel="stylesheet" href="admin/template/alert/jquery.alerts.css" type="text/css" />
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css" type="text/css" />
<script src="js/ajax_truyen.js"></script>  
<script type="text/javascript">

	    $(document).on('submit', '#bangnhap', function(e) { 
 		    var valid = true, errorMessage="";
		
 		    if ($('#txt_name').val().length == 0  || $('#txt_name').val().length > 40 ) {//lay id
				errorMessage += "The name must not exceed 40 characters.\n";
				valid = false;
			}
	
			if ($('#txt_job').val() === '' || $('#txt_job').val().length > 80) {//lay id
				errorMessage += "The job must not exceed 80 characters.\n";
				valid = false;
			} 
			
			if ($('#txt_task').val() === '' || $('#txt_task').val().length > 300 || $('#txt_task').val().length < 50) {//lay id
				errorMessage += "The task must be greater than 50 and not exceed 300 characters.\n";
				valid = false;
			}
			
			var fileName = $('#txt_hinhanh').val();
			
			if(fileName !== ''){
				if(!validate_fileupload(fileName)){
	 		    	errorMessage += "Invalid image file\n\nYou may only upload files with the following extensions: JPG, PNG, GIF ";
	 		    	valid = false;
				}
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
			style="font-weight: 700; color: blue; font-size: 16pt">Add Work plan </span>
			
<!-- Staff_ThemStaff -->
<form action="Staff_EditStaff?action=ghivaoCSDL"  id="bangnhap" method="POST" enctype="multipart/form-data">

<table style="width: 100%;">
			
			<tr>
				 <td width="17%" >
                    <p align="right">Choose your project:
                  </td>
                  <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                  <td width="83%">   
	                    <select
								id="txt_project_select" name="txt_project_select" style="width: 200px; height: 24; margin-top: 0px;">
								<c:forEach var="thumuc" items="${list_pro_staff}">
										<option value="${thumuc.id_pro_nv}" ${tbl_staff.id_pro_nv == thumuc.id_pro_nv ? 'selected' : ''}>${thumuc.name_pro}</option>
								</c:forEach> 
						</select>
				  </td> 
			</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Name staff:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_name"  name="txt_name" style=" width: 400px; height: 20px; " >${tbl_staff.name_nv}</textarea>
                </td>
    		</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Image:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                      <c:if test="${not empty tbl_staff.photo_nv}">
  							<input type ="image" disabled src="${tbl_staff.photo_nv}" id="image_cu" name="image_cu" style=" width: 100px; height: 100px">
					</c:if>
					<input type="hidden" id="getid_nv" name="getid_nv" value="${tbl_staff.id_nv}">
                    <input type="hidden" id="txt_hinhanh_cu" name="txt_hinhanh_cu" value="${tbl_staff.photo_nv}" style="display: none" ></input>
                    <INPUT TYPE="file" NAME="txt_hinhanh" id="txt_hinhanh" CLASS="textbox" size="20"  accept="image/x-png, image/gif, image/jpeg"  >
                </td>
    		</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Job:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_job" name="txt_job" style=" width: 400px; height: 20px; " >${tbl_staff.job_nv}</textarea>
                 </td>
    		</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Task:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_task"  name="txt_task" style=" width: 400px; height: 45px; " >${tbl_staff.task_nv}</textarea>
                </td>
    		</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Email:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_email"  name="txt_email" style=" width: 400px; height: 20px; " >${tbl_staff.email_nv}</textarea>
                </td>
    		</tr>
            
</table>
<br>

	<input type="submit" name="btn_ghiCSDL" value="Save" id="btn_ghiCSDL"
							class="button1"   style="font-size: 12pt; width: 105px; margin-right: 190px; "/>
	<a href="showOthers?action=ourstaff#horizontalTab2">
	<input type="button" name="btn_quaylai" value="Cancel" id="btn_quaylai"
							class="button1"   style="font-size: 12pt; width: 105px "/>
	</a>
</form>