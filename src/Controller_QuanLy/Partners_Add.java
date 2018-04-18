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
import BusinessLogic.tbl_others_ourpartnersBean;
import Entity.tbl_others_ourpartners;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/Partners_Add")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB
public class Partners_Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Partners_Add() {
        super();
    }
    
    @Resource(name = "EventDB")
	private DataSource ds;
    
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
		if (tbl_qt != null) { 
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			FunctionAll func = new FunctionAll();
			
			String name_partners = request.getParameter("txt_name").trim();
			Part filePart = request.getPart("txt_hinhanh");
			String hinhanh = func.getFilename(filePart);
			String introduce = request.getParameter("div_text").trim();
			String link_partners = name_partners.replaceAll("\\s+",""); //replace space to not space
			
			if (!hinhanh.isEmpty()) { //path, hinh, tra ve String
				String path = getServletContext().getRealPath("uploads/logo_ourpartners") ;
				OutputStream out = new FileOutputStream(new File(path + File.separator + hinhanh));
				InputStream filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}

				hinhanh = "uploads/logo_ourpartners" + "/" + hinhanh;

				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}

			} else if(hinhanh.isEmpty()){
				hinhanh = null;
			}
			
			tbl_others_ourpartnersBean bean = new tbl_others_ourpartnersBean(ds);
			tbl_others_ourpartners tbl_partners = new tbl_others_ourpartners();
			tbl_partners.setName_partners(name_partners);
			tbl_partners.setLink_partners(link_partners);
			tbl_partners.setIntroduce(introduce);
			tbl_partners.setLink_logo(hinhanh);
			bean.setTbl_ourpartners(tbl_partners);
			
			try {
				bean.OurPartners_Add();
				request.setAttribute("thongbaotrangthai","success_ourpartners");	
			} catch (SQLException e) {
				request.setAttribute("thongbaotrangthai", e );
			} finally {
				request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
						response);
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
