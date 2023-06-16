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
import managers.PatientManager;
import model.Appointment;
import model.Patient;

/**
 * Servlet implementation class DentistCreateAppointmentServlet
 */
@WebServlet("/DentistCreateAppointmentServlet")
public class DentistCreateAppointmentServlet extends HttpServlet {
	private static final String TARGET = "/dentists/dentistCalendar.jsp";
	private static final String MESSAGE_PARAM = "messageDentistCalendar";
	private static final long serialVersionUID = 1L;
	private AppointmentHelper appointmentHelper;
	private Patient patient;
	private PatientManager patientManager;
	private String host;
	private String port;
	private String user;
	private String pass;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DentistCreateAppointmentServlet() {
		super();
		appointmentHelper = new AppointmentHelper();
		patientManager = new PatientManager();
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
		// Try to find patient with entered jmbg
		String messageValue = "";
		String jmbg = request.getParameter("jmbg");

		if (jmbg != null && !jmbg.isBlank()) {
			patient = patientManager.findPatientByJmbg(jmbg);
			if (patient != null) {
				// Grab reason,length and indexes params and also grab list of lists from
				// current session
				int length = Integer.parseInt(request.getParameter("length"));
				String reasonForVisit = request.getParameter("reasonForVisit");
				int outerCounter = Integer.parseInt(request.getParameter("outerCounter"));
				int innerCounter = Integer.parseInt(request.getParameter("innerCounter"));

				// Using outer and inner index, grab specific inner list and send it to helper
				// method along with related index
				List<List<Appointment>> listOfAppointmentsForDates = (List<List<Appointment>>) request.getSession()
						.getAttribute("dentistAllAppointments");
				List<Appointment> appointmentsList = listOfAppointmentsForDates.get(outerCounter);
				Object[] result = appointmentHelper.makeAnAppointment(length, reasonForVisit, patient, innerCounter,
						appointmentsList);
				boolean checkAppointment = (boolean) result[0];

				if (checkAppointment) {
					// Update outer list with freshly updated inner list
					List<Appointment> updatedAppointments = (List<Appointment>) result[1];
					listOfAppointmentsForDates.set(outerCounter, updatedAppointments);
					request.getSession().setAttribute("dentistAllAppointments", listOfAppointmentsForDates);
					messageValue = "Succesfully made an appointment for patient " + patient.getFirstName() + " "
							+ patient.getLastName() + ".";

					Appointment emailAppointment = updatedAppointments.get(innerCounter);
					// Try to send e-mails
					try {
						EmailUtility.sendEmail(host, port, user, pass, emailAppointment, "reserved");
					} catch (MessagingException e) {
						e.printStackTrace();
						messageValue = "Appointment saved, but error occurred while trying to send e-mail for confirmation.";
					}
				} else {
					messageValue = "You attempted to make an appointment within invalid time frame.";
				}
			} else {
				messageValue = "There's no patient with entered JMBG.";
			}
		} else {
			messageValue = "Please fill out JMBG field.";
		}

		request.setAttribute(MESSAGE_PARAM, messageValue);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(TARGET);
		rd.forward(request, response);
	}

}
