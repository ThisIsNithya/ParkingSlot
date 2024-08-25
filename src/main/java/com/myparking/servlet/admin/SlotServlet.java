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
import java.util.Map;

import com.myparking.location.Location;
import com.myparking.location.LocationRepo;
import com.myparking.slot.Slot;
import com.myparking.slot.SlotRepo;
import com.myparking.vehicle.Vehicle;

/**
 * Servlet implementation class SlotServlet
 */
@WebServlet(urlPatterns = { "/admin/addslot", "/admin/slotstore" })
public class SlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlotServlet() {
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
			 case "/admin/addslot" :
				 getSlotDetails(request , response);
				 break;
			 case "/admin/slotstore" :
				 storeSlotDetails(request , response);
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
	
	public void getSlotDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			if( username != null && role.equals("Admin") ) {
				SlotRepo slotList = new SlotRepo();
				List<Slot> slotLists = slotList.getAvailableAllSlotList();
				LocationRepo location = new LocationRepo();
				List<Location> locationList = location.getAllLocation();
				request.setAttribute("locationList", locationList);
				request.setAttribute("slotLists", slotLists);
				request.getRequestDispatcher("slot.jsp").forward(request, response);
			}else {
				response.sendRedirect("../login"); 
			} 
		}else {
			response.sendRedirect("../login"); 
		} 
		
	}
	
	public void storeSlotDetails(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			String role = (String) session.getAttribute("role");
			if( username != null && role.equals("Admin") ) {
				SlotRepo slotRepo = new SlotRepo();
				Slot slot = new Slot();
				slot.setLocationid(Integer.parseInt(request.getParameter("locationid")));
				slot.setSlotno(request.getParameter("slotno"));
				try {
					slotRepo.storeSlots(slot);
					response.sendRedirect("addslot?message=Added Succesfully");
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
