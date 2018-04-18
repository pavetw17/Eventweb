<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.tbl_qt.username}">
	<jsp:forward page="../403.jsp"></jsp:forward>
</c:if>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript"	src="admin/template/alert/jquery.alerts.js"></script>
<link rel="stylesheet" href="admin/template/alert/jquery.alerts.css" type="text/css" />
<script type="text/javascript" src="js/jquery.pagination.js" ></script>
<link rel="stylesheet" href="css/pagination.css" type="text/css" />  
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css" type="text/css" />


<script>
	$(function() {
		$("#dp_ngaybd").datepicker({
			numberOfMonths : 1,
			dateFormat : 'dd-mm-yy',
			changeMonth : true,
			changeYear : true,
			yearRange : '2012:2020',
		 	onSelect : function(selected) {
				$("#dp_ngayketthuc").datepicker("option", "minDate", selected);
			}
		
		}).attr('readonly', 'readonly').val("01-01-2014");
		
		$("#dp_ngayketthuc").datepicker({
			numberOfMonths : 1,
			dateFormat : 'dd-mm-yy',
			changeMonth : true,
			changeYear : true,
			yearRange : '2012:2020',
			onSelect : function(selected) {
			
				$("#dp_ngaybd").datepicker("option", "maxDate", selected);
			}
		}).attr('readonly', 'readonly').val($.datepicker.formatDate("dd-mm-yy", new Date()));
	});

</script>

<style type="text/css">
div.error {
	color: #C00;
	font-size: 14px;
	position: relative;
}
</style>
<script type="text/javascript">
	var bandau = 0;
	function pageselectCallback(page_index, jq){  //hien so trang len
		//alert('1111aaaaa1');

      // Get number of elements per pagionation page from form
      var items_per_page = 10;
      var offset = page_index*items_per_page;                    
     // var limit = Math.min((page_index+1) * items_per_page, bandau); //so dong
      var limit = items_per_page;
  	//alert(page_index + '  ' + offset);
      
      $.post( "/eventweb/Highlights_TimTin", {
      	action: 'list',
      	    txt_tenbai: $("#txt_tenbai").val(),
		   	txt_ngaybd: $("#dp_ngaybd").val(),
		   	txt_ngayketthuc: $("#dp_ngayketthuc").val(),
      	offset: offset,
  	    limit: limit
      }, function(newcontent){	 
 	        $('#tbl_timkiem').html(newcontent);
 	   	});        
      // Prevent click eventpropagation
      return false;
  	}  

	function timkiemtheodieukien(){
		 /*   var ten_bai = $("#txt_tenbai").val(); 
		   var ngay_bd = $("#dp_ngaybd").val();
		   var ngay_kt = $("#dp_ngayketthuc").val(); */
		  
		  // alert(ngay_bd);
		  
		$.post("/eventweb/Highlights_TimTin", {
			
			    txt_tenbai: $("#txt_tenbai").val(),
			   	txt_ngaybd: $("#dp_ngaybd").val(),
			   	txt_ngayketthuc: $("#dp_ngayketthuc").val(),
		   action: 'getlength'
 	   	}, function(j){	    	  		
	  
 	        bandau = parseInt(j);
 	       	if(bandau<0) bandau=0;	  
			var optInit = {callback: pageselectCallback};

			optInit['items_per_page'] = 10;
			optInit['num_display_entries'] = 4;  //Number of pagination links shown
			optInit['num_edge_entries'] = 2;  //Number of start and end points
			optInit['prev_text'] = "Previous";
			optInit['next_text'] = "Next";
			optInit['ellipse_text'] = "...";
			
			$("#Pagination").pagination(bandau, optInit);
 	   	});
	}	

$(document).ready(function(){
	
	  $("#btn_submit").click(function(){
		  var opt = timkiemtheodieukien();
          // Re-create pagination content with new parameters
          $("#Pagination").pagination(bandau, opt);
		  
      });
	  
	  $("#btn_lamtuoi").click(function(){
		  $("#txt_tenbai").val(''); 
		   $("#dp_ngaybd").val('');
		   $("#dp_ngayketthuc").val(''); 
		   
      });

	  $("#btn_themtin").click(function(){
		  window.location.href='showTinTuc?action=themHighlights';
      });
	  
});
	
$(document).keypress(function(event){
    var keycode = (event.keyCode ? event.keyCode : event.which);
    if(keycode == '13'){
        //alert('Bạn vừa nhấn phím "enter" trên trang web'); 
    	timkiemtheodieukien();
    }
});
  	 
</script>
	  

<table style="width: 100%;">
	<tr>
		<td><span ID="lblTK"
			style="font-weight: 700; color: blue; font-size: 16pt">News Highlights Management</span></td>
	</tr>
	<tr>
		<td style="width: 80px" align="right"></td>
		<td style="width: 80px" align="right"></td>
	</tr>
</table>
<form >
	<table cellpadding="4" cellspacing="0" width="100%"
		style="border: 1px solid">
		<tr>
			<td colspan="6"
				style="background: url(js/menu/menu_source/images/menu-bg.png) repeat; color: #FFF; font-weight: 700;">User Input</td>
		</tr>
		<tr>
			<td width="19%" align="right">Title:</td>
			<td width="2%">&nbsp;</td>
			<td width="30%"><input type="text" id="txt_tenbai"
				name="txt_tenbai" style="width: 250px" /></td>
		</tr>
		<tr>
			<td width="19%" align="right">Start date:</td>
			<td width="2%">&nbsp;</td>
			<td width="28%"><input type="text" id="dp_ngaybd"
				name="txt_ngaybd" /></td>
		</tr>
		<tr>
			<td width="19%" align="right">End date:</td>
			<td width="2%">&nbsp;</td>
			<td width="28%"><input type="text" id="dp_ngayketthuc"
				name="txt_ngayketthuc" /></td>
		</tr>
		<tr>
			<td width="19%"></td>
			<td width="2%">&nbsp;</td>
			<td width="28%" align="left"><div class="buttom"
					style="float: left !important;">
						<input type="button" name="btn_submit" value=" Search " id="btn_submit"
							class="button1"  style="  font-size: 12pt; width: 70px;"/>
				</div></td>
				
			<td width="19%" align="right"><div class="buttom"
					style="float: left !important;">
						<input type="button" name="btn_lamtuoi" value=" Reset " id="btn_lamtuoi"
							class="button1"   style="  font-size: 12pt "/>
				</div></td>
			<td width="2%">&nbsp;</td>
			<td width="28%" align="left"></td>
		</tr>
	</table>
</form>
<br>

<input type="button" name="btn_themtin" value=" Add Your News " id="btn_themtin"
							class="button1"   style="  font-size: 12pt "/>
<br><br/>

<table cellspacing="0" cellpadding="3" rules="cols" border="1" id="tbl_timkiem"
	style="color: Black; background-color: White; border-color: #999999; border-width: 1px; border-style: Solid; width: 100%; border-collapse: collapse;">
</table>
		 <div id="Pagination" class="pagination">
            </div>	

