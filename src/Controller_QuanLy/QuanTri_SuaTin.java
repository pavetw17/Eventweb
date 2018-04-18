package Controller_QuanLy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.FunctionAll;
import BusinessLogic.tbl_taikhoan_quantriBean;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/QuanTri_SuaTin")
public class QuanTri_SuaTin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuanTri_SuaTin() {
        super();
    }
    
	@Resource(name = "EventDB")
	private DataSource ds;

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			String username = request.getParameter("txt_username");
			String password = request.getParameter("txt_password");
			String email = request.getParameter("txt_email");
			String question = request.getParameter("txt_question");
			
			if((username != null && !username.isEmpty()) && (password != null && !password.isEmpty()) 
					&& (email != null && !email.isEmpty()) && (question != null && !question.isEmpty()) ){
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				tbl_taikhoan_quantri tbl_qt_edit = new tbl_taikhoan_quantri();
				tbl_qt_edit.setPassword(FunctionAll.getMD5(password));
				tbl_qt_edit.setUsername(username);
				tbl_qt_edit.setEmail_quantri(email);
				tbl_qt_edit.setId_account(tbl_qt.getId_account());
				tbl_qt_edit.setQuestion(question);
				
				tbl_taikhoan_quantriBean bean = new tbl_taikhoan_quantriBean(ds);
				bean.setTbl_quantri(tbl_qt_edit);
				try {
					bean.QuanTri_UpdateAccount();
					out.print("success");
				} catch (SQLException e) {
					out.print("Error Sql" + e.toString());
				}
			} else {
				response.sendRedirect("404.jsp");
			}
			
		} else { // khong phai quanly
			response.sendRedirect("admin/index.jsp");
		}
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	xuly(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

}
