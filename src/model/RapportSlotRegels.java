package model;

public class RapportSlotRegels extends QuizRapportDecorator {
	
	private QuizRapport quizRapport;
	
	public RapportSlotRegels(QuizRapport qr){
		this.quizRapport = qr;
	}
	
	@Override
	QuizDeelname getQuizDeelname() {
		return quizRapport.getQuizDeelname();
	}

	@Override
	public String getQuizRapport() {
		String basisRapport = quizRapport.getQuizRapport();
		String totaalRapport = "";
		String slotTekst = "\n\n";
		slotTekst += quizRapport.getQuizDeelname().getDeelnameScore() >= 7 ? "Gefeliciteerd!" : quizRapport.getQuizDeelname().getDeelnameScore() >= 5 ? "Geslaagd." : "Dit moet beter in de toekomst.";
		slotTekst += "\n(De gemiddelde score op de quiz was " + (int)quizRapport.getQuizDeelname().getGemiddeldeScore() + ")\n";
		totaalRapport = basisRapport + slotTekst;
		return totaalRapport;
	}

}
