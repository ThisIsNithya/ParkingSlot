package com.myparking.servlet.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.myparking.member.Member;
import com.myparking.member.MemberDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/" , "/login" , "/logout" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		switch (action) {
		 case "/login" :
			 loginPage(request , response);
			 break;
		 case "/" :
			 loginPage(request , response);
			 break;
		 case "/logout" :
			 validateLogOut(request , response);
			 break; 
		 default :
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
			 break;
		  
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		 case "/login" :
			 validateLogin(request, response);
			 break;
		 default :
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
			 break;
		  
		  }
	}

	private void validateLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String emailId = request.getParameter("email");
		String password = request.getParameter("password");
		MemberDao memberDao = new MemberDao();
		try {
			Member isLogged = memberDao.validateCredential(emailId , password);
			if(isLogged.getEmail() != null ) {
				/*
				 * Cookie usernameCookie = new Cookie("username" , emailId);
				 * response.addCookie(usernameCookie);
				 */
				HttpSession session = request.getSession(true);
                session.setAttribute("username", isLogged.getEmail());
                session.setAttribute("role", isLogged.getRole());
                session.setAttribute("fullname", isLogged.getFullname());
                if( isLogged.getRole().equals("Admin")) {
                	response.sendRedirect("admin/addslot");
                }else {
                	response.sendRedirect("member/mybooking");
                }
			}else {
				response.sendRedirect("login?error=Invalid credentials");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void validateLogOut(HttpServletRequest request, HttpServletResponse response) {
			
			try {
				HttpSession session = request.getSession(false);
				if (session != null) {
		            session.invalidate();
		        }
				response.sendRedirect("login");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void loginPage(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				String role = (String) session.getAttribute("role");
				if(  role != null ) {
					if(role.equals("Admin")) {
	                	response.sendRedirect("admin/addslot");
	                }else {
	                	response.sendRedirect("member/mybooking");
	                }
				}else {
		        	request.getRequestDispatcher("/member/login.jsp").forward(request, response);
		        }
				
	        }else {
	        	request.getRequestDispatcher("/member/login.jsp").forward(request, response);
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	

}
