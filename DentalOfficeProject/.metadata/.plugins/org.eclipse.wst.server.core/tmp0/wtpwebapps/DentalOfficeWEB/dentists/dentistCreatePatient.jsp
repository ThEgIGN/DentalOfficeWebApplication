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
<title>Create new patient</title>
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
	<h4>Enter information about new patient:</h4>
	<form action="/DentalOfficeWEB/CreatePatientServlet" method="post">
		<input type="hidden" name="targetJSP"
			value="/dentists/dentistCreatePatient.jsp">
		<table>
			<tr>
				<td>First name:</td>
				<td><input type="text" name="firstName"></td>
			</tr>
			<tr>
				<td>Last name:</td>
				<td><input type="text" name="lastName"></td>
			</tr>
			<tr>
				<td>JMBG:</td>
				<td><input type="text" name="jmbg"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Phone number:</td>
				<td><input type="text" name=phoneNumber></td>
			</tr>
			<tr>
				<td><input type="submit" value="Proceed"></td>
			</tr>
		</table>
	</form>
	<h3>${messageCreatePatient}</h3>
</body>
</html>