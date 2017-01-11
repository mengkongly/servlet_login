package com.rupp.sample.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	ArrayList<Users> arrUser	=	new ArrayList<Users>();
	
	@Override
    public void init() throws ServletException {
		System.out.println("---init method call ---");
        arrUser.add(new Users(1, "mengkong2009@gmail.com", "12345", new java.util.Date(System.currentTimeMillis()), ""));		
        arrUser.add(new Users(2, "aaaaa", "12345", new java.util.Date(System.currentTimeMillis()), "ddd.jpg"));
        arrUser.add(new Users(3, "bbbbb", "12345", new java.util.Date(System.currentTimeMillis()), "eee.jpg"));
        arrUser.add(new Users(4, "ccccc", "12345", new java.util.Date(System.currentTimeMillis()), ""));
        arrUser.add(new Users(5, "ddddd", "12345", new java.util.Date(System.currentTimeMillis()), ""));
        arrUser.add(new Users(6, "eeeee", "12345", new java.util.Date(System.currentTimeMillis()), ""));
        arrUser.add(new Users(7, "fffff", "12345", new java.util.Date(System.currentTimeMillis()), ""));
        arrUser.add(new Users(8, "ggggg", "12345", new java.util.Date(System.currentTimeMillis()), ""));
        arrUser.add(new Users(9, "hhhhh", "12345", new java.util.Date(System.currentTimeMillis()), ""));
        arrUser.add(new Users(10, "iiiii", "12345", new java.util.Date(System.currentTimeMillis()), ""));
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(String.format("=====%s method is called ====", request.getMethod()));
     
    }

    // Method to handle POST method request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session	=	request.getSession();
    	String email	=	request.getParameter("txtemail");
        String password	=	request.getParameter("txtpassword");
        String urlPhoto	=	"";
        boolean is_succ	=	false;
    	for(Users user: arrUser){
    		if(user.getEmail().equals(email) && user.getPassword().equals(password)){
    			System.out.println("Login Successfully.");
    			session.setAttribute("id", user.getId());
    			session.setAttribute("email", user.getEmail());
    			session.setAttribute("createDate", user.getCreateDate());
    			
    			if(!user.getUrlPhoto().equals("")){
	    			urlPhoto	=	request.getContextPath()+File.separator+"uploads"+File.separator+user.getUrlPhoto();
	    			session.setAttribute("urlPhoto", urlPhoto);
    			}
    			System.out.println("Photo:"+urlPhoto);
    			session.setAttribute("authen", "1");
    			is_succ	=	true;    			
    		}
    	}
    	if(is_succ){    		
    		response.sendRedirect("profileInfo");
    	}else{
	    	session.setAttribute("authen", 0);
	    	session.setAttribute("message", "Login Failed.");
	    	System.out.println("Login Failed.");
	    	response.sendRedirect("formLogin.jsp");
    	}
    	
    	//request.setAttribute("message", "Login Failed.");
    	//request.getRequestDispatcher("formLogin.jsp").forward(request, response);
    	
    	//System.out.println(request.getParameterNames());    
    	//doGet(request, response);
    }
    @Override
    public void destroy() {
        System.out.println("=====destroy method is called====");
    }
}
