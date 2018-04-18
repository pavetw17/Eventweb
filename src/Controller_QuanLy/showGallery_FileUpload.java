package Controller_QuanLy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import BusinessLogic.tbl_gallery_imageBean;
import Entity.tbl_gallery_image;
import Entity.tbl_taikhoan_quantri;

@WebServlet("/showGallery_FileUpload")
public class showGallery_FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long maxFileSize = 50 * 1024 * 1024 ;  //50MB
	private int maxMemSize =  1024 * 1024;  // 1MB
	private static String defaultFolder = "uploads/lamviec";
	
	@Resource(name = "EventDB")
	private DataSource ds;
	
	public showGallery_FileUpload() {
		super();
	}

	protected void xuly(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SizeLimitExceededException {
		tbl_taikhoan_quantri tbl_qt = (tbl_taikhoan_quantri) request
				.getSession().getAttribute("tbl_qt");
		if (tbl_qt != null) {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			factory.setSizeThreshold(maxMemSize);

			
			String path_server = getServletContext().getRealPath(defaultFolder);
			File tmpDir = new File(path_server + File.separator + "temp" );
			if(!tmpDir.isDirectory()) {
				tmpDir.mkdirs();
			}
			factory.setRepository(tmpDir);

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax( maxFileSize );

			try{ 
				
				List fileItems = upload.parseRequest(request);
				
				Iterator  i = fileItems.iterator();
				String id_thumuc =  null; 
				ArrayList<String> listString_hinhanh = new ArrayList<>(); 
				while (i.hasNext ()) 
				{
					FileItem fi = (FileItem)i.next();
					if(fi.isFormField()){
						  
						String name = fi.getFieldName();  
						String value = fi.getString();  
						if ( name.equalsIgnoreCase("txt_foldername_select") ){
							 id_thumuc = value;
						}
						
					}
					else if(!fi.isFormField()){	 
						
						String fileName = fi.getName();
						listString_hinhanh.add(fileName); //lay ten anh
						File uploadedFile = new File(path_server + File.separator + fileName);
						fi.write( uploadedFile ) ;
					}
				}
				
				tbl_gallery_imageBean bean_image = new tbl_gallery_imageBean(ds);
				for(int j = 0 ; j< listString_hinhanh.size() ; j++){
						tbl_gallery_image tbl_image = new tbl_gallery_image();
						tbl_image.setId_thumuc(Integer.parseInt(id_thumuc));
						tbl_image.setLink_image(defaultFolder + File.separator + listString_hinhanh.get(j));
						tbl_image.setName_image(listString_hinhanh.get(j).substring(0, listString_hinhanh.get(j).indexOf(".")));
						bean_image.setTbl_gallery_image(tbl_image);
						bean_image.themImage();
					}
				
				out.println("Upload File Completed");
				deleteTmpFiles(tmpDir);
			}
			catch(SizeLimitExceededException ex)
			{   
				System.out.println(ex.getMessage() + " size");
				out.println("+++++ Error:" + ex.getMessage() + "size");
			}
			catch(Exception ex)
			{	
				System.out.println(ex.getMessage());
				out.println("+++++ Error:" + ex.getMessage() );
			} 

		} else { //khong phai quanly
			response.sendRedirect("admin/index.jsp");
		}
	}

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			xuly(request, response);
		} catch (SizeLimitExceededException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			xuly(request, response);
		} catch (SizeLimitExceededException e) {
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
