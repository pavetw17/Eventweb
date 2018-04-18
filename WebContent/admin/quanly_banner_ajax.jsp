<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.tbl_bannerBean"%>
<%@page language="java" import="Entity.tbl_banner"%>
<script type="text/javascript" src="admin/template/alert/jquery.alerts.js"></script>
<link rel="stylesheet" href="admin/template/alert/jquery.alerts.css"  type="text/css" />

<%!@Resource(name = "EventDB")
	private DataSource ds;%>	

<script>
$(document).ready(function() {
	var _URL = window.URL || window.webkitURL;
	$("#id_hinhanh_so_1").change(function(e) {
	    var file, img;

	    if ((file = this.files[0])) {
	        img = new Image();
	        img.onload = function() {
	            alert(this.width + " " + this.height);
	        };
	        img.onerror = function() {
	            alert( "not a valid file: " + file.type);
	        };
	        img.src = _URL.createObjectURL(file);
	    }
	});
	
	
		$('.savebutton').click(function(e) {
			$("#luu_id_banner").val($(this).attr('href')); //lay href luu vao bien an 
			/* alert($(this).attr('href'));  */
			/* alert($("#luu_id_banner").val()); */
			
			/* var $luu_id = $("#luu_id_banner").val(); */ //luu bien kieu Jquery
			for(var i = 1 ;  i <= $("#luu_id_banner").val(); i++){
				if($("#luu_id_banner").val() == i){  //kiem tra so
					
					//$('#your_element').attr('id','the_new_id'); //doi ten id 
					
					var txt_title = $("#id_input_so_" + i).val();
					var txt_content = $("#id_content_so_" + i).val(); 
					if($("#id_hinhanh_so_" + i).val() === '' ){
						var txt_hinhanh = $("#id_anh_cu_so_" + i).attr("src");				
					} else {
						var txt_hinhanh = $("#id_hinhanh_so_" + i).val(); 
						
						if(!validate_fileupload(txt_hinhanh)){
			 		    	alert("Nhập hình đúng định dạng JPG, PNG, GIF ");
			 		    	return false;
						}
						
					}
					var txt_linkURL = $("#id_linkURL_so_" + i).val(); 
					var txt_pos = $("#id_pos_so_" + i).val(); 
					var txt_status = $($("#id_status_so_" + i)).prop('checked'); 
					break;
				}
			} 
					/* alert( txt_hinhanh + " " + txt_pos + " " + txt_status ); */ 	
			 
			$.post("/eventweb/showBanner_Sua", {
				txt_link : $(this).attr('href'),
				txt_title:  txt_title,
				txt_content: txt_content,
				txt_linkURL: txt_linkURL,
				txt_hinhanh: txt_hinhanh,
				txt_pos: txt_pos,
				txt_status: txt_status
				
			}, function(j){	    	  		
				if(j=="success"){
	 	    		jAlert("Post Successful","Alert");
	 	    		<%-- setTimeout("location.href = '<%=request.getContextPath()%>/showTinTuc?action=quanlytintuc';",1500);  --%>
				}else{
	 	    		jAlert("Error:" + j,"Alert");
	 	    		<%--  setTimeout("location.href = '<%=request.getContextPath()%>/LoiSQL.jsp';",1500);  chay OK --%>
	 	    		 setTimeout('history.go(-1)',1500);
		 	   	}; 
			});
			return false;
		});
	});
	
	
