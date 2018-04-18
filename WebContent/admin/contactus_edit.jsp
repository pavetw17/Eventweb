<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type='text/javascript' src='js/jquery-1.10.2.js'></script>
<script type="text/javascript" src="tiny_mce/tiny_mce.js"></script>

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
 		    
			if ($('#div_text').val() === '' || $('#div_text').val().length < 200) {//lay id
				errorMessage += "The content must be greater than 200 characters.\n";
				valid = false;
			}
	
			if (!valid && errorMessage.length > 0) {
				alert(errorMessage);
				e.preventDefault(); //the default action of the event will not be triggered.
			}
			
	 }); 

</script>
  
  <br><br>
  <span ID="lblTK"	style="font-weight: 700; color: blue; font-size: 16pt">Edit Page Contact Us</span>
  <br> <br>
<!-- AboutUs_Edit -->
<form action="AboutUs_Edit"  id="bangnhap" method="POST" enctype="multipart/form-data">
	<input type="hidden" id="getid_aboutus" name="getid_aboutus" value="${contact_us.id}">
	<br><br>
	<textarea name="div_text" id="div_text" style="margin-bottom: 10px; width: 1000px; height: 100%;"> ${contact_us.contents} </textarea>
	<br>
	<input type="submit" name="btn_ghiCSDL" value="Save" id="btn_ghiCSDL"
							class="button1"   style="font-size: 12pt; width: 105px; margin-right: 190px; "/>
	
	<input type="button" onclick="history.back();" name="btn_quaylai" value="Cancel" id="btn_quaylai"
							class="button1"   style="font-size: 12pt; width: 105px "/>
</form>