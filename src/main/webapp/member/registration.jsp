<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MyParking</title>
<link rel="shortcut icon" type="image/x-icon" href="https://parking.com/img/unity/favicon.ico"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link href="../css/app.css" type="text/css" rel="stylesheet">

</head>
<body>

<div class="container mt-3">
<form action="store" method="post">
    <div class="row jumbotron box8">
      <div class="col-sm-12 mx-t3 mb-4">
        <h2 class="text-center text-info">Register</h2>
      </div>
      <div class="col-sm-6 form-group">
        <label for="name-f">Full Name</label>
        <input type="text" class="form-control" name="fullname" placeholder="Enter your first name." required>
      </div>
      <div class="col-sm-6 form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" name="email" placeholder="Enter your email." required>
      </div>
      <div class="col-sm-6 form-group">
        <label for="address-1">Address Line</label>
        <input type="address" class="form-control" name="address" placeholder="Locality/House/Street no." required>
      </div>
      <div class="col-sm-6 form-group">
        <label for="tel">Phone</label>
        <input type="tel" name="mobno" class="form-control" placeholder="Enter Your Contact Number." required>
      </div>
      <div class="col-sm-6 form-group">
        <label for="pass">Password</label>
        <input type="Password" name="password" class="form-control" placeholder="Enter your password." required>
      </div>
      <div class="col-sm-6 form-group">
        <label for="pass2">Confirm Password</label>
        <input type="Password" name="cnf-password" class="form-control" placeholder="Re-enter your password." required>
      </div>

      <div class="col-sm-12 form-group mb-0">
        <button class="btn btn-primary float-right" id="js-member_registration">Submit</button>
      </div>
        <!-- Register Link -->
        <p class="text-center register-test mt-3">Already Register ? <a href="../login" class="text-decoration-none">Click here to login</a></p>
     

    </div>
 </form>   
</div>

</body>
</html>