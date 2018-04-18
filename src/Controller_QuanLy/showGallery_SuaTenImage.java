package Controller_QuanLy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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

@WebServlet("/showGallery_SuaTenImage")
public class showGallery_SuaTenImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name = "EventDB")
	private DataSource ds;
	
	public showGallery_SuaTenImage() {
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
			
			String id_thumuc = request.getParameter("id_folder");
			String id_image = request.getParameter("id_image");
			String name_imgae = request.getParameter("name_image");
			
			//System.out.println(id_thumuc + "   " + id_image + "   " + name_imgae);
			
			tbl_gallery_image tbl_image = new tbl_gallery_image();
			tbl_image.setId_thumuc(Integer.parseInt(id_thumuc));
			tbl_image.setId_image(Integer.parseInt(id_image));
			bean.setTbl_gallery_image(tbl_image);
			try {
				bean.SuaTenImage(name_imgae);
				out.print("success");
			} catch (SQLException e) {
				out.print(e.toString());
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
