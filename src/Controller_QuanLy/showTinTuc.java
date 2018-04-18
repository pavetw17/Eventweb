package Controller_QuanLy;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_workplanBean;
import Entity.tbl_taikhoan_quantri;


@WebServlet("/showTinTuc")
public class showTinTuc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public showTinTuc() {
        super();
    }

	
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
		if (tbl_qt != null) { 
			String action = request.getParameter("action");
			if (action != null && !action.isEmpty()){
				if(action.equals("highlights")){ 
					request.setAttribute("page", "MasterPageQuanLy");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("themHighlights")){
					request.setAttribute("page", "trangHighlights_ThemTin");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("events")){
					request.setAttribute("page", "Events_trangQuanLy");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("themEvents")){
					request.setAttribute("page", "trangEvents_ThemTin");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("workplan")){
					tbl_workplanBean bean = new tbl_workplanBean(ds);
					//request.setAttribute("list_workplan",bean.LayTatCaWorkplans_QuanTri());
					request.setAttribute("get_list_year", bean.Workplan_GetDistinctYear());
					request.setAttribute("page", "Workplan_trangQuanLy");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("themWorkplan")){
					tbl_workplanBean bean = new tbl_workplanBean(ds);
					request.setAttribute("get_list_year", bean.Workplan_GetDistinctYear());
					request.setAttribute("page", "trangWorkplan_ThemTin");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("projects")){
					request.setAttribute("page", "Project_trangQuanLy");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("themProjects")){
					request.setAttribute("page", "trangProject_ThemTin");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("publications")){
					request.setAttribute("page", "Publications_trangQuanLy");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if (action.equals("themPublications")){
					request.setAttribute("page", "trangPublications_ThemTin");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else {
					response.sendRedirect("404.jsp");
			    }
				
			} else {
				response.sendRedirect("404.jsp");
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
