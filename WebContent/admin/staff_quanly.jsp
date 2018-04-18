<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/easyResponsiveTabs.js"></script>
<link rel="stylesheet" href="css/easy-responsive-tabs.css" type="text/css" /> 
<script type="text/javascript"	src="admin/template/alert/jquery.alerts.js"></script>
<link rel="stylesheet" href="admin/template/alert/jquery.alerts.css" type="text/css" />
<script type="text/javascript" src="js/jquery.pagination.js" ></script>
<link rel="stylesheet" href="css/pagination.css" type="text/css" />  
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css" type="text/css" />
<script src="js/ajax_truyen.js"></script>  

<style type="text/css">
div.error {
	color: #C00;
	font-size: 14px;
	position: relative;
}
</style>
<script type="text/javascript">
		var bandau = 0;
		function pageselectCallback(page_index, jq){  //hien so trang len
	
	      // Get number of elements per pagionation page from form
	      var items_per_page = 10;
	      var offset = page_index*items_per_page;                    
	     // var limit = Math.min((page_index+1) * items_per_page, bandau); //so dong
	      var limit = items_per_page;
	  	 //alert(page_index + '  ' + offset);
	      
	      $.post( "/eventweb/Staff_FindStaff", {
	      	action: 'list',
	      		id_project: $("#txt_project_select_find").val(),
	      	    txt_name: $("#txt_name").val(),
		      	offset: offset,
				limit: limit
	      }, function(newcontent){	 
	 	        $('#tbl_timkiem').html(newcontent);
	 	   	});        
	      // Prevent click eventpropagation
	      return false;
	  	}  

		function timkiemtheodieukien(){
		  // alert(ngay_bd);
		  
		$.post("/eventweb/Staff_FindStaff", {
				id_project: $("#txt_project_select_find").val(),
      	   		txt_name: $("#txt_name").val(),
		   action: 'getlength'
 	   	}, function(j){	    	  		
	  
 	        bandau = parseInt(j);
 	       	if(bandau<0) bandau=0;	  
			var optInit = {callback: pageselectCallback};

			optInit['items_per_page'] = 10;
			optInit['num_display_entries'] = 4;  //Number of pagination links shown
			optInit['num_edge_entries'] = 2;  //Number of start and end points
			optInit['prev_text'] = "Previous";
			optInit['next_text'] = "Next";
			optInit['ellipse_text'] = "...";
			
			$("#Pagination").pagination(bandau, optInit);
 	   	});
	}	

		$(document).ready(function(){
			var hien_an = true;
			$('input#btn_rename').hide();  
			$("#txt_rename").hide(); 
	
			//hide show reaname work plan
			$('#href_rename').click(function(){
		    		if(hien_an === true){
			    		$('#btn_rename').show();  
			        	$("#txt_rename").show();
			        	$('#href_remove').hide();
			        	hien_an = false;
			        	 $('#txt_workplan_select').attr('disabled', 'disabled');
		    		}else{
		    	    	$('#btn_rename').hide();  
		    	    	$("#txt_rename").hide();
		    	    	$('#href_remove').show();
		    			hien_an = true;
		    			  $('#txt_workplan_select').removeAttr("disabled");
		    		}
	    	}); 
			
			
			//rename 
			 $("#btn_rename").click(function(){
	        	var valid = true, errorMessage='';
	        	if($('#txt_rename').val().trim().length === 0 ) {
					errorMessage += 'You must rename it. \n';
					valid = false; 
				}
	
	        	if($('#txt_project_manager').val() == null ) {
					errorMessage += 'You must choose project. \n';
					valid = false; 
				}
	        	
	        	if(!valid && errorMessage.length>0){
					alert(errorMessage);
					e.preventDefault(); 
				}
	        	
	        	$.post("/eventweb/Staff_EditNameProject", {
	        		txt_rename: $("#txt_rename").val(),
	        		id_project: $('#txt_project_manager').val(),
			   	 	}, function(j){	 
			   	 		if(j=="success"){
			   	 			jAlert("Rename success!","Infomation");
			   	 			call_dropdownlist_project();
    	 				
			   	 			document.getElementById("txt_rename").value = "";
			   	 			
			  	    	}else{
			  	    		jAlert("Error: A project that name already exists. Please enter a different name.","Error");
			  	    		document.getElementById("txt_rename").value = "";
			  	    	}
			   		});
	   	 	}); 
			
			
			//create 
			 $("#btn_new_project").click(function(){
			    	var valid = true, errorMessage='';
		        	if($('#txt_new_project').val().trim().length === 0 ) {
						errorMessage += 'You must create a project. \n';
						valid = false; 
					} 
		        	
		         	if(!valid && errorMessage.length>0){
							alert(errorMessage);
							e.preventDefault(); 
					}
		         	
		         	$.post("/eventweb/Staff_CreateProject", {
		         		txt_new_project: $("#txt_new_project").val(),
		         		
				   	 	}, function(j){	 
				   	 		if(j=="success"){
			    	 			jAlert("Create success!","Infomation");
			    	 			call_dropdownlist_project();
			    	 		
			    	 			
				  	    	}else{
				  	    		jAlert("Error: A project that name already exists. Please enter a different name.","Error");
				  	    	}
				   		});
			    });
			
			//remove 
			  $('#href_remove').click(function(){
				 	var valid = true, errorMessage='';
					
		        	if($('#txt_project_manager').val() == null ) {
						errorMessage += 'You must choose project. \n';
						valid = false; 
					}
		        	
		        	if(!valid && errorMessage.length>0){
						alert(errorMessage);
						e.preventDefault(); 
		        	}
				 
		        	if (confirm("Are you sure?")) {
			        	$.post("/eventweb/Staff_DeleteProject", {
			        		id_project: $('#txt_project_manager').val(),
				   	 	}, function(j){	 
					   	 	if(j=="success"){
					   	 		jAlert("Delete succeeded!","Infomation");
					   	 		call_dropdownlist_project();
					   	 	}else{
					   	 		jAlert("Error:" + j, "Error");
					   	 	}
				   		 });
		            }
		        	
		        	e.preventDefault(); 
			 	}); 
			
			$('#horizontalTab').easyResponsiveTabs({
		        type: 'default', //Types: default, vertical, accordion           
		        width: 'auto', //auto or any width like 600px
		        fit: true,   // 100% fit in a container
		        closed: 'accordion', // Start closed if in accordion view
		        activate: function(event) { // Callback function if tab is switched
		            var $tab = $(this);
		            var $info = $('#tabInfo');
		            var $name = $('span', $info);
		
		            $name.text($tab.text());
		
		            $info.show();
		        }
		    });
	
		    $("#btn_submit").click(function(){
		    	  var valid = true, errorMessage="";	
		    	
		    	  if ($('#txt_workplan_select_find').val() === null) {//lay id
						errorMessage += " You must choose work plan .\n";
						valid = false;
				  }
		    	  
		    	  if (!valid && errorMessage.length > 0) {
						alert(errorMessage);
						e.preventDefault(); //the default action of the event will not be triggered.
				  }
		    	  
				  var opt = timkiemtheodieukien();
		          // Re-create pagination content with new parameters
		          $("#Pagination").pagination(bandau, opt);
	        });
	  
		    $("#btn_lamtuoi").click(function(){
			   $("#txt_name").val(''); 
			   
	        });

			$("#btn_themstaff").click(function(){
				  window.location.href='showOthers?action=themProjectStaff';
		    });
		});
	
		$(document).keypress(function(event){
		    var keycode = (event.keyCode ? event.keyCode : event.which);
		    if(keycode == '13'){
		        //alert('Bạn vừa nhấn phím "enter" trên trang web'); 
		        
		    	timkiemtheodieukien();
		    }
		});

	 	function call_dropdownlist_project(){
	 		var http = new getXMLHttpRequestObject();
	 			var url = "<c:out value="${pageContext.request.contextPath}"/>/admin/page_ajax/load_staff_project.jsp"; 
	 			http.open("GET", url, true);
	 			http.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 			http.timeout = 1000;
	 			http.onreadystatechange = function() {
	 				if (http.readyState == 4) {
	 					if (http.status == 200) {
	 						var temp= http.responseText;
   	 							document.getElementById("txt_project_manager").innerHTML = "";
 								document.getElementById("txt_project_manager").innerHTML += temp.trim();
 								
 								document.getElementById("txt_project_select_find").innerHTML = "";
 								document.getElementById("txt_project_select_find").innerHTML += temp.trim();	
	 					}
	 				}
	 			};
	 			http.send(null);
	 	}
  	 
