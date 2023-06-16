package managers;

import javax.persistence.EntityManager;
import java.util.List;

import model.Patient;

public class PatientManager {

	public Patient addPatient(String firstName, String lastName, String jmbg, String email, String phoneNumber) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			Patient patient = new Patient();
			patient.setFirstName(firstName);
			patient.setLastName(lastName);
			patient.setEmail(email);
			patient.setJmbg(jmbg);
			patient.setPhoneNumber(phoneNumber);
			em.persist(patient);
			em.getTransaction().commit();
			return patient;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Patient findPatientByJmbg(String jmbg) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			List<Patient> patients = em.createQuery("select p from Patient p where p.jmbg like :jmbg", Patient.class)
					.setParameter("jmbg", jmbg).getResultList();
			if (patients != null) {
				return patients.size() == 1 ? patients.get(0) : null;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}