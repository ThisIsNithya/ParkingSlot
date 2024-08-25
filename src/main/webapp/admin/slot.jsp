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

<%@ include file="/member/header.jsp"  %>

<!--The html below this line is for display purpose only-->

<div class="col-md-12" style="margin-top:100px;">
<div class="row mt-3 justify-content-center">
    <div class="col-md-11 card" >
        <div class="row " style="background-color: #b400ff;color: white;margin: 10px;padding: 10px;">
            <div class="col-md-12 d-flex justify-content-center" >
                <h2>Add Slot</h2>
            </div>
        </div>
        
       
        <div class="row mt-2">
            <div class="col-md-12">
                <div class="row mb-3">
                    <div class="col-md-12">
                        <form class="card" action="slotstore">
                          	 <%
                            List<Location> locationList = (List<Location>) request.getAttribute("locationList");
				       %>
                             <br>
                            <!-- select vehicle    -->
                            <div class="col-md-12 m-2">
                                <div class="form-group">
                                    <label for="vehicle_type">Select Location</label>
                                    <select class="form-control" name="locationid">
                                       <option>--Select--</option>
                                       <% for ( Location location : locationList){ %>
												 <option value="<%=location.getLocationid() %>"><%=location.getLocation_name() %></option>
										 <% } %>
									</select>
                                </div>
                            </div>
                            <!-- end vechicle select -->
                            
                            <!-- select vehicle    -->
                            <div class="col-md-12 m-2">
                                <div class="form-group">
                                    <label for="vehicle_type">Slot Number</label>
                                    <input  class="form-control" type="text" name="slotno" placeholder="Slot No" autocomplete="off" required>
                                </div>
                            </div>
                            <!-- end vechicle select -->

                            
                            <!-- book button    -->
                            <div class="col-md-12 mt-3 mb-5">
                                <div class="m-1 mt-2">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                           <a href="addslot" class="btn btn-sm btn-danger btn-right">Cancel</a>
										   <button type="submit" class="btn btn-sm btn-primary btn-right">Submit</button>

                                        </div>
                                    </div>                                    
                                </div>
                            </div>
                            <!-- end book button -->
                        </form>

                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col-md-12"><h5>Slots Available</h5></div>
                    <div class="col-md-12">
                       <table class="table">
							  <thead>
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col">Location</th>
							      <th scope="col">Slot No</th>
							      <th scope="col">Slot Status</th>
							    </tr>
							  </thead>
							  <tbody>
								<%
								 List<Slot> allSlots = (List<Slot>) request.getAttribute("slotLists");
								
								if(allSlots.size() > 0 ){
									int count = 1;
									for( Slot allSlot : allSlots){
								%>
								<tr>
								  <th scope="row"><%= count %></th>
								  <td><%= allSlot.getLocation() %></td>
								  <td><%= allSlot.getSlotno()  %></td>
								  <td><%= allSlot.getStatus() == 1 ? "Booked" : "Available" %></td>
								</tr>
								<%  count = count + 1; }} else{%>
								<tr>
								  <th colspan="3">No Data to display</th>
								</tr>
								 <%  } %>
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