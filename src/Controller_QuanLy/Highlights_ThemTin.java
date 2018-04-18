package Controller_QuanLy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.servlet.http.Part;
import javax.sql.DataSource;

import BusinessLogic.FunctionAll;
import BusinessLogic.tbl_newsBean;
import Entity.tbl_news;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/Highlights_ThemTin")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB
public class Highlights_ThemTin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "EventDB")
	private DataSource ds;

	public Highlights_ThemTin() {
		super();
	}

	protected void xuly(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
		if (tbl_qt != null) {
			FunctionAll func = new FunctionAll();
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
					
			String tenbai = request.getParameter("txt_tenbai").trim();
			String tomtat = request.getParameter("txt_tomtat").trim();
			Part filePart = request.getPart("txt_hinhanh");
			String hinhanh = getFilename(filePart);
			String noidung = request.getParameter("div_text").trim();

			if (!hinhanh.isEmpty()) { //path, hinh, tra ve String
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

			} else if(hinhanh.isEmpty()){
				hinhanh = null;
			}

			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			/*System.out.println("thang" + month);*/

			java.sql.Date ngay_dang = new java.sql.Date(date.getTime());
			SimpleDateFormat formatDdate = new SimpleDateFormat("dd-MM-yyyy");
			String ngay_dang_bai = formatDdate.format(ngay_dang);
			tbl_news tblNew = new tbl_news(tenbai, tomtat,
					noidung, func.DoiNgayThangRaSoNguyen(ngay_dang_bai), hinhanh,month,year);

			tbl_newsBean bean_tinNews = new tbl_newsBean(ds);
			bean_tinNews.setTbl_news(tblNew);
			try {
				bean_tinNews.themNews();
				request.setAttribute("thongbaotrangthai","success");
			} catch (SQLException e) {
				/*	System.out.println(e);*/
				request.setAttribute("thongbaotrangthai", e );
			} finally {
				request.getRequestDispatcher("admin/alertbox.jsp").forward(request,
						response);
			}
		} else { //khong phai quanly
			response.sendRedirect("admin/index.jsp");
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
