package persistenty;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Opdracht;
import model.Quiz;
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
			System.out.println("Probleem bij het toevogegen van een opdracht in de databank: " + e.getMessage());
		}

	}

	@Override
	public ArrayList<Quiz> getQuizzen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void voegQuizToe(Quiz quiz) {
		// TODO Auto-generated method stub

	}

}
