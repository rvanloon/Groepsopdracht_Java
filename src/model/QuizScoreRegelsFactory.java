package model;

public class QuizScoreRegelsFactory {
	
	private static QuizScoreRegelsFactory instance;
	
	private QuizScoreRegelsFactory(){
	}
	
	public static QuizScoreRegelsFactory getInstance(){
		if(instance == null){
			instance = new QuizScoreRegelsFactory();
		}
		return instance;
	}
	
	public QuizScore getQuizScore(QuizDeelname qd, SoortenScores scoreSoort){
		QuizScore quizScore = null;
		if(scoreSoort == SoortenScores.EnkelAntwoorden){
			quizScore = new ScoreGoedeAntwoorden(qd);
		}
		else if(scoreSoort == SoortenScores.AntwoordenMetTijd){
			quizScore = new ScoreAntwoordenMetTijd(qd);
		}
		else if(scoreSoort == SoortenScores.AntwoordenMetTijdEnPogingen){
			quizScore = new ScoreAntwoordenMetPogingenEnTijd(qd);
		}
		else{
			throw new IllegalArgumentException("Verkeerde SoortenScore");
		}
		return quizScore;
	}

}
