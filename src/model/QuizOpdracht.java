package model;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class QuizOpdracht {

	private int maxScore;
	private Opdracht opdracht;
	private Quiz quiz;

	/**
	 * @param maxScore
	 * @param opdracht
	 * @param quiz
	 */
	public QuizOpdracht(int maxScore, Opdracht opdracht, Quiz quiz)
			throws IllegalArgumentException {
		this.maxScore = maxScore;
		this.opdracht = opdracht;
		this.quiz = quiz;
	}

	/**
	 * @return the maxScore
	 */
	public int getMaxScore() {
		return maxScore;
	}

	/**
	 * @param maxScore
	 *            the maxScore to set (mag niet negatief zijn)
	 */
	public void setMaxScore(int maxScore) {
		if (maxScore < 0) {
			throw new IllegalArgumentException(
					"maxscore mag niet negatief zijn.");
		}
		this.maxScore = maxScore;
	}

	/**
	 * @return the opdracht
	 */
	public Opdracht getOpdracht() {
		return opdracht;
	}

	/**
	 * @param opdracht
	 *            the opdracht to set
	 */
	public void setOpdracht(Opdracht opdracht) {
		if (opdracht == null) {
			throw new IllegalArgumentException("opdracht is null.");
		}

		this.opdracht = opdracht;
	}

	/**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz
	 *            the quiz to set
	 */
	public void setQuiz(Quiz quiz) {
		if (quiz == null) {
			throw new IllegalArgumentException("quiz is null.");
		}

		this.quiz = quiz;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxScore;
		result = prime * result
				+ ((opdracht == null) ? 0 : opdracht.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizOpdracht other = (QuizOpdracht) obj;
		if (maxScore != other.maxScore)
			return false;
		if (opdracht == null) {
			if (other.opdracht != null)
				return false;
		} else if (!opdracht.equals(other.opdracht))
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
