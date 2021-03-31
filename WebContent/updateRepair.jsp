<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Repairs</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>


<div class="container">




	<form action="viewRepairs" method="post"  role="form" data-toggle="validator" >
	<h2><%= request.getParameter("name") %></h2>
	
	<div class="form-group col-xs-4">
		<label for="mileage" class="control-label col-xs-4">Mileage:</label>
		<input type="text" name="mileage" id="mileage" class="form-control" value=<%= request.getParameter("mileage") %> required="true"/> 
		
		<label for="lastUpdated" class="control-label col-xs-4">Last Updated:</label>
		<input type="date" name="lastUpdated" id="lastUpdated" class="form-control" value=<%= request.getParameter("lastUpdated") %> required="true"/> 
		
		<input type="hidden" id="name" name="name" value="<%= request.getParameter("name") %>">
		<input type="hidden" id="id" name="id" value="<%= request.getParameter("id") %>">
		
		<br></br>
		<button type="submit" class="btn btn-primary  btn-md">Update</button> 


	</div>
	</form>
	
	
	
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>