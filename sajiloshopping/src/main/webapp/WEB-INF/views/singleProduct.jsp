<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<!-- Breadcrumb -->
	<div class="row">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/allproducts">Products</a></li>
				<li class="active">${product.name}</li>
			</ol>
		</div>
	</div>

	<div class="row">
		<!-- To display the image -->
		<div class="col-xs-12 col-md-4">
			<img src="${images}/${product.code}.jpg"
				class="img img-responsive singlefixImg" />
		</div>

		<!-- Diplay the product contents -->
		<div class="col-xs-12 col-md-8">
			<h3>${product.name}</h3>
			<hr />
			<p>${product.description}</p>
			<hr />
			<h4>
				Price : <strong>Rs. ${product.unitPrice} /-</strong>
			</h4>
			<hr />
			<c:choose>
				<c:when test="${product.quantity < 1}">
					<h6>Qty. Available : <span style="color:red;">Out of Stock!</span></h6>
					<a href="javascript:void(0)" class="btn btn-danger disabled"><strike>Add to Cart</strike></a>
				</c:when>
				<c:otherwise>
					<h6>Qty. Available : ${product.quantity}</h6>
					<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">Add to Cart</a>
				</c:otherwise>
			</c:choose>

			<a href="${contextRoot}/show/allproducts" class="btn btn-primary">
				Back </a>
		</div>
	</div>

</div>