<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
th, td {
	padding: 4px;
}
</style>
<meta charset="ISO-8859-1">
<title>Dental Office Name</title>
</head>
<body>
	<table style="width: 100%; border-collapse: collapse;">
		<tr>
			<td style="text-align: left"><b><a
					href="/DentalOfficeWEB/ReturnToChoicesServlet">Back to choices</a></b></td>
			<td><b><a href="/DentalOfficeWEB/index.jsp">Dental
						Office</a></b></td>
			<td style="text-align: right"><b><a
					href="/DentalOfficeWEB/LogoutServlet">Logout</a></b></td>
		</tr>
	</table>
	<br>
	<h1>Welcome to our Dental Office!</h1>
	<h3>Please choose your role from ones below.</h3>
	<form action="/DentalOfficeWEB/patients/createPatient.jsp">
		<button type="submit">For patients</button>
	</form>
	<br>
	<form action="/DentalOfficeWEB/dentists/loginDentist.jsp">
		<button type="submit">For dentists</button>
	</form>
</body>
</html>