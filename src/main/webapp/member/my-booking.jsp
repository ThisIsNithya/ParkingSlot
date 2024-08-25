<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.myparking.member.MyBooking" %>    
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
                <h2>My Bookings</h2>
            </div>
        </div>
        <hr>
        <div class="row mt-2">
            <div class="col-md-12">
                <div class="row mb-3">
                    <div class="col-md-12 overflow-auto" style="height: 35rem;">
                        <div class="accordion" id="accordionExample">
                        <%
				            List<MyBooking> mybookings = (List<MyBooking>) request.getAttribute("mybookings");
				        
				        if(mybookings.size() > 0 ){
				        	for( MyBooking mybooking : mybookings){
				        		
				        		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
				                String formattedDate = sdf.format(mybooking.getDate());
				        %>			        
				                        
                            <div >
                                <div  class="card">
                                    <div class="card-header" id="headingTwo">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <h5><b>Location :</b> <%= mybooking.getLocationName() %></h5>
                                            </div>
                                            <div class="col-md-7">
                                                <h5><b>Date :</b> <%=formattedDate %></h5>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-auto mr-auto">
                                                  <b>Slot No :</b> <%= mybooking.getSlotName() %> <br> 
                                                  <b>Vehicle Type :</b> <%= mybooking.getVehicle_type() %> <br> 
                                                  <b>Duration :</b> <%= mybooking.getDuration() %> hours <br> 
                                                  <div *ngIf="booking.paid != 0">
                                                    <b>Cost :</b> <%= mybooking.getCost() %> rupees
                                                  </div>
                                                </div>
                                                <% if(mybooking.getPaid() == 1 ){ %>
                                                <div class="col-auto">
                                                    <p class="text-success font-weight-bold">Booking Completed</p>
                                                  </div>
                                                <% } %>  
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                            </div> 
                            <%} }else{ %> 
                            <div >
                                <h5>
                                    No Bookings Found...
                                </h5>
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

</body>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->



</html>