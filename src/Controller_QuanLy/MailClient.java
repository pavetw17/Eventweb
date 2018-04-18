package Controller_QuanLy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import BusinessLogic.tbl_taikhoan_quantriBean;

@WebServlet("/MailClient")
public class MailClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "EventDB")
	private DataSource ds;
	
    public MailClient() {
        super();
    }

    protected void xuly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String to_email = request.getParameter("txt_email");
		String question = request.getParameter("txt_teacher");
		if((to_email != null && !to_email.isEmpty()) && (question != null && !question.isEmpty()) ){
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
	    	try {
				tbl_taikhoan_quantriBean bean = new tbl_taikhoan_quantriBean(ds);
				try {
					int id_account = bean.QuanTri_CheckEmail(to_email,question);
					if(id_account != 0){
						String[] arr = to_email.split("@");
			    		String smtp_isp = arr[1];
			    		String password = getSaltString();
			    		String from_email = "phanmemthuyloi2014@gmail.com";
			    		String pass_email = "phanmemthuyloi269";
			    		String noidung = "<h1> Hello</h1> "
			    				+ " <br> New Password: " + password;
			    		bean.QuanTri_SetUpdateForgotPassword(password, id_account);
			    		if(smtp_isp.equalsIgnoreCase("gmail.com")){
			    			send("smtp.gmail.com", to_email, from_email, pass_email, "Forgot Password", noidung);
			    			out.print("success");
			    		} else if (smtp_isp.equalsIgnoreCase("yahoo.com")){
			    			send("smtp.mail.yahoo.com", to_email, from_email, pass_email, "Forgot Password", noidung);
			    			out.print("success");
			    		}
					} else{
						out.print("Please check your email address. " );
					}
				} catch (SQLException e) {
					out.print("Please check your email address or data. " + e.toString());
				}
	    	} catch (AddressException e) {
	    		out.print("Address failed 1. " + e.toString());
	    	//	System.out.println("Sending failed 	1. " + e);
			//	Logger.getLogger(MailClient.class.getName()).log(Level.SEVERE, null);
			} catch (MessagingException e) {
				out.print("Sending failed 2. " + e.toString());
			//	System.out.println("Sending failed 2. " + e);
			//	Logger.getLogger(MailClient.class.getName()).log(Level.SEVERE, null);
			}
		} else {
			response.sendRedirect("index.jsp");
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		xuly(request, response);
	}

	public static void send(String smtpServer, String to, String from, String pwd,
            String subject, String body) throws AddressException, MessagingException{
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", javax.net.ssl.SSLSocketFactory.class.getName());
        
        final String login = from;
        final String f_pwd = pwd;
        
        Session session = Session.getDefaultInstance(props, new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(login, f_pwd);
        	}
        });
        
        // — Create a new message –
        Message msg = new MimeMessage(session);
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		msg.setSubject(subject);
		msg.setContent(body, "text/html; charset=UTF-8");
			
		Transport.send(msg);
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
