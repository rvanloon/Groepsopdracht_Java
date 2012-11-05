package model;

import java.util.ArrayList;

import utils.Datum;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class QuizDeelname implements Comparable<QuizDeelname> {

	private Leerling leerling;
	private Leraar leraar;
	private Quiz quiz;
	private Datum datumDeelname;
	private ArrayList<OpdrachtAntwoord> opdrachtAntwoorden;

	/**
	 * Constructor. Indien deze gebruikt, mag er geen leraar meer ingevuld
	 * worden.
	 * 
	 * @param leerling
	 * @param quiz
	 * @param datumDeelname
	 */
	public QuizDeelname(Leerling leerling, Quiz quiz, Datum datumDeelname) {
		setLeerling(leerling);
		setQuiz(quiz);
		setDatumDeelname(datumDeelname);
		opdrachtAntwoorden = new ArrayList<OpdrachtAntwoord>();
	}

	/**
	 * Constructor. Indien deze gebruikt, mag er geen leerling meer ingevuld
	 * worden.
	 * 
	 * @param leraar
	 * @param quiz
	 * @param datumDeelname
	 */
	public QuizDeelname(Leraar leraar, Quiz quiz, Datum datumDeelname) {
		setLeraar(leraar);
		setQuiz(quiz);
		setDatumDeelname(datumDeelname);
		opdrachtAntwoorden = new ArrayList<OpdrachtAntwoord>();
	}

	/**
	 * @return the leerling
	 */
	public Leerling getLeerling() {
		return leerling;
	}

	/**
	 * Stel de leerling in. indien er een leraar ingevuld is, kan er geen
	 * leerling meer ingevuld worden
	 * 
	 * @param leerling
	 *            the leerling to set
	 * @throws IllegalArgumentException
	 */
	public void setLeerling(Leerling leerling) throws IllegalArgumentException {
		if (leerling == null) {
			throw new IllegalArgumentException("leerling is null");
		}
		if (leraar != null) {
			throw new IllegalArgumentException(
					"indien er een leraar ingevuld is, kan er geen leerling meer ingevuld worden");
		}
		this.leerling = leerling;
	}

	/**
	 * @return the leraar
	 */
	public Leraar getLeraar() {
		return leraar;
	}

	/**
	 * stel de leraar in. indien er een leerling ingevuld is, kan er geen leraar
	 * meer ingevuld worden
	 * 
	 * @param leraar
	 *            the leraar to set
	 * @throws IllegalArgumentException
	 */
	public void setLeraar(Leraar leraar) throws IllegalArgumentException {
		if (leraar == null) {
			throw new IllegalArgumentException("leraar is null");
		}
		if (leerling != null) {
			throw new IllegalArgumentException(
					"indien er een leerling ingevuld is, kan er geen leraar meer ingevuld worden");
		}
		this.leraar = leraar;
	}

	/**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz
	 *            the quiz to set
	 */
	public void setQuiz(Quiz quiz) throws IllegalArgumentException {
		if (quiz == null) {
			throw new IllegalArgumentException("quiz is null");
		}
		this.quiz = quiz;
	}

	/**
	 * @return the datumDeelname
	 */
	public Datum getDatumDeelname() {
		return datumDeelname;
	}

	/**
	 * @param datumDeelname
	 *            the datumDeelname to set
	 */
	public void setDatumDeelname(Datum datumDeelname)
			throws IllegalArgumentException {
		if (datumDeelname == null) {
			throw new IllegalArgumentException("datumDeelname is null");
		}
		this.datumDeelname = datumDeelname;
	}

	/**
	 * @return the opdrachtAntwoorden
	 */
	public ArrayList<OpdrachtAntwoord> getOpdrachtAntwoorden() {
		return opdrachtAntwoorden;
	}

	/**
	 * @param opdrachtAntwoorden
	 *            the opdrachtAntwoorden to set
	 */
	public void setOpdrachtAntwoorden(
			ArrayList<OpdrachtAntwoord> opdrachtAntwoorden)
			throws IllegalArgumentException {
		if (opdrachtAntwoorden == null) {
			throw new IllegalArgumentException("opdrachtAntwoorden is null");
		}
		this.opdrachtAntwoorden = opdrachtAntwoorden;
	}

	/**
	 * Geeft de sore van deze deelname. Wordt herleid naar 10, afgerond op 0
	 * cijfers.
	 * 
	 * @return int, de score
	 */
	public int getDeelnameScore() {
		int aantalAntwoorden = opdrachtAntwoorden.size();
		if (aantalAntwoorden == 0)
			return 0;
		int somMaxscore = 0;
		double somScore = 0;
		for (OpdrachtAntwoord antwoord : opdrachtAntwoorden) {
			somMaxscore += antwoord.getQuizopdracht().getMaxScore();
			somScore += antwoord.GetOpdrachtScore();
		}
		return (int) Math.round((somScore / somMaxscore) * 10);
	}

	/**
	 * Voegt een opdrachtAntwoord toe aan de lijst van OpdrachtAntwoorden
	 * 
	 * @param opdrachtAntwoord
	 *            OpdrachtAntwoord
	 * @throws IllegalArgumentException
	 *             Gooit een exception als het antwoord al aanwezig is of als
	 *             het antwoord null is
	 */
	protected void voegOpdrachtAntwoordToe(OpdrachtAntwoord opdrachtAntwoord)
			throws IllegalArgumentException {
		if (opdrachtAntwoord == null) {
			throw new IllegalArgumentException("Opdracht mag niet null zijn");
		}
		int index = opdrachtAntwoorden.indexOf(opdrachtAntwoord);
		if (index == -1) {
			opdrachtAntwoorden.add(opdrachtAntwoord);
		} else {
			throw new IllegalArgumentException("Opdracht bestaat al");
		}
	}

	/**
	 * Verwijderd een opdrachtAntwoord uit de lijst van OpdrachtAntwoorden
	 * 
	 * @param opdrachtAntwoord
	 * @throws IllegalArgumentException
	 *             Gooit een exception als het antwoord null is of niet aanwezig
	 *             is
	 */
	protected void verwijderOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord)
			throws IllegalArgumentException {
		if (opdrachtAntwoord == null) {
			throw new IllegalArgumentException("Opdracht mag niet null zijn");
		}
		int index = opdrachtAntwoorden.indexOf(opdrachtAntwoord);
		if (index == -1) {
			throw new IllegalArgumentException("Opdracht bestaat niet");
		} else {
			opdrachtAntwoorden.remove(opdrachtAntwoord);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datumDeelname == null) ? 0 : datumDeelname.hashCode());
		result = prime * result
				+ ((leerling == null) ? 0 : leerling.hashCode());
		result = prime * result + ((leraar == null) ? 0 : leraar.hashCode());
		result = prime
				* result
				+ ((opdrachtAntwoorden == null) ? 0 : opdrachtAntwoorden
						.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
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
		QuizDeelname other = (QuizDeelname) obj;
		if (datumDeelname == null) {
			if (other.datumDeelname != null)
				return false;
		} else if (!datumDeelname.equals(other.datumDeelname))
			return false;
		if (leerling == null) {
			if (other.leerling != null)
				return false;
		} else if (!leerling.equals(other.leerling))
			return false;
		if (leraar != other.leraar)
			return false;
		if (opdrachtAntwoorden == null) {
			if (other.opdrachtAntwoorden != null)
				return false;
		} else if (!opdrachtAntwoorden.equals(other.opdrachtAntwoorden))
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuizDeelname [leerling=" + leerling + ", leraar=" + leraar
				+ ", quiz=" + quiz + ", datumDeelname=" + datumDeelname + "]";
	}

	/**
	 * gebruikt de score om te vergelijken.
	 * 
	 * @param o
	 * @return int
	 * @throws IllegalArgumentException
	 */
	@Override
	public int compareTo(QuizDeelname o) {
		if (o == null) {
			throw new IllegalArgumentException("QuizDeelname is null.");
		}
		double verschil = getDeelnameScore() - o.getDeelnameScore();
		return verschil == 0 ? 0 : verschil > 0 ? 1 : -1;
	}

	public static void main(String[] args) {
		Datum d1 = new Datum(4, 11, 2012);
		Datum d2 = new Datum(4, 11, 2012);
		System.out.println(d1.equals(d2));
	}

}
