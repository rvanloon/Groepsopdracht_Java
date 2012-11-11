package model;

/**
 * @author rvanloon
 * @version 1
 * 
 */
public interface Valideerbaar {

	boolean isValide(String antwoord);

	String getValideertekst();

}
