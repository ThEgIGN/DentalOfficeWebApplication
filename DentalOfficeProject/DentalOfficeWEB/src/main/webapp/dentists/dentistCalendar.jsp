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
<title>Dentists calendar</title>
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
		<h4>Hello dentist. You can choose to view all appointments for
			one day or for one week, starting from selected date.</h4>
		<form action="/DentalOfficeWEB/DentistAvailableAppointmentsServlet"
			method="get">
			<table>
				<tr>
					<td>Choose date:</td>
					<td><input type="date" name="dateAppointment"></td>
				</tr>
				<tr>
					<td>Choose day/week:</td>
					<td><select name="lengthCalendar"><option value="day">Day</option>
							<option value="week">Week</option></select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Show Appointments"></td>
				</tr>
			</table>
		</form>
		<h4>If you need to add new patient to database, feel free to do
			it here:</h4>
		<form action="/DentalOfficeWEB/dentists/dentistCreatePatient.jsp">
			<button type="submit">Create new patient</button>
			<br>
			<h3>${messageCreatePatient}</h3>
			<h3>${messageDentistCalendar}</h3>
			<h3>${appointmentInfoMessage}</h3>
		</form>
	</c:if>
	<c:if test="${!empty dentistAllAppointments}">
		<c:forEach items="${dentistAllAppointments}"
			var="dentistAppointmentsForOneDate" varStatus="outerCounter">
			<h3>
				Showing appointments for date:
				<fmt:formatDate pattern="yyyy-MM-dd"
					value="${dentistAppointmentsForOneDate[0].date}" />
			</h3>
			<table style="text-align: center;" border="1">
				<tr>
					<th>Starting time</th>
					<th>Ending time</th>
					<th>Dentist</th>
					<th>Patient</th>
					<th>Patients email</th>
					<th>Visit reason</th>
					<th>Make an appointment</th>
					<th>Cancellation</th>
				</tr>
				<c:forEach items="${dentistAppointmentsForOneDate}" var="a"
					varStatus="innerCounter">
					<tr>
						<td>${a.timeStart}</td>
						<td>${a.timeEnd}</td>
						<td>${a.dentist.firstName} ${a.dentist.lastName}</td>
						<td>${a.patient.firstName} ${a.patient.lastName}</td>
						<td>${a.patient.email}</td>
						<td>${a.description}</td>
						<c:choose>
							<c:when test="${empty a.patient.patientId}">
								<td><form
										action="/DentalOfficeWEB/DentistCreateAppointmentServlet"
										method="post">
										Visit reason: <input type="text" name="reasonForVisit">
										Length: <select name="length"><option value=30>30</option>
											<option value=60>60</option></select> <br>Patients JMBG: <input
											type="text" name="jmbg"> <input type="hidden"
											name="outerCounter" value="${outerCounter.index}"> <input
											type="hidden" name="innerCounter"
											value="${innerCounter.index}">
										<button type="submit">Reserve</button>
									</form></td>
								<td>Available</td>
							</c:when>
							<c:otherwise>
								<td>Already reserved</td>
								<td><form
										action="/DentalOfficeWEB/DentistDeleteAppointmentServlet"
										method="post">
										<input type="hidden" name="outerCounter"
											value="${outerCounter.index}"> <input type="hidden"
											name="innerCounter" value="${innerCounter.index}">
										<button type="submit">Cancel</button>
									</form></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</c:if>
</body>
</html>