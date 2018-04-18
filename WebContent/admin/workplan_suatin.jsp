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
		
 		    if($('#txt_workplan_select').val() == null ) {
				errorMessage += 'You must choose work plan. \n';
				valid = false; 
			}
 		    
 		    if ($('#txt_contents').val().length == 0  || $('#txt_contents').val().length > 200 || $('#txt_contents').val().length < 100 ) {//lay id
				errorMessage += "The contents must be greater than 100 and not exceed 200 characters.\n";
				valid = false;
			}
	
			if ($('#txt_objectives').val() === '' || $('#txt_objectives').val().length > 200 || $('#txt_objectives').val().length < 100) {//lay id
				errorMessage += "The objectives must be greater than 100 and not exceed 200 characters.\n";
				valid = false;
			} 
			
			if ($('#txt_status').val() === '' || $('#txt_status').val().length > 50 || $('#txt_status').val().length < 10) {//lay id
				errorMessage += "The status must be greater than 10 and not exceed 50 characters.\n";
				valid = false;
			} 
			
		    var CurrentDay =new Date( $.datepicker.formatDate('mm-dd-yy', new Date()));
		    var SelectDay = new Date ( $('#dp_ngaybd').val().replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
		    
		    if( SelectDay < CurrentDay )
		    {
		    	errorMessage +="The selected date must be greater than or equal to current date.\n";
		    	valid = false;		    	
		    } 
			      
			if (!valid && errorMessage.length > 0) {
				alert(errorMessage);
				e.preventDefault(); //the default action of the event will not be triggered.
			}
		 }); 

	    $(document).ready(function () {
	    
		$(function() {
				$("#dp_ngaybd").datepicker({
					numberOfMonths : 1,
					dateFormat : 'dd-mm-yy',
					changeMonth : true,
					changeYear : true,
					yearRange : '2014:2024',
				 	onSelect : function(selected) {
						$("#dp_ngayketthuc").datepicker("option", "minDate", selected);
					}
				
				}).attr('readonly', 'readonly');
				
				$("#dp_ngayketthuc").datepicker({
					numberOfMonths : 1,
					dateFormat : 'dd-mm-yy',
					changeMonth : true,
					changeYear : true,
					yearRange : '2014:2024',
					onSelect : function(selected) {
					
						$("#dp_ngaybd").datepicker("option", "maxDate", selected);
					}
				}).attr('readonly', 'readonly');
			});
		
	 });
</script>

<span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Edit Page Work plan</span>
			
<!-- Workplan_SuaTin  -->
<form action="Workplan_EditNews?action=ghivaoCSDL"  id="bangnhap" method="POST" enctype="multipart/form-data">

<table style="width: 100%;">
<tr>
			<tr>
				 <td width="17%" >
                    <p align="right">Choose your workplan:
                  </td>
                  <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                  <td width="83%">   
	                    <select
								id="txt_workplan_select" name="txt_workplan_select" style="width: 200px; height: 24; margin-top: 0px;">
								<!-- <option value="0">--Your Workplan--</option> -->
								<c:forEach var="thumuc" items="${list_dropdown}">
										<option value="${thumuc.id_workplan}"  ${tbl_wp.id_workplan == thumuc.id_workplan ? 'selected' : ''} >${thumuc.name_parent}</option>
								</c:forEach>
						</select>
				  </td> 
			</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Contents:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_contents"  name="txt_contents" style=" width: 400px;">${tbl_workplan.content}</textarea>
                </td>
    		</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Objectives:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_objectives" name="txt_objectives" style=" width: 400px;">${tbl_workplan.objectives}</textarea>
                 </td>
    		</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Status:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_status"  name="txt_status" style=" width: 400px;">${tbl_workplan.status}</textarea>
                </td>
    		</tr>
    		<tr>	  
                <td width="17%"  align="right">Time begin:</td> 
                <td width="1%"  align="right"><font color="#FF0000"></font></td>
                <td width="83%" >
                   <input type="text" id="dp_ngaybd" name="dp_ngaybd" value="${time_begin}"/>
                  </td>
            </tr>
    		<tr>	  
                <td width="17%"  align="right">Time end:</td> 
                <td width="1%"  align="right"><font color="#FF0000"></font></td>
                <td width="83%" >
                   <input type="text" id="dp_ngayketthuc" name="dp_ngayketthuc" value="${time_end}"/>
                  </td>
            </tr>
            
</table>
<br>
    <input type="hidden" id="get_id_workplan" name="get_id_workplan" value="${tbl_workplan.id_workplan}">
	<input type="submit" name="btn_ghiCSDL" value="Save" id="btn_ghiCSDL"
							class="button1"   style="font-size: 12pt; width: 105px; margin-right: 190px; "/>
	<a href="showTinTuc?action=workplan#horizontalTab2">
	<input type="button" name="btn_quaylai" value="Cancel" id="btn_quaylai"
							class="button1"   style="font-size: 12pt; width: 105px "/>
	</a>
</form>