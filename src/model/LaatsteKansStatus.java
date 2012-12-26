package model;

public class LaatsteKansStatus extends QuizStatus {
	private Quiz quiz;
	
	public LaatsteKansStatus(Quiz quiz){
		this.quiz = quiz;
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
		throw new IllegalStateException("De quiz is status \"laatste kans\" een quizdeelname toevoegen is niet toegestaan");				
	}

	@Override
	void verwijderQuizDeelname(QuizDeelname quizDeelname) {
		throw new IllegalStateException("De quiz is status \"laatste kans\" een quizdeelname verwijderen is niet toegestaan");				
	}
	
	@Override
	public String toString(){
		return "LaatsteKans";
	}
}
