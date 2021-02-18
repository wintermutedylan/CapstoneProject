<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script> 
</head>
<body>
	<div class="container">
	<form action="/CapstoneWeb" method="post" role="form" >              
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty carList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    
                                    <td>Make</td>
                                    <td>Model</td>
                                    <td>Year</td>
                                    <td>Mileage</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <c:forEach var="car" items="${carList}">
                                <c:url value="/editCar" var="editURL">
                                </c:url>
                                <c:url value="/deleteCar" var="deleteURL">
                                </c:url>
                                
                                <tr>
                                                                      
                                    <td><c:out value="${car.make}"></c:out></td>
                                    <td>${car.model}</td>
                                    <td>${car.year}</td>
                                    <td>${car.mileage}</td>
                                    <td><a href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
                                    <td><a href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
                                      
                                    
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    
                </c:choose>                        
            </form>
	</div>

</body>
</html>