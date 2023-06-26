<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Read Share</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-10 mt-5">
				<h1>Add a Book to your shelf!</h1>
			</div>
			<div class="col-2 mt-5">
				<a href="/users/logout" class="btn btn-danger">Logout</a>
			</div>
		</div>
		<div class="mt-5">
			<form:form action="/books/process/create" method="post"
				modelAttribute="book">
				<form:input type="hidden" path="postedBy" value="${user_id}" />
				<div class="form-group">
					<label for="title">Title:</label>
					<form:input type="text" path="title" class="form-control" />
										<small class="form-text text-danger"> <form:errors
								path="title" />
						</small>
				</div>
				<div class="form-group">
					<label for="author">Author:</label>
					<form:input type="text" path="author" class="form-control" />
										<small class="form-text text-danger"> <form:errors
								path="author" />
						</small>
				</div>
				<div class="form-group">
					<label for="thoughts">My Thoughts:</label>
					<form:input type="text" path="thoughts" class="form-control" />
										<small class="form-text text-danger"> <form:errors
								path="thoughts" />
						</small>
				</div>
				<input type="submit" value="Submit" class="btn btn-success" />
			</form:form>
		</div>
	</div>

</body>
</html>