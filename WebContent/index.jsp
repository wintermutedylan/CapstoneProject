<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java EE</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script> 
</head>
<body>
<div class="container">
    <h1>Hello JSP and Servlet!</h1>
    
    
    <form action="/helloServlet" method="post" role="form" >              
                <input type="hidden" id="action" name="action">
                
                        <table  class="table table-striped">
                        	<tbody>
                            
                                <tr>
                                    
                                    <td>Make</td>
                                    <td>Model</td>
                                    <td><%= request.getParameter("make")%></td>
                                    
                                    <td>Year</td>
                                    <td>Mileage</td>
                                    <td></td>
                                </tr>
                                <c:forEach items="${requestScope.carList}" var="car">
                                <c:url value="editCar" var="editURL">
									
								</c:url>
								<c:url value="deleteCar" var="deleteURL">
									
								</c:url>
								<c:url value="viewCarAttributes" var="attributeURL">
									
								</c:url>
                            
                            
                                
                                <tr>
                                                                      
                                    <td><c:out value="${car.make}"></c:out></td>
                                    <td><c:out value="${car.model}"></c:out></td>
                                    <td><c:out value="${car.year}"></c:out></td>
                                    <td><c:out value="${car.mileage}"></c:out></td>
                                    <td><a href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
                                    <td><a href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
                                    <td><a href='<c:out value="${attributeURL}" escapeXml="true"></c:out>'>View Attributes</a></td>
                                      
                                    
                                </tr>
                                </c:forEach>
                              
                            </tbody>            
                        </table>  
                                           
            </form>
	

	<form action ="new-car.jsp" method="get">            
		<br></br>
		<button type="submit" class="btn btn-primary  btn-md">New Car</button> 
	</form>
	</div>
</body>
</html>