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

import BusinessLogic.tbl_workplanBean;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/Workplan_DeleteNews")
public class Workplan_DeleteNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Workplan_DeleteNews() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
    	if (tbl_qt != null) {
    		PrintWriter out = response.getWriter();
			String id_news = request.getParameter("id_news");
			tbl_workplanBean bean = new tbl_workplanBean(ds);
			try {
				bean.Workplan_XoaTheoMa(id_news);
				out.print("success");
			} catch (SQLException e) {
				out.print(e.toString());
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
