package model;
import java.util.ArrayList;
import java.util.List;
import utils.DatumGregorian;

/**
 * 
 * @author Sven
 *
 */

public class Quiz implements Cloneable, Comparable<Quiz>
{	
	private String onderwerp;
	private ArrayList<Integer> leerjaren;
	private Leraar auteur;
	private DatumGregorian datumRegistratie;
	private Boolean isTest;
	private QuizStatus status;
	private ArrayList<QuizOpdracht> opdrachten;
	
	/**
	 * Geeft het onderwerp van de Quiz terug
	 * @return String
	 */
	public String getOnderwerp() {
		return onderwerp;
	}
	
	public void setOnderwerp(String onderwerp) throws IllegalArgumentException {
		if(onderwerp.equals(null)){
			throw new IllegalArgumentException("Onderwerp mag niet null zijn");
		}
		this.onderwerp = onderwerp;
	}
	
	/**
	 * Geeft een ArrayList van de leerjaren terug waarvoor de quiz bedoeld is
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getLeerjaren() {
		return leerjaren;
	}
	
	public void setLeerjaren(ArrayList<Integer> leerjaren) throws IllegalArgumentException {
		if(leerjaren == null){
			throw new IllegalArgumentException("Aantal leerjaren mag niet null zijn");
		}
		this.leerjaren = leerjaren;
	}

	/**
	 * Geeft de auteur terug die de quiz opgesteld heeft
	 * @return enum Leraar
	 */
	public Leraar getAuteur() {
		return auteur;
	}

	public void setAuteur(Leraar auteur) throws IllegalArgumentException {
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

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}
	
	/**
	 * Geeft de registratiedatum van de quiz terug
	 * @return DatumGregorian
	 * @throws Gooit NullPointerException als de datum null is 
	 */
	public DatumGregorian getDatumRegistratie() {
		return datumRegistratie;
	}

	public void setDatumRegistratie(DatumGregorian datumRegistratie) throws IllegalArgumentException {
		if(datumRegistratie == null){
			throw new IllegalArgumentException("Datum mag niet null zijn");
		}
		this.datumRegistratie = datumRegistratie;
	}
	
	/**
	 * geeft de status terug van de quiz
	 * @return QuizStatus
	 */
	public QuizStatus getStatus() {
		return status;
	}

	public void setStatus(QuizStatus status) throws IllegalArgumentException {
		if(status == null){
			throw new IllegalArgumentException("Datum mag niet null zijn");
		}
		this.status = status;
	}
	
	/**
	 * Geeft een lijst terug van de QuizOpdrachten
	 * @return List QuizOpdrachten
	 */
	public List<QuizOpdracht> getOpdrachten() {
		return opdrachten;
	}

	public void setOpdrachten(ArrayList<QuizOpdracht> opdrachten) {
		this.opdrachten = opdrachten;
	}


	public Quiz(String onderwerp, Leraar auteur, Boolean test, int... jaren)
	{
		setOnderwerp(onderwerp);
		setAuteur(auteur);
		setIsTest(test);
		this.leerjaren = new ArrayList<Integer>();
		for(int jaar : jaren){
			leerjaren.add(jaar);
		}
		this.opdrachten = new ArrayList<QuizOpdracht>();
		setStatus(QuizStatus.InConstructie);
		setDatumRegistratie(new DatumGregorian());
	}
	
	
	public void voegOpdrachtToe(QuizOpdracht opdracht) throws IllegalArgumentException
	{
		if(opdracht == null){
			throw new IllegalArgumentException("De datum mag niet null zijn");
		}
		opdrachten.add(opdracht);
	}
	
	public void verwijderOpdracht(QuizOpdracht opdracht)
	{
		if(opdracht == null){
			throw new IllegalArgumentException("De datum mag niet null zijn");
		}
		int index = this.opdrachten.indexOf(opdracht);
		if(index == -1){
			throw new IllegalArgumentException("Opdracht niet aanwezig in quiz");
		}
		else{
			this.opdrachten.remove(opdracht);
		}
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
		result = prime * result
				+ ((leerjaren == null) ? 0 : leerjaren.hashCode());
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result
				+ ((opdrachten == null) ? 0 : opdrachten.hashCode());
		result = prime
				* result
				+ ((datumRegistratie == null) ? 0 : datumRegistratie.hashCode());
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
		if (leerjaren == null) {
			if (other.leerjaren != null)
				return false;
		} else if (!leerjaren.equals(other.leerjaren))
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
		if (datumRegistratie == null) {
			if (other.datumRegistratie != null)
				return false;
		} else if (!datumRegistratie.equals(other.datumRegistratie))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public int compareTo(Quiz q) {
		if(q == null){
			throw new IllegalArgumentException("Quiz mag niet null zijn");
		}
		return q.onderwerp.compareTo(this.onderwerp);
	}
	
	@Override
	public Quiz clone(){
		int[] jaren = new int[(leerjaren.size()-1)];
		int i = 0;
		for(int jaar : leerjaren){
			jaren[i] = jaar;
		}
		Quiz clone = new Quiz(this.onderwerp, this.auteur, this.isTest, jaren);
		clone.setDatumRegistratie(datumRegistratie);
		clone.setStatus(this.status);
		clone.setOpdrachten(this.opdrachten);
		return clone;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
				

	}




}
