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

@WebServlet("/Staff_CreateProject")
public class Staff_CreateProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Staff_CreateProject() {
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
			
			tbl_others_project_staffBean bean = new tbl_others_project_staffBean(ds);
			tbl_others_project_staff tbl_project = new tbl_others_project_staff();
			tbl_project.setName_pro(request.getParameter("txt_new_project"));
			bean.setTbl_pro_staff(tbl_project);
			
			try {
				int temp = bean.Staff_taoProject();
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
