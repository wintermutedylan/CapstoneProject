<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>Car Attributes</title>
</head>
<body>
<div class="container">
	
    <h1>Car Attributes</h1>
    
    
                  
        
		
		
                
                        <table  class="table table-striped">
                        	<tbody>
                            
                                <tr>
                                    
                                    <td>Name</td>
                                    <td>Mileage</td>
                                    <td>Last Updated (yyyy-MM-dd)</td>
                                    
                                    <td></td>
                                </tr>
                                <c:forEach items="${requestScope.attributeList}" var="attribute">
                                <c:url value="updateAttribute.jsp" var="updateURL">
                                	<c:param name="name" value="${attribute.name}"></c:param>
                                	<c:param name="mileage" value="${attribute.mileage}"></c:param>
                                	<c:param name="lastUpdated" value="${attribute.lastUpdated}"></c:param>
                                	<c:param name="id" value='<%= request.getParameter("id") %>'></c:param>
                                	
									
								</c:url>
								
                                <tr>
                                                                      
                                    <td><c:out value="${attribute.name}"></c:out></td>
                                    <td><c:out value="${attribute.mileage}"></c:out></td>
                                    <td><c:out value="${attribute.lastUpdated}"></c:out></td>
                                    
                                    
                                    <td><button type="submit" class="btn btn-primary btn-md" onclick="location.href='${updateURL}'; ">Update</button></td>
                                    
                                    
                                </tr>
                                </c:forEach>
                              
                            </tbody>            
                        </table>  
                        
    <form action ="helloServlet" method="get">            
		<br></br>
		<button type="submit" class="btn btn-primary  btn-md">Back to Car list</button> 
	</form>
                                           
            
	
</div>









<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>