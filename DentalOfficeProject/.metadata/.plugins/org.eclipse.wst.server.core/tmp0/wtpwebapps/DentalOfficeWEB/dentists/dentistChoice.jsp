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
<title>Choose one of the options dentist</title>
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
	<c:if test="${!empty currentDentist}">
		<h3>Welcome ${currentDentist.firstName}
			${currentDentist.lastName}. You can make an appointment or check your
			existing ones.</h3>
		<form action="/DentalOfficeWEB/dentists/dentistCalendar.jsp">
			<button type="submit">Dentists calendar</button>
		</form>
		<h4>If you want to change deadline for appointments, please enter
			number of hours:</h4>
		<form action="/DentalOfficeWEB/ChangeExistingDeadlineServlet"
			method="post">
			<table>
				<tr>
					<td>Hours:</td>
					<td><input type="text" name="hours"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Change deadline"></td>
				</tr>
			</table>
		</form>
	</c:if>
	<br> ${messageChangingDeadline}
</body>
</html>