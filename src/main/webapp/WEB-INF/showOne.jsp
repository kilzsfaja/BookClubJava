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
				<h1>
					<c:out value="${book.title}" />
				</h1>
			</div>
			<div class="col-2 mt-5">
				<a href="/books" class="btn btn-info">back to all Books</a>
			</div>
		</div>
		<div class="mt-5">
			<h3>
				<c:out value="${book.postedBy.userName}" />
				read
				<c:out value="${book.title}" />
				by
				<c:out value="${book.author}" />
			</h3>
			<br>
			<p>
				Here are
				<c:out value="${book.postedBy.userName}" />'s thoughts:
			</p>
			<p>
				"<i><c:out value="${book.thoughts}"/></i>"
			</p>
			<c:if test="${user_id == book.postedBy.id }">
				<div class="d-flex gap-2">
				<a href="/books/edit/${book.id}"
					class="btn btn-warning">Edit</a>
					<form action="/books/delete/${book.id}" method="post">
						<input type="hidden" name="_method" value="delete" /> <input
							type="submit" value="Delete" class="btn btn-danger" />
					</form>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>