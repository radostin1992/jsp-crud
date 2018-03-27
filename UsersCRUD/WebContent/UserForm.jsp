<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management Application</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
<style>
.submit {
	background-color: #008CBA;
	border: none;
	border-radius: 4px;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<link rel="stylesheet" href="/resources/demos/style.css">

</head>
<body>
	<div class="container">
		<center>
			<h1>User Management</h1>
			<h2>
				<a href="new">Add New User</a> &nbsp;&nbsp;&nbsp; <a href="list">List
					All Users</a>

			</h2>
		</center>

		<c:if test="${user != null}">
			<form class="form-horizontal" action="update" method="post">
		</c:if>
		<c:if test="${user == null}">
			<form class="form-horizontal" action="insert" method="post">
		</c:if>

		<div class="col-sm-12">
			<h3>
				<c:if test="${user != null}">
                        Edit User
                    </c:if>
				<c:if test="${user == null}">
                        Add New User
                    </c:if>
			</h3>
		</div>


		<c:if test="${user != null}">
			<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
		</c:if>



		<div class="form-group">
			<label for="firstName" class="col-sm-2 control-label">First
				Name</label>
			<div class="col-sm-10">
				<input type="text"  name="firstName" class="form-control" id="firstNmae"
					placeholder="Enter user first name"
					value="<c:out value='${user.firstName}' />" required>
			</div>
		</div>



		<div class="form-group">
			<label for="lastName" class="col-sm-2 control-label">Last
				Name</label>
			<div class="col-sm-10">
				<input type="text"  name="lastName" class="form-control" id="lastName"
					placeholder="Enter user last name"
					value="<c:out value='${user.lastName}' />" required>
			</div>
		</div>

		<div class="form-group">
			<label for="birthDate" class="col-sm-2 control-label">Date of
				birth</label>
			<div class="col-sm-10">
				<input type="date" name="birthDate" class="form-control" id="birthDate"
					value="<c:out value='${user.birthdate}' />" required>
			</div>
		</div>

		<div class="form-group">
			<label for="phonenumber" class="col-sm-2 control-label">Phone
				Number</label>
			<div class="col-sm-10">
				<input type="text"  name="phonenumber" class="form-control" id="phonenumber"
					placeholder="Enter user phone number"
					onkeypress='return event.charCode >= 48 && event.charCode <= 57'
					value="<c:out value='${user.phoneNumber}'  />" required>
			</div>
		</div>

		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">E-mail</label>
			<div class="col-sm-10">
				<input type="email" name="email" class="form-control" id="email"
					placeholder="Enter user e-mail address"
					value="<c:out value='${user.email}' />" required>
			</div>
		</div>

		<div class="form-group" align="center">
			<input class="submit" type="submit" value="Save" />
		</div>
	</form>	
	</div>



</body>
</html>