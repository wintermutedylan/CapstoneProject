<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="google-signin-client_id" content="370185456533-9ee92cjuhgpe2uog5k6nv5qrdq5c1irs.apps.googleusercontent.com">
<title>List of Cars</title>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

/* Float cancel and delete buttons and add an equal width */
.cancelbtn, .deletebtn {
  float: left;
  width: 50%;
}

/* Add a color to the cancel button */
.cancelbtn {
  background-color: #ccc;
  color: black;
}

/* Add a color to the delete button */
.deletebtn {
  background-color: #f44336;
}

/* Add padding and center-align text to the container */
.container {
  padding: 16px;
  text-align: center;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: #474e5d;
  padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* Style the horizontal ruler */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
 
/* The Modal Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15px;
  font-size: 40px;
  font-weight: bold;
  color: #f1f1f1;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and delete button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .deletebtn {
     width: 100%;
  }
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  
</head>
<body>
<div class="container">
    <h1>Car List</h1>
    
    
                  
                <input type="hidden" id="action" name="action">
                <input type="hidden" id="idCar" name="idCar">
                
                        <table  class="table table-striped">
                        	<tbody>
                            
                                <tr>
                                    
                                    <td>Make</td>
                                    <td>Model</td>
                                    <td>Year</td>
                                    <td>Mileage</td>
                                    
                                    <td></td>
                                </tr>
                                <c:forEach items="${requestScope.carList}" var="car">
                                <c:url value="editCar.jsp" var="editURL">
                                	<c:param name="carId" value="${car.id}"></c:param>
									<c:param name="carMake" value="${car.make}"></c:param>
									<c:param name="carModel" value="${car.model}"></c:param>
									<c:param name="carYear" value="${car.year}"></c:param>
									<c:param name="carMileage" value="${car.mileage}"></c:param>
									
								</c:url>
								<c:url value="deleteCar" var="deleteURL">
									<c:param name="id" value="${car.id}"></c:param>
								</c:url>
								<c:url value="viewAttribute" var="attributeURL">
									<c:param name="id" value="${car.id}"></c:param>
									<c:param name="carMake" value="${car.make}"></c:param>
									<c:param name="carModel" value="${car.model}"></c:param>
									<c:param name="carYear" value="${car.year}"></c:param>
									<c:param name="carMileage" value="${car.mileage}"></c:param>
									
								</c:url>
								<c:url value="viewRepairs" var="repairsURL">
									<c:param name="id" value="${car.id}"></c:param>
									<c:param name="carMake" value="${car.make}"></c:param>
									<c:param name="carModel" value="${car.model}"></c:param>
									<c:param name="carYear" value="${car.year}"></c:param>
									<c:param name="carMileage" value="${car.mileage}"></c:param>
									
								</c:url>
                            
                            
                                
                                <tr>
                                                                      
                                    <td><c:out value="${car.make}"></c:out></td>
                                    <td><c:out value="${car.model}"></c:out></td>
                                    <td><c:out value="${car.year}"></c:out></td>
                                    <td><c:out value="${car.mileage}"></c:out></td>
                                    
                                    <td><button type="submit" class="btn btn-primary btn-md" onclick="document.getElementById('action').value = 'edit'; document.getElementById('idCar').value = '${car.id}'; location.href='${editURL}'; ">Edit</button></td>
                                    <td><button type="submit" class="btn btn-primary  btn-md" onclick="document.getElementById('action').value = 'delete'; document.getElementById('idCar').value = '${car.id}'; document.getElementById('id01').style.display='block'">Delete</button></td>
                                    
                                    <td><button type="submit" class="btn btn-primary btn-md" onclick="document.getElementById('idCar').value = '${car.id}'; location.href='${attributeURL}'; ">Maintenance</button></td>
                                    <td><button type="submit" class="btn btn-primary btn-md" onclick="document.getElementById('idCar').value = '${car.id}'; location.href='${repairsURL}'; ">Repairs</button></td>
                                    
                                </tr>
                                </c:forEach>
                              
                            </tbody>            
                        </table>  
                                           
            
	
	<b>Welcome: ${user.fullname}</b>
	<form action ="new-car.jsp" method="get">   
		<br></br>
		<button type="submit" class="btn btn-primary  btn-md">New Car</button> 
	</form>

	

	
	<form action ="login.jsp" method="get">
		<br></br>
		<button type="submit" class="btn btn-primary  btn-md">Login</button>
	</form>
	
	<form action ="register.jsp" method="get">     
		<br></br>
		<button type="submit" class="btn btn-primary  btn-md">Register new user</button> 
	</form>
	
	<form action ="logout" method="get">       
		<br></br>
		<button type="submit" class="btn btn-primary  btn-md">Logout</button> 
	</form>

	
	
	
	
	


<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">×</span>
  <form class="modal-content" action="helloServlet" method="post" role="form">
  <input type="hidden" id="action" name="action" value="delete">
  <input type="hidden" id="carId" name="carId">
  
    <div class="container">
      <h1>Delete Car</h1>
      <p>Are you sure you want to delete this car?</p>
    
      <div class="clearfix">
        <button type="button"  onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit"  onclick="document.getElementById('carId').value = document.getElementById('idCar').value; document.getElementById('action').value = 'delete'; document.getElementById('id01').style.display='none'" class="deletebtn">Delete</button>
      </div>
    </div>
  </form>
</div>


	
	</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
</body>
</html>