<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@include file="../shared/flows-header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-5">
			<div class="card border-info text-white">
				<div class="card-header">
					<h4>Registration - Address</h4>
				</div>
				<div class="card-body">
					<sf:form method="POST" class="form" id="billingForm"
						modelAttribute="billing">
						<div class="form-group row">
							<label class="control-label col-md-4">Address Line One</label>
							<div class="col-md-8">
								<sf:input path="addressLineOne" type="text" class="form-control"
									placeholder="Address Line One" />
								<sf:errors path="addressLineOne" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-4">Address Line Two</label>
							<div class="col-md-8">
								<sf:input path="addressLineTwo" type="text" class="form-control"
									placeholder="Address Line Two" />
								<sf:errors path="addressLineTwo" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-4">Postal Code</label>
							<div class="col-md-8">
								<sf:input path="postalCode" type="text" class="form-control"
									placeholder="Eg: 44600" />
								<sf:errors path="postalCode" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-4">City</label>
							<div class="col-md-8">
								<sf:input path="city" type="text" class="form-control"
									placeholder="Current City" />
								<sf:errors path="city" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-4">State</label>
							<div class="col-md-8">
								<sf:input path="state" type="text" class="form-control"
									placeholder="Current State" />
								<sf:errors path="state" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-4">Country</label>
							<div class="col-md-8">
								<sf:input path="country" type="text" class="form-control"
									placeholder="Nepal" />
								<sf:errors path="country" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="form-group">
							<div class="row">
								<div class="col-md-6">
									<!-- Submit button for moving to the personal -->
									<button type="submit" class="btn btn-success"
										name="_eventId_personal">Previous - Personal</button>
								</div>
								<div class="col-md-6">
									<!-- Submit button for confirmation -->
									<button type="submit" class="btn btn-success"
										name="_eventId_confirm">Next - Confirm</button>

								</div>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>

</div>

<%@include file="../shared/flows-footer.jsp"%>


