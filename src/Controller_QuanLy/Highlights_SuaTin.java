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

import BusinessLogic.tbl_newsBean;
import Entity.tbl_news;
import Entity.tbl_taikhoan_quantri;


@WebServlet("/Highlights_SuaTin")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50, 
maxRequestSize=1024*1024*100)      // 100 MB
public class Highlights_SuaTin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Highlights_SuaTin() {
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
				tbl_newsBean bean = new tbl_newsBean(ds);
				tbl_news tbl_new = bean.SuaTin_TimKimTheoMa_Highlights(Integer.parseInt(id_tintuc));
				request.setAttribute("tb_news", tbl_new);
				request.setAttribute("page", "trangHighlights_SuaTin");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			
			} else if (request.getParameter("action").equalsIgnoreCase("ghivaoCSDL")) { 
				String tenbai = request.getParameter("txt_tenbai").trim();
				String tomtat = request.getParameter("txt_tomtat").trim();
				Part filePart = request.getPart("txt_hinhanh");
				String hinhanh = getFilename(filePart);
				String hinhanh_cu = request.getParameter("txt_hinhanh_cu");
				String noidung = request.getParameter("div_text").trim();
				String getid_tintuc = request.getParameter("getid_tintuc"); //lay idtintuc tu input file hidden ben trang jsp
				
				if(hinhanh.isEmpty()){  //lay anh cu
					if(hinhanh_cu.isEmpty()){
						hinhanh = null;
					}else {
						hinhanh = hinhanh_cu;
					}
				} else if (!hinhanh.isEmpty()){  //gui anh moi
					String path = getServletContext().getRealPath("uploads/baiviet") ;
					OutputStream out = new FileOutputStream(new File(path + File.separator + hinhanh));
					InputStream filecontent = filePart.getInputStream();

					int read = 0;
					final byte[] bytes = new byte[1024];

					while ((read = filecontent.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}

					hinhanh = "uploads/baiviet" + "/" + hinhanh;
					if (out != null) {
						out.close();
					}
					if (filecontent != null) {
						filecontent.close();
					}
				}	
					tbl_news tblNew = new tbl_news();
					tblNew.setId_news(Integer.parseInt(getid_tintuc));
					tblNew.setName_news(tenbai);
					tblNew.setSummary(tomtat);
					tblNew.setContents(noidung);
					tblNew.setPhoto(hinhanh);
					
					tbl_newsBean bean = new tbl_newsBean(ds);
					bean.setTbl_news(tblNew);
					try {
						bean.SuaTin_CapNhatTheoMa_Highlights();
						request.setAttribute("thongbaotrangthai","success");
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

	private String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
}
