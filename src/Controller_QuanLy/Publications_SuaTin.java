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

import BusinessLogic.tbl_tt_publicationsBean;
import Entity.tbl_taikhoan_quantri;
import Entity.tbl_tt_publications;

@WebServlet("/Publications_SuaTin")
@MultipartConfig(fileSizeThreshold=1024*1024,    // 1 MB maxMemSize
maxFileSize=1024*1024*50,          // 50 MB 
maxRequestSize=1024*1024*100)      // 100 MB
public class Publications_SuaTin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Publications_SuaTin() {
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
				tbl_tt_publicationsBean bean = new tbl_tt_publicationsBean(ds);
				tbl_tt_publications tbl_pub = bean.Publications_SuaTin_TimKimTheoMa(Integer.parseInt(id_tintuc));
				request.setAttribute("tbl_pub", tbl_pub);
				request.setAttribute("page", "trangPublications_SuaTin");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			
			} else if (request.getParameter("action").equalsIgnoreCase("ghivaoCSDL")) { 
				String tenbai = request.getParameter("txt_tenbai").trim();
				String tomtat = request.getParameter("txt_tomtat").trim();
				String noidung = request.getParameter("div_text").trim();
				String getid_tintuc = request.getParameter("getid_tintuc"); //lay idtintuc tu input file hidden ben trang jsp
				
					tbl_tt_publications tblPub = new tbl_tt_publications();
					tblPub.setId_publication(Integer.parseInt(getid_tintuc));
					tblPub.setName_publication(tenbai);
					tblPub.setSummary(tomtat);
					tblPub.setContents(noidung);
					
					tbl_tt_publicationsBean bean = new tbl_tt_publicationsBean(ds);
					bean.setTbl_tt_publications(tblPub);
					try {
						bean.Publications_SuaTin_CapNhatTheoMa();
						request.setAttribute("thongbaotrangthai","success_publications");
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
