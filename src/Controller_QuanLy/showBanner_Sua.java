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

import BusinessLogic.tbl_bannerBean;
import Entity.tbl_banner;
import Entity.tbl_taikhoan_quantri;


@WebServlet("/showBanner_Sua")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB

public class showBanner_Sua extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
   
    public showBanner_Sua() {
        super();
    }
    
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute(
				"tbl_qt");
    	if (tbl_qt != null){
    		response.setContentType("text/html;charset=UTF-8");
    		request.setCharacterEncoding("UTF-8");
    		response.setCharacterEncoding("UTF-8");
			
			//do de tung form nen lay duoc luu_id_banner
			String id_banner_link = request.getParameter("luu_id_banner");
			if (id_banner_link != null && !id_banner_link.isEmpty()){	
			
				String title_banner = null;
				String txt_content = null;
				String txt_linkURL = null;
				String txt_hinhanh = null;
				String txt_pos = null;
				String txt_status = null;
				String hinhanh_cu = request.getParameter("hinh_anh_cu");
				for(int i=1 ; i<= Integer.parseInt(id_banner_link); i++){
					if( i == Integer.parseInt(id_banner_link) ){
						title_banner =  request.getParameter("id_input_so_"+ i) ;
						txt_content =  request.getParameter("id_content_so_"+ i) ;
						txt_linkURL =  request.getParameter("id_linkURL_so_"+ i) ;
						Part filePart = request.getPart("id_hinhanh_so_"+ i); // Retrieves <input type="file" name="file">
						txt_hinhanh = getFilename(filePart);
						
						//txt_hinhanh =  request.getParameter("id_hinhanh_so_"+ i) ;
						txt_pos =  request.getParameter("id_pos_so_"+ i) ;
						txt_status =  request.getParameter("id_status_so_"+ i) ;

						if (txt_hinhanh != null && !txt_hinhanh.isEmpty()) { //path, hinh, tra ve String
							String path = getServletContext().getRealPath("uploads/banner") ;
							OutputStream out = new FileOutputStream(new File(path + File.separator + txt_hinhanh));
							InputStream filecontent = filePart.getInputStream();

							int read = 0;
							final byte[] bytes = new byte[1024];

							while ((read = filecontent.read(bytes)) != -1) {
								out.write(bytes, 0, read);
							}

							txt_hinhanh = "uploads/banner" + "/" + txt_hinhanh;

							if (out != null) {
								out.close();
							}
							if (filecontent != null) {
								filecontent.close();
							}
						}
						break;
					}
				}

					if(txt_hinhanh.isEmpty() && hinhanh_cu != null){
						txt_hinhanh = hinhanh_cu;
					}
	
					//checkbox neu: on la checked, null la ko checked
					boolean active = (null != txt_status);
	
					tbl_banner tbl_banner = new tbl_banner();
					tbl_banner.setId_banner(Integer.parseInt(id_banner_link));
					tbl_banner.setTitle_banner(title_banner);
					tbl_banner.setContents(txt_content);
					tbl_banner.setLink(txt_linkURL);
					tbl_banner.setImages(txt_hinhanh);
					tbl_banner.setPos(Integer.parseInt(txt_pos));
					tbl_banner.setStatus(active);
	
					tbl_bannerBean bean = new tbl_bannerBean(ds);
					bean.setTbl_banner(tbl_banner);
					try {
						bean.suaBanner();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
			} 
			request.setAttribute("page", "QuanLyBanner");
			request.getRequestDispatcher("chuyentrang.jsp").forward(request,
					response);
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
