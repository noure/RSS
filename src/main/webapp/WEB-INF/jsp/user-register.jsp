<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<c:if test="${param.success eq true  }">
	<div class="alert alert-success">registration successfull !</div>
</c:if>
<form:form commandName="user" cssClass="form-horizontal registerForm">

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Name</label>
		<div class="col-sm-10">
			<form:input cssClass="form-control" path="name" placeholder="Name" />
			<form:errors path="name" />
		</div>
	</div>



	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email</label>
		<div class="col-sm-10">
			<form:input cssClass="form-control" path="email" placeholder="Email" />
			<form:errors path="email" />
		</div>
	</div>


	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password</label>
		<div class="col-sm-10">
			<form:password cssClass="form-control" path="password"
				placeholder="Password" />
			<form:errors path="password" />
		</div>
	</div>

	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password
			again</label>
		<div class="col-sm-10">
			<input type="password" name="password_again" class="form-control"
				placeholder=" Renter the Password" />
		</div>
	</div>



	<div class="form-group">
		<input type="submit" value="Save" class="btn btn-lg btn-primary ">


	</div>



</form:form>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(".registerForm")
								.validate(
										{

											rules : {

												name : {
													required : true,
													minlength : 3,

													remote : {
														url : "<spring:url value='/register/available.html' />",
														type : "get",
														data : {
															username : function() {
																return $(
																		"#name")
																		.val();
															}
														}
													}

												},

												email : {
													email : true,
													required : true,

												},

												password : {
													required : true,
													minlength : 5
												},

												password_again : {
													required : true,
													minlength : 5,
													equalTo : "#password"
												},

											},

											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											},
											messages : {
												name : {
													remote : "Ce prénom est déjà utilisé !"
												}
											}
										});
					});
</script>