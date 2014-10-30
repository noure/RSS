<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h2>
	<c:out value="${user.name}" />
</h2>



<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-tabs a:first').tab('show');
		$(".triggerRemove").click(function(e) {
			e.preventDefault();

			$("#modelRemove .btnRemove").attr("href", $(this).attr("href"));
			$("#modelRemove").modal();

		});

	});
</script>

<br />
<br />

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
	<c:forEach items="${user.blogs }" var="blog">

		<li><a href="#blog_${blog.id}" role="tab" data-toggle="tab"><c:out
					value="${blog.name }" /> </a></li>
	</c:forEach>
</ul>

<!-- Tab panes -->
<!-- Tab panes -->
<div class="tab-content">
	<c:forEach items="${user.blogs }" var="blog">
		<div class="tab-pane" id="blog_${blog.id}">

			<a href='<spring:url value="/blog/remove/${blog.id }.html"/>'
				class="btn btn-danger triggerRemove">Remove this blog </a>
			<h1>
				<c:out value="${blog.name }" />
			</h1>
			<p>
				<c:out value="${blog.url }" />
			</p>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Date</th>
						<th>Item</th>


					</tr>

				</thead>
				<tbody>
					<c:forEach items="${blog.items }" var="item">

						<tr>
							<td><c:out value="${item.publishDate }" /></td>
							<td><strong><a
									href="<c:out value="${item.link}" />" target="_blank"> <c:out
											value="${item.title }" />
								</a> </strong> <br /> ${item.description }" </td>
						</tr>

					</c:forEach>




				</tbody>

			</table>

		</div>
	</c:forEach>
</div>

<form:form commandName="blog" cssClass="form-horizontal">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">New blog</h4>
				</div>


				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" path="name"
								placeholder="Name" />
							<form:errors path="name" />
						</div>
					</div>



					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">URL: </label>
						<div class="col-sm-10">
							<form:input cssClass="form-control" path="url" placeholder="Url" />
							<form:errors path="url" />

						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" value="Save blog ">
					</div>

				</div>
			</div>
		</div>
	</div>
</form:form>




<!-- Modal for removing blog  -->
<div class="modal fade" id="modelRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove blog</h4>
			</div>
			<div class="modal-body">Are you sur you want to remove blog</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href='' class="btn btn-danger btnRemove">Remove</a>
			</div>
		</div>
	</div>
</div>