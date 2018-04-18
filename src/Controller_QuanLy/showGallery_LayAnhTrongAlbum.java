package Controller_QuanLy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_gallery_imageBean;
import Entity.tbl_gallery_image;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/showGallery_LayAnhTrongAlbum")
public class showGallery_LayAnhTrongAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name = "EventDB")
	private DataSource ds;
	
	public showGallery_LayAnhTrongAlbum() {
        super();
    }

	protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			tbl_gallery_imageBean bean = new tbl_gallery_imageBean(ds);
			PrintWriter out = response.getWriter();
			
			String id_thumuc = request.getParameter("txt_ddl_folder");
			
			if (request.getParameter("action").equalsIgnoreCase("getlength")) {
				int tongso_dong = bean.demTongSoAnh_TrongAlbum(Integer.parseInt(id_thumuc));
				out.print(tongso_dong);  //in ra cho Jquery 
			
			}else if (request.getParameter("action").equalsIgnoreCase("list")) {
				Integer offset = Integer.valueOf(request.getParameter("offset"));
				Integer limit = Integer.valueOf(request.getParameter("limit"));
				ArrayList<tbl_gallery_image> list  = bean.layAnh_TrongAlbum(Integer.parseInt(id_thumuc), limit, offset);
				StringBuffer str = new StringBuffer();
				str.append(" <script>	 "
						+ "$('.deleteButton').click(function(e) {"
						+ "if (confirm('Are you sure?')) { "
						+ "var goTo = $(this).attr('href'); "
						+ " $.post('/eventweb/showGallery_XoaImage', { "
						+ "	id_image: goTo,"
    	 				+ "	}, function(j){	 "
    	 				+ "		if(j=='success'){ "
    	 				+ "			jAlert('Completely delete','Infomation');"
    	 				+ "		timkiemtheodieukien();	"
    	 				+ " 	}else{ "
    	 				+ "	jAlert('Error:' + j,'Error'); "
	  	    			+ "		}"
    	 				+ "	}); "
    	 				+ " } "
						+ "		return false;  	 	 }); "
						+ "</script>");
				
				String data = "<script>";
				data += "$('.updateButton').click(function(e) {"
						+ " var goTo = $(this).attr('href'); "
						+ " var txt = $('#txt_name_image_' + goTo).val();"
						+ " $.post('/eventweb/showGallery_SuaTenImage', { "
						+ "	id_folder: $('#ddl_folder').val(), "
						+ "	id_image: goTo,"
						+ " name_image: txt,"
    	 				+ "	}, function(j){	 "
    	 				+ "		if(j=='success'){ "
    	 				+ "			jAlert('Complete rename','Infomation');"
    	 				+ "			"
    	 				+ " 	}else{ "
    	 				+ "	jAlert('Error:' + j,'Error'); "
	  	    			+ "		}"
    	 				+ "	});"
						+ " return false; }); "
						+ " </script> " ;
				
				out.println(data);
				
				str.append("<table width='100%' border='0' cellpadding='0' cellspacing='1' id='tbl_hinhanh'>");
				str.append("<tr>");
				for (int i = 0; i < list.size(); i++){
				
				str.append("<td width='20%' valign='bottom'>");
				str.append(" <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				str.append("   <tr align='center' valign='middle'>");
				str.append(" 	<td style='padding-bottom:10px;'>");
				str.append("  	<table border='0' cellpadding='0' cellspacing='0' bordercolor='#CCCCCC' style='border-collapse:collapse;'>");
				str.append(" <tr><td align='center'>");
				str.append(" <a href='#'><img src='"+ list.get(i).getLink_image() +"' border='0' style='border: 1px solid #ccc;width: 180px;height: 180px'></a></td></tr>");
				str.append(" </table> </td>	</tr>");
				str.append(" <tr align='center' valign='middle'> <td height='60' valign='top'>");
				str.append("<input type='text' value='" +list.get(i).getName_image() +"' id='txt_name_image_"+ list.get(i).getId_image() +"'/><br>");
				str.append(" <a href='"+ list.get(i).getId_image() +"' class='deleteButton' title='Delete'><img src='admin/images/delete_image.png'/></a>");	
				str.append(" <a href='"+ list.get(i).getId_image() +"' class='updateButton' title='Update'><img src='admin/images/update.png'/></a>");
				str.append("</td>	</tr></table></td>");
					if( (i+1) % 4 ==0 ){
						str.append("</tr>");
					}
				}
				str.append("</table>");
				out.print(str.toString());
			}
		} else { //khong phai quanly
			response.sendRedirect("admin/index.jsp");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

}