function validate_fileupload(fileName)
{
    var allowed_extensions = new Array("jpg","png","gif","JPG","PNG,"GIF");
    var file_extension = fileName.split('.').pop(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.

    for(var i = 0; i <= allowed_extensions.length; i++)
    {
        if(allowed_extensions[i]==file_extension)
        {
            return true; // valid file extension
        };
    }
    return false;
};


</script>


<table style="width: 100%;">
	<tr>
		<td><span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">Banner Manager</span></td>
	</tr>
	<tr>
		<td style="width: 80px" align="right"></td>
		<td style="width: 80px" align="right"></td>
	</tr>
</table>

<% 
		StringBuffer str = new StringBuffer();
		str.append("<table cellspacing='0' cellpadding='3' rules='cols' border='1' id='tbl_banner' "
		+ "style='color: Black; background-color: White; border-color: #999999; border-width: 1px; border-style: Solid; width: 100%; "
		+ "border-collapse: collapse; ' >");
		str.append("	<tbody>");
		str.append("");
		str.append("	<tr	style='color: White; background: url(js/menu/menu_source/images/menu-bg.png) repeat; font-weight: bold; height: 25px'>");
		str.append("		<th scope='col' style='width: 30%;'>Banner</th> ");
		str.append("		<th scope='col'>Properties</th>");
		str.append("		<th scope='col'></th>");
		str.append("	</tr> ");
		
		tbl_bannerBean bean = new tbl_bannerBean(ds);
		ArrayList<tbl_banner> list = bean.LayTatCaBanner_QuanTri();
		for(int i=0; i< list.size(); i++){
			if(i%2 == 0){
				str.append("<tr style='background-color:#F0EEEE' >");
			}else {
				str.append("<tr >");
			}
			str.append("<td><img src='"+ list.get(i).getImages() + "' width='400' height='180'  id='id_anh_cu_so_" + list.get(i).getId_banner() + "'> </td>");
			str.append("<td class='thongtin_banner'> <div> Banner Title: <input type='text' class='text_banner' value='" + list.get(i).getTitle_banner() + "' id='id_input_so_" + list.get(i).getId_banner() + "'> </div>");
			str.append("<br><div> Content: <textarea class='textarea_banner' id='id_content_so_" + list.get(i).getId_banner() + "' rows='4' >"+ list.get(i).getContents() + "</textarea></div> ");
			str.append("<br><div> Image  (900x300 pixels): <INPUT TYPE='file' NAME='txt_hinhanh' id='id_hinhanh_so_" + list.get(i).getId_banner() + "' size='20'></div>");
			str.append("<br><div> Link URL: <input type='text' class='text_banner' value='" + list.get(i).getLink() + "' id='id_linkURL_so_" + list.get(i).getId_banner() + "'></div>");
			str.append("<br><div> Position: <input type='text' class='text_pos' value='"+ list.get(i).getPos() + "' id='id_pos_so_" + list.get(i).getId_banner() + "'></div>");
			if(list.get(i).isStatus()){
				str.append("<br><div> Status: <input type='checkbox' id='id_status_so_" + list.get(i).getId_banner() + "' checked ></div>");
			}else {
				str.append("<br><div> Status: <input type='checkbox' id='id_status_so_" + list.get(i).getId_banner() + "'></div>");
			}
			str.append("</td>");
			str.append("<td> <a href='" + list.get(i).getId_banner() + "'class='savebutton'>Save</a>");
			str.append("</td>");
			str.append("</tr>");
			
			
		}
		str.append("	</tbody>");
		str.append("	</table> ");
		out.print(str.toString());
%>


<input type = "hidden" id="luu_id_banner" name="luu_id_banner">





$("#id_hinhanh_so_6").change(function(e) {
	    var file, img;

	    if ((file = this.files[0])) {
	        img = new Image();
	        img.onload = function() {
	        	alert(this.width + " " + this.height);
	        	if (this.width != 900 || this.height != 300){
	        		locked = false;
	        	}else {
	        		locked = true;
	        	} 
	        
	        };
	        img.onerror = function() {
	            alert( "not a valid file: " + file.type);
	            locked = false;
	        };
	        img.src = _URL.createObjectURL(file);
	        
	    } else {  //khong chon hinh thi true
	    	locked = true;
	    }
	});
	
	
	$('#bang_nhap_6').submit(function(e){
		var valid = true, errorMessage="";
		
	 if($('#id_input_so_6').val().trim().length === 0 ) {//lay id
			errorMessage += "Nhập tên banner.\n";
			valid = false;
		}
	 
	 if($('#id_content_so_6').val().trim().length === 0 ) {//lay id
			errorMessage += "Nhập nội dung banner.\n";
			valid = false;
		}
	 
	 if($('#id_linkURL_so_6').val().trim().length === 0 ) {//lay id
			errorMessage += "Nhập đường dẫn .\n";
			valid = false;
		}
	 
	 if($('#id_pos_so_6').val().trim().length === 0 ) {//lay id
			errorMessage += "Nhập vị trí hiển thị .\n";
			valid = false;
	 } else {
			 var sdtReg=/^[1-9]$/; 
			 if(!sdtReg.test($('#id_pos_so_6').val())){
				errorMessage += "Nhập một số.\n";
				valid = false;
			 }
	 }
	 
	 if(!locked){
		errorMessage += "Nhập hình không đúng yêu cầu.\n";
		valid = false;
	 }
	 
	 if(!valid && errorMessage.length>0){
			alert(errorMessage);
			e.preventDefault(); //the default action of the event will not be triggered.
		}
	});

