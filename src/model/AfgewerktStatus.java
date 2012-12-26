package model;

public class AfgewerktStatus extends QuizStatus {
	private Quiz quiz;
	
	public AfgewerktStatus(Quiz quiz){
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
		throw new IllegalStateException("De quiz is afgewerkt, er mogen geen QuizOpdrachten worden toegevoegd");
		
	}

	@Override
	void verwijderQuizOpdracht(QuizOpdracht opdracht) {
		throw new IllegalStateException("De quiz is afgewerkt, er mogen geen QuizOpdrachten worden verwijderd");
		
	}

	@Override
	void voegQuizDeelnameToe(QuizDeelname quizDeelname) {
		throw new IllegalStateException("De quiz is afgewerkt, er mogen geen QuizDeelnames worden toegevoegd");
		
	}

	@Override
	void verwijderQuizDeelname(QuizDeelname quizDeelname) {
		throw new IllegalStateException("De quiz is afgewerkt, er mogen geen QuizDeelnames verwijderd worden");
		
	}
	
	@Override
	public String toString(){
		return "Afgewerkt";
	}

}
