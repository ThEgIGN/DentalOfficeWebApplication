<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
th, td {
	padding: 4px;
}
</style>
<meta charset="ISO-8859-1">
<title>Please choose what you need</title>
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
	<c:if test="${!empty currentPatient}">
		<h3>Welcome ${currentPatient.firstName}
			${currentPatient.lastName}. You can make an appointment or check your
			existing ones.</h3>
		<form action="/DentalOfficeWEB/patients/createAppointment.jsp">
			<button type="submit">Create appointment</button>
		</form>
		<br>
		<form action="/DentalOfficeWEB/PatientsExistingAppointmentsServlet"
			method="get">
			<button type="submit">Check existing appointments</button>
		</form>
	</c:if>
</body>
</html>