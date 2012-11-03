package model;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class OpdrachtAntwoord implements Comparable<OpdrachtAntwoord> {

	private String laatsteAntwoord;
	private int aantalPogingen;
	private int antwoordTijd;
	private QuizOpdracht quizopdracht;
	private QuizDeelname quizdeelname;

	/**
	 * constructor
	 * 
	 * @param laatsteAntwoord
	 * @param aantalPogingen
	 * @param antwoordTijd
	 * @param quizopdracht
	 * @param quizdeelname
	 * 
	 * @throws IllegalArgumentException
	 */
	public OpdrachtAntwoord(String laatsteAntwoord, int aantalPogingen,
			int antwoordTijd, QuizOpdracht quizopdracht,
			QuizDeelname quizdeelname) throws IllegalArgumentException {
		setLaatsteAntwoord(laatsteAntwoord);
		setAantalPogingen(aantalPogingen);
		setAntwoordTijd(antwoordTijd);
		setQuizopdracht(quizopdracht);
		setQuizdeelname(quizdeelname);
	}

	/**
	 * @return the laatsteAntwoord
	 */
	public String getLaatsteAntwoord() {
		return laatsteAntwoord;
	}

	/**
	 * @param laatsteAntwoord
	 *            the laatsteAntwoord to set
	 * @throws IllegalArgumentException
	 */
	public void setLaatsteAntwoord(String laatsteAntwoord)
			throws IllegalArgumentException {
		if (laatsteAntwoord == null) {
			throw new IllegalArgumentException("laatsteAntwoord is null");
		}
		if (laatsteAntwoord.equals("")) {
			throw new IllegalArgumentException("laatsteAntwoord is leeg");
		}
		this.laatsteAntwoord = laatsteAntwoord;
	}

	/**
	 * @return the aantalPogingen
	 */
	public int getAantalPogingen() {
		return aantalPogingen;
	}

	/**
	 * @param aantalPogingen
	 *            the aantalPogingen to set
	 * @throws IllegalArgumentException
	 */
	public void setAantalPogingen(int aantalPogingen)
			throws IllegalArgumentException {
		if (aantalPogingen <= 0) {
			throw new IllegalArgumentException(
					"aantal pogingen moet minstens één zijn.");
		}
		this.aantalPogingen = aantalPogingen;
	}

	/**
	 * @return the antwoordTijd
	 */
	public int getAntwoordTijd() {
		return antwoordTijd;
	}

	/**
	 * @param antwoordTijd
	 *            the antwoordTijd to set
	 * @throws IllegalArgumentException
	 */
	public void setAntwoordTijd(int antwoordTijd)
			throws IllegalArgumentException {
		if (antwoordTijd <= 0) {
			throw new IllegalArgumentException(
					"antwoordTijd moet minstens één zijn.");
		}
		this.antwoordTijd = antwoordTijd;
	}

	/**
	 * @return the quizopdracht
	 */
	public QuizOpdracht getQuizopdracht() {
		return quizopdracht;
	}

	/**
	 * @param quizopdracht
	 *            the quizopdracht to set
	 * @throws IllegalArgumentException
	 */
	public void setQuizopdracht(QuizOpdracht quizopdracht)
			throws IllegalArgumentException {
		if (quizopdracht == null) {
			throw new IllegalArgumentException("quizeopdracht is null.");
		}
		this.quizopdracht = quizopdracht;
	}

	/**
	 * @return the quizdeelname
	 */
	public QuizDeelname getQuizdeelname() {
		return quizdeelname;
	}

	/**
	 * @param quizdeelname
	 *            the quizdeelname to set
	 * @throws IllegalArgumentException
	 */
	public void setQuizdeelname(QuizDeelname quizdeelname)
			throws IllegalArgumentException {
		if (quizdeelname == null) {
			throw new IllegalArgumentException("quizdeelname is null.");
		}
		this.quizdeelname = quizdeelname;
	}

	/**
	 * Geeft de score weer op dit antwoord. Fout of overtijd is 0. juist en
	 * eerste poging is maximum punten. juist en vervolgpoging is helft van de
	 * punten.
	 * 
	 * @return de score
	 */
	public double GetOpdrachtScore() {
		double score = (double) quizopdracht.getMaxScore();
		if (quizopdracht.getOpdracht().isJuisteAntwoord(laatsteAntwoord)) {
			if (!(quizopdracht.getOpdracht().getMaxAntwoordTijd() > 0 && antwoordTijd > quizopdracht
					.getOpdracht().getMaxAntwoordTijd())) {
				if (aantalPogingen > 1) {
					return score / 2;
				} else {
					return score;
				}
			}
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantalPogingen;
		result = prime * result + antwoordTijd;
		result = prime * result
				+ ((laatsteAntwoord == null) ? 0 : laatsteAntwoord.hashCode());
		result = prime * result
				+ ((quizdeelname == null) ? 0 : quizdeelname.hashCode());
		result = prime * result
				+ ((quizopdracht == null) ? 0 : quizopdracht.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpdrachtAntwoord other = (OpdrachtAntwoord) obj;
		if (aantalPogingen != other.aantalPogingen)
			return false;
		if (antwoordTijd != other.antwoordTijd)
			return false;
		if (laatsteAntwoord == null) {
			if (other.laatsteAntwoord != null)
				return false;
		} else if (!laatsteAntwoord.equals(other.laatsteAntwoord))
			return false;
		if (quizdeelname == null) {
			if (other.quizdeelname != null)
				return false;
		} else if (!quizdeelname.equals(other.quizdeelname))
			return false;
		if (quizopdracht == null) {
			if (other.quizopdracht != null)
				return false;
		} else if (!quizopdracht.equals(other.quizopdracht))
			return false;
		return true;
	}

	/**
	 * Geeft het laatste antwoord terug
	 */
	@Override
	public String toString() {
		return laatsteAntwoord;
	}

	/**
	 * gebruikt de score om te vergelijken.
	 * 
	 * @param o
	 * @return int
	 * @throws IllegalArgumentException
	 */
	@Override
	public int compareTo(OpdrachtAntwoord o) throws IllegalArgumentException {
		if (o == null) {
			throw new IllegalArgumentException("OpdrachtAntwoord is null.");
		}
		double verschil = GetOpdrachtScore() - o.GetOpdrachtScore();
		return verschil == 0 ? 0 : verschil > 0 ? 1 : -1;

	}

	public static void main(String[] args) {

	}

}
