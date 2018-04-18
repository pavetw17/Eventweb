<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type='text/javascript' src='js/jquery-ui-1.10.4.custom.js'></script>
 <link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css" type="text/css" />
 <script>
  $(function() {
    $( "#accordion" ).accordion({
      heightStyle: "content"
    });
  });
  </script>
</head>
<body>

<ul id="accordion">
<li><div>About Us</div></li>
    <li><div>Our Staffs</div>
        <ul>
            <li><a href="#">Project Staffs</a></li>
            <li>&nbsp;&nbsp;</a></li>
            <li><a href="#">Project Technical Advisors and Local Staffs</a></li>
            <li><a href="#">Project's PhD Students and MSc Candidates</a></li>
        </ul>
    </li>
    <li><div>Our Partners</div>
        <ul>
            <li><a href="#">iPhone</a></li>
            <li><a href="#">Facebook</a></li>
            <li><a href="#">Twitter</a></li>
        </ul>
    </li>
    <li><div>Latest</div>
        <ul>
            <li><a href="#">Obama</a></li>
            <li><a href="#">Iran Election</a></li>
            <li><a href="#">Health Care</a></li>
        </ul>
    </li>
</ul>
</body>
</html>