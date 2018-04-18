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

@WebServlet("/Partners_Edit")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB
public class Partners_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 @Resource(name = "EventDB")
		private DataSource ds;
	
    public Partners_Edit() {
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
			if (id_tintuc != null && !id_tintuc.isEmpty()) {
				tbl_others_ourpartnersBean bean = new tbl_others_ourpartnersBean(ds);
				tbl_others_ourpartners tbl_op = bean.OurPartner_SuaTin_TimKimTheoMa(Integer.parseInt(id_tintuc));
				request.setAttribute("tb_partners", tbl_op);
				request.setAttribute("page", "trangOurPartners_SuaPartners");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			}else if (request.getParameter("action").equalsIgnoreCase("ghivaoCSDL")) { 
				FunctionAll func = new FunctionAll();
				String name_partners = request.getParameter("txt_name").trim();
				Part filePart = request.getPart("txt_hinhanh");
				String hinhanh = func.getFilename(filePart);
				String hinhanh_cu = request.getParameter("txt_hinhanh_cu");
				String introduce = request.getParameter("div_text").trim();
				String getid_tintuc = request.getParameter("getid_tintuc"); //lay idtintuc tu input file hidden ben trang jsp				
				String link_partners = name_partners.replaceAll("\\s+",""); //replace space to not space
				
				if(hinhanh.isEmpty()){  //lay anh cu
					if(hinhanh_cu.isEmpty()){
						hinhanh = null;
					}else {
						hinhanh = hinhanh_cu;
					}
				} else if (!hinhanh.isEmpty()){  //gui anh moi
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
				}
				
				tbl_others_ourpartnersBean bean = new tbl_others_ourpartnersBean(ds);
				tbl_others_ourpartners tbl_partner = new tbl_others_ourpartners();
				tbl_partner.setId_partners(Integer.parseInt(getid_tintuc));
				tbl_partner.setIntroduce(introduce);
				tbl_partner.setLink_logo(hinhanh);
				tbl_partner.setLink_partners(link_partners);
				tbl_partner.setName_partners(name_partners);
				bean.setTbl_ourpartners(tbl_partner);
				
				try {
					bean.OurPartners_Edit();
					request.setAttribute("thongbaotrangthai","success_ourpartners");
				} catch (SQLException e) {
					request.setAttribute("thongbaotrangthai", e );
				} finally {
					request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
							response);
				}
			} else { //neu action =null hay empty //loi 500
				response.sendRedirect("index.jsp");
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
