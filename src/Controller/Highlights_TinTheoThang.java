package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.FunctionAll;
import BusinessLogic.tbl_newsBean;
import Entity.tbl_news;

@WebServlet("/Highlights_TinTheoThang")
public class Highlights_TinTheoThang extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "EventDB")
	private DataSource ds;

	public Highlights_TinTheoThang() {
		super();
	}

	protected void xuly(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			FunctionAll func = new  FunctionAll();
			tbl_newsBean bean = new tbl_newsBean(ds);
			String txt_link = request.getParameter("txt_link");
			String[] arr = txt_link.split("_");
			int m_Thang = Integer.parseInt(arr[0]);
			int m_Nam = Integer.parseInt(arr[1]);
			PrintWriter out = response.getWriter();

			if (action.equals("getlength")) {
				int tongso_dong = bean.layTongSoTin_Highlights(m_Thang, m_Nam);
				out.print(tongso_dong); // in ra cho Jquery

			} else if (action.equals("list")) {
				Integer offset = Integer
						.valueOf(request.getParameter("offset"));
				Integer limit = Integer.valueOf(request.getParameter("limit"));
				ArrayList<tbl_news> list = bean.HienTin_Highlights(m_Thang,
						m_Nam, limit, offset);
				StringBuffer str = new StringBuffer();
				for (int i = 0; i < list.size(); i++) {
					str.append("<div class='content_highlights'>");
					if(list.get(i).getPhoto() != null) { 
						str.append("<div class='img_highlights'>");
						str.append("<img src='" + list.get(i).getPhoto()
								+ "' style='width:200px; height:200px'>");
						str.append("</div>");
					}
					str.append("<div class='title_highlights'><a href='RedirectPage?action=xemnoidungHighlights&id_tin_tuc=" +  list.get(i).getId_news() +"'>"
							+ list.get(i).getName_news() + "</a><span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(i).getStart_date()) + ")</span></div>");
					str.append("<div class='text_highlights'>"
							+ list.get(i).getSummary()
							+ " "
							+ "<a href='RedirectPage?action=xemnoidungHighlights&id_tin_tuc=" +  list.get(i).getId_news() +"'> Read more...</a></div>");
					str.append("</div>");
				}

				out.print(str.toString());
			}
		} else {
			response.sendRedirect("index.jsp");
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

}
