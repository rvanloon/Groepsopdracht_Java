package collections;

import java.util.Comparator;

import model.Quiz;

public class QuizComparator2 implements Comparator<Quiz> {

	@Override
	public int compare(Quiz o1, Quiz o2) {
		 return o1.getOpdrachten().size() - o2.getOpdrachten().size() == 0 ? o1.getOnderwerp().compareTo(o2.getOnderwerp()) : o1.getOpdrachten().size() - o2.getOpdrachten().size();
		
	}

}
