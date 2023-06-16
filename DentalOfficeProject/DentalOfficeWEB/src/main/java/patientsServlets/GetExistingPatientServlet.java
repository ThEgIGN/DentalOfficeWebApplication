package patientsServlets;

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
 * Servlet implementation class GetExistingPatientServlet
 */
@WebServlet("/GetExistingPatientServlet")
public class GetExistingPatientServlet extends HttpServlet {
	private static final String MESSAGE_PARAM = "messageCreatePatient";
	private static final long serialVersionUID = 1L;
	private PatientManager patientManager;
	private Patient patient;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetExistingPatientServlet() {
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
		String messageValue = "";
		String target = "";
		String jmbg = request.getParameter("jmbg");

		if (jmbg != null && !jmbg.isBlank()) {
			patient = patientManager.findPatientByJmbg(jmbg);
			// If Manager returned null for some reason, let user know. Else, proceed with
			// patient choice
			if (patient == null) {
				messageValue = "It appears we don't have your profile saved. Please use other form to create new profile.";
				target = "/patients/createPatient.jsp";
			} else {
				request.getSession().setAttribute("currentPatient", patient);
				target = "/patients/patientChoice.jsp";
			}
		} else {
			messageValue = "Please fill out required field.";
			target = "/patients/createPatient.jsp";
		}

		request.setAttribute(MESSAGE_PARAM, messageValue);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(target);
		rd.forward(request, response);
	}

}