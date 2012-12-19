package model;

public class ScoreGoedeAntwoorden implements QuizScore {

	private QuizDeelname quizDeelname;
	
	public ScoreGoedeAntwoorden(QuizDeelname quizDeelname){
		this.quizDeelname = quizDeelname;
	}
	
	@Override
	public int getScore() {
		int score = 0;
		
		for(OpdrachtAntwoord oa : quizDeelname.getOpdrachtAntwoorden()){
			if(oa.getQuizopdracht().getOpdracht().isJuisteAntwoord(oa.getLaatsteAntwoord())){
				score += oa.getQuizopdracht().getMaxScore();
			}
		}
		return score;
	}

}
