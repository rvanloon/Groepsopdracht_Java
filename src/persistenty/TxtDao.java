/**
 * 
 */
package persistenty;

import java.util.ArrayList;

import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Quiz;
import model.QuizCatalogus;

/**
 * @author rvanloon
 * @version 1
 * 
 */
public class TxtDao implements QuizapplicatieDAO {

	private static TxtDao txtDao;
	private OpdrachtCatalogus opdrachtCatalogus;
	private QuizCatalogus quizCatalogus;

	/**
	 * private constructor, Leest de catalogussen in.
	 * 
	 * @throws Exception
	 */
	private TxtDao() throws Exception {
		opdrachtCatalogus = new OpdrachtCatalogus();
		quizCatalogus = new QuizCatalogus();

		opdrachtCatalogus.lezen();
		quizCatalogus.lezen();
	}

	/**
	 * We willen dat er maar één versie van dit object bestaat.
	 * 
	 * @throws Exception
	 */
	public static QuizapplicatieDAO getInstance() throws Exception {
		if (txtDao == null)
			txtDao = new TxtDao();
		return txtDao;
	}

	/**
	 * Schrijft de catalogussen terug weg.
	 * 
	 * @throws Exception
	 */
	@Override
	public void Save() throws Exception {
		opdrachtCatalogus.schrijfCatalogusNaarFile();
		quizCatalogus.schrijfCatalogusNaarFile();
	}

	@Override
	public ArrayList<Opdracht> getOpdrachten() {
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>(
				opdrachtCatalogus.getOpdrachten().values());
		return opdrachten;
	}

	@Override
	public void voegOpdrachtToe(Opdracht opdracht) {
		opdrachtCatalogus.addOpdracht(opdracht);
	}

	@Override
	public ArrayList<Quiz> getQuizzen() {
		return quizCatalogus.getQuizzen();
	}

	@Override
	public void voegQuizToe(Quiz quiz) {
		quizCatalogus.voegQuizToe(quiz);
	}

}
