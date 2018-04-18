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
	var locked = true; 
	<% 
		StringBuffer kiemtra = new StringBuffer();
		for(int i = 1; i <= 6; i++ ){
			kiemtra.append("$('#id_hinhanh_so_" + i + "').change(function(e) {");
			kiemtra.append("var file, img;  " 

			+	   " if ((file = this.files[0])) { "
			+	   "     img = new Image(); "
			+	   "     img.onload = function() { "
			+	   "			if (this.width != 900 || this.height != 300){ "
			+	   "        				locked = false; "
			+	   "        	}else { "
			+	   "        		locked = true; "  //bat su kien locked de tra ve thongbao
			+	   "        	} "
			+	   "	   }; "
			+	   "	        img.onerror = function() { "
			+	   "	            alert( 'not a valid file: ' + file.type); "
			+	   "					locked = false;  "
			+	   "	        }; "
			+	   "	        img.src = _URL.createObjectURL(file); "
			+	   " } else {    "
			+	   "	    	locked = true;  "
			+	   "	    } "
			+	   "	}); "
			
			
			+	   "	$('#bang_nhap_" + i + "').submit(function(e){ "
			+	   "		var valid = true, errorMessage='';  "
			
			+	   "	 if($('#id_input_so_" + i + "').val().trim().length === 0 ) {"
			+	   "			errorMessage += 'The title banner is required. ';\n"
			+	   "			valid = false; "
			+	   "		} " 
				 
			+	   "	 if($('#id_content_so_" + i + "').val().trim().length === 0 ) { "
			+	   "			errorMessage += '          The content banner is required.'; \n"
			+	   "			valid = false; "
			+	   "		} "
				 
			+	   "	 if($('#id_linkURL_so_" + i + "').val().trim().length === 0 ) { "
			+	   "			errorMessage += '          The link is required .'; \n"
			+	   "			valid = false; "
			+	   "		} "
				 
			+	   "	 if($('#id_pos_so_" + i + "').val().trim().length === 0 ) {"
			+	   "			errorMessage += '          The position is required.'; \n"
			+	   "			valid = false;"
			+	   "	 } else {  "
			+	   "			 var sdtReg=/^[1-9]$/;  "
			+	   "			 if(!sdtReg.test($('#id_pos_so_" + i + "').val())){"
			+	   "				errorMessage += '         The position must be numeric.'; \n"
			+	   "				valid = false; "
			+	   "			 } "
			+	   "	 } " 
				 
			+	   "	 if(!locked){"
			+	   "		errorMessage += '          Invalid image file. You may only upload files with the following extensions: JPG,PNG, GIF ';\n"
			+	   "		valid = false;"
			+	   "	 } " 
				 
		 	+	   "	 if(!valid && errorMessage.length>0){"
			+	   "			alert(errorMessage);"
			+	   "			e.preventDefault(); "
			+	   "		}"
			+	   "	});" 
					);
		}
			out.print(kiemtra.toString());
  %>
  
});

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
			str.append("<td class='thongtin_banner'> ");
			
			str.append(" <form action='showBanner_Sua' method='POST' id='bang_nhap_" + list.get(i).getId_banner() + "' enctype='multipart/form-data'>");
			
			str.append("<div> Banner Title: <input type='text' class='text_banner' value='" + list.get(i).getTitle_banner() + "' id='id_input_so_" + list.get(i).getId_banner() + "' name='id_input_so_" + list.get(i).getId_banner() + "'> </div>");
			str.append("<br><div> Content: <textarea class='textarea_banner' id='id_content_so_" + list.get(i).getId_banner() + "' name='id_content_so_" + list.get(i).getId_banner() + "' rows='4' >"+ list.get(i).getContents() + "</textarea></div> ");
			str.append("<br><div> Image  (900x300 pixels): <INPUT TYPE='file' id='id_hinhanh_so_" + list.get(i).getId_banner() + "' name='id_hinhanh_so_" + list.get(i).getId_banner() + "' size='20'  accept='image/x-png, image/gif, image/jpeg' ></div>");
			str.append("<br><div> Link URL: <input type='text' class='text_banner' value='" + list.get(i).getLink() + "' id='id_linkURL_so_" + list.get(i).getId_banner() + "' name='id_linkURL_so_" + list.get(i).getId_banner() + "'></div>");
			str.append("<br><div> Position: <input type='text' class='text_pos' value='"+ list.get(i).getPos() + "' id='id_pos_so_" + list.get(i).getId_banner() + "' name='id_pos_so_" + list.get(i).getId_banner() + "'></div>");
			if(list.get(i).isStatus()){
				str.append("<br><div style='padding-bottom: 15px;'> Status: <input type='checkbox' id='id_status_so_" + list.get(i).getId_banner() + "' checked name='id_status_so_" + list.get(i).getId_banner() + "' ></div>");
			}else {
				str.append("<br><div style='padding-bottom: 15px;'> Status: <input type='checkbox' id='id_status_so_" + list.get(i).getId_banner() + "' name='id_status_so_" + list.get(i).getId_banner() + "'></div>");
			}
			str.append(" <input type = 'submit' id='id_banner' class='savebutton' value='Save'>");
			str.append(" <input type = 'hidden' id='luu_id_banner' name='luu_id_banner' value='" + list.get(i).getId_banner() + "' class='nhap_so_" + list.get(i).getId_banner() + "'>");
			str.append(" <input type = 'hidden' id='hinh_anh_cu' name='hinh_anh_cu' value='" + list.get(i).getImages() + "'>");
			
			str.append(" </form>");
			str.append("</td>");
			str.append("</tr>");
			
		}
		str.append("	</tbody>");
		str.append("	</table> ");
		out.print(str.toString());
%>


