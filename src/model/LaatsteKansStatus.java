package model;

public class LaatsteKansStatus extends QuizStatus {
	private Quiz quiz;
	
	public LaatsteKansStatus(Quiz quiz){
		this.quiz = quiz;
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
		throw new IllegalStateException("De quiz is status \"laatste kans\" een quizopdracht toevoegen is niet toegestaan");		
	}

	@Override
	void verwijderQuizOpdracht(QuizOpdracht opdracht) {
		throw new IllegalStateException("De quiz is status \"laatste kans\" een quizopdracht verwijderen is niet toegestaan");			
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
	public String toString(){
		return "LaatsteKans";
	}
}
