<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*,com.myparking.vehicle.Vehicle" %>      
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
<div class="row justify-content-center">
    <div class="col-md-11 card " >
        <div class="row " style="background-color: #b400ff;color: white;margin: 10px;padding: 10px;">
            <div class="col-md-12 d-flex justify-content-center" >
                <h2>Available Slot</h2>
            </div>
        </div>
        <hr>
        <div class="row mt-2">
            <div class="col-md-12">
                <div class="row mb-3">
                    <div class="col-md-12 overflow-auto" style="height: 35rem;">
                        <div class="accordion" id="accordionExample">
                        <%
                            List<Vehicle> vehicleList = (List<Vehicle>) request.getAttribute("vehicleList");
				            List<Map<String, Object>> availableSlots = (List<Map<String, Object>>) request.getAttribute("availableSlots");
				        %>			        
				                        
                            <div >
                             <% for ( Map<String, Object> availableSlot : availableSlots){ %>
                                <div  class="card">
                                    <div class="card-header" id="headingTwo">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <h5><b>Location :</b> <%= availableSlot.get("location_name") %></h5>
                                            </div>
                                            <div class="col-md-7">
                                                <h5><b>Slots :</b> <%= availableSlot.get("slot") %></h5>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-auto mr-auto">
                                                  <b>Address :</b>  <br> 
                                                  <p><%= availableSlot.get("address") %></p> 
                                                </div>  
                                                <% if( availableSlot.get("slot").equals(0) ){ %>
                                                    <p class="text-success font-weight-bold"> Booking Closed</p>
                                                <% }else{ %>
                                                <div class="col-auto">
                                                    <a href="bookslot?location=<%= availableSlot.get("location_id") %>" class="btn btn-sm btn-danger">Book Now</a>
                                                </div>  
                                                <%} %>
                                            </div>  
											<hr>
                                            <div class="row">
                                                <div class="col-auto mr-auto">
                                                  <b>Price Per Hour :</b>  <br> 
                                                  <% for ( Vehicle vehicle : vehicleList){ %>
                                                  <span>&nbsp;&nbsp;<%=vehicle.getVehicle_type() %> : <%=vehicle.getCost()%> &nbsp;Rs</span>  <br>
                                                  <% } %> 
                                                </div>    
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <% } %> 
                            </div> 
                        </div>
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