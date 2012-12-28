package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class QuizScoreRegelsFactory {

	private static QuizScoreRegelsFactory instance;

	private QuizScoreRegelsFactory() {
	}

	public static QuizScoreRegelsFactory getInstance() {
		if (instance == null) {
			instance = new QuizScoreRegelsFactory();
		}
		return instance;
	}

	public QuizScore getQuizScore(QuizDeelname qd) throws IOException {
		
		String soortScoreNaam;
		try {
			soortScoreNaam = "model."
					+ BeheerQuizApplicatie.getQuizscoreStrategy();
		} catch (IOException e) {
			throw new IOException("Probleem met inlezen settings.");
		}
		
		QuizScore quizScore = null;
		try {
			quizScore = (QuizScore) Class.forName(soortScoreNaam)
					.getConstructor(QuizDeelname.class).newInstance(qd);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quizScore;
	}

}
