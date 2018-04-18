<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="base.css" type="text/css" />
<script type='text/javascript' src='js/jquery-1.10.2.js'></script>
<script type="text/javascript">
			$(document).ready(
					function() {
						//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
						$("div.menu_head").click(
								function() {
									$(this).next("div.menu_body").slideToggle(
											300).siblings("div.menu_body")
											.slideUp("slow");

								});
						//slides the element with class "menu_body" when mouse is over the paragraph
					});
		</script>
</head>
<body>
	<div id="contetn_mainleft">



		
		<h2 class="headertext1">Working Schedule</h2>
		<table class="tablekh-hd">
			<thead>
				<tr>
					<td class="td1"><span class="spanheader">NUM </span></td>
					<td class="td2"><span class="spanheader">Content </span></td>
					<td class="td3"><span class="spanheader">Objectives</span></td>
					<td class="td4"><span class="spanheader">Status </span></td>
					<td class="td5"><span class="spanheader">Time </span>
						<p>
							<span> Begin </span> - <span> End </span>
						</p></td>
				</tr>
			</thead>
		</table>
		<div class="menu_head">
			<div id="divcthd">
				<table class="tablekh-hdcontent">
					<tbody>
						<tr class="trtitile">
							<td class="td1"><span class="number">1 </span></td>
							<td colspan="3" class="td6"><div class="divtitle">
									<span id="id0" class="h2titlekhhd selected">Project
										management, courses and training.</span>
								</div></td>
							<td class="td7"><span class="date1"></span> <span
								class="date2"></span></td>
						</tr>
					</tbody>
				</table>
				<!-- end divcthd-->
			</div>
			<script>
				$("#id0").click(function() {
					$(".divcthd").hide();
					$("#nd0").toggle("slow");
					$("#id0").addClass('selected')
				});
			</script>
			<!-- end div.menu_head-->
		</div>
		<div class="menu_body" style="display: none;">
			<table class="tablekh-hd">
				<tbody>
					<tr>
						<td class="td1">1.1</td>
						<td class="td2"><span>Scientist and staffs TOR</span></td>
						<td class="td3"><span>Scientist and staffs TOR</span></td>
						<td class="td4"><span>Completed</span></td>
						<td class="td5"><span class="date1">12/2011</span> - <span
							class="date2">12/2011</span></td>
					</tr>
					<tr>
						<td class="td1">1.2</td>
						<td class="td2"><a class="andkhhd"
							href="Plans.aspx?ctl=Listplan&amp;p=105&amp;LangID=2&amp;pID=41">PhD
								and MSc student selection</a></td>
						<td class="td3"><a class="andkhhd"
							href="Plans.aspx?ctl=Listplan&amp;p=105&amp;LangID=2&amp;pID=41">PhD
								and MSc student selection</a></td>
						<td class="td4"><a class="andkhhd"
							href="Plans.aspx?ctl=Listplan&amp;p=105&amp;LangID=2&amp;pID=41">Completed</a></td>
						<td class="td5"><span class="date1">12/2011</span> - <span
							class="date2">01/2012</span></td>
					</tr>
					<tr>
						<td class="td1">1.3</td>
						<td class="td2"><span>Setup office, purchase equipment</span></td>
						<td class="td3"><span>Setup office, purchase equipment</span></td>
						<td class="td4"><span>Ongoing</span></td>
						<td class="td5"><span class="date1">12/2011</span> - <span
							class="date2">05/2012</span></td>
					</tr>
					<tr>
						<td class="td1">1.4</td>
						<td class="td2"><span>PhD cources</span></td>
						<td class="td3"><span>PhD cources</span></td>
						<td class="td4"><span>Ongoing</span></td>
						<td class="td5"><span class="date1">12/2011</span> - <span
							class="date2">12/2012</span></td>
					</tr>
					<tr class="end">
						<td class="td1">1.5</td>
						<td class="td2"><span>Training and study tour</span></td>
						<td class="td3"><span>Training and study tour</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">01/2013</span> - <span
							class="date2">12/2013</span></td>
					</tr>
				</tbody>
				<!-- end tablekh-hd-->
			</table>
		</div>
		<div class="menu_head">
			<div id="divcthd">
				<table class="tablekh-hdcontent">
					<tbody>
						<tr class="trtitile">
							<td class="td1"><span class="number">2 </span></td>
							<td colspan="3" class="td6"><div class="divtitle">
									<span id="id1" class="h2titlekhhd">Climate change impact
										models of major aquaculture systems</span>
								</div></td>
							<td class="td7"><span class="date1"></span> <span
								class="date2"></span></td>
						</tr>
					</tbody>
				</table>
				<!-- end divcthd-->
			</div>
			<script>
				$("#id1").click(function() {
					$(".divcthd").hide();
					$("#nd1").toggle("slow");
					$("#id1").addClass('selected')
				});
			</script>
			<!-- end div.menu_head-->
		</div>
		<div class="menu_body">
			<table class="tablekh-hd">
				<tbody>
					<tr>
						<td class="td1">2.1</td>
						<td class="td2"><span>Construct GIS map of major
								aquaculture system of northern of Vietnam</span></td>
						<td class="td3"><span>Construct GIS map of major
								aquaculture system of northern of Vietnam</span></td>
						<td class="td4"><span>Ongoing</span></td>
						<td class="td5"><span class="date1">12/2011</span> - <span
							class="date2">12/2012</span></td>
					</tr>
					<tr>
						<td class="td1">2.2</td>
						<td class="td2"><span>Identify and illustrate key
								climatic factors that affect the aquculture production</span></td>
						<td class="td3"><span>Identify and illustrate key
								climatic factors that affect the aquculture production</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">01/2012</span> - <span
							class="date2">06/2013</span></td>
					</tr>
					<tr>
						<td class="td1">2.3</td>
						<td class="td2"><span>Identify and illustrate water
								quality parameters that affect the aquaculture production</span></td>
						<td class="td3"><span>Identify and illustrate water
								quality parameters that affect the aquaculture production</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">03/2012</span> - <span
							class="date2">09/2012</span></td>
					</tr>
					<tr class="end">
						<td class="td1">2.4</td>
						<td class="td2"><span>GIS modeling to predict the
								future impacts of climate change to major aquaculture system</span></td>
						<td class="td3"><span>GIS modeling to predict the
								future impacts of climate change to major aquaculture system</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">05/2012</span> - <span
							class="date2">11/2013</span></td>
					</tr>
				</tbody>
				<!-- end tablekh-hd-->
			</table>
		</div>
		<div class="menu_head">
			<div id="divcthd">
				<table class="tablekh-hdcontent">
					<tbody>
						<tr class="trtitile">
							<td class="td1"><span class="number">3 </span></td>
							<td colspan="3" class="td6"><div class="divtitle">
									<span id="id2" class="h2titlekhhd">Climate change and
										biosecurity of aquaculture</span>
								</div></td>
							<td class="td7"><span class="date1"></span> <span
								class="date2"></span></td>
						</tr>
					</tbody>
				</table>
				<!-- end divcthd-->
			</div>
			<script>
				$("#id2").click(function() {
					$(".divcthd").hide();
					$("#nd2").toggle("slow");
					$("#id2").addClass('selected')
				});
			</script>
			<!-- end div.menu_head-->
		</div>
		<div class="menu_body">
			<table class="tablekh-hd">
				<tbody>
					<tr>
						<td class="td1">3.1</td>
						<td class="td2"><span>Identify the impact of climate
								change on biodiversity of the pathogens of major aquaculture
								species</span></td>
						<td class="td3"><span>Identify the impact of climate
								change on biodiversity of the pathogens of major aquaculture
								species</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">02/2011</span> - <span
							class="date2">11/2013</span></td>
					</tr>
					<tr>
						<td class="td1">3.2</td>
						<td class="td2"><span>Describe impact of climate
								change on food safety of cultured aquatic organisms</span></td>
						<td class="td3"><span>Describe impact of climate
								change on food safety of cultured aquatic organisms</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">02/2011</span> - <span
							class="date2">11/2014</span></td>
					</tr>
					<tr class="end">
						<td class="td1">3.3</td>
						<td class="td2"><span>Describe current use and
								recommend prudent practice of probiotic and antimicrobial in
								aquaculture</span></td>
						<td class="td3"><span>Describe current use and
								recommend prudent practice of probiotic and antimicrobial in
								aquaculture</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">02/2011</span> - <span
							class="date2">11/2013</span></td>
					</tr>
				</tbody>
				<!-- end tablekh-hd-->
			</table>
		</div>
		<div class="menu_head">
			<div id="divcthd">
				<table class="tablekh-hdcontent">
					<tbody>
						<tr class="trtitile">
							<td class="td1"><span class="number">4 </span></td>
							<td colspan="3" class="td6"><div class="divtitle">
									<span id="id3" class="h2titlekhhd">Communication and
										dissemination</span>
								</div></td>
							<td class="td7"><span class="date1"></span> <span
								class="date2"></span></td>
						</tr>
					</tbody>
				</table>
				<!-- end divcthd-->
			</div>
			<script>
				$("#id3").click(function() {
					$(".divcthd").hide();
					$("#nd3").toggle("slow");
					$("#id3").addClass('selected')
				});
			</script>
			<!-- end div.menu_head-->
		</div>
		<div class="menu_body">
			<table class="tablekh-hd">
				<tbody>
					<tr>
						<td class="td1">4.1</td>
						<td class="td2"><span>Policy recommendation, adapting
								measures, and demonstrations</span></td>
						<td class="td3"><span>Policy recommendation, adapting
								measures, and demonstrations</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">11/2013</span> - <span
							class="date2">11/2014</span></td>
					</tr>
					<tr>
						<td class="td1">4.2</td>
						<td class="td2"><span>Website</span></td>
						<td class="td3"><span>Website</span></td>
						<td class="td4"><span>Ongoing</span></td>
						<td class="td5"><span class="date1">05/2012</span> - <span
							class="date2">12/2012</span></td>
					</tr>
					<tr>
						<td class="td1">4.3</td>
						<td class="td2"><span>Newsletter</span></td>
						<td class="td3"><span>Newsletter</span></td>
						<td class="td4"><span>Ongoing</span></td>
						<td class="td5"><span class="date1"></span> <span
							class="date2"></span></td>
					</tr>
					<tr>
						<td class="td1">4.4</td>
						<td class="td2"><span>Climate change networks</span></td>
						<td class="td3"><span>Climate change networks</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">12/2011</span> - <span
							class="date2">07/2012</span></td>
					</tr>
					<tr>
						<td class="td1">4.5</td>
						<td class="td2"><span>Danish Embassy and MOST</span></td>
						<td class="td3"><span>Danish Embassy and MOST</span></td>
						<td class="td4"><span>Ongoing</span></td>
						<td class="td5"><span class="date1">12/2011</span> - <span
							class="date2">05/2012</span></td>
					</tr>
					<tr class="end">
						<td class="td1">4.6</td>
						<td class="td2"><span>Newspaper, Television</span></td>
						<td class="td3"><span>Newspaper, Television</span></td>
						<td class="td4"><span>Preparing...</span></td>
						<td class="td5"><span class="date1">05/2012</span> - <span
							class="date2">11/2014</span></td>
					</tr>
				</tbody>
				<!-- end tablekh-hd-->
			</table>
		</div>
		<div class="menu_head">
			<div id="divcthd">
				<table class="tablekh-hdcontent">
					<tbody>
						<tr class="trtitile">
							<td class="td1"><span class="number">5 </span></td>
							<td colspan="3" class="td6"><div class="divtitle">
									<span id="id4" class="h2titlekhhd">Project Closure</span>
								</div></td>
							<td class="td7"><span class="date1">09/2014</span> - <span
								class="date2">11/2014</span></td>
						</tr>
					</tbody>
				</table>
				<!-- end divcthd-->
			</div>
			<script>
				$("#id4").click(function() {
					$(".divcthd").hide();
					$("#nd4").toggle("slow");
					$("#id4").addClass('selected')
				});
			</script>
			<!-- end div.menu_head-->
		</div>


		<div style="clear: both"></div>



		<!-- end div content main left-->
	</div>
