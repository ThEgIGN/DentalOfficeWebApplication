package dentistsServlets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeExistingDeadlineServlet
 */
@WebServlet("/ChangeExistingDeadlineServlet")
public class ChangeExistingDeadlineServlet extends HttpServlet {
	private static final String TARGET = "/dentists/dentistChoice.jsp";
	private static final String MESSAGE_PARAM = "messageChangingDeadline";
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "\\deadline.txt";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeExistingDeadlineServlet() {
		super();
	}

	// Change value of current file
	private boolean changeDeadlineValue(int newValue) {
		String path = getServletContext().getRealPath("/WEB-INF");
		path += FILE_NAME;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(Integer.toString(newValue));
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
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
		String hoursString = request.getParameter("hours");
		if (hoursString != null && !hoursString.isBlank()) {
			int hours = Integer.parseInt(hoursString);
			if (hours >= 0) {
				boolean checkDeadline = changeDeadlineValue(hours);
				if (checkDeadline) {
					messageValue = "Successfully changed deadline value to " + hours + " hours.";
				} else {
					messageValue = "Unexpected error occured. Please try again.";
				}
			} else {
				messageValue = "Please enter valid number of hours.";
			}
		} else {
			messageValue = "Please fill out required field.";
		}

		request.setAttribute(MESSAGE_PARAM, messageValue);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(TARGET);
		rd.forward(request, response);
	}

}
