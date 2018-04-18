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
import BusinessLogic.tbl_eventsBean;
import Entity.tbl_events;
import Entity.tbl_taikhoan_quantri;


@WebServlet("/Events_SuaTin")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB

public class Events_SuaTin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
    
    public Events_SuaTin() {
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
				tbl_eventsBean bean = new tbl_eventsBean(ds);
				tbl_events tbl_event = bean.Events_SuaTin_TimKimTheoMa(Integer.parseInt(id_tintuc));
				request.setAttribute("start_date_events", func.DoiSoNguyenRaNgayThang(tbl_event.getStart_date_events()));
				request.setAttribute("end_date_events", func.DoiSoNguyenRaNgayThang(tbl_event.getEnd_date_events()));
				request.setAttribute("start_time", func.formatTimestampAsTwelveHour_string(tbl_event.getStart_time()));
				request.setAttribute("end_time", func.formatTimestampAsTwelveHour_string(tbl_event.getEnd_time()));
				request.setAttribute("tbl_events", tbl_event);
				request.setAttribute("page", "trangEvents_SuaTin");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (request.getParameter("action").equalsIgnoreCase("ghivaoCSDL")) { 
				String tenbai = request.getParameter("txt_tenbai").trim();
				String tomtat = request.getParameter("txt_tomtat").trim();
				String noidung = request.getParameter("div_text").trim();

				String start_date = request.getParameter("dp_ngaybd");
				String end_date = request.getParameter("dp_ngayketthuc");
				String start_time = request.getParameter("time_start");
				String end_time = request.getParameter("time_end");
				String location = request.getParameter("txt_location").trim();
				
				Part filePart = request.getPart("txt_hinhanh");
				String hinhanh = func.getFilename(filePart);
				
				String hinhanh_cu = request.getParameter("txt_hinhanh_cu");
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
				
				tbl_events tbl_event = new tbl_events();
				tbl_event.setId_events(Integer.parseInt(getid_tintuc));
				tbl_event.setName_events(tenbai);
				tbl_event.setSummary(tomtat);
				tbl_event.setStart_date_events(func.DoiNgayThangRaSoNguyen(start_date));
				tbl_event.setEnd_date_events(func.DoiNgayThangRaSoNguyen(end_date));
				tbl_event.setStart_time(func.formatStringAsTwelveHour_sql(start_time));
				tbl_event.setEnd_time(func.formatStringAsTwelveHour_sql(end_time));
				tbl_event.setLocation(location);
				tbl_event.setContents(noidung);
				tbl_event.setPhoto(hinhanh);
				
				tbl_eventsBean bean = new tbl_eventsBean(ds);
				bean.setTbl_events(tbl_event);
				try {
					bean.Events_SuaTin_CapNhatTheoMa();
					request.setAttribute("thongbaotrangthai","success_events");
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
