package Controller_QuanLy;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.FunctionAll;
import BusinessLogic.tbl_workplanBean;
import Entity.tbl_taikhoan_quantri;
import Entity.tbl_workplan;


@WebServlet("/Workplan_AddNews")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB maxMemSize
maxRequestSize = 1024 * 1024 * 100) // 100 MB

public class Workplan_AddNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Workplan_AddNews() {
        super();
       
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null){
			FunctionAll func = new FunctionAll();
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String contents = request.getParameter("txt_contents").trim();
			String objectives = request.getParameter("txt_objectives").trim();
			String status = request.getParameter("txt_status").trim();
			
			String start_date = request.getParameter("dp_ngaybd");
			String end_date = request.getParameter("dp_ngayketthuc");
			String id_parent = request.getParameter("txt_workplan_select");
			
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			
			java.sql.Date ngay_dang = new java.sql.Date(date.getTime());
			SimpleDateFormat formatDdate = new SimpleDateFormat("dd-MM-yyyy");
			String ngay_dang_bai = formatDdate.format(ngay_dang);
			
			tbl_workplanBean bean = new tbl_workplanBean(ds);
			tbl_workplan tbl_wp = new tbl_workplan();
			tbl_wp.setId_parent(Integer.parseInt(id_parent));
			tbl_wp.setContent(contents);
			tbl_wp.setObjectives(objectives);
			tbl_wp.setStatus(status);
			tbl_wp.setPost_workplan_date(func.DoiNgayThangRaSoNguyen(ngay_dang_bai));
			tbl_wp.setTime_begin(func.DoiNgayThangRaSoNguyen(start_date));
			tbl_wp.setTime_end(func.DoiNgayThangRaSoNguyen(end_date));
			tbl_wp.setYear(year);
			bean.setTbl_workplan(tbl_wp);
			try {
				bean.Workplan_themtin();
				request.setAttribute("thongbaotrangthai","success_workplan");
			} catch (SQLException e) {
				request.setAttribute("thongbaotrangthai", e );
			} 
			finally {
				request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
						response);
			}
			
		}else { // khong phai quanly
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
