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

import BusinessLogic.tbl_others_project_staffBean;
import Entity.tbl_others_project_staff;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/Staff_EditNameProject")
public class Staff_EditNameProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
	public Staff_EditNameProject() {
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
			
			String id_project= request.getParameter("id_project");
			String txt_rename = request.getParameter("txt_rename");
			tbl_others_project_staffBean bean = new tbl_others_project_staffBean(ds);
			tbl_others_project_staff tbl_pro = new tbl_others_project_staff();
			tbl_pro.setId_pro_nv(Integer.parseInt(id_project));
			tbl_pro.setName_pro(txt_rename);
			bean.setTbl_pro_staff(tbl_pro);
			try {
				if(bean.Staff_SuaTenProject() == 0){
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
