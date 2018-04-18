package Controller_QuanLy;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import BusinessLogic.FunctionAll;
import BusinessLogic.tbl_taikhoan_quantriBean;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/DangNhap")
public class DangNhap extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public DangNhap() {
        super();
    }
    
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("txt_username");
		String password = request.getParameter("txt_password");
		tbl_taikhoan_quantriBean bean = new tbl_taikhoan_quantriBean(ds);
				try {
			tbl_taikhoan_quantri tbl_qt = bean.dangNhap(username, FunctionAll.getMD5(password));
			HttpSession session = request.getSession();
			if (tbl_qt != null) {
				session.setAttribute("tbl_qt",tbl_qt);
				response.sendRedirect("showTrangChu");
			} else {
				response.sendRedirect("./admin/index.jsp?kq=error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

}
