package collections;

import java.util.Comparator;

import model.Quiz;

public class QuizComparator implements Comparator<Quiz> {

	@Override
	public int compare(Quiz o1, Quiz o2) {
		int result;

		result = o1.getOpdrachten().size() - o2.getOpdrachten().size();
		if (result == 0) {
			result = o1.getOnderwerp().compareTo(o2.getOnderwerp());
		}
		return result;
	}

}
