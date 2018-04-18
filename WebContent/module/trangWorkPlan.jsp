<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page language="java" import="javax.annotation.Resource"%>
<%@page language="java" import="javax.sql.DataSource"%>
<%@page language="java" import="BusinessLogic.tbl_workplanBean"%>
<%@page language="java" import="Entity.tbl_news"%>
<%@page language="java" import="java.util.ArrayList"%>
<%@page language="java" import="BusinessLogic.FunctionAll"%>	
	
	
	<script type="text/javascript">
		$(document).ready(
				function() {
					//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
					$("div.menu_head").click(
							function() {
								$(this).next("div.menu_body").slideToggle(300)
										.siblings("div.menu_body").slideUp("slow");

							});
					//slides the element with class "menu_body" when mouse is over the paragraph
				/* 	siblings(), chức năng của hàm này tức là nó quét tất cả các thẻ kề cận giống nó. Khi mà chúng ta click vào thì cái mình đang click thì nó không tính, nó sẽ tình những cái thẻ li nào còn lại ở bên ngoài,
				 */
				});
	</script>

<div id="contetn_main">
		<div class="project_collection"><h2>Working Schedule</h2></div>
	<table class="table_results" style="background-color: rgb(20, 65, 3); border-spacing: 2px;">
		<thead>
			<tr>
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
	
	<div id="noidung">
		<%!@Resource(name = "EventDB")
	   private DataSource ds;%>
	
		<%
			tbl_workplanBean bean = new tbl_workplanBean(ds);
			out.print(bean.Workplan_showHome());
		%>
	</div>
	
</div>






<!--<div class="menu_head">
		 div id="divcthd">
			<table class="table_results_content">
				<tbody>
					<tr class="trtitile">
						<td colspan="3" class="td6"><div class="divtitle">
								<span id="id0" class="h2title_results">Project
									management, courses and training.</span>
							</div></td>
						<td class="td7"><span class="date1"></span> <span
							class="date2"></span></td>
					</tr>
				</tbody>
			</table>
			end divcthd
		</div>
		end div.menu_head
	</div>
	<div class="menu_body ">
		<table class="table_results">
			<tbody>
				<tr>
					<td class="td2"><span>Scientist and staffs TOR</span></td>
					<td class="td3"><span>Scientist and staffs TOR</span></td>
					<td class="td4"><span>Completed</span></td>
					<td class="td5"><span class="date1">12/2011</span> - <span
						class="date2">12/2011</span></td>
				</tr>
				<tr>
					<td class="td2"><span>Setup office, purchase equipment</span></td>
					<td class="td3"><span>Setup office, purchase equipment</span></td>
					<td class="td4"><span>Ongoing</span></td>
					<td class="td5"><span class="date1">12/2011</span> - <span
						class="date2">05/2012</span></td>
				</tr>
			</tbody>
			end tablekh-hd
		</table> -->
			<!-- <div style="clear: both"></div>

	end div content main left
</div>--> 
		