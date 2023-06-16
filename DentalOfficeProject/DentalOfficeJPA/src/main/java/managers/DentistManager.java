package managers;

import java.util.List;
import javax.persistence.EntityManager;

import model.Dentist;

public class DentistManager {

	public Dentist getFirstDentist() {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			List<Dentist> list = em.createQuery("select d from Dentist d where d.dentistId = 1", Dentist.class)
					.getResultList();
			return list == null ? null : list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Dentist confirmDentistIdentity(String jmbg) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			List<Dentist> dentists = em.createQuery("select d from Dentist d where d.jmbg like :jmbg", Dentist.class)
					.setParameter("jmbg", jmbg).getResultList();
			if (dentists != null) {
				return dentists.size() == 1 ? dentists.get(0) : null;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
