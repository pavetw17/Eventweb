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
<script src="js/ajax_truyen.js"></script>  	
<script type="text/javascript">
			var bandau = 0;
			function pageselectCallback(page_index, jq){  //hien so trang len
			
			 // Get number of elements per pagionation page from form
			 var items_per_page = 16;
			 var offset = page_index*items_per_page;                    
			 // var limit = Math.min((page_index+1) * items_per_page, bandau); //so dong
			 var limit = items_per_page;
			 //alert(page_index + '  ' + offset);
			  
			 $.post( "/eventweb/showGallery_LayAnhTrongAlbum", {
			  	action: 'list',
			  	txt_ddl_folder: $("#ddl_folder").val(),
			  	offset: offset,
				limit: limit
			  }, function(newcontent){	 
				        $('#tbl_hinhanh').html(newcontent);
				   	});        
			  // Prevent click eventpropagation
			  return false;
			}  
			
			function timkiemtheodieukien(){
				$.post("/eventweb/showGallery_LayAnhTrongAlbum", {
					txt_ddl_folder: $("#ddl_folder").val(),
	        		action: 'getlength'
				   	}, function(j){	    	  		
			  
				    bandau = parseInt(j);
				   	if(bandau<0) bandau=0;	  
					var optInit = {callback: pageselectCallback};
			
					optInit['items_per_page'] = 16;
					optInit['num_display_entries'] = 4;  //Number of pagination links shown
					optInit['num_edge_entries'] = 2;  //Number of start and end points
					optInit['prev_text'] = "Previous";
					optInit['next_text'] = "Next";
					optInit['ellipse_text'] = "...";
					
					$("#Pagination").pagination(bandau, optInit);
				   	});
			 }
 
 
 
 
    $(document).ready(function () {
    	
    	
    /*---------------  Album Manager Start  --------------*/	
    	 var hien_an = true;
    	 $('input#btn_rename').hide();  
    	 $("#txt_rename").hide(); 
    
    	 $('#href_rename').click(function(){
	    		if(hien_an === true){
		    		$('input#btn_rename').show();  
		        	$("#txt_rename").show();
		        	$('#href_remove').hide();
		        	hien_an = false;
		        	 $('select[name="ddl_folder"]').attr('disabled', 'disabled');
	    		}else{
	    	    	$('input#btn_rename').hide();  
	    	    	$("#txt_rename").hide(); 
	    	    	$('#href_remove').show();
	    			hien_an = true;
	    			  $('select[name="ddl_folder"]').removeAttr("disabled");
	    		}
    	 });
    	
	   	 $("#btn_rename").click(function(){
	        	var valid = true, errorMessage='';
	        	if($('#txt_rename').val().trim().length === 0 ) {
					errorMessage += 'You must rename it. \n';
					valid = false; 
				}
	
	        	if($('#ddl_folder').val() == 0 ) {
					errorMessage += 'You must choose album. \n';
					valid = false; 
				}
	        	
	        	if(!valid && errorMessage.length>0){
					alert(errorMessage);
					e.preventDefault(); 
				}
	        	
	        	$.post("/eventweb/showGallery_SuaTenAlbum", {
	        		txt_rename: $("#txt_rename").val(),
	        		id_thumuc: $('#ddl_folder').val(),
			   	 	}, function(j){	 
			   	 		if(j=="success"){
			   	 			jAlert("Rename success!","Infomation");
			   	 			
			   	 			var http = new getXMLHttpRequestObject();
			   	 			var url = "<c:out value="${pageContext.request.contextPath}"/>/admin/page_ajax/load_album.jsp";
			   	 			http.open("GET", url, true);
			   	 			http.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			   	 			http.timeout = 1000;
			   	 			http.onreadystatechange = function() {
			   	 				if (http.readyState == 4) {
			   	 					if (http.status == 200) {
			   	 						document.getElementById("ddl_folder").innerHTML = "<option value='0'>---Your Album---</option>";
			   	 						var temp= http.responseText;
			   	 						document.getElementById("ddl_folder").innerHTML += temp.trim();
			   	 						
				   	 					document.getElementById("txt_foldername_select").innerHTML = "<option value='0'>---Your Album---</option>";
				 						document.getElementById("txt_foldername_select").innerHTML += temp.trim();
				 						document.getElementById("txt_rename").value = "";
			   	 					}
			   	 				}
			   	 			};
			   	 			http.send(null);
			   	 			
			  	    	}else{
			  	    		jAlert("Error: A album that name already exists. Please enter a different name.","Error");
			  	    		document.getElementById("txt_rename").value = "";
			  	    	}
			   		});
	   	 });
    	 
	   	$('#href_remove').click(function(){
	   		var valid = true, errorMessage='';

        	if($('#ddl_folder').val() == 0 ) {
				errorMessage += 'You must choose album. \n';
				valid = false; 
			}
        	
        	if(!valid && errorMessage.length>0){
				alert(errorMessage);
				e.preventDefault(); 
        	}
        	
        	if (confirm("Are you sure?")) {
	        	$.post("/eventweb/showGallery_XoaAlbum", {
	        		id_thumuc: $('#ddl_folder').val(),
		   	 	}, function(j){	 
			   	 	if(j=="success"){
			   	 		jAlert("Delete succeeded","Infomation");
			   	 	var http = new getXMLHttpRequestObject();
	   	 			var url = "<c:out value="${pageContext.request.contextPath}"/>/admin/page_ajax/load_album.jsp";
	   	 			http.open("GET", url, true);
	   	 			http.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	   	 			http.timeout = 1000;
	   	 			http.onreadystatechange = function() {
	   	 				if (http.readyState == 4) {
	   	 					if (http.status == 200) {
	   	 						document.getElementById("ddl_folder").innerHTML = "<option value='0'>---Your Album---</option>";
	   	 						var temp= http.responseText;
	   	 						document.getElementById("ddl_folder").innerHTML += temp.trim();
	   	 						
		   	 					document.getElementById("txt_foldername_select").innerHTML = "<option value='0'>---Your Album---</option>";
		 						document.getElementById("txt_foldername_select").innerHTML += temp.trim();
	   	 						
	   	 					}
	   	 				}
	   	 			};
	   	 			http.send(null);
			   	 	}else{
			   	 		jAlert("Error:" + j, "Error");
			   	 	}
		   		 });
            }
        	
        	e.preventDefault(); 
	 	});
	   	 
	   	 
	   	 $('select[name="ddl_folder"]').change(function(){
	   		 if($('#ddl_folder').val() != 0){
	   		 var opt = timkiemtheodieukien();
	          // Re-create pagination content with new parameters
	          $("#Pagination").pagination(bandau, opt);
	   		 }
	   	 });
    	
	   	 
    	
    /*---------------  Album Manager End --------------*/
       
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
        
   
    /*-------------------------  File Up Manager Start  ------------------------*/
        function validate_fileupload(fileName)
    	{
    	    var allowed_extensions = new Array("jpg","png","gif");
    	    var file_extension = fileName.split('.').pop().toLowerCase();; // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

    	    for(var i = 0; i <= allowed_extensions.length; i++)
    	    {
    	        if(allowed_extensions[i]==file_extension)
    	        {
    	            return true; // valid file extension
    	        }
    	    }
    	    return false;
    	}
        
        $("#btn_tao_album").click(function(){
        	var valid = true, errorMessage='';
        	if($('#txt_foldername').val().trim().length === 0 ) {
				errorMessage += 'You must create an album. \n';
				valid = false; 
			} 
        	
         	if(!valid && errorMessage.length>0){
					alert(errorMessage);
					e.preventDefault(); 
			}
  	
    		$.post("/eventweb/showGallery_ThemAlbum", {
    			txt_foldername: $("#txt_foldername").val(),
    	 	}, function(j){	    
    	 		if(j=="success"){
    	 			jAlert("Create success!","Infomation");
    	 			
    	 			var http = new getXMLHttpRequestObject();
    	 			var url = "<c:out value="${pageContext.request.contextPath}"/>/admin/page_ajax/load_album.jsp";
    	 			http.open("GET", url, true);
    	 			http.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    	 			http.timeout = 1000;
    	 			http.onreadystatechange = function() {
    	 				if (http.readyState == 4) {
    	 					if (http.status == 200) {
    	 						document.getElementById("txt_foldername_select").innerHTML = "<option value='0'>---Your Album---</option>";
    	 						var temp= http.responseText;
    	 						document.getElementById("txt_foldername_select").innerHTML += temp.trim();
    	 						
    	 						document.getElementById("ddl_folder").innerHTML = "<option value='0'>---Your Album---</option>";
    	 						document.getElementById("ddl_folder").innerHTML += temp.trim();
    	 						
    	 						}
    	 				}
    	 			};
    	 			http.send(null);
    	 			
	  	    	}else{
	  	    		jAlert("Error: A album that name already exists. Please enter a different name.","Error");
	  	    	}
    		});
    	});
        
        
        $('form#myForm').submit(function(event){
          //disable the default form submission
            event.preventDefault();
          //grab all form data  
            var formData = new FormData($(this)[0]); 
            var valid = true, errorMessage='';
            
                var iVal = $('#txt_foldername_select').val();
      	        if (iVal == 0) 
      	        {
      	        	errorMessage +="You must choose album.\n";
      	            valid = false;
      	        }  
	            
      	        var fileName = $("#fileuploader").val();
	            if(fileName.trim().length === 0 ) {
					errorMessage += 'You must choose a image. \n';
					valid = false; 
				} else{
		            var uploadedFile = document.getElementById('fileuploader');
		            var fileSize = uploadedFile.files[0].size;
		            var fileInput = $('.upload-file');
		            var maxSize = fileInput.data('max-size');
		            
		            if(fileSize>maxSize){
		            	errorMessage += 'File size is more than' + maxSize + ' bytes. \n';
		            	valid = false;
		            } 
		            
		            if(!validate_fileupload(fileName)){
		 		    	errorMessage += "Invalid image file\n\nYou may only upload files with the following extensions: JPG, PNG, GIF ";
		 		    	valid = false;
					}
            }
            
            
           /*  if(fileInput.get(0).files.length){
                var fileSize = fileInput.get(0).files[0].size;  */
            
          	if(!valid && errorMessage.length>0){
   						alert(errorMessage);
   						e.preventDefault(); 
    		}
          	
            $.ajax({
                url: $(this).attr('action'),
                type: "POST",      
                cache: false,
                processData: false,
                contentType: false,
                data: formData,
               
            	success: function (res) {
            		$("#fileuploader").val("");
            		$('#eventsmessage').css("color","green");
                    $("#eventsmessage").text(res); 
             	},      
              	error: function(jqXHR, textStatus, errorThrown) {
              		$("#fileuploader").val("");
              		$('#eventsmessage').css("color","red");
              		$("#eventsmessage").text(textStatus + ': ' + errorThrown); 
                }

              });
        });
        
    });
    
    /*-------------------------  File Up Manager End  ------------------------*/
