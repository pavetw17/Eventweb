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

import BusinessLogic.tbl_others_staffBean;
import Entity.tbl_others_staff;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/Staff_FindStaff")
public class Staff_FindStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Staff_FindStaff() {
        super();
    }

    @Resource(name = "EventDB")
	private DataSource ds;
    
    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			String name_nv = null;
			if(request.getParameter("txt_name")!=null && !request.getParameter("txt_name").isEmpty()){
				name_nv = request.getParameter("txt_name");
			}
			
			tbl_others_staffBean bean = new tbl_others_staffBean(ds);
			if (request.getParameter("action").equalsIgnoreCase("getlength")) {
				int tongso_dong = bean.Staff_layTongSoDongStaff_coDieuKien(name_nv, Integer.parseInt(request.getParameter("id_project")));
				out.print(tongso_dong);  //in ra cho Jquery 
			} else if (request.getParameter("action").equalsIgnoreCase("list")){
				Integer offset = Integer.valueOf(request.getParameter("offset"));
				Integer limit = Integer.valueOf(request.getParameter("limit"));

				ArrayList<tbl_others_staff> list = bean.Staff_timkiem(name_nv,Integer.parseInt(request.getParameter("id_project")),String.valueOf(limit),String.valueOf(offset));
				StringBuffer str = new StringBuffer();
				
				str.append(" <script>	 "
						+ "$('.deleteButton').click(function(e) {"
						+ "if (confirm('Are you sure?')) { "
						+ "var goTo = $(this).attr('href'); "
						+ " $.post('/eventweb/Staff_DeleteStaff', { "
						+ "	id_nv: goTo,"
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
						str.append(	"		<th scope='col' style='width:18%'>Name</th> ");
						str.append(	"		<th scope='col' style='width:20%'>Photo</th>");
						str.append(	"			<th scope='col' style='width:70%' >Job</th>");
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
				      str.append("	<td>" +  list.get(i).getName_nv() + "</td> ");
				      if(list.get(i).getPhoto_nv() == null){
				    	  str.append("	<td> </td>");
				      }else {
				    	  str.append("	<td align='center'><img src='" +  list.get(i).getPhoto_nv() + "' style='width: 142px; height: 190px'> </td> ");
				      }
				      str.append("  <td style='font-size: small;'>" + list.get(i).getJob_nv() + "</td>");
				      str.append("	<td style='width: 30px'> ");
				      str.append("  <a href='Staff_EditStaff?id_nv=" + list.get(i).getId_nv() + "'" );
				      str.append("	style='color: Red;'> Edit </a></td>");
				      str.append("	<td style='width: 30px'> ");
				      str.append("  <a href='" + list.get(i).getId_nv() + "' class='deleteButton'");
				      str.append("	style='color: Red;'> Delete </a></td>");
				      str.append("	</tr> ");
			      
					}
					  str.append("<tr> <td  colspan='9' style=' border-top-style: double;'>Tổng cộng: " + list.size() + " </td>	</tr></tbody></table> ");	
							
					   out.print(str.toString());
				    }
			
		} else { // khong phai quanly
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
