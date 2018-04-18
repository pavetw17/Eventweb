package Controller_QuanLy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long maxFileSize = 50 * 1024 * 1024 ;  //50MB
	private int maxMemSize =  1024 * 1024;  // 1MB
	private static String defaultFolder = "uploads";

    public FileUpload() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processMethod(request,response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	private void processMethod(HttpServletRequest request,
			HttpServletResponse response) throws FileUploadException, IOException, ServletException 
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		boolean isMultipart= ServletFileUpload.isMultipartContent(request);
		if( !isMultipart ){
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	    }
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		 /*
         *Set the size threshold, above which content will be stored on disk.
         */
	    factory.setSizeThreshold(maxMemSize);
		
	    /*
         * Set the temporary directory to store the uploaded files of size above threshold.
         */
	    File tmpDir = new File(getServletContext().getRealPath(defaultFolder) + File.separator + "temp" );
	    if(!tmpDir.isDirectory()) {
            tmpDir.mkdirs();
        }
	    factory.setRepository(tmpDir);
	    
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setSizeMax( maxFileSize );
	      
	      try{ 
	    	  // Parse the request to get file items.
	          List fileItems = upload.parseRequest(request);
	          // Process the uploaded file items
	          Iterator i = fileItems.iterator();
	          
	          while ( i.hasNext () ) 
	          {
	        	  FileItem fi = (FileItem)i.next();
	        	  if(fi.isFormField()){
	        		  //Plain request parameters will come here.  
	        		  String name = fi.getFieldName();  
	        		  String value = fi.getString();  

	        		  System.out.println(name + "  " + value);
	        	  }
	        	  else if(!fi.isFormField()){	 //uploaded files will come here.
	        		  // Get the uploaded file parameters
	        		  String fileName = fi.getName();

	        		  File uploadedFile = new File(getServletContext().getRealPath(defaultFolder) + File.separator + fileName);

	        		  fi.write( uploadedFile ) ;
	        	  }
	          }
	          out.println("Upload File Completed");
	          deleteTmpFiles(tmpDir);
	       }
	      catch(SizeLimitExceededException ex)
	      {
	    	  System.out.println(ex.getMessage() + " size");
	    	  out.println(ex.getMessage() + "size");
	    	  
	      }
	      catch(Exception ex)
	      {
	    	  System.out.println(ex.getMessage());
	    	  out.println(ex.getMessage() + "size");
	      } 
	     
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processMethod(request,response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	 private void deleteTmpFiles(File tmpDir) throws Exception{
	        // Get all files in directory
	        File[] files = tmpDir.listFiles();
	        for (File file : files){
	            // Delete each file
	            if (!file.delete()){
	                // Failed to delete file
	                throw new Exception("Failed to delete "+file);
	            }
	        }
	       
	    }
}
