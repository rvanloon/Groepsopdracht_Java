package persistenty;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Opdracht;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizDB;

public class SqlDao implements QuizapplicatieDAO {
	
	private static SqlDao sqlDao;
	private QuizDB database;
	
	private SqlDao() throws SQLException{
		database = new QuizDB();
	}
	
	public static SqlDao getInstance() throws SQLException{
		if(sqlDao == null){
			sqlDao = new SqlDao();
		}
		return sqlDao;
	}

	@Override
	public void Save() {
		// TODO Auto-generated method stub
		//eigenlijk niet nodig, wordt meteen weggeschreven naar databank
	}

	//TODO vang de sql expection verlopig hier op
	@Override
	public ArrayList<Opdracht> getOpdrachten() {
		ArrayList<Opdracht> opdrachten = null;
		try {
			opdrachten = database.getOpdrachten();
		} catch (SQLException e) {
			System.out.println("Probleem bij het ophalen van de opdrachten uit de databank: " + e.getMessage());
		}
		return opdrachten;
	}

	@Override
	public void voegOpdrachtToe(Opdracht opdracht) {
		try {
			database.voegOpdrachtToe(opdracht);
		} catch (SQLException e) {
			System.out.println("Probleem bij het toevoegen van een opdracht in de databank: " + e.getMessage());
		}

	}

	@Override
	public ArrayList<Quiz> getQuizzen() {
		ArrayList<Quiz> quizzen = null;
		try {
			quizzen = database.getQuizzen();
		} catch (SQLException e) {
			System.out.println("Probleem met het ophalen van de quizzen uit de databank: " + e.getMessage());
		}
		return quizzen;
	}

	@Override
	public void voegQuizToe(Quiz quiz) {
		try {
			database.voegQuizToe(quiz);
		} catch (SQLException e) {
			System.out.println("Probleem met toevoegen van de quiz aan de databank: " + e.getMessage());
		}

	}

}
