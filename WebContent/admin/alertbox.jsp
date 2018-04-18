<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="admin/template/alert/jquery.alerts.js"></script>
<link rel="stylesheet" href="admin/template/alert/jquery.alerts.css"
	type="text/css" />


<script type="text/javascript">
    function thanhcong_publications() {
    	jAlert("Article posted successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showTinTuc?action=publications';",1000);
    }
    
    function thanhcong_highlights() {
    	jAlert("Article posted successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showTinTuc?action=highlights';",1000);
    }
    
    function thanhcong_project() {
    	jAlert("Article posted successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showTinTuc?action=projects';",1000);
    }
    
    function thanhcong_events() {
    	jAlert("Article posted successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showTinTuc?action=events';",1000);
    }
    
    function thanhcong_workplan() {
    	jAlert("Article posted successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showTinTuc?action=workplan#horizontalTab2';",1000);
    }
    
    function thanhcong_ourpartners() {
    	jAlert("Article posted successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showOthers?action=ourpartners';",1000);
    }
    
    function thanhcong_ourstaff() {
    	jAlert("Successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showOthers?action=ourstaff#horizontalTab2';",1000);
    }
    
    function thanhcong_joinus() {
    	jAlert("Successfully","Successed"); 
 		setTimeout("location.href = '<%=request.getContextPath()%>/showOthers?action=joinus';",500);
    }
    
    function khongthanhcong(j){
    	jAlert("Failure. Error" + j  ,"Alert");
 		<%--  setTimeout("location.href = '<%=request.getContextPath()%>/LoiSQL.jsp';",1500);  chay OK--%>
 		 setTimeout('history.go(-1)',1500);
    }
    
</script>

<c:choose>
	<c:when test="${thongbaotrangthai eq 'success'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_highlights();
		      });
		    </script>
	</c:when>
	<c:when test="${thongbaotrangthai eq 'success_publications'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_publications();
		      });
		    </script>
	</c:when>
	<c:when test="${thongbaotrangthai eq 'success_joinus'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_joinus();
		      });
		    </script>
	</c:when>
	<c:when test="${thongbaotrangthai eq 'success_project'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_project();
		      });
		    </script>
	</c:when>
	<c:when test="${thongbaotrangthai eq 'success_events'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_events();
		      });
		    </script>
	</c:when>
	<c:when test="${thongbaotrangthai eq 'success_workplan'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_workplan();
		      });
		    </script>
	</c:when>
	<c:when test="${thongbaotrangthai eq 'success_ourpartners'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_ourpartners();
		      });
		    </script>
	</c:when>
	<c:when test="${thongbaotrangthai eq 'success_staff'}">
			<script type="text/javascript">  
		      $(document).ready(function(){
		      	thanhcong_ourstaff();
		      });
		    </script>
	</c:when>
	<c:otherwise>
			<script type="text/javascript">
			var someJsVar = "<c:out value='${thongbaotrangthai}'/>";
	      	$(document).ready(function(){
	    	  khongthanhcong(someJsVar);
	        });
	      </script>
	</c:otherwise>
</c:choose>


