package model;

public class RapportKopRegels extends QuizRapportDecorator {
	
	private QuizRapport quizRapport;
	
	public RapportKopRegels(QuizRapport qr){
		this.quizRapport = qr;
	}	

	@Override
	QuizDeelname getQuizDeelname() {
		return quizRapport.getQuizDeelname();
	}

	@Override
	public String getQuizRapport() {
		String basisRapport = quizRapport.getQuizRapport();
		String kopTekst = "";
		kopTekst += "DatumDeelname: " + quizRapport.getQuizDeelname().getDatumDeelname();
		kopTekst += "\tAuteur quiz: " + quizRapport.getQuizDeelname().getAuteur() + "\n\n";
		String totaalRapport = kopTekst + basisRapport;
		return totaalRapport;
	}
}
