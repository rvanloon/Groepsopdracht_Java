package model;

public class AfgeslotenStatus extends QuizStatus {
	private Quiz quiz;
	
	public AfgeslotenStatus(Quiz quiz){
		this.quiz = quiz;
	}
	
	@Override
	public String toString(){
		return "Afgesloten";
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
		throw new IllegalStateException("De quiz is afgesloten, een QuizOpdracht toevoegen is niet meer toegestaan");		
	}

	@Override
	void verwijderQuizOpdracht(QuizOpdracht opdracht) {
		throw new IllegalStateException("De quiz is afgesloten, een QuizOpdracht verwijderen is niet meer toegestaan");		
	}

	@Override
	void voegQuizDeelnameToe(QuizDeelname quizDeelname) {
		throw new IllegalStateException("De quiz is afgesloten, een QuizDeelname toevoegen is niet meer toegestaan");	
	}

	@Override
	void verwijderQuizDeelname(QuizDeelname quizDeelname) {
		throw new IllegalStateException("De quiz is afgesloten, een QuizDeelname verwijderen is niet meer toegestaan");	
		
	}
}
