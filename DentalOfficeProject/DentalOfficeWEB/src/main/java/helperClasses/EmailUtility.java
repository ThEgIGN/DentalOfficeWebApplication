package helperClasses;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Appointment;

public class EmailUtility {
	public static void sendEmail(String host, String port, final String userName, final String password,
			Appointment appointment, String operationType) throws MessagingException {

		// Set SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Create a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		StringBuilder sbAppointment = new StringBuilder();
		sbAppointment.append("\nAppointment date: " + appointment.getDate());
		sbAppointment.append("\nAppointment time: " + appointment.getTimeStart() + "-" + appointment.getTimeEnd());
		sbAppointment.append("\nReason for visit: " + appointment.getDescription());
		sbAppointment.append(
				"\nPatient: " + appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
		sbAppointment.append(
				"\nDentist: " + appointment.getDentist().getFirstName() + " " + appointment.getDentist().getLastName());

		String subject = "Dental Office - Appointment ID " + appointment.getAppointmentId() + " succesfully "
				+ operationType;
		String finalWords = operationType.equals("reserved") ? "See you soon!" : "Hope to see you again.";
		String topic = operationType.equals("reserved") ? "appointment" : "cancelation";

		StringBuilder sbMessage = new StringBuilder();

		// Create e-mail message for patient
		Message msgPatient = new MimeMessage(session);

		sbMessage.append("Hello " + appointment.getPatient().getFirstName());
		sbMessage.append(", we have received your " + topic + ".");
		sbMessage.append("\nHere's information about it:\n");
		sbMessage.append(sbAppointment.toString());
		sbMessage.append("\n\n" + finalWords);
		sbMessage.append("\nDental Office");

		msgPatient.setFrom(new InternetAddress(userName));
		msgPatient.addRecipient(Message.RecipientType.TO, new InternetAddress(appointment.getPatient().getEmail()));
		msgPatient.setSubject(subject);
		msgPatient.setSentDate(new Date());
		msgPatient.setText(sbMessage.toString());

		// Create e-mail message for dentist
		Message msgDentist = new MimeMessage(session);

		sbMessage.delete(0, sbMessage.length());
		sbMessage.append("Hello " + appointment.getDentist().getFirstName());
		sbMessage.append(", you have a new " + topic + ".");
		sbMessage.append("\nHere's information about it:\n");
		sbMessage.append(sbAppointment.toString());
		sbMessage.append("\n\nDental Office");

		msgDentist.setFrom(new InternetAddress(userName));
		msgDentist.addRecipient(Message.RecipientType.TO, new InternetAddress(appointment.getDentist().getEmail()));
		msgDentist.setSubject(subject);
		msgDentist.setSentDate(new Date());
		msgDentist.setText(sbMessage.toString());

		// Send e-mails
		Transport.send(msgPatient);
		Transport.send(msgDentist);

	}
}
