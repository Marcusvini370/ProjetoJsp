<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Curso JSP</title>
<style type="text/css">

form{
position: absolute;
top: 40%;
left: 40%;
right: 33%;
}

H4{
position: absolute;
top: 30%;
left: 45%;
}
.msg{
top:18%;
left: 45%;
font-size: 15px;
color: #664d03;
    background-color: #fff3cd;
    border-color: #ffecb5;
}

</style>
</head>
<body>
	<h4>Bem Vindo ao curso de JSP</h4>

	<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="row g-3">
<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
		
			 <div class="col-md-12">
				<label  class="form-label">Login:</label>
				<input  class="form-control" type="text" name="login" required>
			
			</div>

			 <div class="col-md-12">
			<label class="form-label">Senha:</label>
			<input class="form-control" type="password" name="senha"required>
			
			</div>

			
			<button class="btn btn-primary mt-5" type="submit" >Acessar</button>
			

		
		
	</form>
	

	<H4 class="msg">${msg}</H4>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</body>
</html>