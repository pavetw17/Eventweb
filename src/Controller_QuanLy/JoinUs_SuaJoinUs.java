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
import BusinessLogic.tbl_others_joinusBean;
import Entity.tbl_others_joinus;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/JoinUs_SuaJoinUs")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB
public class JoinUs_SuaJoinUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 @Resource(name = "EventDB")
		private DataSource ds;
	
    public JoinUs_SuaJoinUs() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
		if (tbl_qt != null) { 
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			FunctionAll func = new FunctionAll();
			String id_joinus = request.getParameter("getid_joinus");
			String name_joinus = request.getParameter("txt_name").trim();
			String link_joinus = request.getParameter("txt_link").trim();
			
			Part filePart = request.getPart("txt_hinhanh");
			String hinhanh = func.getFilename(filePart);
			String hinhanh_cu = request.getParameter("txt_hinhanh_cu");
			
			if(hinhanh.isEmpty()){  //lay anh cu
				if(hinhanh_cu.isEmpty()){
					hinhanh = null;
				}else {
					hinhanh = hinhanh_cu;
				}
			} else if (!hinhanh.isEmpty()){  //gui anh moi
				String path = getServletContext().getRealPath("uploads/icon_social") ;
				OutputStream out = new FileOutputStream(new File(path + File.separator + hinhanh));
				InputStream filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}

				hinhanh = "uploads/icon_social" + "/" + hinhanh;
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
			}
			
			tbl_others_joinus tbl_joinus = new tbl_others_joinus();
			tbl_joinus.setId_joinus(Integer.parseInt(id_joinus));
			tbl_joinus.setName_joinus(name_joinus);
			tbl_joinus.setLink_joinus(link_joinus);
			tbl_joinus.setImage_joinus(hinhanh);
			tbl_others_joinusBean bean = new tbl_others_joinusBean(ds);
			bean.setTbl_joinus(tbl_joinus);
			try {
				bean.JoinUs_suaJoinUs();
				request.setAttribute("thongbaotrangthai","success_joinus");
			} catch (SQLException e) {
				request.setAttribute("thongbaotrangthai", e );
			} finally {
				request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
						response);
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
