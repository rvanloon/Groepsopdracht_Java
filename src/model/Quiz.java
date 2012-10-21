package model;
import java.util.Arrays;
import java.util.List;
import utils.Datum;

/**
 * 
 * @author Sven
 *
 */

public class Quiz implements Cloneable, Comparable<Quiz>
{	
	private String onderwerp;
	private int[] leerjaren;
	private Leraar auteur;
	private Datum registratieDatum;
	private Boolean isTest;
	private QuizStatus status;
	private List<QuizOpdracht> opdrachten;
	
	/**
	 * Geeft het onderwerp van de Quiz terug
	 * @return String
	 */
	public String getOnderwerp() {
		if(onderwerp.isEmpty()){
			return "Onderwerp nog niet ingevuld";
		}
		else{
			return onderwerp;
		}
	}
	
	private void setOnderwerp(String onderwerp) {
		if(onderwerp.isEmpty() || onderwerp == null){
			throw new IllegalArgumentException("Onderwerp moet ingevuld zijn");
		}
		else {
			this.onderwerp = onderwerp;			
		}
	}
	
	/**
	 * Geeft een integer array terug van de leerjaren waarvoor de quiz gemaakt is
	 * @return integer array
	 */

	public int[] getLeerjaren() {
		if (leerjaren.length == 0) {
			return null;
		}
		else{			
			return leerjaren;
		}	
	}

	private void setLeerjaren(int[] leerjaren) throws IllegalArgumentException {
		if(leerjaren == null){
			throw new IllegalArgumentException("leerjaren niet ingevuld");
		}
		else if(leerjaren.length == 0){
			throw new IllegalArgumentException("Leerjaren niet ingevuld");
		}
		else {
			for(int i : leerjaren){
				if(i < 1 || i > 6){
					throw new IllegalArgumentException("Leerjaren tussen 1 en 6");
				}
			}
			this.leerjaren = leerjaren;
		}
	}
	
	/**
	 * Geeft de auteur terug die de quiz opgesteld heeft
	 * @return enum Leraar
	 */
	public Leraar getAuteur() {
		return auteur;
	}

	private void setAuteur(Leraar auteur) {
		if(auteur == null){
			throw new IllegalArgumentException("Auteur mag niet null zijn");
		}
		this.auteur = auteur;
	}
	
	/**
	 * Geeft true terug als een leerling maar één keer aan de quiz mag deelnemen. 
	 * Geeft false terug als een leerling meerdere keren aan een quiz mag deelnemen
	 * @return boolean
	 */
	public Boolean getIsTest() {
		return isTest;
	}

	private void setIsTest(Boolean isTest) {
		if(isTest == null){
			throw new IllegalArgumentException("Test mag niet null zijn");
		}
		this.isTest = isTest;
	}
	
	public Datum getRegistratieDatum() {
		return registratieDatum;
	}

	public void setRegistratieDatum(Datum registratieDatum) {
		this.registratieDatum = registratieDatum;
	}

	public QuizStatus getStatus() {
		return status;
	}

	private void setStatus(QuizStatus status) throws IllegalArgumentException {
		if(status == null){
			throw new IllegalArgumentException("Status mag niet null zijn");
		}
		this.status = status;
	}

	public String getOpdrachten() {
		//TODO geeft een lijst van opdrachten, QuizOpdracht moet iterable zijn voor foreach loop
		return "";
	}
	
	public Quiz(String onderwerp, Leraar auteur, Boolean test, int... jaren)
	{
		setOnderwerp(onderwerp);
		setAuteur(auteur);
		setIsTest(test);
		setLeerjaren(jaren);
		setStatus(QuizStatus.In_constructie);		
	}


	@Override
	public String toString() {		
		return this.onderwerp + ", " + this.auteur + ", " + this.status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + ((isTest == null) ? 0 : isTest.hashCode());
		result = prime * result + Arrays.hashCode(leerjaren);
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result
				+ ((opdrachten == null) ? 0 : opdrachten.hashCode());
		result = prime
				* result
				+ ((registratieDatum == null) ? 0 : registratieDatum.hashCode());
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
		if (auteur != other.auteur)
			return false;
		if (isTest == null) {
			if (other.isTest != null)
				return false;
		} else if (!isTest.equals(other.isTest))
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
		if (registratieDatum == null) {
			if (other.registratieDatum != null)
				return false;
		} else if (!registratieDatum.equals(other.registratieDatum))
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
				

	}




}
