package com.rupp.sample.web;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private boolean isMultipart;
    private String filePath;
    //50k
    private int maxFileSize = 5000 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    
    @Override
    public void init() throws ServletException {    	
        filePath = getServletContext().getRealPath("")+getServletContext().getInitParameter("file-upload"); 
        System.out.println("My file path:"+filePath);       
        
    }

   
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        
        HttpSession session	=	request.getSession();
        int id	=	0;
        String email	=	"";
        String createDate	=	"";
        String urlPhoto		=	"";
       
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        String tempDir = System.getProperty("java.io.tmpdir");
        factory.setRepository(new File(tempDir));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator<FileItem> i = fileItems.iterator();

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    //String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    //String contentType = fi.getContentType();
                    //boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    System.out.println(String.format(" fileName [%s] size [%s]", fileName, sizeInBytes));
                    
                    // Write the file
                    file = new File(filePath + fileName);                    
                    fi.write(file);
                                        
                    urlPhoto	=	request.getContextPath()+File.separator+"uploads"+File.separator+fileName;
                    System.out.println(" file location :" + urlPhoto);
                }else{
                	if("hidID".equals(fi.getFieldName()))	id	=	Integer.parseInt(fi.getString());
                	if("hidEmail".equals(fi.getFieldName()))	email	=	fi.getString();
                	if("hidCreateDate".equals(fi.getFieldName()))	createDate	=	fi.getString();
                	if("hidUrlPhoto".equals(fi.getFieldName()) && urlPhoto.isEmpty())	
                		urlPhoto	=	request.getContextPath()+File.separator+"uploads"+File.separator+fi.getString();
                	                	               
                }                
            }
            
            session.setAttribute("id", id);
			session.setAttribute("email", email);
			session.setAttribute("createDate", createDate);
			session.setAttribute("urlPhoto", urlPhoto);
						
			response.sendRedirect("profileInfo");
           
        }
        catch (Exception ex) {
            //System.out.println(ex);
            throw new ServletException(ex.getMessage());
        }
    }
}
