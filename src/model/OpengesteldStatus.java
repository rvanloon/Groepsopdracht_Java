package model;

public class OpengesteldStatus extends QuizStatus {
	private Quiz quiz;

	public OpengesteldStatus(Quiz quiz) {
		this.quiz = quiz;
	}

	@Override
	public void setLaatsteKans() {
		quiz.setStatus(quiz.getLaatsteKans());
	}

	@Override
	public void setAfgesloten() {
		quiz.setStatus(quiz.getAfgesloten());
	}

	@Override
	boolean setOnderwerpToegestaan() {
		return false;
	}

	@Override
	boolean setLeerjarenToegestaan() {
		return false;
	}

	@Override
	boolean setAuteurToegestaan() {
		return false;
	}

	@Override
	boolean setIsTestToegestaan() {
		return false;
	}

	@Override
	boolean setDatumRegistratieToegestaan() {
		return false;
	}

	@Override
	boolean setOpdrachtenToegestaan() {
		return false;
	}

	@Override
	boolean setQuizDeelnamesToegestaan() {
		return false;
	}

	@Override
	void voegQuizOpdrachtToe(QuizOpdracht opdracht) {
		throw new IllegalStateException(
				"De quiz is opengesteld, een QuizOpdracht toevoegen is niet meer toegestaan");
	}

	@Override
	void verwijderQuizOpdracht(QuizOpdracht opdracht) {
		throw new IllegalStateException(
				"De quiz is opengesteld, een QuizOpdracht verwijderen is niet meer toegestaan");
	}

	@Override
	void voegQuizDeelnameToe(QuizDeelname quizDeelname) {
		if (quizDeelname == null) {
			throw new IllegalArgumentException(
					"De quizDeelname mag niet null zijn");
		}
		if (quiz.getQuizDeelnames().contains(quizDeelname)) {
			throw new IllegalArgumentException(
					"Deze quizDeelname is al toegevoegd.");
		}
		quiz.getQuizDeelnames().add(quizDeelname);
	}

	@Override
	void verwijderQuizDeelname(QuizDeelname quizDeelname) {
		if (quizDeelname == null) {
			throw new IllegalArgumentException(
					"De quizDeelname mag niet null zijn");
		}
		if (!(quiz.getQuizDeelnames().contains(quizDeelname))) {
			throw new IllegalArgumentException(
					"De quizDeelname zit niet in de lijst");
		}
		quiz.getQuizDeelnames().remove(quizDeelname);
	}

	@Override
	public String toString() {
		return "Opengesteld";
	}

}
