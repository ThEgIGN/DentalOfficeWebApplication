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
<title>Welcome Dentist</title>
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
	<c:choose>
		<c:when test="${empty currentPatient}">
			<h4>Hello dentist, please enter your JMBG for confirmation:</h4>
			<form action="/DentalOfficeWEB/GetExistingDentistServlet"
				method="post">
				<table>
					<tr>
						<td>JMBG:</td>
						<td><input type="text" name="jmbg"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Proceed"></td>
					</tr>
				</table>
			</form>
			<br>
	${messageLoginDentist}
	</c:when>
		<c:otherwise>
			<h3>Patient, please logout first.</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>