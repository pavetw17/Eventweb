package Controller_QuanLy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import BusinessLogic.FunctionAll;
import BusinessLogic.tbl_others_project_staffBean;
import BusinessLogic.tbl_others_staffBean;
import Entity.tbl_others_staff;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/Staff_EditStaff")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB
public class Staff_EditStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Staff_EditStaff() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
    	if (tbl_qt != null) {
    		response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id_nv = request.getParameter("id_nv");
			if (id_nv != null && !id_nv.isEmpty()) {
				tbl_others_staffBean bean = new tbl_others_staffBean(ds);
				tbl_others_project_staffBean bean_pro = new tbl_others_project_staffBean(ds);
				tbl_others_staff tbl_staff = bean.Staff_SuaStaff_TimKiemTheoMa((Integer.parseInt(id_nv)));
				request.setAttribute("tbl_staff", tbl_staff);
				request.setAttribute("list_pro_staff", bean_pro.Staff_GetAllNameProjcet());
				request.setAttribute("page", "trangStaff_EditStaff");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (request.getParameter("action").equalsIgnoreCase("ghivaoCSDL")){
				FunctionAll func = new FunctionAll();
				String name = request.getParameter("txt_name").trim();
				String id_project = request.getParameter("txt_project_select");
				Part filePart = request.getPart("txt_hinhanh");
				String hinhanh = func.getFilename(filePart);
				String job = request.getParameter("txt_job").trim();
				String task = request.getParameter("txt_task").trim();
				String email = request.getParameter("txt_email").trim();
				String getid_nv = request.getParameter("getid_nv");
				String hinhanh_cu = request.getParameter("txt_hinhanh_cu");
				
				if(hinhanh.isEmpty()){  //lay anh cu
					if(hinhanh_cu.isEmpty()){
						hinhanh = null;
					}else {
						hinhanh = hinhanh_cu;
					}
				} else if (!hinhanh.isEmpty()){  //gui anh moi
					String path = getServletContext().getRealPath("uploads/staff") ;
					OutputStream out = new FileOutputStream(new File(path + File.separator + hinhanh));
					InputStream filecontent = filePart.getInputStream();

					int read = 0;
					final byte[] bytes = new byte[1024];

					while ((read = filecontent.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}

					hinhanh = "uploads/staff" + "/" + hinhanh;
					if (out != null) {
						out.close();
					}
					if (filecontent != null) {
						filecontent.close();
					}
				}	
				
				tbl_others_staff tbl_staff = new tbl_others_staff();
				tbl_staff.setId_nv(Integer.parseInt(getid_nv));
				tbl_staff.setName_nv(name);
				tbl_staff.setTask_nv(task);
				tbl_staff.setPhoto_nv(hinhanh);
				tbl_staff.setJob_nv(job);
				tbl_staff.setEmail_nv(email);
				tbl_staff.setId_pro_nv(Integer.parseInt(id_project));
				
				tbl_others_staffBean bean = new tbl_others_staffBean(ds);
				bean.setTbl_staff(tbl_staff);
				
				try {
					bean.Staff_SuaStaff();
					request.setAttribute("thongbaotrangthai","success_ourstaff");
				} catch (SQLException e) {
					request.setAttribute("thongbaotrangthai", e );
				} finally {
					request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
							response);
				}
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
