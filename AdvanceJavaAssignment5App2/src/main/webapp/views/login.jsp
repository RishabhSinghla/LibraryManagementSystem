<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>Login Page</title>
</head>
<body>

	<div class="container">
		<div class="row mt-5 align-items-center">
			<div class="col-md-8 mx-auto offset-md-4 ">
				<div class="card ">
					<c:if test="${not empty message}">
						<div class="text-center mb-2" style="color: red">
							<h3>${message}</h3>
						</div>
					</c:if>
					<div class="card-header">

						<h3>Login</h3>
					</div>
					<div class="card-body">
						<form action="/validate" method="post" onsubmit="validateForm()">
							<div class="form-group">
								<div class="input-group mb-3 mx-0 mx-md-5">
									<label for="username" class="col-3">Username :</label> <span
										style="color: red">*</span>
									<div class="col-8 col-md-7">
										<input name="username" type="text" class="form-control"
											id="name" required>
										<c:if test="${not empty validation_message}">
											<div class="text-center mb-2" style="color: red">
												<h6>${validation_message}</h6>
											</div>
										</c:if>
									</div>
									<span class="formerror"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group mb-3 mx-0 mx-md-5">
									<label for="password" class="col-3">Password :</label> <span
										style="color: red">*</span>
									<div class="col-8 col-md-7">
										<input name="password" type="password" class="form-control"
											id="password" required>
										<c:if test="${not empty validation_message}">
											<div class="text-center mb-2" style="color: red">
												<h6>${validation_message}</h6>
											</div>
										</c:if>
									</div>
								</div>
							</div>
							<div class="card-footer text-center">
								<button type="submit" class="btn btn-outline-primary">Login
									>></button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>


</body>
</html>