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

import com.myparking.vehicle.VehicleRepo;
import com.myparking.vehicle.Vehicle;

/**
 * Servlet implementation class VehicleServlet
 */
@WebServlet(urlPatterns = { "/admin/addvehicle", "/admin/vehiclestore" })
public class VehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleServlet() {
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
			 case "/admin/addvehicle" :
				 getVehicleDetails(request , response);
				 break;
			 case "/admin/vehiclestore" :
				 storeVehicleDetails(request , response);
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
	
	public void getVehicleDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			if( username != null && role.equals("Admin") ) {
				VehicleRepo vehicleList = new VehicleRepo();
				List<Vehicle> vehicleLists = vehicleList.getAllVehicle();
				request.setAttribute("vehicleLists", vehicleLists);
				request.getRequestDispatcher("vehicle.jsp").forward(request, response);
			}else {
				response.sendRedirect("../login"); 
			}
		}else {
			response.sendRedirect("../login"); 
		} 
		
	}
	
	public void storeVehicleDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			if( username != null && role.equals("Admin") ) {
				VehicleRepo vehicleRepo = new VehicleRepo();
				Vehicle vehicle = new Vehicle();
				vehicle.setVehicle_type(request.getParameter("vehicle_type"));
				vehicle.setCost(Float.parseFloat(request.getParameter("cost")));
				try {
					vehicleRepo.storeVehicle(vehicle);
					response.sendRedirect("addvehicle?message=Added Succesfully");
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
