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
import BusinessLogic.tbl_eventsBean;
import Entity.tbl_events;

@WebServlet("/Events_TinTheoThang")
public class Events_TinTheoThang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Events_TinTheoThang() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			
			FunctionAll func = new  FunctionAll();
			tbl_eventsBean bean = new tbl_eventsBean(ds);
			String txt_link = request.getParameter("txt_link");
			String[] arr = txt_link.split("_");
			int m_Thang = Integer.parseInt(arr[0]);
			int m_Nam = Integer.parseInt(arr[1]);
			PrintWriter out = response.getWriter();
			
			if (action.equals("getlength")) {
				int tongso_dong = bean.Events_Laytongsotin(m_Thang, m_Nam);
				out.print(tongso_dong); 

			}else if (action.equals("list")) {
				Integer offset = Integer
						.valueOf(request.getParameter("offset"));
				Integer limit = Integer.valueOf(request.getParameter("limit"));
				ArrayList<tbl_events> list = bean.Events_Hientin(m_Thang,
						m_Nam, limit, offset);
				StringBuffer str = new StringBuffer();
				for (int i = 0; i < list.size(); i++) {
					str.append("<div class='content_events'>");
					str.append("<div class='title_events'>");
					str.append("<a href='RedirectPage?action=xemnoidungEvents&id_tin_tuc="+ list.get(i).getId_events() +"'>"+ list.get(i).getName_events()+ "</a> <br> <span class='date'>("+ func.DoiSoNguyenRaNgayThang(list.get(i).getPost_start_date()) +")</span>");
					str.append("</div>");
					str.append("<div class='details_events'>");
					if(list.get(i).getPhoto() != null){
						str.append("<div class='details_left_events'> <img src='" + list.get(i).getPhoto() +"' align='left'style='width:150px; height:100px; margin-right: 10px;' >" + list.get(i).getSummary() +"</div>");
					}else {
						str.append("<div class='details_left_events'>" + list.get(i).getSummary() +"</div>");
					}
					str.append("<div class='details_right_events'><p><b>Dates</b>");
					str.append("<br> From: "+ func.DoiSoNguyenRaNgayThang(list.get(i).getStart_date_events()) +"<br> To: "+ func.DoiSoNguyenRaNgayThang(list.get(i).getEnd_date_events()));
					str.append("<br> " + func.formatTimestampAsTwelveHour_string(list.get(i).getStart_time()) + " - " + func.formatTimestampAsTwelveHour_string(list.get(i).getEnd_time()) + "</p>");
					str.append("<p>	<b>Location</b><br>" + list.get(i).getLocation() +"</p></div></div></div>");			
				}

				out.print(str.toString());
			}
			
		} else {
			response.sendRedirect("index.jsp");
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

}
