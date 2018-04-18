package Controller_QuanLy;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_others_aboutusBean;
import Entity.tbl_others_aboutus;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/AboutUs_Edit")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxRequestSize=1024*1024*100)  
public class AboutUs_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AboutUs_Edit() {
        super();
    }

    @Resource(name = "EventDB")
	private DataSource ds;
    
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("getid_aboutus");
			String contents = request.getParameter("div_text").trim();
			tbl_others_aboutusBean bean = new tbl_others_aboutusBean(ds);
			tbl_others_aboutus tbl_about = new tbl_others_aboutus(Integer.parseInt(id),contents);
			bean.setTbl_aboutus(tbl_about);
			
			try {
				bean.AboutUs_EditContents();
				request.setAttribute("thongbaotrangthai","success");
			} catch (SQLException e) {
				request.setAttribute("thongbaotrangthai", e );
			} finally {
				request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
						response);
			}
		}else { //khong phai quanly
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
