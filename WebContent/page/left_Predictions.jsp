<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="BusinessLogic.menuBean"%>
<%@page language="java" import="javax.sql.DataSource"%>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js" ></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
<link rel="stylesheet" href="css/pagination.css" type="text/css" /> 

<script type="text/javascript">
	ddaccordion.init({
		headerclass : "headerbar", //Shared CSS class name of headers group
		contentclass : "submenu", //Shared CSS class name of contents group
		revealtype : "mouseover", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay : 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev : true, //Collapse previous content (so only one open at any time)? true/false
		defaultexpanded : [ 0 ], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen : true, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault : false, //Should contents open by default be animated into view?
		persiststate : true, //persist state of opened contents within browser session?
		toggleclass : [ "", "selected" ], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml : [ "", "", "" ], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed : "normal", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit : function(headers, expandedindices) { //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose : function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
			//do nothing
		}
	});

	var bandau = 0;
	
	function pageselectCallback(page_index, jq){  //hien so trang len
		//alert('1111aaaaa1');
	     /*  alert('lan 2' + $("#thang_nam").val() ); */
	      // Get number of elements per pagionation page from form
	      var items_per_page = 5;
	      var offset = page_index*items_per_page;                    
	      var limit = items_per_page;
	      
	      $.post( "/eventweb/TinTheoThang_Highlights", {
	      	txt_link :  $("#thang_nam").val(),
	      	offset: offset,
	  	    limit: limit,
	      	action: 'list'
	      }, function(newcontent){	 
	 	        $('#cactin_highlights').html(newcontent);
	 	   	});        
	      // Prevent click eventpropagation
	      return false;
	  	}
	
	$(document).ready(function() {
		$('.basic').click(function(e) {
				/* alert($(this).attr('href')); */
		 	$("#thang_nam").val($(this).attr('href'));
		 	    /* alert($("#thang_nam").val()); */
			$.post("/eventweb/TinTheoThang_Highlights", {
				txt_link : $(this).attr('href'),
			    action: 'getlength'
			
			}, function(j){	    	  		
		 	        bandau = parseInt(j);
		 	       	if(bandau<0) bandau=0;	  
					var optInit = {callback: pageselectCallback}; 
	
					optInit['items_per_page'] = 5;
					optInit['num_display_entries'] = 4;  //Number of pagination links shown
					optInit['num_edge_entries'] = 2;  //Number of start and end points
					optInit['prev_text'] = "Previous";
					optInit['next_text'] = "Next";
					optInit['ellipse_text'] = "...";
					
					$("#Pagination").pagination(bandau, optInit);
				/* alert('222222'); */
				
		 	   	});
			return false;
			});
		});
</script>

<div class="urbangreymenu">

	<%!@Resource(name = "EventDB")
	private DataSource ds;%>
<%
	menuBean bean = new menuBean(ds);
	String div_m = bean.getMenuLeft(ds);
%>

<%
	out.println(div_m);
%>

<!-- 	<h3 class="headerbar" >
		<a href="#" id="2014"  >2014</a>
	</h3>
	<ul class="submenu">
		<li><a href="7_2014" class='basic'>7</a></li>
		<li><a href="6_2014" class='basic'>6</a></li>
	</ul>

	<h3 class="headerbar">
		<a href="#">2013</a>
	</h3>
	<ul class="submenu">
		<li><a href="6_2013" class='basic'>6</a></li>
		<li><a href="5_2013" class='basic'>5</a></li>
	</ul>
 -->	<input type = "hidden" id="thang_nam" name="thang_nam">
</div>





