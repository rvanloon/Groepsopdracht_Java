package model;

public class InConstructieStatus extends QuizStatus {
	
	private Quiz quiz;
	
	public InConstructieStatus(Quiz quiz){
		this.quiz = quiz;
	}	

	@Override
	boolean setOnderwerpToegestaan() {
		return true;
	}

	@Override
	boolean setLeerjarenToegestaan() {
		return true;
	}

	@Override
	boolean setAuteurToegestaan() {
		return true;
	}
	
	@Override
	boolean setIsTestToegestaan() {
		return true;		
	}

	@Override
	boolean setDatumRegistratieToegestaan() {
		return true;		
	}

	@Override
	boolean setOpdrachtenToegestaan() {
		return true;
		
	}

	@Override
	boolean setQuizDeelnamesToegestaan() {
		return true;		
	}

	@Override
	void voegQuizOpdrachtToe(QuizOpdracht opdracht) {
		if (opdracht == null) {
			throw new IllegalArgumentException(
					"De quizDeelname mag niet null zijn");
		}
		if (quiz.getOpdrachten().contains(opdracht)) {
			throw new IllegalArgumentException(
					"Deze opdracht is al toegevoegd.");
		}
		quiz.getOpdrachten().add(opdracht);		
	}

	@Override
	void verwijderQuizOpdracht(QuizOpdracht opdracht) {
		if (opdracht == null) {
			throw new IllegalArgumentException("De opdracht mag niet null zijn");
		}
		
		int index = quiz.getOpdrachten().indexOf(opdracht);
		if (index == -1) {
			throw new IllegalArgumentException("Opdracht niet aanwezig in quiz");
		} else {
			quiz.getOpdrachten().remove(opdracht);
		}
		
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
		return "InConstructie";
	}






}
