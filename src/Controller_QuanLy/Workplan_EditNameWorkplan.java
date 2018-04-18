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
import Entity.tbl_workplan;

@WebServlet("/Workplan_EditNameWorkplan")
public class Workplan_EditNameWorkplan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Workplan_EditNameWorkplan() {
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
			
			String id_workplan= request.getParameter("id_workplan");
			String txt_rename = request.getParameter("txt_rename");
			tbl_workplanBean bean = new tbl_workplanBean(ds);
			tbl_workplan tbl_wp = new tbl_workplan();
			tbl_wp.setId_workplan(Integer.parseInt(id_workplan));
			tbl_wp.setName_parent(txt_rename);
			bean.setTbl_workplan(tbl_wp);
			try {
				if(bean.SuaTenWorkplan() == 0){
					out.print("success");
				}else {
					out.println("error");
				}
			} catch (SQLException e) {
				out.println("error");
			}
			
		} else { // khong phai quanly
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
