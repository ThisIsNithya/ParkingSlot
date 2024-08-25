package com.myparking.servlet.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.myparking.location.Location;
import com.myparking.location.LocationRepo;
import com.myparking.member.MemberDao;
import com.myparking.member.MyBooking;
import com.myparking.member.MyBookingDao;
import com.myparking.slot.Slot;
import com.myparking.slot.SlotRepo;
import com.myparking.vehicle.Vehicle;
import com.myparking.vehicle.VehicleRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class MemberParkingBooking
 */
@WebServlet(urlPatterns = { "/member/mybooking", "/member/availableslots", "/member/bookslot", "/member/confirmslot" })
public class MemberParkingBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberParkingBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
        
		try {
			switch (action) {
			 case "/member/mybooking" :
				 myBookingList(request , response);
				 break;
			 case "/member/availableslots" :
				 availableSlots(request , response);
				 break; 
			 case "/member/bookslot" :
				 bookSlot(request , response);
				 break; 
			 case "/member/confirmslot" :
				 confirmSlot(request , response);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	public void myBookingList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			if( username != null ) {
				MyBookingDao mybookingdao = new MyBookingDao();
				List<MyBooking> mybookings = mybookingdao.getMyBooking(username);
				request.setAttribute("mybookings", mybookings);
				request.getRequestDispatcher("my-booking.jsp").forward(request, response);
			}else {
				response.sendRedirect("../login");
			}
		}else {
			response.sendRedirect("../login");
		}
		
	}
	
	public void availableSlots(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			if( username != null ) {
				SlotRepo slots = new SlotRepo();
				List<Map<String, Object>> availableSlots = slots.getAllAvailableSlots();
				VehicleRepo vehicle = new VehicleRepo();
				List<Vehicle> vehicleList = vehicle.getAllVehicle();
				request.setAttribute("availableSlots", availableSlots);
				request.setAttribute("vehicleList", vehicleList);
				request.getRequestDispatcher("available-slot.jsp").forward(request, response);
			}else {
				response.sendRedirect("../login");
			}
		}else {
			response.sendRedirect("../login");
		}
	}
	
	public void bookSlot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			if( username != null ) {
				 int locationId = Integer.parseInt(request.getParameter("location"));
				 VehicleRepo vehicle = new VehicleRepo();
				 List<Vehicle> vehicleList = vehicle.getAllVehicle();
				 LocationRepo location = new LocationRepo();
				 Location locationList = location.getListById(locationId);
				 SlotRepo slots = new SlotRepo();
				 List<Slot> slotsList = slots.getAllSlotByLocation(locationId);

				 request.setAttribute("locationList", locationList);
				 request.setAttribute("vehicleList", vehicleList);
				 request.setAttribute("slotsList", slotsList);
				 request.getRequestDispatcher("book-slot.jsp").forward(request, response);
			}else {
				response.sendRedirect("../login");
			}
		}else {
			response.sendRedirect("../login"); 
		}    
		
	}
	
	public void confirmSlot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			if( username != null ) {
				MyBooking mybooking = new MyBooking();
				mybooking.setEmail( username );
				mybooking.setVehicle_type(request.getParameter("vehicle_type"));
				mybooking.setVehicle_no(request.getParameter("vehicle_no"));
				mybooking.setLocationid(Integer.parseInt(request.getParameter("locationid")));
				mybooking.setSlotid(request.getParameter("slotid"));
				mybooking.setTime(request.getParameter("time"));
				mybooking.setDuration(Float.parseFloat(request.getParameter("duration")));
				mybooking.setCost(20);
				mybooking.setPaid(1);
				
				MyBookingDao mybookingDao = new MyBookingDao();
				
				try {
					mybookingDao.bookMemberSlot(mybooking);
					response.sendRedirect("mybooking?message=Added Succesfully");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				response.sendRedirect("../login");
			}
		}else {
			response.sendRedirect("../login");
		}
		
	}	
	

}
