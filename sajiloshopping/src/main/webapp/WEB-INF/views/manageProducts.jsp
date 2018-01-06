<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<c:if test="${not empty message}">
		<div class="col-md-12">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${message}
			</div>
		</div>
	</c:if>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6 card border-info mb-3">
			<div class="card-header" role="button" data-toggle="collapse" data-target="#card-body">
				<h4>
					Product Management
				</h4>
			</div>

			<div class="card-body" id="card-body">
				<!-- Form Elements -->
				<sf:form class="form-horizontal" modelAttribute="nproduct"
					action="${contextRoot}/manage/products" method="POST"
					enctype="multipart/form-data">

					<div class="form-group row">
						<label class="control-label col-md-3" for="name">Product
							Name</label>
						<div class="col-md-9">
							<sf:input type="text" class="form-control" path="name" id="name"
								placeholder="Enter the product name..." />
							<sf:errors path="name" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group row">
						<label class="control-label col-md-3" for="brand">Brand
							Name</label>
						<div class="col-md-9">
							<sf:input type="text" class="form-control" path="brand"
								id="brand" placeholder="Enter the brand name..." />
							<sf:errors path="brand" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group row">
						<label class="control-label col-md-3" for="description">Description</label>
						<div class="col-md-9">
							<sf:textarea rows="4" class="form-control" path="description"
								id="description"
								placeholder="write the description of the product" />
							<sf:errors path="description" cssClass="help-block" element="em" />
						</div>
					</div>

					<div class="form-group row">
						<label class="control-label col-md-3" for="unitPrice">Unit
							Price</label>
						<div class="col-md-9">
							<div class="input-group">
								<div class="input-group-addon">Rs.</div>
								<sf:input type="number" class="form-control" path="unitPrice"
									id="unitPrice" placeholder="unit price in Rupees" />
							</div>
							<sf:errors path="unitPrice" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group row">
						<label class="control-label col-md-3" for="quantity">Quantity</label>
						<div class="col-md-9">
							<sf:input type="number" class="form-control" path="quantity"
								id="quantity" placeholder="quantities available" />
						</div>
					</div>
					<!-- File element for image upload -->
					<div class="form-group row">
						<label class="control-label col-md-3" for="file">Image</label>
						<div class="col-md-9">
							<label class="custom-file"> <sf:input type="file"
									class="custom-file-input" path="file" id="file" /> <span
								class="custom-file-control"></span>
							</label>
							<sf:errors path="file" cssClass="help-block" element="em" />
						</div>
					</div>
					<div class="form-group row">
						<label class="control-label col-md-3" for="categoryId">Category</label>
						<div class="col-md-9">
							<sf:select class="form-control" path="categoryId" id="categoryId"
								items="${categories}" itemLabel="name" itemValue="id" />

							<c:if test="${product.id == null}">
								<div class="text-right">
									<br />
									<button type="button" data-toggle="modal"
										data-target="#myCategoryModal" class="btn btn-success btn-xs">Add
										Category</button>
								</div>
							</c:if>
						</div>
					</div>

					<div class="form-group row">
						<div class="col-md-3"></div>
						<div class="col-md-9">
							<input type="submit" class="btn btn-primary" name="submit"
								id="submit" value="Submit" />

							<!-- Using hidden fields for rest of the product entities -->
							<sf:hidden path="id" />
							<sf:hidden path="code" />
							<sf:hidden path="supplierId" />
							<sf:hidden path="active" />
							<sf:hidden path="purchases" />
							<sf:hidden path="views" />
						</div>
					</div>
				</sf:form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Add New Category</h4>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
					</div>
					
					<div class="modal-body">
						<!-- Category Form -->
						<sf:form id="categoryForm"
							modelAttribute="category" 
							action="${contextRoot}/manage/category" 
							method="POST" 
							class="form-horizontal"
						>
							<div class="form-group row">
								<label for="category_name" class="control-label col-md-4">Category Name</label>
								<div class="col-md-8">
									<sf:input type="text" path="name" id="category_name" class="form-control"/>
								</div>
							</div>
							
							<div class="form-group row">
								<label for="category_description" class="control-label col-md-4">Description Name</label>
								<div class="col-md-8">
									<sf:textarea cols="" rows="5" path="description" id="category_description" class="form-control"/>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-8">
									<input type="submit" value="Add Category" class="btn btn-success"/>
								</div>
							</div>
						</sf:form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">	
				<h3>Available Products</h3>
			</div>
			<div class="col-xs-12 col-md-12">
				<div class="container-fluid">
					<div class="table-responsive">
						<!-- Products table for Admin -->
						<table id="adminProductsTable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Id</th>
									<th>&#160;</th>
									<th>Name</th>
									<th>Brand</th>
									<th>Qnty</th>
									<th>Unit Price</th>
									<th>Active</th>
									<th>Edit</th>
								</tr>
							</thead>
							
						</table>
						
					</div>
				</div>
			</div>
		</div>
		
</div>