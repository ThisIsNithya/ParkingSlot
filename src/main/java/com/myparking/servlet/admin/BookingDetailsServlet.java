package com.myparking.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.myparking.location.Location;
import com.myparking.location.LocationRepo;
import com.myparking.member.MyBooking;
import com.myparking.member.MyBookingDao;
import com.myparking.slot.Slot;
import com.myparking.slot.SlotRepo;

/**
 * Servlet implementation class BookingDetailsServlet
 */
@WebServlet(urlPatterns = { "/admin/allbookings"})
public class BookingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			 case "/admin/allbookings" :
				 getSlotDetails(request , response);
				 break;
			 
			 default :
				 response.sendError(HttpServletResponse.SC_NOT_FOUND);
				 break;
			  
			  }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void getSlotDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			if( username != null && role.equals("Admin") ) {
				MyBookingDao booking = new MyBookingDao();
				List<MyBooking> allBookings = booking.getAllBooking();
				request.setAttribute("allBookings", allBookings);
				request.getRequestDispatcher("allbooking.jsp").forward(request, response);
			}else {
				response.sendRedirect("../login"); 
			} 
		}else {
			response.sendRedirect("../login"); 
		} 
		
	}

}
