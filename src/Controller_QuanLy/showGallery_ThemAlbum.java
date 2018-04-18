package Controller_QuanLy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

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

@WebServlet("/showGallery_ThemAlbum")
public class showGallery_ThemAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public showGallery_ThemAlbum() {
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
			
			tbl_gallery_thumucBean bean_thumuc = new tbl_gallery_thumucBean(ds);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			tbl_gallery_thumuc tbl_thumuc = new tbl_gallery_thumuc();
			tbl_thumuc.setTen_thumuc(request.getParameter("txt_foldername").trim());
			tbl_thumuc.setYear(year);
			bean_thumuc.setTbl_gallery_thumuc(tbl_thumuc);
			
			try {
				int temp = bean_thumuc.taoAlbum();
				if(temp > 0){
					out.println("error");
				}else {
					out.print("success");
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
