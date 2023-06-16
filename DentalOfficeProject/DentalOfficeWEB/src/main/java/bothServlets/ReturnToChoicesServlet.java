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
 * Servlet implementation class ReturnToChoicesServlet
 */
@WebServlet("/ReturnToChoicesServlet")
public class ReturnToChoicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnToChoicesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String targetJSP = "";
		// Tries to grab dentist and patient from current session
		// If non exists, then user just randomly clicked Back to choices
		Dentist dentist = (Dentist) request.getSession().getAttribute("currentDentist");
		Patient patient = (Patient) request.getSession().getAttribute("currentPatient");
		if (dentist != null) {
			targetJSP = "/dentists/dentistChoice.jsp";
		} else if (patient != null) {
			targetJSP = "/patients/patientChoice.jsp";
		} else {
			targetJSP = "/index.jsp";
		}

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(targetJSP);
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
