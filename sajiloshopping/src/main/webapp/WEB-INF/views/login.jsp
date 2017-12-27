<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="jquery" value="/resources/jquery" />
<spring:url var="images" value="/resources/images" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Sajilo Shopping - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap Core CSS	 -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Cyborg Theme -->
<link href="${css}/bootstrap-cyborg-theme.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-fixed-top" role="navigation">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Sajilo
						Shopping</a>
				</div>
			</div>
		</nav>

		<!-- Page Content -->
		<div class="container">
			<%-- Will be displayed if the credentials are wrong --%>
			<c:if test="${not empty message}">
				<div class="row">
					<div class="col-md-6">
						<div class="alert alert-danger">${message}</div>
					</div>	
				</div>
			</c:if>
			
			<%-- Will be displayed if the users have logged out --%>
			<c:if test="${not empty logout}">
				<div class="row">
					<div class="col-md-6">
						<div class="alert alert-danger">${logout}</div>
					</div>	
				</div>
			</c:if>
			
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-*">
					<div class="card card-primary">
						<div class="card-header">
							<h4>Personal Details</h4>
						</div>
						<div class="card-body">
							<form action="${contextRoot}/login" class="form" method="POST" id="loginForm">
								<div class="form-group">
									<label for="email">Email:</label>
									<div>
										<input type="text" class="form-control" name="email" id="username" placeholder="Your email id..." />
									</div>
								</div>
								<div class="form-group">
									<label for="password">Password:</label>
									<div>
										<input type="text" class="form-control" name="password" id="password" placeholder="password..." />
									</div>
								</div>
								<div class="form-group">
									<div>
										<input type="submit" value="Login" class="btn btn-primary"/>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									</div>
								</div>
							</form>
						</div>
						<div class="card-footer">
							<a class="navbar-brand" href="${contextRoot}/register">Register here</a>
						</div>
					</div>
				</div>
			</div>
		</div>




		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<script src="${jquery}/jquery.js"></script>

		<!-- Jquery Validate -->
		<script src="${jquery}/jquery.validate.js"></script>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.bundle.min.js"></script>

		<!-- Self Coded javascript -->
		<script src="${js}/myapp.js"></script>
	</div>



</body>

</html>