</body>
</html> --%>

<!-- <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>  
<script src="js/ajaxupload.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	/* <-- Chọn vị trí ảnh thumbnail sẽ hiển thị  */
	var thumb = $('img#thumb');
	/*   Chọn input trong form có id là imageUpload   */
   	new AjaxUpload('imageUpload', {
		/*  Lấy thuộc tính action từ html 	 */	
		action: $('form#newHotnessForm').attr('action'),
		/* <-- Đặt tên để sử dụng với server side script  */
		name: 'image',
		/* <-- Sau khi chọn file thêm class loading vào div preview  */
		onSubmit: function(file, extension) {
			$('div.preview').addClass('loading');
		},
		/* <-- Sau khi file upload xong bỏ class loadding và hiển thị ảnh thumbnail bằng cách thay đổi thuộc tính src 
	 */	
	 
	 	onComplete: function(file, response) {
			thumb.load(function(){
				$('div.preview').removeClass('loading');
				thumb.unbind();
			});
			thumb.attr('src', response);
		}
	});
});
</script>


<div class="preview"> <img id="thumb" width="100px" height="100px" src="" /> </div>
<span class="wrap hotness">
<form id="newHotnessForm" action="/playground/ajax_upload">
  <label>Upload a Picture of Yourself</label>
  <input type="file" id="imageUpload" size="20" />
  <button type="submit" class="button">Save</button>
</form>
</span> -->


