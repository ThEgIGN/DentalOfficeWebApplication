package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * The persistent class for the appointments database table.
 * 
 */
@Entity
@Table(name = "appointments")
@NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private int appointmentId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String description;

	private int length;

	@Column(name = "time_end")
	private Time timeEnd;

	@Column(name = "time_start")
	private Time timeStart;

	// bi-directional many-to-one association to Dentist
	@ManyToOne
	@JoinColumn(name = "dentist_id")
	private Dentist dentist;

	// bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public Appointment() {
	}

	public Appointment(Date date, int length, Time timeEnd, Time timeStart, Dentist dentist) {
		this.date = date;
		this.length = length;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.dentist = dentist;
	}

	public int getAppointmentId() {
		return this.appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Time getTimeEnd() {
		return this.timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Time getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Dentist getDentist() {
		return this.dentist;
	}

	public void setDentist(Dentist dentist) {
		this.dentist = dentist;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}