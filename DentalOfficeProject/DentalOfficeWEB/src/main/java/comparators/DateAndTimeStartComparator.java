package comparators;

import java.util.Comparator;

import model.Appointment;

public class DateAndTimeStartComparator implements Comparator<Appointment> {

	@Override
	public int compare(Appointment a1, Appointment a2) {
		// Sort by dates. If dates are same then sort by timeStart
		int dateResult = a1.getDate().compareTo(a2.getDate());
		return dateResult == 0 ? a1.getTimeStart().toLocalTime().compareTo(a2.getTimeStart().toLocalTime())
				: dateResult;
	}

}
