package model;

public class ScoreAntwoordenMetPogingenEnTijd implements QuizScore {

	private QuizDeelname quizDeelname;
	
	public ScoreAntwoordenMetPogingenEnTijd(QuizDeelname deelname){
		this.quizDeelname = deelname;
	}
	
	//maximum aantal punten bij goed antwoord binnen tijd en eerste poging, 0 bij antwoord dat fout is of buiten tijd, de helft van de punten
	//bij een antwoord dat juist is met meer dan één poging
	@Override
	public int getScore() {
		double maxScore = 0.0;
		double somScore = 0.0;
		double somMaxScore = 0.0;
		
		for(OpdrachtAntwoord oa : quizDeelname.getOpdrachtAntwoorden()){
			maxScore = oa.getQuizopdracht().getMaxScore(); 
			somMaxScore += maxScore; 
			if(oa.getQuizopdracht().getOpdracht().isJuisteAntwoord(oa.getLaatsteAntwoord()) // antwoord moet juist zijn
					&& (oa.getAntwoordTijd() <= oa.getQuizopdracht().getOpdracht().getMaxAntwoordTijd() || // antwoord moet binnen tijd zijn
					oa.getQuizopdracht().getOpdracht().getMaxAntwoordTijd() == 0)){ // of er is geen max antwoordtijd
				if(oa.getAantalPogingen() > 1){
					somScore += maxScore / 2;
				}
				else{
					somScore += maxScore;
				}
			}
		}
		return (int) Math.round((somScore / somMaxScore) * 10);
	}
	
	

}
