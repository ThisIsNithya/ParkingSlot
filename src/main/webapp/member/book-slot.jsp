<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*,com.myparking.vehicle.Vehicle,com.myparking.location.Location,com.myparking.slot.Slot" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Parking</title>
</head>
<link rel="shortcut icon" type="image/x-icon" href="https://parking.com/img/unity/favicon.ico"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link href="../css/member.css" type="text/css" rel="stylesheet">

<body>	

<%@ include file="header.jsp"  %>

<!--The html below this line is for display purpose only-->

<div class="col-md-12" style="margin-top:100px;">
<div class="row mt-3 justify-content-center">
    <div class="col-md-11 card" >
        <div class="row " style="background-color: #b400ff;color: white;margin: 10px;padding: 10px;">
            <div class="col-md-12 d-flex justify-content-center" >
                <h2>Book Slot</h2>
            </div>
        </div>
        
        <hr>
		 <%
                            List<Vehicle> vehicleList = (List<Vehicle>) request.getAttribute("vehicleList");
							List<Slot> slotsList = (List<Slot>) request.getAttribute("slotsList");
                            Location locationList = new Location();
                                     locationList = (Location) request.getAttribute("locationList");
				       %>
        <div class="row mt-2">
            <div class="col-md-12">
                <div class="row mb-3">
                    <div class="col-md-12">
                        <h4>Location: <%= locationList.getLocation_name() %></h4>
                        <form class="card" action="confirmslot">
                           <input  class="form-control" type="hidden" name="locationid" value="<%= locationList.getLocationid() %>" required>
                            
                            <!-- select vehicle    -->
                            <div class="col-md-12 m-2">
                                <div class="form-group">
                                    <label for="vehicle_type">Select Vehicle Type</label>
                                    <select class="form-control" name="vehicle_type">
										<% for ( Vehicle vehicle : vehicleList){ %>
												 <option value="<%=vehicle.getVehicle_type() %>"><%=vehicle.getVehicle_type() %></option>
										 <% } %>
									</select>
                                </div>
                            </div>
                            <!-- end vechicle select -->

                            <!-- vehicle number enter -->
                            <div class="col-md-12 m-2">
                                    <input  class="form-control" type="text" name="vehicle_no" placeholder="Vehicle Number" autocomplete="off" required>

                                    <small>FORMAT: TN-01-AA-1111</small>
                            </div>
                            <!-- end vehicle number -->

                            <!-- select date and time -->
                            <div class="col-md-12 mt-2 mb-3">
                                <div class="row m-2">
                                    <div>
                                        Select Time: <input  class="form-control" type="time" id="time"  name="time">
                                        <br>
                                        <small>NOTE: Book 2 hours earlier</small>
                                    </div>            
                                </div>
                            </div>
                            <!-- end date and time -->

                            <!-- enter hours    -->
                            <div class="col-md-12 mb-3 ml-2">

                                <input  class="form-control" name="duration" id="duration" type="number" min="1" max="24" id="duration" placeholder="Estimated Hours" autocomplete="off"  required>
                            </div>
                            <!-- end hours    -->

                            <!-- select slot    -->
                            <div class="col-md-12">
                                <h4 class="m-2">Select Slot</h4>
                                <div class="row border ">
                                    <div class="col-12 m-1 pt-3 pb-0" > 
                                         <% for ( Slot slot : slotsList){ %>
										   <% if(slot.getStatus() != 0 ){ %> 
										   <div class="form-radio form-radio-inline">
												<input type="radio" disabled  id="<%= slot.getSlotno()  %>" value="<%= slot.getSlotid()  %>">
	                                            <label  id="slot" for="<%= slot.getSlotno()  %>"><%= slot.getSlotno()  %></label> 
	                                            <small class="text-danger">Already Booked!</small>
											</div>
										   <% }else{ %> 
										   <div class="form-radio form-radio-inline">
											  <input name="slotid" class="form-radio-input" type="radio"  value="<%= slot.getSlotid()  %>">
											  <label class="form-radio-label"><%= slot.getSlotno()  %></label>
											</div>
										   <% } %>		
										<% } %>	
                                    </div>
                                </div>
                            </div>
                            <!-- end slot select     -->
                            
                            <!-- book button    -->
                            <div class="col-md-12 mt-3 mb-5">
                                <div class="m-1 mt-2">
                                    <div class="row">
                                        <div class="col-sm-offset-10 col-2">
                                           <button type="submit" class="btn btn-sm btn-danger btn-right">Submit</button>

                                        </div>
                                    </div>                                    
                                </div>
                            </div>
                            <!-- end book button -->
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
	

</body>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->



</html>