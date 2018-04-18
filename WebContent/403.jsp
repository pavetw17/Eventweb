
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>403</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    	<div align="center">
         <!--   <img src="images/unauthorized.JPG" alt="403"> -->
            <h1 style="color: red;"> Permission Denied!</h1>
          <h2> Click <a href="<%=request.getContextPath()%>/index.jsp">here </a> to try again!</h2>
    	</div>
    </body>
</html>
