package dentistsServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.DentistManager;
import model.Dentist;

/**
 * Servlet implementation class GetExistingDentistServlet
 */
@WebServlet("/GetExistingDentistServlet")
public class GetExistingDentistServlet extends HttpServlet {
	private static final String MESSAGE_PARAM = "messageLoginDentist";
	private static final long serialVersionUID = 1L;
	private DentistManager dentistManager;
	private Dentist dentist;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetExistingDentistServlet() {
		super();
		dentistManager = new DentistManager();
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
			dentist = dentistManager.confirmDentistIdentity(jmbg);
			// If Manager returned null for some reason, let user know. Else, proceed with
			// dentist choice
			if (dentist == null) {
				messageValue = "It appears we don't have your profile saved.";
				target = "/dentists/loginDentist.jsp";
			} else {
				request.getSession().setAttribute("currentDentist", dentist);
				target = "/dentists/dentistChoice.jsp";
			}
		} else {
			messageValue = "Please fill out required field.";
			target = "/dentists/loginDentist.jsp";
		}

		request.setAttribute(MESSAGE_PARAM, messageValue);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
