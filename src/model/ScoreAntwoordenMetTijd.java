package model;

public class ScoreAntwoordenMetTijd implements QuizScore {

	private QuizDeelname quizDeelname;

	public ScoreAntwoordenMetTijd(QuizDeelname deelname) {
		this.quizDeelname = deelname;
	}

	// maximum aantal punten bij goed antwoord binnen tijd, 0 bij antwoord dat
	// fout is of buiten tijd
	@Override
	public int getScore() {
		double maxScore = 0.0;
		double somScore = 0.0;
		double somMaxScore = 0.0;

		for (OpdrachtAntwoord oa : quizDeelname.getOpdrachtAntwoorden()) {
			maxScore = oa.getQuizopdracht().getMaxScore();
			somMaxScore += maxScore;
			if (oa.getQuizopdracht().getOpdracht()
					.isJuisteAntwoord(oa.getLaatsteAntwoord()) // antwoord moet
																// juist zijn
					&& (oa.getAntwoordTijd() <= oa.getQuizopdracht()
							.getOpdracht().getMaxAntwoordTijd() || // antwoord
																	// moet
																	// binnen
																	// tijd zijn
					oa.getQuizopdracht().getOpdracht().getMaxAntwoordTijd() == 0)) { // ofwel
																						// is
																						// er
																						// geen
																						// max
																						// antwoordtijd
				somScore += maxScore;
			}
		}
		return (int) Math.round((somScore / somMaxScore) * 10);
	}

}
