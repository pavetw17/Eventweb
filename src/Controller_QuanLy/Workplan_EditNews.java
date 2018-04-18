package Controller_QuanLy;

import java.io.IOException;
import java.sql.SQLException;

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


@WebServlet("/Workplan_EditNews")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxRequestSize=1024*1024*100)      // 100 MB

public class Workplan_EditNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Workplan_EditNews() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id_tintuc = request.getParameter("id_tin_tuc");
			FunctionAll func = new FunctionAll();
			if (id_tintuc != null && !id_tintuc.isEmpty()) {
				tbl_workplanBean bean = new tbl_workplanBean(ds);
				tbl_workplan tbl_wp = bean.Workplan_SuaTin_TimKiemTheoMa(Integer.parseInt(id_tintuc));
				request.setAttribute("list_dropdown", bean.LayTatCaWorkplans_TheoNam_QuanTri(tbl_wp.getYear()));
				request.setAttribute("tbl_workplan", tbl_wp);
				//request.setAttribute("get_id_parent", tbl_wp.getId_parent());
				request.setAttribute("time_begin", func.DoiSoNguyenRaNgayThang(tbl_wp.getTime_begin()));
				request.setAttribute("time_end", func.DoiSoNguyenRaNgayThang(tbl_wp.getTime_end()));
				request.setAttribute("page", "trangWorkplan_SuaTin");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (request.getParameter("action").equalsIgnoreCase("ghivaoCSDL")){
				String contents = request.getParameter("txt_contents").trim();
				String objectives = request.getParameter("txt_objectives").trim();
				String status = request.getParameter("txt_status").trim();
				
				String start_date = request.getParameter("dp_ngaybd");
				String end_date = request.getParameter("dp_ngayketthuc");
				String id_parent = request.getParameter("txt_workplan_select");
				String id_workplan = request.getParameter("get_id_workplan");
				
				tbl_workplan tbl_wp = new tbl_workplan();
				tbl_wp.setId_parent(Integer.parseInt(id_parent));
				tbl_wp.setContent(contents);
				tbl_wp.setObjectives(objectives);
				tbl_wp.setStatus(status);
				tbl_wp.setTime_begin(func.DoiNgayThangRaSoNguyen(start_date));
				tbl_wp.setTime_end(func.DoiNgayThangRaSoNguyen(end_date));
				tbl_wp.setId_workplan(Integer.parseInt(id_workplan));
				
				tbl_workplanBean bean = new tbl_workplanBean(ds);
				bean.setTbl_workplan(tbl_wp);
				try {
					bean.Workplan_SuaTin_CapNhatTheoMa();
					request.setAttribute("thongbaotrangthai","success_workplan");
				} catch (SQLException e) {
					request.setAttribute("thongbaotrangthai", e );
				} finally {
					request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
							response);
				}
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
