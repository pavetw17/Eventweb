package Controller_QuanLy;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_gallery_thumucBean;
import Entity.tbl_gallery_thumuc;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/showGallery")
public class showGallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public showGallery() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
		if (tbl_qt != null) {
			tbl_gallery_thumucBean bean = new tbl_gallery_thumucBean(ds);
			ArrayList<tbl_gallery_thumuc> list =  bean.LayTatCaThuMuc_QuanTri();
			request.setAttribute("list_thumuc", list);
			request.setAttribute("page", "QuanLyGallery");
			request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
		} else {
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
