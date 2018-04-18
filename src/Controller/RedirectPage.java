package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_gallery_imageBean;
import BusinessLogic.tbl_others_ourpartnersBean;
import BusinessLogic.tbl_others_project_staffBean;
import Entity.tbl_gallery_image;
import Entity.tbl_others_ourpartners;

@WebServlet("/RedirectPage")
public class RedirectPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "EventDB")
	private DataSource ds;

	public RedirectPage() {
		super();
	}

	protected void XuLy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			if (action.equals("project")) {
				request.setAttribute("page", "TrangProject");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("xemnoidungProject")) {
				request.setAttribute("page", "XemNoiDungProject");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("events")) {
				request.setAttribute("page", "TrangEvents");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("xemnoidungEvents")) {
				request.setAttribute("page", "XemNoiDungEvents");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("aboutus")) {
				/*
				 * tbl_others_ourpartnersBean bean = new
				 * tbl_others_ourpartnersBean(ds);
				 * ArrayList<tbl_others_ourpartners> list_op =
				 * bean.OurPartner_GetLeft(); request.setAttribute("list",
				 * list_op);
				 */
				request.setAttribute("page", "TrangAboutUs");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("ourpartners")) {
				tbl_others_ourpartnersBean bean = new tbl_others_ourpartnersBean(
						ds);
				ArrayList<tbl_others_ourpartners> list_op = bean
						.OurPartner_SelectAll();
				request.setAttribute("list", list_op);
				request.setAttribute("page", "TrangOurPartners");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("contactus")) {
				request.setAttribute("page", "TrangContactUs");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("photoalbums")) {
				tbl_gallery_imageBean bean = new tbl_gallery_imageBean(ds);
				ArrayList<tbl_gallery_image> list_hienanh = bean
						.hienAnhMoiNhat_TrangPhoto();
				request.setAttribute("list", list_hienanh);
				request.setAttribute("page", "TrangPhotoAlbums");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("publications")) {
				request.setAttribute("page", "TrangPublications");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("xemnoidungPublications")) {
				request.setAttribute("page", "XemNoiDungPublications");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} /*
			 * else if (action.equals("projectstaff")) {
			 * request.setAttribute("page", "TrangProjectStaff");
			 * request.getRequestDispatcher("chuyentrang.jsp").forward( request,
			 * response); }
			 */else if (action.equals("workplan")) {
				request.setAttribute("page", "TrangWorkPlan");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} /*
			 * else if (action.equals("predictions")) {
			 * request.setAttribute("page", "TrangPredictions");
			 * request.getRequestDispatcher("chuyentrang.jsp").forward( request,
			 * response); }
			 */else if (action.equals("highlights")) {
				request.setAttribute("page", "TrangHighlights");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("xemnoidungHighlights")) {
				request.setAttribute("page", "XemNoiDungHighlights");
				request.getRequestDispatcher("chuyentrang.jsp").forward(
						request, response);
			} else if (action.equals("FindStaff")) {
				String id_pro_nv = request.getParameter("id_pro_nv");
				tbl_others_project_staffBean bean = new tbl_others_project_staffBean(
						ds);
				try {
					request.setAttribute("list_staff", bean
							.Staff_GetStaffaccordingProject(Integer
									.parseInt(id_pro_nv)));
					request.setAttribute("page", "trangStaff_AccordingProject");
					request.getRequestDispatcher("chuyentrang.jsp").forward(
							request, response);
				} catch (NumberFormatException e) {
					response.sendRedirect("index.jsp"); // detect sql injection
														// || cau lenh sql da
														// kiem tra nhap vao so
														// nguyen, neu khong
														// phai la loi
				} catch (SQLException e) {
					response.sendRedirect("index.jsp");
				}
			} else {
				response.sendRedirect("index.jsp");
			} 
		} else {
			response.sendRedirect("index.jsp");
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		XuLy(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		XuLy(request, response);
	}

}
