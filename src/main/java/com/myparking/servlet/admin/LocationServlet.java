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
import com.myparking.slot.Slot;

/**
 * Servlet implementation class LocationServlet
 */
@WebServlet(urlPatterns = { "/admin/addlocation" , "/admin/locationstore" })
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationServlet() {
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
			 case "/admin/addlocation" :
				 getLocationDetails(request , response);
				 break;
			 case "/admin/locationstore" :
				 storeLocationDetails(request , response);
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
	
	public void getLocationDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			if( username != null && role.equals("Admin") ) {
				LocationRepo locationList = new LocationRepo();
				List<Location> locationLists = locationList.getAllLocation();
				request.setAttribute("locationLists", locationLists);
				request.getRequestDispatcher("location.jsp").forward(request, response);
			}else {
				response.sendRedirect("../login"); 
			} 
		}else {
			response.sendRedirect("../login"); 
		}  
		
	}
	
	
	public void storeLocationDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			if( username != null && role.equals("Admin") ) {
				LocationRepo locationList = new LocationRepo();
				Location location = new Location();
				location.setLocation_name(request.getParameter("location_name"));
				location.setArea(request.getParameter("area"));
				try {
					locationList.storeLocation(location);
					response.sendRedirect("addlocation?message=Added Succesfully");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
