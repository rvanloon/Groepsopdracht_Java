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
	
	public QuizScore getQuizScore(QuizDeelname qd){
		//hoe bepalen welke implementatie van score?
		ScoreGoedeAntwoorden quizScore = new ScoreGoedeAntwoorden(qd);
		return quizScore;
	}

}
