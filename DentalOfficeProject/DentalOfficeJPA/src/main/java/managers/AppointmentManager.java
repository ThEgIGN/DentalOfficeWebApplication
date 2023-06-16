package managers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.Appointment;

public class AppointmentManager {

	public List<Appointment> getAllAppointmentsForDate(Date date) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			List<Appointment> list = em
					.createQuery("select a from Appointment a where a.date = :date", Appointment.class)
					.setParameter("date", date).getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Appointment addAppointment(Appointment a) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean removeAppointment(Appointment a) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.contains(a) ? a : em.merge(a));
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Appointment> getAllAppointmentsForJmbg(String jmbg) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			Date dateToday = new Date();
			List<Appointment> list = em
					.createQuery("select a from Appointment a where a.patient.jmbg like :jmbg and a.date >= :dateToday",
							Appointment.class)
					.setParameter("jmbg", jmbg).setParameter("dateToday", dateToday).getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
