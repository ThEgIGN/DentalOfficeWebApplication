package dentistsServlets;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helperClasses.AppointmentHelper;
import helperClasses.EmailUtility;
import model.Appointment;

/**
 * Servlet implementation class DentistDeleteAppointmentServlet
 */
@WebServlet("/DentistDeleteAppointmentServlet")
public class DentistDeleteAppointmentServlet extends HttpServlet {
	private static final String TARGET = "/dentists/dentistCalendar.jsp";
	private static final String MESSAGE_PARAM = "messageDentistCalendar";
	private static final long serialVersionUID = 1L;
	private AppointmentHelper appointmentHelper;
	private String host;
	private String port;
	private String user;
	private String pass;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DentistDeleteAppointmentServlet() {
		super();
		appointmentHelper = new AppointmentHelper();
	}

	// Using init() because we are reading from web.xml
	public void init() {
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageValue = "";
		int outerCounter = Integer.parseInt(request.getParameter("outerCounter"));
		int innerCounter = Integer.parseInt(request.getParameter("innerCounter"));
		List<List<Appointment>> listOfAppointmentsForDates = (List<List<Appointment>>) request.getSession()
				.getAttribute("dentistAllAppointments");
		Object[] result = appointmentHelper.removeAnAppointmentFromListOfLists(listOfAppointmentsForDates, innerCounter,
				outerCounter);

		boolean deleteCheck = (boolean) result[0];
		if (deleteCheck) {
			listOfAppointmentsForDates = (List<List<Appointment>>) result[1];
			request.getSession().setAttribute("dentistAllAppointments", listOfAppointmentsForDates);
			messageValue = "Successfully canceled requested appointment.";

			// Try to send e-mails
			Appointment emailAppointment = (Appointment) result[2];
			try {
				EmailUtility.sendEmail(host, port, user, pass, emailAppointment, "canceled");
			} catch (MessagingException e) {
				e.printStackTrace();
				messageValue = "Appointment canceled, but error occurred while trying to send e-mail for confirmation.";
			}
		} else {
			messageValue = "Error occured while trying to delete selected appointment. Please try again.";
		}

		request.setAttribute(MESSAGE_PARAM, messageValue);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(TARGET);
		rd.forward(request, response);
	}

}
