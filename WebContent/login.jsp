<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Website</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>

</head>
<body>
    <div class="container">
        <h1>User Login</h1>
        <form action="login" method="post" id="loginForm" class="form-horizontal needs-validation" novalidate>
        <input type="hidden" id="action" name="action" value="login">
        <div class="form-outline">
            <label for="email" class="col-sm-2 form-label">Email:</label>
            <div class="col-sm-10">
            	<input name="email" id="email" class="form-control" placeholder="john.smith@gmail.com" required/>
            	    <div class="valid-feedback">Looks good!</div>
            		<div class="invalid-feedback">Please enter an email.</div>
            </div>
            
        </div>
        <div class="form-outline">
            <label for="password" class="col-sm-2 form-label">Password:</label>
            <div class="col-sm-10">
            <input type="password" class="form-control" id="password" name="password" placeholder="password" required/>
                    <div class="valid-feedback">Looks good!</div>
            		<div class="invalid-feedback">Please enter a password.</div>
            </div>
		</div>
            
                  
            <div class="col-sm-offset-2 col-sm-10"> 
            <br>${message}
            <br></br>
            	<button type="submit" class="btn btn-primary btn-md">Login</button>
            </div>
        </form>
    </div>
<script >
 
(() => {
	  'use strict';

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  const forms = document.querySelectorAll('.needs-validation');

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms).forEach((form) => {
	    form.addEventListener('submit', (event) => {
	      if (!form.checkValidity()) {
	        event.preventDefault();
	        event.stopPropagation();
	      }
	      form.classList.add('was-validated');
	    }, false);
	  });
	})();
 
    
</script>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>