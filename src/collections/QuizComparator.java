package collections;

import java.util.Comparator;

import model.Quiz;

public class QuizComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Quiz quiz1 = (Quiz) o1;
		Quiz quiz2 = (Quiz) o2;
		int result;
		
		result = quiz1.getOpdrachten().size() - quiz1.getOpdrachten().size();
		if (result == 0) {
			result = quiz1.getOnderwerp().compareTo(quiz2.getOnderwerp());
		}
		return result;
	}

}
