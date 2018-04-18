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
import Entity.tbl_gallery_thumuc;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/showGallery_SuaTenAlbum")
public class showGallery_SuaTenAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public showGallery_SuaTenAlbum() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			String id_thumuc = request.getParameter("id_thumuc");
			String txt_rename = request.getParameter("txt_rename");
			tbl_gallery_thumucBean bean_thumuc = new tbl_gallery_thumucBean(ds);
			tbl_gallery_thumuc tbl_thumuc = new tbl_gallery_thumuc();
			tbl_thumuc.setId_thumuc(Integer.parseInt(id_thumuc));
			tbl_thumuc.setTen_thumuc(txt_rename);
			bean_thumuc.setTbl_gallery_thumuc(tbl_thumuc);
			try {
				if(bean_thumuc.SuaTenAlbum() == 0){
					out.print("success");
				}else {
					out.println("error");
				}
			} catch (SQLException e) {
				out.println("error");
			}
			
		}  else { //khong phai quanly
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
