package model;

public abstract class QuizStatus {
	
	abstract boolean setOnderwerpToegestaan();
	abstract boolean setLeerjarenToegestaan();
	abstract boolean setAuteurToegestaan();
	abstract boolean setIsTestToegestaan();
	abstract boolean setDatumRegistratieToegestaan();
	abstract boolean setOpdrachtenToegestaan();
	abstract boolean setQuizDeelnamesToegestaan();
	abstract void voegQuizOpdrachtToe(QuizOpdracht opdracht);
	abstract void verwijderQuizOpdracht(QuizOpdracht opdracht);
	abstract void voegQuizDeelnameToe(QuizDeelname quizDeelname);
	abstract void verwijderQuizDeelname(QuizDeelname quizDeelname);
	
	public void setAfgewerkt(){
		throw new IllegalArgumentException("Statuswijziging niet toegestaan");
	}
	
	public void setOpengesteld(){
		throw new IllegalArgumentException("Statuswijziging niet toegestaan");
	}
	
	public void setLaatsteKans(){
		throw new IllegalArgumentException("Statuswijziging niet toegestaan");
	}
	
	public void setAfgesloten(){
		throw new IllegalArgumentException("Statuswijziging niet toegestaan");
	}
	
	//nodig voor QuizDB, QuizCatalogus en ToevoegenQuizcontroller
	/**
	 * Static function om een quiz een status mee te geven via een string
	 *  
	 * @param Quiz quiz, String status
	 */
	public static void setStatus(Quiz quiz, String status){
		if(status.equals("InConstructie")){
			quiz.setStatus(quiz.getInConstructie());
		}
		else if(status.equals("Afgewerkt")){
			quiz.setStatus(quiz.getAfgewerkt());
		}
		else if(status.equals("Opengesteld")){
			quiz.setStatus(quiz.getOpengesteld());
		}
		else if(status.equals("LaatsteKans")){
			quiz.setStatus(quiz.getLaatsteKans());
		}
		else if(status.equals("Afgesloten")){
			quiz.setStatus(quiz.getAfgesloten());
		}
		else{
			throw new IllegalArgumentException("ongekende status");
		}
	}
	
}
