package Controller_QuanLy;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_taikhoan_quantriBean;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/showAdmin")
public class showAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public showAdmin() {
		super();
	}

	@Resource(name = "EventDB")
	private DataSource ds;

	protected void xuly(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			String action = request.getParameter("action");
			if (action != null && !action.isEmpty()) {
				if (action.equals("accountsettings")) {
					tbl_taikhoan_quantriBean bean = new tbl_taikhoan_quantriBean(ds);
					try {
						tbl_taikhoan_quantri tbl_qt_edit = bean.QuanTri_GetAccountAccordingID(tbl_qt.getId_account());
						request.setAttribute("tbl_qt", tbl_qt_edit);
						request.setAttribute("page", "trangAdmin_EditAccount");
						request.getRequestDispatcher("chuyentrang.jsp").forward(request,
								response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					response.sendRedirect("404.jsp");
				}
			} else {
				response.sendRedirect("404.jsp");
			}
		} else { // khong phai quanly
			response.sendRedirect("admin/index.jsp");
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

}
