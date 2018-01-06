
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-5">
				<div class="card text-white border-info mb-3">
					<div class="card-header">
						<h4>Registration - Personal</h4>
					</div>
					<div class="card-body">
						<sf:form method="POST" class="form" id="registerForm" modelAttribute="user">
							<div class="form-group row">
								<label class="control-label col-md-4">First Name</label>
								<div class="col-md-8">
									<sf:input path="firstName" type="text" class="form-control" placeholder="First Name"/>
									<sf:errors path="firstName" cssClass="help-block" element="em"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="control-label col-md-4">Last Name</label>
								<div class="col-md-8">
									<sf:input path="lastName" type="text" class="form-control" placeholder="Last Name"/>
									<sf:errors path="lastName" cssClass="help-block" element="em"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="control-label col-md-4">Email</label>
								<div class="col-md-8">
									<sf:input path="email" type="text" class="form-control" placeholder="abc@xyz.com"/>
									<sf:errors path="email" cssClass="help-block" element="em"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="control-label col-md-4">Contact Number</label>
								<div class="col-md-8">
									<sf:input path="contactNumber" type="text" class="form-control" placeholder="98-XXXXXXXX" maxlength="10"/>
									<sf:errors path="contactNumber" cssClass="help-block" element="em"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="control-label col-md-4">Password</label>
								<div class="col-md-8">
									<sf:input path="password" type="password" class="form-control" placeholder="password"/>
									<sf:errors path="password" cssClass="help-block" element="em"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="control-label col-md-4">Confirm Password</label>
								<div class="col-md-8">
									<sf:input path="confirmPassword" type="password" class="form-control" placeholder="Re-enter password"/>
									<sf:errors path="confirmPassword" cssClass="help-block" element="em"/>
								</div>
							</div>
							<!-- Radio Button using bootstrap class of radio-inline -->
							<div class="form-group row">
								<label class="control-label col-md-4">Select Role</label>
								<div class="col-md-8 btn-group" data-toggle="buttons">
									<label class="btn btn-secondary active">
										<sf:radiobutton path="role" name="role" value="USER" checked="checked"/>User   
									</label>
									<label class="btn btn-secondary">
										<sf:radiobutton path="role" name="role" value="SUPPLIER"/>Supplier
									</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-8">
									<button type="submit" class="btn btn-success" name="_eventId_billing"> Billing-NEXT </button>
								</div>
							</div>
						</sf:form>
					</div>
				</div>
			</div>
		</div>		
	</div>
<%@include file="../shared/flows-footer.jsp" %>			
			
			
