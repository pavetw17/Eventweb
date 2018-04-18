package Controller;

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

@WebServlet("/Photo_AnhTheoGallery")
public class Photo_AnhTheoGallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Photo_AnhTheoGallery() {
        super();
       
    }
	
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id_thumuc = request.getParameter("id_thumuc");
			tbl_gallery_imageBean bean = new tbl_gallery_imageBean(ds);
			PrintWriter out = response.getWriter();

			if (action.equals("getlength")) {
				int tongso_dong = bean.demTongSoAnh_TrongAlbum(Integer.parseInt(id_thumuc));
				out.print(tongso_dong); // in ra cho Jquery
				
			} else if (action.equals("list")) {
				Integer offset = Integer.valueOf(request.getParameter("offset"));
				Integer limit = Integer.valueOf(request.getParameter("limit"));
				ArrayList<tbl_gallery_image> list = bean.layAnh_TrongAlbum(Integer.parseInt(id_thumuc), limit, offset);
				StringBuffer str = new StringBuffer();
				
				String data = "<script type=\"text/javascript\">";
				data +=   "$(function() { ";
				data +=  "$('#hinh a').lightBox();" ;
				data += " });" ;
				data += "</script>";
				
				out.println(data);
				
				str.append("<ul class='ulphoto'>");
				int k = 2;
				for (int i = 0; i < list.size(); i++) {
					str.append("<li id='hinh' ");
					if( (i+1) % k == 0 ){
						str.append(" class='licenter' ");
						k = k + 3;
					}
					str.append("><a href='"+ list.get(i).getLink_image()+"' title='"+ list.get(i).getName_image() +"'>");
					str.append("<img class='imgpic' src='"+ list.get(i).getLink_image() +"' style='width: 206px; height: 150px'></a></li>");
					if((i +1) % 3 == 0){
						str.append("</ul><div style='clear: both'></div> <ul class='ulphoto'>");
					}
				}
				out.print(str.toString());
			}
		}else {
			response.sendRedirect("index.jsp");
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

}
