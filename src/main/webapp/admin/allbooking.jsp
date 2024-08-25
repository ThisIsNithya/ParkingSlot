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

<%@ include file="/member/header.jsp"  %>

<!--The html below this line is for display purpose only-->

<div class="col-md-12" style="margin-top:100px;">
<div class="row mt-3 justify-content-center">
    <div class="col-md-11 card" >
        <div class="row " style="background-color: #b400ff;color: white;margin: 10px;padding: 10px;">
            <div class="col-md-12 d-flex justify-content-center" >
                <h2>All Booking Details</h2>
            </div>
        </div>
        
       
        <div class="row mt-2">
            <div class="col-md-12">
                
                <div class="row mb-3">
                    <div class="col-md-12">
                       <table class="table">
							  <thead>
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col">Email</th>
							      <th scope="col">Vehicle Type</th>
							      <th scope="col">Vehicle No</th>
							      <th scope="col">Date</th>
							      <th scope="col">Time</th>
							      <th scope="col">Duration</th>
							      <th scope="col">Cost</th>
							      <th scope="col">Location</th>
							      <th scope="col">Slot Number</th>
							      <th scope="col">Status</th>
							    </tr>
							  </thead>
							  <%  List<MyBooking> allBookings = (List<MyBooking>) request.getAttribute("allBookings"); 
							  
							  if(allBookings.size() > 0 ){
									int count = 1;
									for( MyBooking booking : allBookings){							  %>
							  <tbody>
							  	<tr>
							      	<td><%= count %></td>
									<td><%= booking.getEmail() %></td>
									<td><%= booking.getVehicle_type() %></td>
									<td><%= booking.getVehicle_no() %></td>
									<td><%= booking.getDate() %></td>
									<td><%= booking.getTime() %></td>
									<td><%= booking.getDuration() %></td>
									<td><%= booking.getCost() %></td>
									<td><%= booking.getLocationName() %></td>
									<td><%= booking.getSlotName() %></td>
									<td><%= booking.getPaid() %></td>
							    </tr>
							     <%  count = count + 1;}}else{ %>
							     <th colspan="11">No Data to display</th>
							     <% } %>
							  </tbody>
					</table>
                    
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