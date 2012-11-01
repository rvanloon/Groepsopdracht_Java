package model;

import java.util.ArrayList;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class Leerling implements Comparable<Leerling> {

	private String leerlingNaam;
	private int leerjaar;
	private ArrayList<QuizDeelname> quizdeelnames;

	/**
	 * Constructor
	 * 
	 * @param leerlingNaam
	 * @param leerjaar
	 * @throws IllegalArgumentException
	 */
	public Leerling(String leerlingNaam, int leerjaar)
			throws IllegalArgumentException {
		setLeerjaar(leerjaar);
		setLeerlingNaam(leerlingNaam);
		quizdeelnames = new ArrayList<QuizDeelname>();
	}

	/**
	 * Geeft de naam van de leerling.
	 * 
	 * @return the leerlingNaam
	 */
	public String getLeerlingNaam() {
		return leerlingNaam;
	}

	/**
	 * Stel de naam van de leerling in.
	 * 
	 * @param leerlingNaam
	 *            the leerlingNaam to set
	 * @throws IllegalArgumentException
	 * 
	 */
	public void setLeerlingNaam(String leerlingNaam)
			throws IllegalArgumentException {
		if (leerlingNaam == null) {
			throw new IllegalArgumentException("Leerlingnaam is null.");
		}
		if (leerlingNaam.equals("")) {
			throw new IllegalArgumentException("Leerlingnaam is blanco.");
		}
		this.leerlingNaam = leerlingNaam;
	}

	/**
	 * Geeft het leerjaar van de leerling
	 * 
	 * @return the leerjaar
	 */
	public int getLeerjaar() {
		return leerjaar;
	}

	/**
	 * Stel het leerjaar van de leerling in. een getal tussen 1 en 6.
	 * 
	 * @param leerjaar
	 *            the leerjaar to set
	 * @throws IllegalArgumentException
	 */
	public void setLeerjaar(int leerjaar) throws IllegalArgumentException {
		if (leerjaar < 1 || leerjaar > 6) {
			throw new IllegalArgumentException(
					"leerjaar moet tussen 1 en 6 zijn");
		}
		this.leerjaar = leerjaar;
	}

	/**
	 * Geeft een arraylist met de quizdeelnames van deze leerling.
	 * 
	 * @return the quizdeelnames arraylist
	 */
	public ArrayList<QuizDeelname> getQuizdeelnames() {
		return quizdeelnames;
	}

	/**
	 * @param quizdeelnames
	 *            the quizdeelnames to set
	 * @throws IllegalArgumentException
	 */
	public void setQuizdeelnames(ArrayList<QuizDeelname> quizdeelnames)
			throws IllegalArgumentException {
		if (quizdeelnames == null) {
			throw new IllegalArgumentException("quizdeelnames is null");
		}
		this.quizdeelnames = quizdeelnames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + leerjaar;
		result = prime * result
				+ ((leerlingNaam == null) ? 0 : leerlingNaam.hashCode());
		result = prime * result
				+ ((quizdeelnames == null) ? 0 : quizdeelnames.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leerling other = (Leerling) obj;
		if (leerjaar != other.leerjaar)
			return false;
		if (leerlingNaam == null) {
			if (other.leerlingNaam != null)
				return false;
		} else if (!leerlingNaam.equals(other.leerlingNaam))
			return false;
		if (quizdeelnames == null) {
			if (other.quizdeelnames != null)
				return false;
		} else if (!quizdeelnames.equals(other.quizdeelnames))
			return false;
		return true;
	}

	/**
	 * Geeft een kopie van deze leerling terug.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Leerling kopie = new Leerling(getLeerlingNaam(), getLeerjaar());
		kopie.setQuizdeelnames((ArrayList<QuizDeelname>) getQuizdeelnames()
				.clone());
		return kopie;
	}

	/**
	 * Geeft de naam van de leerling.
	 */
	@Override
	public String toString() {
		return getLeerlingNaam();
	}

	/**
	 * Vergelijkt de leerling op basis van zijn naam
	 * 
	 * @param o
	 *            Leerling
	 * @return int
	 */
	@Override
	public int compareTo(Leerling o) {
		if (o == null) {
			throw new IllegalArgumentException("leerling is null");
		}
		return leerlingNaam.compareTo(o.getLeerlingNaam());
	}

	public static void main(String[] args) {

	}

}
