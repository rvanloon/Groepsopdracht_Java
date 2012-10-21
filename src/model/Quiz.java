package model;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Sven
 *
 */

public class Quiz implements Cloneable, Comparable<Quiz>
{	
	private String onderwerp;
	private String[] leerjaren;
	private Leraar auteur;
	private Boolean isTest;
	private Boolean isUniekeDeelname;
	private QuizStatus status;
	private List<QuizOpdracht> opdrachten;

	public String getOnderwerp() {
		return onderwerp;
	}

	private void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}

	public String[] getLeerjaren() {
		return leerjaren;
	}

	private void setLeerjaren(String[] leerjaren) {
		this.leerjaren = leerjaren;
	}
	
	public Leraar getAuteur() {
		return auteur;
	}

	private void setAuteur(Leraar auteur) {
		this.auteur = auteur;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	private void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public Boolean getIsUniekeDeelname() {
		return isUniekeDeelname;
	}

	private void setIsUniekeDeelname(Boolean isUniekeDeelname) {
		this.isUniekeDeelname = isUniekeDeelname;
	}

	public QuizStatus getStatus() {
		return status;
	}

	private void setStatus(QuizStatus status) {
		this.status = status;
	}

	public List<QuizOpdracht> getOpdrachten() {
		return opdrachten;
	}

	private void setOpdrachten(List<QuizOpdracht> opdrachten) {
		this.opdrachten = opdrachten;
	}
	
	public Quiz(String onderwerp, Leraar auteur, Boolean test, String... jaren){
		this.onderwerp = onderwerp;
		this.auteur = auteur;
		this.isTest = test;
		int i = 0;
		leerjaren = new String[jaren.length];
		for(String s : leerjaren){
			jaren[i]=s;
		}
		this.status = QuizStatus.In_constructie;
		
	}


	@Override
	public String toString() {		
		return this.onderwerp + ", " + this.auteur + ", " + this.status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isTest == null) ? 0 : isTest.hashCode());
		result = prime
				* result
				+ ((isUniekeDeelname == null) ? 0 : isUniekeDeelname.hashCode());
		result = prime * result + Arrays.hashCode(leerjaren);
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result
				+ ((opdrachten == null) ? 0 : opdrachten.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Quiz other = (Quiz) obj;
		if (isTest == null) {
			if (other.isTest != null)
				return false;
		} else if (!isTest.equals(other.isTest))
			return false;
		if (isUniekeDeelname == null) {
			if (other.isUniekeDeelname != null)
				return false;
		} else if (!isUniekeDeelname.equals(other.isUniekeDeelname))
			return false;
		if (!Arrays.equals(leerjaren, other.leerjaren))
			return false;
		if (onderwerp == null) {
			if (other.onderwerp != null)
				return false;
		} else if (!onderwerp.equals(other.onderwerp))
			return false;
		if (opdrachten == null) {
			if (other.opdrachten != null)
				return false;
		} else if (!opdrachten.equals(other.opdrachten))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public int compareTo(Quiz o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Quiz clone(){
		// TODO
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
