<%@page import="BusinessLogic.tbl_others_joinusBean"%>
<%@page language="java" import="Entity.tbl_others_joinus"%>
<%@page import="Entity.tbl_gallery_image"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.tbl_others_aboutusBean"%>
<%@page language="java" import="Entity.tbl_others_aboutus"%>
<%@page import="BusinessLogic.tbl_gallery_imageBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%!@Resource(name = "EventDB")
   private DataSource ds;%>

<script type="text/javascript">
    $(function() {
        $('#gallery a').lightBox();
    });
</script>

<div id="templatemo_footer_wrapper">
	<div id="templatemo_footer">

		<!-- <div class="footer_box">
			<h4>Partners</h4>
			<ol class="footer_list">
				<li><a href="#">Dsaa</a></li>
			</ol>
		</div> -->
		
		<div class="footer_box ">
			<h4>
					<a href="RedirectPage?action=contactus">Contact Us</a>
			</h4>
			<p>
			<%
				tbl_others_aboutusBean bean_ab = new tbl_others_aboutusBean(ds);
				tbl_others_aboutus tbl_about = bean_ab.AboutUs_GetContents(2);
				
				StringBuffer buf = new StringBuffer();
				buf.append(tbl_about.getContents());
				
				out.print(buf.toString());
			%></p>
		</div>

		<div class="footer_box">
			<h4>Join Us</h4>
			<ul style="padding: 0 0 0 0">
			 <%
				tbl_others_joinusBean bean_joinus = new tbl_others_joinusBean(ds);
				ArrayList<tbl_others_joinus> list_ju = bean_joinus.JoinUs_GetAllList();
				for(int i = 0; i< list_ju.size(); i++ ){
					out.print("<li class='service-list'>");
					out.print("<a href='http://"+ list_ju.get(i).getLink_joinus() +"'  target='_new'><img src='"+ list_ju.get(i).getImage_joinus() +"'title='"+ list_ju.get(i).getName_joinus() +"' class='img_social'> </a>");
					out.print( "<p ><a href='http://"+ list_ju.get(i).getLink_joinus() +"'  target='_new'> " + list_ju.get(i).getName_joinus() + "</a></p> </li>");
				}
			%> 
			</ul>
                                        
		</div>

		<div class="photo_album">
				<h4>
					<a href="RedirectPage?action=photoalbums">Photo Albums</a>
				</h4>
				<table id="table_ha">
					<tr class="td1">
					<%
						tbl_gallery_imageBean bean = new tbl_gallery_imageBean(ds);
						ArrayList<tbl_gallery_image> list = bean.hien4Anh_TrangFooter();
						for(int i = 0; i< list.size(); i++ ){
							out.print("<td id='gallery'><a href='"+ list.get(i).getLink_image() +"'title='"+ list.get(i).getName_image() +"'>");
							out.print("<img src='" + list.get(i).getLink_image() +"' style='width: 118px; height: 88px'></a></td>");
							out.print("<td>&nbsp;&nbsp;</td>");
							if((i+1) % 2 == 0){
								out.print("</tr> <tr class='td1'>");
							}
						}
					%>
				
					<!-- <tr class="td1">
					<td id="gallery"><a href="uploads/lamviec/IMAGE1662.jpg"
						title="Hội nghị tổng kết"> <img
							src="uploads/lamviec/IMAGE1662.jpg"
							style="width: 118px; height: 88px"></a></td>
					</tr>
				 -->
				</table>
		</div>

		
		<!-- <div class="footer_box ">
			<h4>About Us</h4>
			<p>N.</p>
		</div> -->

		<div class="cleaner"></div>
	</div>
	<!-- end of footer -->

	<div id="copyright">

		<div class="left">
			Copyright © 2014 <a href="#">Your Company Name</a><br> Designed
			by <a href="http://phanmemthuyloi.vn/" target="_parent">Centre
				For Water Resources Software</a>
		</div>
		<div class="right">
			<a href="index.jsp">home</a><a href="RedirectPage?action=aboutus">about us</a><a href="RedirectPage?action=contactus">contact us</a>
		</div>
	</div>
	<!-- end of templatemo_footer -->
</div>