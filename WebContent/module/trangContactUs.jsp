<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.tbl_others_aboutusBean"%>
<%@page language="java" import="Entity.tbl_others_aboutus"%>

<!-- Nap ban do -->
<link type="text/css" rel="stylesheet" href="js/map/style.css" />
<script type="text/javascript" src="js/map/demo.js"></script>
<script type="text/javascript" src="js/map/jquery.ui.map.js"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false"></script>

<script type="text/javascript">
	$(function() {
		demo.add(function() {
			$('#map_canvas').gmap({
				'center' : '21.092118, 105.785021',
				'zoom' : 16,
				'disableDefaultUI' : true,
				'callback' : function() {
					var self = this;
					self.addMarker({
						'position' : this.get('map').getCenter()
					}).click(function() {
						self.openInfoWindow({
							'content' : 'We are here!'
						}, this);
					});
				}
			});
		}).load();
	});
</script>

<div class="details_contactus">
	<div class="project_collection">
		<h2>Contact Us</h2>
	</div>

	<div class="chinhthuc">
		<div class="contents">
			<%!@Resource(name = "EventDB")
			private DataSource ds;%>
			
			<%
				tbl_others_aboutusBean bean = new tbl_others_aboutusBean(ds);
				tbl_others_aboutus tbl_about = bean.AboutUs_GetContents(2);
				
				StringBuffer buf = new StringBuffer();
				buf.append(tbl_about.getContents());
				
				out.print(buf.toString());
			%>
		
		</div>

		<div class="map_contactus">
			<div id="map_canvas" class="map rounded"></div>
		</div>
	</div>
</div>