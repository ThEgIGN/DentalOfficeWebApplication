package dentistsServlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helperClasses.AppointmentHelper;
import model.Appointment;

/**
 * Servlet implementation class DentistAvailableAppointmentsServlet
 */
@WebServlet("/DentistAvailableAppointmentsServlet")
public class DentistAvailableAppointmentsServlet extends HttpServlet {
	private static final String TARGET = "/dentists/dentistCalendar.jsp";
	private static final String MESSAGE_PARAM = "appointmentInfoMessage";
	private static final long serialVersionUID = 1L;
	private AppointmentHelper appointmentHelper;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DentistAvailableAppointmentsServlet() {
		super();
		appointmentHelper = new AppointmentHelper();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageValue = "";
		String dateAppointmentString = request.getParameter("dateAppointment");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateAppointment = null;
		// If dentist wants to view one day or whole week
		String calendarLenght = request.getParameter("lengthCalendar");

		if (dateAppointmentString != null && !dateAppointmentString.isBlank()) {
			try {
				dateAppointment = sdf.parse(dateAppointmentString);
			} catch (ParseException e) {
				e.printStackTrace();
				// Unreachable (at least in Firefox)
			}

			// Add number of appointments for date based on week (7) or day (1)
			List<List<Appointment>> listOfAppointmentsForDates = new ArrayList<>();
			if (calendarLenght.equals("week")) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateAppointment);
				for (int i = 0; i < 7; i++) {
					listOfAppointmentsForDates
							.add(appointmentHelper.createListWithAllAppointmentsForDate(dateAppointment));
					cal.add(Calendar.HOUR_OF_DAY, 24);
					dateAppointment = cal.getTime();
				}
			} else {
				listOfAppointmentsForDates.add(appointmentHelper.createListWithAllAppointmentsForDate(dateAppointment));
			}

			request.getSession().setAttribute("dentistAllAppointments", listOfAppointmentsForDates);
		} else {
			messageValue = "Please select valid date.";
		}

		request.setAttribute(MESSAGE_PARAM, messageValue);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(TARGET);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
