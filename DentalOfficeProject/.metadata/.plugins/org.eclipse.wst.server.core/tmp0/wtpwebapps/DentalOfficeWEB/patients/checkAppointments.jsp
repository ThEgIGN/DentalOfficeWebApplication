<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
th, td {
	padding: 4px;
}
</style>
<meta charset="ISO-8859-1">
<title>Check your existing appointments</title>
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
	<h3>Your existing appointments:</h3>
	<br>
	<c:if test="${!empty patientAllAppointments}">
		<table style="text-align: center;" border="1">
			<tr>
				<th>Date</th>
				<th>Starting time</th>
				<th>Ending time</th>
				<th>Dentist</th>
				<th>Visit reason</th>
				<th>Cancellation</th>
			</tr>
			<c:forEach items="${patientAllAppointments}" var="a"
				varStatus="counter">
				<tr>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${a.date}" /></td>
					<td>${a.timeStart}</td>
					<td>${a.timeEnd}</td>
					<td>${a.dentist.firstName} ${a.dentist.lastName}</td>
					<td>${a.description}</td>
					<td><form
							action="/DentalOfficeWEB/PatientsExistingAppointmentsServlet"
							method="post">
							<input type="hidden" name="appointment" value="${counter.index}">
							<button type="submit">Cancel</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br>
	<h3>${messagePatientAllAppointments}</h3>
</body>
</html>