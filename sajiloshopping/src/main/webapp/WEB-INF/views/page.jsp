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
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>Sajilo Shopping - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap Core CSS	 -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Cyborg Theme -->
<link href="${css}/bootstrap-cyborg-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">
			<!-- To load the Home Content -->
			<c:if test="${userClickHome == true}">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- Loads only when user clicks "About" -->
			<c:if test="${userClickAbout == true}">
				<%@include file="about.jsp"%>
			</c:if>
			
			<!-- Loads only when user clicks "All Products" -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- Loads only when user clicks "Contact" -->
			<c:if test="${userClickContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>
			
			<!-- Loads only when user clicks "showProduct" -->
			<c:if test="${userClickShowSingleProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			
			<!-- Loads only when user clicks "manageProducts" -->
			<c:if test="${userClickManageProducts == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>
			
			
			

			<!-- Footer -->
			<%@include file="./shared/footer.jsp"%>

			<script src="${jquery}/jquery.js"></script>
			
			<!-- Jquery Validate -->
			<script src="${jquery}/jquery.validate.js"></script>
			
			<!-- Bootstrap core JavaScript -->
			<script src="${js}/bootstrap.bundle.min.js"></script>
			
			<!-- DataTable Plugin -->
			<script src="${jquery}/jquery.dataTables.js"></script>
			
			<!-- DataTable Bootstrap Script -->
			<script src="${js}/dataTables.bootstrap4.js"></script>
			
			<!-- Bootbox -->
			<script src="${js}/bootbox.min.js"></script>

			<!-- Self Coded javascript -->
			<script src="${js}/myapp.js"></script>
		</div>
	</div>



</body>

</html>
