<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adding a new car</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">   		
	<script src="../js/bootstrap.min.js"></script>
</head>
<body>
<h1>Add a new car here!!!!</h1>
	<!--new-employee.jsp in notepad++ goes here.  allows for a form to add the new car.  put edit, edit attributes, and delete in the table when you display car on main page.-->
	<div class="container">
            <form action="/helloServlet" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                
                <h2>Employee</h2>
                <div class="form-group col-xs-4">
                    <label for="make" class="control-label col-xs-4">Make:</label>
                    <input type="text" name="make" id="make" class="form-control" value="${car.make}" required="true"/>                                   

                    <label for="model" class="control-label col-xs-4">Model:</label>                   
                    <input type="text" name="model" id="model" class="form-control" value="${car.model}" required="true"/> 

                    <label for="year" class="control-label col-xs-4">Year:</label>
                    <input type="text" name="year" id="year" class="form-control" value="${car.year}" required="true"/>

                    <label for="mileage" class="control-label col-xs-4">Mileage:</label>                   
                    <input type="text" name="mileage" id="mileage" class="form-control" value="${car.mileage}" placeholder="0" required="true"/>

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Accept</button> 
                </div>                                                      
            </form>
        </div>
</body>
</html>