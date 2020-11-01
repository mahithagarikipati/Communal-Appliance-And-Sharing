<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<title>Lender Appliance Registration</title>
	<link href="css/main.css" rel="stylesheet" />
</head>

<body>
<div class="header1">
  <h1>Communal Appliance Sharing Network
  <div class="header1-right">
    <a class="active" href="index.html"><i class="fa fa-home"></i>Login</a>
    <a class="hover" href="#contact"><i class="fa fa-address-book-o" aria-hidden="true"></i>Contact Us</a>
  </div>
  </h1>
</div>
<div>
<h2 class="header2">User Registration</h2>
    <form action="register" method="post">
 
        User ID <input type="text" name="user_id"/><br/>
        Item Name <input type="text" name="appliance_name"/><br/>
        Item Description <input type="text" name="appliance_desc" /><br/>
        <input type="text" id="available_from_dt" name="available_from_dt" /><br/>
        <input type="text" name="available_to_dt" /><br/>
		Price for each day in $ <input type="text" name="price_per_day" /><br/>
		<p>I hereby agree to all terms and conditions of Communal Appliance Borrower application</p>
		<input type="radio" id="agree" name="termsconditions" value="agree">
			<label for="agree">agree</label>
		<input type="radio" id="disagree"  name="termsconditions" value="disagree">
			<label for="disagree">disagree</label>
		
        <input type="submit" value="LEND"/>
    </form>
	</div>
</body>

</body>
</html>
