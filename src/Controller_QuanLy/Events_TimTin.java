package Controller_QuanLy;

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
import Entity.tbl_taikhoan_quantri;

@WebServlet("/Events_TimTin")
public class Events_TimTin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public Events_TimTin() {
        super();
      
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			FunctionAll func = new FunctionAll();
			String ten_bai = null;
			Integer tu_ngay = null ;
			Integer den_ngay = null;
			
			if(request.getParameter("txt_tenbai")!=null && !request.getParameter("txt_tenbai").isEmpty()){
				ten_bai = request.getParameter("txt_tenbai");
			}
			
			if(request.getParameter("txt_ngaybd")!=null && !request.getParameter("txt_ngaybd").isEmpty()){
				tu_ngay = func.DoiNgayThangRaSoNguyen(request.getParameter("txt_ngaybd"));
			}
			
			if(request.getParameter("txt_ngayketthuc")!=null &&!request.getParameter("txt_ngayketthuc").isEmpty()){
				den_ngay = func.DoiNgayThangRaSoNguyen(request.getParameter("txt_ngayketthuc"));
			}
			
			tbl_eventsBean bean = new tbl_eventsBean(ds);
			PrintWriter out = response.getWriter();
			
			if (request.getParameter("action").equalsIgnoreCase("getlength")) {
				int tongso_dong = bean.layTongSoDongEvents_coDieuKien(ten_bai, String.valueOf(tu_ngay), String.valueOf(den_ngay));
				out.print(tongso_dong);  //in ra cho Jquery 
			
			}else if (request.getParameter("action").equalsIgnoreCase("list")) {
				Integer offset = Integer
						.valueOf(request.getParameter("offset"));
				Integer limit = Integer.valueOf(request.getParameter("limit"));
				ArrayList<tbl_events> list = bean.timkiem(ten_bai,String.valueOf(tu_ngay),String.valueOf(den_ngay),String.valueOf(limit),String.valueOf(offset));
				StringBuffer str = new StringBuffer();
				
				str.append(" <script>	 "
						+ "$('.deleteButton').click(function(e) {"
						+ "if (confirm('Are you sure?')) { "
						+ "var goTo = $(this).attr('href'); "
						+ " $.post('/eventweb/Events_XoaTin', { "
						+ "	id_news: goTo,"
    	 				+ "	}, function(j){	 "
    	 				+ "		if(j=='success'){ "
    	 				+ "			jAlert('Delete succeeded','Success');"
    	 				+ "		timkiemtheodieukien();	"
    	 				+ " 	}else{ "
    	 				+ "	jAlert('Failure. Error:' + j,'Error'); "
	  	    			+ "		}"
    	 				+ "	}); "
    	 				+ " } "
						+ "		return false;  	 	 }); "
						+ "</script>");
				
				str.append(		"<table cellspacing='0' cellpadding='3' rules='cols' border='1' id='tbl_timkiem' "
						+ "style='color: Black; background-color: White; border-color: #999999; border-width: 1px; border-style: Solid; width: 100%; "
						+ "border-collapse: collapse; ' >");
				str.append( 	"	<tbody>");
				str.append(		"");
						str.append(	"	<tr	style='color: White; background: url(js/menu/menu_source/images/menu-bg.png) repeat; font-weight: bold; height: 25px'>");
						str.append(	"		<th scope='col' style='width:18%'>Title</th> ");
						str.append(	"		<th scope='col' style='width:70%'>Summary</th>");
						str.append(	"			<th scope='col' style='width:9%' >Post Date</th>");
						str.append(	"	<th scope='col' style='width:1%'></th>");
						str.append(	"	<th scope='col' style='width:1%'></th>");
						str.append(	"	<th scope='col' style='width:1%'></th>");
						str.append( "	</tr> ");
				
				//System.out.print("list in ra" + list.size());
				int cnt = 1;
				for (int i = 0; i < list.size(); i++){
					if (cnt % 2 ==  0) {
							str.append("	<tr style='background-color:#ccc'> ");
					}
					cnt ++;
		      str.append("	<td>" +  list.get(i).getName_events() + "</td> ");
		      str.append("	<td>" +  list.get(i).getSummary() + " </td> ");
		      str.append("  <td style='font-size: small;'>" + func.DoiSoNguyenRaNgayThang(list.get(i).getPost_start_date()) + "</td>");
		      str.append("	<td style='width: 30px'> ");
		      str.append("  <a href='RedirectPage?action=xemnoidungEvents&id_tin_tuc=" + list.get(i).getId_events() + "' target='_blank'");
		      str.append("	style='color: Red;'> View </a></td>");
		      str.append("	<td style='width: 30px'> ");
		      str.append("  <a href='Events_SuaTin?id_tin_tuc=" + list.get(i).getId_events()  + "'" );
		      str.append("	style='color: Red;'> Edit </a></td>");
		      str.append("	<td style='width: 30px'> ");
		      str.append("  <a href='" + list.get(i).getId_events() + "' class='deleteButton'");
		      str.append("	style='color: Red;'> Delete </a></td>");
		      str.append("	</tr> ");
	      
			}
			  str.append("<tr> <td  colspan='9' style=' border-top-style: double;'>Tổng cộng: " + list.size() + " </td>	</tr></tbody></table> ");	
					
			   out.print(str.toString());
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