</script>
<table style="width: 100%;">
	<tr>
		<td><span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Staff Management</span></td>
	</tr>
	<tr>
		<td style="width: 80px" align="right"></td>
		<td style="width: 80px" align="right"></td>
	</tr>
</table>	  

<div id="horizontalTab">
            <ul class="resp-tabs-list">
                <li>Project Manager</li>
                <li>Staff Manager</li>
            </ul>
            <div class="resp-tabs-container">
                <div>
                	 <p style="margin-left: 24px;" >Create new project: <input type="text" id="txt_new_project" name="txt_new_project" class="chon"> 
               	 <input type="button" name="btn_new_project" value=" Add Project " id="btn_new_project"
		   			class="button1"   style="  font-size: 12pt "/> </p>
                <br>                
                    <p  style="margin-left: 24px;"> Choose your project: 
                	 <select id="txt_project_manager" name="txt_project_manager" style="width: 200px; height: 24; margin-top: 0px;">
								<!-- <option value="0">--Your Workplan--</option> -->
							    <c:forEach var="thumuc" items="${list_pro_staff}">
										<option value="${thumuc.id_pro_nv}">${thumuc.name_pro}</option>
								</c:forEach> 
						</select>
						<a href="#" id="href_remove" title="Remove"><img src="admin/images/delete.png"/></a>
						<a href="#" id="href_rename" title="Rename"><img src="admin/images/rename.png"/></a> <input type="text" id="txt_rename" /> 
						<input type="button" name="btn_rename" value=" Ok " id="btn_rename"
							class="button1"   style="font-size: 12pt "/>
                	</p>
                </div>
                
             <div>
	<table cellpadding="4" cellspacing="0" width="100%"
		style="border: 1px solid">
		<tr>
			<td colspan="6"
				style="background: url(js/menu/menu_source/images/menu-bg.png) repeat; color: #FFF; font-weight: 700;">User Input</td>
		</tr>
		
		<tr>
				 <td width="17%" >
                    <p align="right">Choose your project:
                  </td>
                  <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                  <td width="83%">   
	                    <select	id="txt_project_select_find" name="txt_project_select_find" style="width: 200px; height: 24; margin-top: 0px;">
						  <c:forEach var="thumuc" items="${list_pro_staff}">
										<option value="${thumuc.id_pro_nv}">${thumuc.name_pro}</option>
								</c:forEach> 
						</select>
				  </td> 
		</tr>
		
		<tr>
			<td width="19%" align="right">Staff Name:</td>
			<td width="2%">&nbsp;</td>
			<td width="30%"><input type="text" id="txt_name"
				name="txt_name" style="width: 250px" /></td>
		</tr>
		<tr>
			<td width="19%"></td>
			<td width="2%">&nbsp;</td>
			<td width="28%" align="left"><div class="buttom"
					style="float: left !important;">
						<input type="button" name="btn_submit" value=" Search " id="btn_submit"
							class="button1"  style="  font-size: 12pt; width: 70px; margin-right: 50px;"/>
							
						<input type="button" name="btn_lamtuoi" value=" Reset " id="btn_lamtuoi"
							class="button1"   style="  font-size: 12pt;  " />	
				</div></td>
				
		</tr>
	</table>
<br>

<input type="button" name="btn_themstaff" value=" Add Staff Member" id="btn_themstaff"
							class="button1"   style="  font-size: 12pt "/>
<br><br/>

<table cellspacing="0" cellpadding="3" rules="cols" border="1" id="tbl_timkiem"
	style="color: Black; background-color: White; border-color: #999999; border-width: 1px; border-style: Solid; width: 100%; border-collapse: collapse;">
</table>
		 <div id="Pagination" class="pagination">
            </div>	
                
                </div>
             </div>
</div>




