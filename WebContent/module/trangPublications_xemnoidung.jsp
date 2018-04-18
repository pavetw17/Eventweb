<%@page import="BusinessLogic.tbl_tt_publicationsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="Entity.tbl_tt_publications"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.FunctionAll"%>

<%!@Resource(name = "EventDB")
	private DataSource ds;%>

<%
		String id_tintuc = request.getParameter("id_tin_tuc");
		if (id_tintuc != null && !id_tintuc.isEmpty() )  {
			if(id_tintuc.matches("-?\\d+(\\.\\d+)?")) { //kiem tra xem co phai la so khong
				/*  System.out.print(String.valueOf(id_tintuc).length());  */
				if(String.valueOf(id_tintuc).length() <= 5) { //kiem tra id_tin_tuc co phai 5 so khong
%>

<div class="details_highlights">
	<div class="project_collection">
		<h2>Publications</h2>
	</div>

	<div id="cactin_publications">
		<div class="chitiet_tintuc">
		<%		FunctionAll func = new  FunctionAll();
				tbl_tt_publicationsBean bean = new tbl_tt_publicationsBean(ds);
				ArrayList<tbl_tt_publications> list = bean.Publications_XemNoiDungTinTheoMa(id_tintuc);
				if(list.size() > 0) {
					StringBuffer buf = new StringBuffer();
					buf.append("<div class='tieude_tintuc'>" + list.get(0).getName_publication() + "");
					buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(0).getPost_start_date()) + ")</span></div>");
					buf.append("<div class='tomtat_tintuc'>" + list.get(0).getSummary() + "</div>");
					buf.append("<div class='noidung_tintuc'>" + list.get(0).getContents() + "</div>");
					
					buf.append("<div class='divquaylai'><a href='javascript:history.back()' class='buttonQuayLai'>Back</a></div>");
					buf.append("<div class='tinkhac'>News other</div>");
					
					out.print(buf.toString());
					buf.setLength(0);
				
				for(int i= 1;i<list.size();i++){
					buf.append("<li><a href='RedirectPage?action=xemnoidungPublications&id_tin_tuc=" +  list.get(i).getId_publication() +"' title='"+ list.get(i).getSummary() +"'>" + list.get(i).getName_publication() + "</a>");
					buf.append("<span class='date'>(" + func.DoiSoNguyenRaNgayThang(list.get(i).getPost_start_date()) + ")</span></li>");
				}
		%>
		</div>
		<div class="list_events">
			<ol class="list_ol">
				<%
					out.print(buf.toString());
				%>
			</ol>
		</div>
	</div>

	<div id="Pagination" class="pagination"></div>
</div>
<%   } else {  //id tin tuc vuot qua 5 so
			%>
			<meta http-equiv="refresh" content="0; url=index.jsp" />
		<%
		    }

				
		} else {  //list.size==0
			%>
					<meta http-equiv="refresh" content="0; url=index.jsp" />
			<%
		}
	} else { //id_tin_tuc khong phai la number
		%>
		<meta http-equiv="refresh" content="0; url=index.jsp" />
		<%
	}
			
} else {  //id_tin_tuc is null or empty
	%>
<meta http-equiv="refresh" content="0; url=index.jsp" />
	<%
}

%>

