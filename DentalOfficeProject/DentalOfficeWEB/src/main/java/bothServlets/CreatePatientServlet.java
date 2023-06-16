package bothServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PatientManager;
import model.Patient;

/**
 * Servlet implementation class CreatePatientServlet
 */
@WebServlet("/CreatePatientServlet")
public class CreatePatientServlet extends HttpServlet {
	private static final String MESSAGE_PARAM = "messageCreatePatient";
	private static final long serialVersionUID = 1L;
	private PatientManager patientManager;
	private Patient patient;

	// Check if required params (all but phone number) are not empty
	private boolean checkParams(String[] params) {
		for (String p : params) {
			if (p == null || p.isBlank()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePatientServlet() {
		super();
		patientManager = new PatientManager();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Since this servlet is being used by a patient and doctor, we need to know
		// where to send response with targetJSP
		String messageValue = "";
		String targetJSP = request.getParameter("targetJSP");
		String jmbg = request.getParameter("jmbg");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String[] params = { firstName, lastName, email, jmbg };
		boolean checkParams = checkParams(params);

		// If every needed params isn't blank, proceed
		if (checkParams) {
			// Make sure that user with same JMBG isn't being created
			Patient tempPatient = patientManager.findPatientByJmbg(jmbg);
			if (tempPatient == null) {
				patient = patientManager.addPatient(firstName, lastName, jmbg, email, phoneNumber);
				// If Manager returned null for some reason, let user know. Else, proceed with
				// patient choice
				if (patient == null) {
					messageValue = "Unexpected error occured while creating your profile.";
				} else if (targetJSP.equals("/patients/createPatient.jsp")) {
					request.getSession().setAttribute("currentPatient", patient);
					targetJSP = "/patients/patientChoice.jsp";
				} else {
					messageValue = "Successfully added new patient " + patient.getFirstName() + " "
							+ patient.getLastName() + ".";
					targetJSP = "/dentists/dentistCalendar.jsp";
				}
			} else {
				messageValue = "It appears profile with entered JMBG already exists in our database.";
			}
		} else {
			messageValue = "Please fill out all the required fields.";
		}

		request.setAttribute(MESSAGE_PARAM, messageValue);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(targetJSP);
		rd.forward(request, response);
	}

}
