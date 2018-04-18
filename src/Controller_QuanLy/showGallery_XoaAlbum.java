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

import BusinessLogic.tbl_gallery_thumucBean;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/showGallery_XoaAlbum")
public class showGallery_XoaAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public showGallery_XoaAlbum() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			PrintWriter out = response.getWriter();
			
			String id_thumuc = request.getParameter("id_thumuc");
			tbl_gallery_thumucBean bean_thumuc = new tbl_gallery_thumucBean(ds);
			try {
				bean_thumuc.XoaAlbum(id_thumuc);
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
