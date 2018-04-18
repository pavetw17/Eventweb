<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type='text/javascript' src='js/jquery-1.10.2.js'></script>
<script type="text/javascript" src="tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css" type="text/css" />
<script type="text/javascript" src="js/jquery.ptTimeSelect.js"></script>
<link rel="stylesheet" href="css/jquery.ptTimeSelect.css" type="text/css" />

<script type="text/javascript">
  tinymce.init({
    	// General options
	 	//mode : "textareas",
	 	mode: "exact",
	 	elements:"div_text",
	 	theme : "advanced",
		plugins : "imagemanager,filemanager,autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave,visualblocks",

		// Theme options
		theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor,tablecontrols",
		//theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,advhr,|,print,|,ltr,rtl,|,fullscreen",
		//theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft,visualblocks",
    	theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,

		// Style formats
		style_formats : [
			{title : 'Bold text', inline : 'b'},
			{title : 'Red text', inline : 'span', styles : {color : '#ff0000'}},
			{title : 'Red header', block : 'h1', styles : {color : '#ff0000'}},
			{title : 'Table styles'},
			{title : 'Table row 1', selector : 'tr', classes : 'tablerow1'}
		],
		height : "500px"  
  
  });
  
	    $(document).on('submit', '#bangnhap', function(e) { 
 		    var valid = true, errorMessage="";
 		   	var fileName = $('#txt_hinhanh').val();
			
			if(fileName !== ''){
				if(!validate_fileupload(fileName)){
	 		    	errorMessage += "Invalid image file\n\nYou may only upload files with the following extensions: JPG, PNG, GIF ";
	 		    	valid = false;
				}
			}
			
			if ($('#txt_tomtat').val().length == 0  || $('#txt_tomtat').val().length < 200 || $('#txt_tomtat').val().length > 300 ) {//lay id
				errorMessage += "The summary must be greater than 200 and not exceed 300 characters.\n";
				valid = false;
			}
	
			if ($('#txt_tenbai').val() === '' || $('#txt_tenbai').val().length > 100) {//lay id
				errorMessage += "The title must be less than 100 characters long .\n";
				valid = false;
			} 
			
			if ($('#div_text').val() === '' || $('#div_text').val().length < 200) {//lay id
				errorMessage += "The content must be greater than 200 characters.\n";
				valid = false;
			}
				
			var time_start =  $('#time_start').val();
		    var time_end =  $('#time_end').val();
		      
		    //convert dd-mm-yyyy to mm-dd-yyyy
		    var luu_date_start = new Date( $('#dp_ngaybd').val().replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );
	    	var date_start = (luu_date_start.getMonth() + 1) + '-' + luu_date_start.getDate() + '-' + luu_date_start.getFullYear() ;
		    //d.getMonth() + 1; //Months are zero based
		      
		    var end_start = new Date( $('#dp_ngayketthuc').val().replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );
		    end_start = (end_start.getMonth() + 1) + '-' + end_start.getDate() + '-' + end_start.getFullYear() ;
		      
		    if(Date.parse(end_start + ' ' + time_end) < Date.parse(date_start + ' ' + time_start))
		    {
		        errorMessage += "End time should exceed the start time.\n";
		        valid = false;
		    }
		    else if(Date.parse(end_start + ' ' + time_end) == Date.parse(date_start + ' ' + time_start))
		    {
		    	errorMessage +="Start time and end time cannot be same.\n";
		    	valid = false;
		    } 			      
			
		    
		    var CurrentDay =new Date( $.datepicker.formatDate('mm-dd-yy', new Date()));
		    var SelectDay = new Date (luu_date_start);
		    
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
			
			}).attr('readonly', 'readonly').val($.datepicker.formatDate("dd-mm-yy", new Date()));
			
			$("#dp_ngayketthuc").datepicker({
				numberOfMonths : 1,
				dateFormat : 'dd-mm-yy',
				changeMonth : true,
				changeYear : true,
				yearRange : '2014:2024',
				onSelect : function(selected) {
				
					$("#dp_ngaybd").datepicker("option", "maxDate", selected);
				}
			}).attr('readonly', 'readonly').val($.datepicker.formatDate("dd-mm-yy", new Date()));
	});
	
	  $(document).ready(function(){
          // find the input fields and apply the time select to them.
          $('#time_start').ptTimeSelect();
          $('#time_end').ptTimeSelect();
          
      });

</script>

<span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Add Events </span>
			
			
<!-- Events_ThemTin  -->
<form action="Events_ThemTin"  id="bangnhap" method="POST" enctype="multipart/form-data">

<table style="width: 100%;">
			<tr>
                <td width="17%" >
                    <p align="right">Title:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000">*</font></td>
                <td width="83%" >
               <textarea id="txt_tenbai" name="txt_tenbai" style=" width: 400px;  " ></textarea>
    		</tr>
    		<tr>
                <td width="17%" >
                    <p align="right">Summary:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_tomtat"  name="txt_tomtat" style=" width: 400px;  " ></textarea>
                 </td>
    		</tr>
    		 <tr>	  
                <td width="17%"  align="right">Image:</td>
                <td width="1%"  align="right"><font color="#FF0000"></font></td>
                <td width="83%" >
                    <INPUT TYPE="file" NAME="txt_hinhanh" id="txt_hinhanh" CLASS="textbox" size="20"  accept="image/x-png, image/gif, image/jpeg"  ></td>
            </tr>
    		<tr>	  
                <td width="17%"  align="right">Start date:</td> 
                <td width="1%"  align="right"><font color="#FF0000"></font></td>
                <td width="83%" >
                   <input type="text" id="dp_ngaybd" name="dp_ngaybd" />
                   Start time: 
                   <input id="time_start" name="time_start" style="width: 55px" readonly value="7:00 AM" />
                  </td>
            </tr>
    		<tr>	  
                <td width="17%"  align="right">End date:</td> 
                <td width="1%"  align="right"><font color="#FF0000"></font></td>
                <td width="83%" >
                   <input type="text" id="dp_ngayketthuc" name="dp_ngayketthuc" />
                   End time: 
                   &nbsp<input id="time_end" name="time_end" style="width: 55px" readonly  value="7:30 AM" />
                  </td>
            </tr>
            <tr>
                <td width="17%" >
                    <p align="right">Location:</td>
                <td width="1%"  align="center">
                    <font color="#FF0000"></font></td>
                <td width="83%" >
                 <textarea id="txt_location"  name="txt_location" style=" width: 400px;  " ></textarea>
                 </td>
    		</tr>
</table>
<br>

	<textarea name="div_text" id="div_text" style="margin-bottom: 10px; width: 1000px; height: 100%;"> </textarea>
	<br>
	<input type="submit" name="btn_ghiCSDL" value="Save" id="btn_ghiCSDL"
							class="button1"   style="font-size: 12pt; width: 105px; margin-right: 190px; "/>
	
	<input type="button" onclick="history.back();"	 name="btn_quaylai" value="Cancel" id="btn_quaylai"
							class="button1"   style="font-size: 12pt; width: 105px "/>
</form>