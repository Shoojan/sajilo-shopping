<%@include file="../shared/flows-header.jsp" %>
	<div class="container">
		
		<div class="row">
			<!-- Column to display the personal details -->
			<div class="col-sm-6">
				<div class="card card-primary">
					<div class="card-header">
						<h4>Personal Details</h4>
					</div>
					<div class="card-body">
						<!-- Code to display personal details -->
						<h4>${registerModel.user.firstName} ${registerModel.user.lastName}</h4>
						<h5>Email: ${registerModel.user.email}</h5>
						<h5>Contact: ${registerModel.user.contactNumber}</h5>
						<h5>Role: ${registerModel.user.role}</h5>
					</div>
					<div class="card-footer">
						<!-- Anchor to move to edit of personal details -->
							<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-success">Edit</a>
						
					</div>
				</div>
			</div>
			
			<!-- Column to display the Address details -->
			<div class="col-sm-6">
				<div class="card card-primary">
					<div class="card-header">
						<h4>Address Details</h4>
					</div>
					<div class="card-body">
						<!-- Code to display communication details -->
						<h5>${registerModel.billing.addressLineOne}</h5>
						<h5>${registerModel.billing.addressLineTwo}</h5>
						<h5>${registerModel.billing.city} - ${registerModel.billing.postalCode}</h5>
						<h5>${registerModel.billing.state}, ${registerModel.billing.country}</h5>
					</div>
					<div class="card-footer">
						<!-- Anchor to move to edit of communication details -->
							<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-success">Edit</a>
						
					</div>
				</div>
			</div>
			
			<!-- Confirm Buttom for displaying the details -->
			<div class="row">
				<div class="col-sm-4">
					<div class="text-centre">
						<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-success">Confirm</a>
					</div>
				</div>
			</div>
		</div>
		
	</div>

<%@include file="../shared/flows-footer.jsp" %>			
			
			
