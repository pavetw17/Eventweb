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

<script>
	$(function() {
		$("#dp_ngaybd").datepicker({
			numberOfMonths : 1,
			dateFormat : 'dd-mm-yy',
			changeMonth : true,
			changeYear : true,
			yearRange : '2012:2020',
		 	onSelect : function(selected) {
				$("#dp_ngayketthuc").datepicker("option", "minDate", selected);
			}
		
		}).attr('readonly', 'readonly').val("01-01-2014");
		
		$("#dp_ngayketthuc").datepicker({
			numberOfMonths : 1,
			dateFormat : 'dd-mm-yy',
			changeMonth : true,
			changeYear : true,
			yearRange : '2012:2020',
			onSelect : function(selected) {
			
				$("#dp_ngaybd").datepicker("option", "maxDate", selected);
			}
		}).attr('readonly', 'readonly').val($.datepicker.formatDate("dd-mm-yy", new Date()));
	});

</script>

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
	      
	      $.post( "/eventweb/Workplan_FindNews", {
	      	action: 'list',
	      		id_workplan: $("#txt_workplan_select_find").val(),
	      	    txt_tenbai: $("#txt_tenbai").val(),
			   	txt_ngaybd: $("#dp_ngaybd").val(),
			   	txt_ngayketthuc: $("#dp_ngayketthuc").val(),
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
		  
		$.post("/eventweb/Workplan_FindNews", {
				id_workplan: $("#txt_workplan_select_find").val(),
      	   		txt_tenbai: $("#txt_tenbai").val(),
			   	txt_ngaybd: $("#dp_ngaybd").val(),
			   	txt_ngayketthuc: $("#dp_ngayketthuc").val(),
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
			
			
			//rename work plan
			 $("#btn_rename").click(function(){
	        	var valid = true, errorMessage='';
	        	if($('#txt_rename').val().trim().length === 0 ) {
					errorMessage += 'You must rename it. \n';
					valid = false; 
				}
	
	        	if($('#txt_workplan_manager').val() == null ) {
					errorMessage += 'You must choose work plan. \n';
					valid = false; 
				}
	        	
	        	if(!valid && errorMessage.length>0){
					alert(errorMessage);
					e.preventDefault(); 
				}
	        	
	        	$.post("/eventweb/Workplan_EditNameWorkplan", {
	        		txt_rename: $("#txt_rename").val(),
	        		id_workplan: $('#txt_workplan_manager').val(),
			   	 	}, function(j){	 
			   	 		if(j=="success"){
			   	 			jAlert("Rename success!","Infomation");
			   	 			call_dropdownlist_workplan(document.getElementById('txt_year_manager').value,2);
    	 				
			   	 			call_dropdownlist_workplan(document.getElementById('txt_year_select').value,1);
			   	 			document.getElementById("txt_rename").value = "";
			   	 			
			  	    	}else{
			  	    		jAlert("Error: A work plan that name already exists. Please enter a different name.","Error");
			  	    		document.getElementById("txt_rename").value = "";
			  	    	}
			   		});
	   	 	}); 
			
			
			//create a new worplan
			 $("#btn_new_workplan").click(function(){
			    	var valid = true, errorMessage='';
		        	if($('#txt_new_workplan').val().trim().length === 0 ) {
						errorMessage += 'You must create a workplan. \n';
						valid = false; 
					} 
		        	
		         	if(!valid && errorMessage.length>0){
							alert(errorMessage);
							e.preventDefault(); 
					}
		         	
		         	$.post("/eventweb/Workplan_CreateWorkplan", {
		         		txt_new_workplan: $("#txt_new_workplan").val(),
		         		
				   	 	}, function(j){	 
				   	 		if(j=="success"){
			    	 			jAlert("Create success!","Infomation");
			    	 			//form workplan
			    	 		// 	$("#txt_year_manager").val('0');  
			    	 		//	$("#txt_workplan_manager").text('');  
			    	 			call_dropdownlist_workplan(document.getElementById('txt_year_manager').value,2);
			    	 		
			    	 			//form find content
			    	 		//	$("#txt_year_select").val('0'); 
			    	 		//	$("#txt_workplan_select_find").text('');
			    	 			call_dropdownlist_workplan(document.getElementById('txt_year_select').value,1);
			    	 			
				  	    	}else{
				  	    		jAlert("Error: A work plan that name already exists. Please enter a different name.","Error");
				  	    	}
				   		});
			    });
			
			//remove news of work plan
			  $('#href_remove').click(function(){
				 	var valid = true, errorMessage='';
					
		        	if($('#txt_workplan_manager').val() == null ) {
						errorMessage += 'You must choose work plan. \n';
						valid = false; 
					}
		        	
		        	if(!valid && errorMessage.length>0){
						alert(errorMessage);
						e.preventDefault(); 
		        	}
				 
				 
		        	if (confirm("Are you sure?")) {
			        	$.post("/eventweb/Workplan_DeleteWorkplan", {
			        		id_workplan: $('#txt_workplan_manager').val(),
				   	 	}, function(j){	 
					   	 	if(j=="success"){
					   	 		jAlert("Delete succeeded!","Infomation");
					   	 		call_dropdownlist_workplan(document.getElementById('txt_year_manager').value,2);
		    	 				
					   	 		call_dropdownlist_workplan(document.getElementById('txt_year_select').value,1);
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
			   $("#txt_tenbai").val(''); 
			   $("#dp_ngaybd").val('');
			   $("#dp_ngayketthuc").val(''); 
			   
	        });

			$("#btn_themtin").click(function(){
				  window.location.href='showTinTuc?action=themWorkplan';
		    });
			
			$('#txt_year_select').change(function(){
				call_dropdownlist_workplan(document.getElementById('txt_year_select').value,1);
			});
			
			$('#txt_year_manager').change(function(){
				call_dropdownlist_workplan(document.getElementById('txt_year_manager').value,2);
			});
	  
						
});
	
		$(document).keypress(function(event){
		    var keycode = (event.keyCode ? event.keyCode : event.which);
		    if(keycode == '13'){
		        //alert('Bạn vừa nhấn phím "enter" trên trang web'); 
		        
		        var valid = true, errorMessage='';
		        
		    	if($('#txt_workplan_select_find').val() == null ) {
					errorMessage += 'You must choose work plan. \n';
					valid = false; 
				}
	        	
	        	if(!valid && errorMessage.length>0){
					alert(errorMessage);
					e.preventDefault(); 
				}
		        
		    	timkiemtheodieukien();
		    }
		});

	 	function call_dropdownlist_workplan(year,option_dd){
	 		var http = new getXMLHttpRequestObject();
	 			var url = "<c:out value="${pageContext.request.contextPath}"/>/admin/page_ajax/load_workplan_accordingtoYear.jsp?year=" 
	 							+ year;
	 			http.open("GET", url, true);
	 			http.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	 			http.timeout = 1000;
	 			http.onreadystatechange = function() {
	 				if (http.readyState == 4) {
	 					if (http.status == 200) {
	 						var temp= http.responseText;
	 						if(option_dd == 1){
   	 							document.getElementById("txt_workplan_select_find").innerHTML = "";
 								document.getElementById("txt_workplan_select_find").innerHTML += temp.trim();
	 						}else{
	 							document.getElementById("txt_workplan_manager").innerHTML = "";
 								document.getElementById("txt_workplan_manager").innerHTML += temp.trim();
	 						}
	 					}
	 				}
	 			};
	 			http.send(null);
	 	}
  	 
</script>
<table style="width: 100%;">
	<tr>
		<td><span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Work plans Management</span></td>
	</tr>
	<tr>
		<td style="width: 80px" align="right"></td>
		<td style="width: 80px" align="right"></td>
	</tr>
</table>	  

<div id="horizontalTab">
            <ul class="resp-tabs-list">
                <li>Work plans Manager</li>
                <li>Contents Manager</li>
            </ul>
            <div class="resp-tabs-container">
                <div>
                	 <p style="margin-left: 24px;" >Create new workplan: <input type="text" id="txt_new_workplan" name="txt_new_workplan" class="chon"> 
               	 <input type="button" name="btn_new_workplan" value=" Add Workplan " id="btn_new_workplan"
		   			class="button1"   style="  font-size: 12pt "/> </p>
                <br><br>
                <p style="margin-left: 24px;">Choose your year:
                  <select
								id="txt_year_manager" name="txt_year_manager" style="width: 200px; height: 24; margin-top: 0px;">
								<option value="0">--Your Year--</option>
								<c:forEach var="year" items="${get_list_year}">
										<option value="${year}">${year}</option>
								</c:forEach>
						</select>
                </p>
                 
                
                    <p  style="margin-left: 24px;"> Choose your work plan: 
                	 <select id="txt_workplan_manager" name="txt_workplan_manager" style="width: 200px; height: 24; margin-top: 0px;">
								<!-- <option value="0">--Your Workplan--</option> -->
								<%-- <c:forEach var="thumuc" items="${requestScope.list_workplan}">
										<option value="${thumuc.id_workplan}">${thumuc.name_parent}</option>
								</c:forEach> --%>
						</select>
						<a href="#" id="href_remove" title="Remove"><img src="admin/images/delete.png"/></a>
						<a href="#" id="href_rename" title="Rename"><img src="admin/images/rename.png"/></a> <input type="text" id="txt_rename" /> 
						<input type="button" name="btn_rename" value=" Ok " id="btn_rename"
							class="button1"   style="font-size: 12pt "/>
                	</p>
                </div>
                
             <div>
                <form >
	<table cellpadding="4" cellspacing="0" width="100%"
		style="border: 1px solid">
		<tr>
			<td colspan="6"
				style="background: url(js/menu/menu_source/images/menu-bg.png) repeat; color: #FFF; font-weight: 700;">User Input</td>
		</tr>
		
		<tr>
				 <td width="17%" >
                    <p align="right">Choose your year:
                  </td>
                  <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                  <td width="83%">   
	                    <select
								id="txt_year_select" name="txt_year_select" style="width: 200px; height: 24; margin-top: 0px;">
								<option value="0">--Your Year--</option>
								<c:forEach var="year" items="${get_list_year}">
										<option value="${year}">${year}</option>
								</c:forEach>
						</select>
				  </td> 
		</tr>
		
		<tr>
				 <td width="17%" >
                    <p align="right">Choose your workplan:
                  </td>
                  <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                  <td width="83%">   
	                    <select	id="txt_workplan_select_find" name="txt_workplan_select_find" style="width: 200px; height: 24; margin-top: 0px;">
								<!-- <option value="0">--Your Workplan--</option> -->
								<%-- <c:forEach var="thumuc" items="${requestScope.list_workplan}">
										<option value="${thumuc.id_workplan}">${thumuc.name_parent}</option>
								</c:forEach> --%>
						</select>
				  </td> 
		</tr>
		
		<tr>
			<td width="19%" align="right">Contents:</td>
			<td width="2%">&nbsp;</td>
			<td width="30%"><input type="text" id="txt_tenbai"
				name="txt_tenbai" style="width: 250px" /></td>
		</tr>
		<tr>
			<td width="19%" align="right">Start date:</td>
			<td width="2%">&nbsp;</td>
			<td width="28%"><input type="text" id="dp_ngaybd"
				name="txt_ngaybd" /></td>
		</tr>
		<tr>
			<td width="19%" align="right">End date:</td>
			<td width="2%">&nbsp;</td>
			<td width="28%"><input type="text" id="dp_ngayketthuc"
				name="txt_ngayketthuc" /></td>
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
</form>
<br>

<input type="button" name="btn_themtin" value=" Add Your Workplan " id="btn_themtin"
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




