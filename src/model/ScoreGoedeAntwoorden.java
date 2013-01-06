package model;

public class ScoreGoedeAntwoorden implements QuizScore {

	private QuizDeelname quizDeelname;

	public ScoreGoedeAntwoorden(QuizDeelname quizDeelname) {
		this.quizDeelname = quizDeelname;
	}

	// Maximum score bij goed antwoord, nul bij fout antwoord
	@Override
	public int getScore() {
		double maxScore;
		double somScore = 0;
		double somMaxScore = 0;

		for (OpdrachtAntwoord oa : quizDeelname.getOpdrachtAntwoorden()) {
			maxScore = oa.getQuizopdracht().getMaxScore();
			somMaxScore += maxScore;
			if (oa.getQuizopdracht().getOpdracht()
					.isJuisteAntwoord(oa.getLaatsteAntwoord())) {
				somScore += maxScore;
			}
		}
		return (int) Math.round((somScore / somMaxScore) * 10);
	}

}
