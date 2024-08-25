package com.myparking.servlet.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.myparking.member.Member;
import com.myparking.member.MemberDao;

/**
 * Servlet implementation class MemberRegistrationServlet
 */
@WebServlet("/registration/*")
public class MemberRegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getPathInfo();
		if( action != null && action.equals("/store") ) {
			insertMember(request, response);
		}else if( action != null && action.equals("/form") ) {
			request.getRequestDispatcher("/member/registration.jsp").forward(request, response);
		}else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void insertMember(HttpServletRequest request, HttpServletResponse response) {
		Member member = new Member();
		member.setEmail( request.getParameter("email") );
		member.setFullname( request.getParameter("fullname") );
		member.setMobno( request.getParameter("mobno") );
		member.setPassword( request.getParameter("password") );
		member.setAddress( request.getParameter("address") );
		
		MemberDao memberDao = new MemberDao();
		
		try {
			memberDao.registerMemberDetails(member);
			response.sendRedirect("form?message=Added Succesfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

}
