package persistenty;

import java.util.ArrayList;

import model.Opdracht;
import model.Quiz;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public interface QuizapplicatieDAO {

	public void Save() throws Exception;

	public ArrayList<Opdracht> getOpdrachten();

	public void voegOpdrachtToe(Opdracht opdracht);

	public ArrayList<Quiz> getQuizzen();

	public void voegQuizToe(Quiz quiz);

}