</script>

<table style="width: 100%;">
	<tr>
		<td><span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Gallery Management</span></td>
	</tr>
	<tr>
		<td style="width: 80px" align="right"></td>
		<td style="width: 80px" align="right"></td>
	</tr>
</table>

<div id="horizontalTab">
            <ul class="resp-tabs-list">
                <li>File Up Manager</li>
                <li>Album Manager</li>
            </ul> 
            <div class="resp-tabs-container">
                <div>
	                    <p>Create new album: <input type="text" id="txt_foldername" name="txt_foldername" class="chon"> 
	                   	 <input type="button" name="btn_tao_album" value=" Add Album " id="btn_tao_album"
							class="button1"   style="  font-size: 12pt "/> </p>
	                    <br>
							
                	<form id="myForm" method="post" action="showGallery_FileUpload" enctype="multipart/form-data">
	                    <p> Choose your album: 
	                    <select
								id="txt_foldername_select" name="txt_foldername_select" style="width: 200px; height: 24; margin-top: 0px;">
								<option value="0">--Your Album--</option>
								<c:forEach var="thumuc" items="${requestScope.list_thumuc}">
										<option value="${thumuc.id_thumuc}">${thumuc.ten_thumuc}</option>
								</c:forEach>
						</select>
						</p>
						<br>
					    
					  <input id="fileuploader" name="fileuploader" type="file" multiple="multiple"  data-max-size="52428800"  accept="image/x-png, image/gif, image/jpeg" >
					  <br><br>
					  <input type="submit" value="Upload" id="btn_submit" />
        			</form>
					    <div id="eventsmessage" class="eventsmessage" style="color: red"></div>
                </div>
                <div>
                    <p>  Choose your album:   <select
								id="ddl_folder" name="ddl_folder" style="width: 200px; height: 24; margin-top: 0px;">
								<option value="0">--Your Album--</option>
								<c:forEach var="thumuc" items="${requestScope.list_thumuc}">
										<option value="${thumuc.id_thumuc}">${thumuc.ten_thumuc}</option>
								</c:forEach>
						</select> 
						<a href="#" id="href_remove" title="Remove"><img src="admin/images/delete.png"/></a>
						<a href="#" id="href_rename" title="Rename"><img src="admin/images/rename.png"/></a> <input type="text" id="txt_rename" /> 
						<input type="button" name="btn_rename" value=" Ok " id="btn_rename"
							class="button1"   style="font-size: 12pt "/>
						</p>
				
				<div id="tbl_hinhanh">
				</div>
				 <div id="Pagination" class="pagination">
            </div>	
						
                </div>
            </div>
        </div> 
        
        
