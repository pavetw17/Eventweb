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

import BusinessLogic.tbl_workplanBean;
import Entity.tbl_taikhoan_quantri;
import Entity.tbl_workplan;


@WebServlet("/Workplan_CreateWorkplan")
public class Workplan_CreateWorkplan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Workplan_CreateWorkplan() {
        super();
        
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			tbl_workplanBean bean = new tbl_workplanBean(ds);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			tbl_workplan tbl_wp = new tbl_workplan();
			tbl_wp.setName_parent(request.getParameter("txt_new_workplan").trim());
			tbl_wp.setYear(year);
			bean.setTbl_workplan(tbl_wp);
			
			try {
				int temp = bean.taoWorkplan();
				if(temp > 0){
					out.println("error");
				}else {
					out.print("success");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
