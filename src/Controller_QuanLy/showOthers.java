package Controller_QuanLy;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_others_aboutusBean;
import BusinessLogic.tbl_others_joinusBean;
import BusinessLogic.tbl_others_project_staffBean;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/showOthers")
public class showOthers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public showOthers() {
        super();
    }
    
    @Resource(name = "EventDB")
	private DataSource ds;
    
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
		if (tbl_qt != null) { 
			String action = request.getParameter("action");
			if (action != null && !action.isEmpty()){
				if(action.equals("aboutus")){
					tbl_others_aboutusBean bean = new tbl_others_aboutusBean(ds);
					request.setAttribute("tbl_aboutus", bean.AboutUs_GetContents(1));
					request.setAttribute("page", "AboutUs");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if(action.equals("contactus")){
					tbl_others_aboutusBean bean = new tbl_others_aboutusBean(ds);
					request.setAttribute("contact_us", bean.AboutUs_GetContents(2));
					request.setAttribute("page", "ContactUs");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if(action.equals("joinus")){
					tbl_others_joinusBean bean_joinus = new tbl_others_joinusBean(ds);
					request.setAttribute("list_joinus", bean_joinus.JoinUs_GetAllList());
					request.setAttribute("page", "JoinUs");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if(action.equals("ourpartners")){
					request.setAttribute("page", "OurPartners");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if(action.equals("themOurPartners")){
					request.setAttribute("page", "trangOurPartners_ThemPartners");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
							response);
				} else if(action.equals("ourstaff")){
					tbl_others_project_staffBean bean = new tbl_others_project_staffBean(ds);
					request.setAttribute("list_pro_staff", bean.Staff_GetAllNameProjcet());
					request.setAttribute("page", "OurStaff");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
				} else if(action.equals("themProjectStaff")){
					tbl_others_project_staffBean bean = new tbl_others_project_staffBean(ds);
					request.setAttribute("list_pro_staff", bean.Staff_GetAllNameProjcet());
					request.setAttribute("page", "trangStaff_ThemStaff");
					request.getRequestDispatcher("chuyentrang.jsp").forward(request,
							response);
				} else {
					response.sendRedirect("404.jsp");
			    }
			}  else {
				response.sendRedirect("404.jsp");
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
