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
					Welcome,
					<c:out value="${loggedInUser.userName}" />.</h1>
				<p>Books from everyone's shelves.</p>
			</div>
			<div class="col-2 mt-5">
				<a href="/books/create" class="btn btn-info">Add a Book</a> <a
					href="/users/logout" class="btn btn-danger">Logout</a>
			</div>
		</div>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author Name</th>
						<th>Posted By</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${allBooks}">
						<tr>
							<td><c:out value="${book.id}" /></td>
							<td><a href="/books/${book.id}"><c:out
										value="${book.title}" /></a></td>
							<td><c:out value="${book.author}" /></td>
							<td><c:out value="${book.postedBy.userName}" /></td>
							<c:if test="${user_id == book.postedBy.id }">
							<td class="d-flex gap-2"><a href="/books/edit/${book.id}" class="btn btn-warning">Edit</a>
								<form action="/books/delete/${book.id}" method="post">
									<input type="hidden" name="_method" value="delete" /> <input
										type="submit" value="Delete" class="btn btn-danger" />
								</form></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


	</div>
</body>
</html>