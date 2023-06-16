package bothServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dentist;
import model.Patient;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tries to grab dentist and patient from current session
		// If non exists, then user just randomly clicked Logout
		Dentist dentist = (Dentist) request.getSession().getAttribute("currentDentist");
		Patient patient = (Patient) request.getSession().getAttribute("currentPatient");
		// Try to remove all objects that are related to specific role
		if (dentist != null) {
			request.getSession().removeAttribute("currentDentist");
			request.getSession().removeAttribute("dentistAllAppointments");
		} else if (patient != null) {
			request.getSession().removeAttribute("currentPatient");
			request.getSession().removeAttribute("patientAllAppointments");
			request.getSession().removeAttribute("patientAvailableAppointments");
			request.getSession().removeAttribute("patientAppointmentsDate");
		}

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.jsp");
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
