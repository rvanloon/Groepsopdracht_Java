package model;

import java.sql.*;
import java.util.ArrayList;

import utils.Datum;

public class QuizDB {
	private static final String URL = "jdbc:mysql://localhost/QuizDB";
	private static final String login = "root";
	private static final String paswoord = "javatest";
	private Connection connection;
	
	/**
	 * default constructor die de verbinding opent met de database
	 * @throws SQLException
	 */
	public QuizDB() throws SQLException {		
		try {
			connection = DriverManager.getConnection(URL, login, paswoord);			
		} catch (SQLException e) {
			throw new SQLException("Error in constructor QuizDB: " + e.getMessage());
		}
	}
	
	/**
	 * Geeft een ArrayList van quizzen terug die aanwezig zijn in de databank
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Quiz> getQuizzen() throws SQLException{
		ArrayList<Quiz> quizzen = new ArrayList<Quiz>();
		ResultSet resultSetQuiz = null;
		ResultSet resultSetQuizOpdrachten = null;
		Statement statementQuiz = null;
		PreparedStatement statementQuizOpdrachten = null;		

		try {
			statementQuiz = connection.createStatement();
			resultSetQuiz = statementQuiz.executeQuery("SELECT * FROM quiz");
			statementQuizOpdrachten = connection.prepareStatement("SELECT * FROM quizopdracht WHERE idQuiz = ?");
			while(resultSetQuiz.next()){
				int idQuiz = resultSetQuiz.getInt("idQuiz");
				Quiz quiz = maakQuiz(idQuiz);
				//toevoegen quizopdrachten
				statementQuizOpdrachten.setInt(1, idQuiz);
				resultSetQuizOpdrachten = statementQuizOpdrachten.executeQuery();
				while(resultSetQuizOpdrachten.next()){
					int idOpdracht = resultSetQuizOpdrachten.getInt("idOpdracht");
					Opdracht opdracht = maakOpdracht(idOpdracht);
					QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, resultSetQuizOpdrachten.getInt("maxScore"));
				}
				quizzen.add(quiz);
			}			
		} catch (NumberFormatException e) {
			System.out.println("Fout bij aanmaken quiz: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Fout bij aanmaken quiz: " + e.getMessage());
		} finally{
			resultSetQuiz.close();
			statementQuiz.close();
			//connection.close();
		}
		
		return quizzen;		
	}
	
	/**
	 * Geeft een ArrayList van opdrachten die aanwezig zijn in de databank
	 * @return ArrayList<Opdracht>
	 * @throws SQLException
	 */
	public ArrayList<Opdracht> getOpdrachten() throws SQLException{
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
		ResultSet resultSetOpdracht = null;
		Statement statementOpdracht = null;
		ResultSet resultSetQuizOpdrachten = null;
		PreparedStatement statementQuizOpdrachten = null;	
		
		try {
			statementOpdracht = connection.createStatement();
			resultSetOpdracht = statementOpdracht.executeQuery("SELECT * FROM opdracht");
			statementQuizOpdrachten = connection.prepareStatement("SELECT * FROM quizopdracht WHERE idOpdracht = ?");
			
			//creëren opdracht met quizopdrachten en toevoegen aan array
			while(resultSetOpdracht.next()){
				int opdrachtId = resultSetOpdracht.getInt("idOpdracht");
				Opdracht opdracht = maakOpdracht(opdrachtId);
				//toevoegen quizOpdrachten
				statementQuizOpdrachten.setInt(1, opdrachtId);
				resultSetQuizOpdrachten = statementQuizOpdrachten.executeQuery();
				while(resultSetQuizOpdrachten.next()){
					int idQuiz = resultSetQuizOpdrachten.getInt("idQuiz");
					Quiz quiz = maakQuiz(idQuiz);
					QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, resultSetQuizOpdrachten.getInt("maxScore"));
				}
				
				opdrachten.add(opdracht);
			}
		} finally{			
			resultSetOpdracht.close();
			statementOpdracht.close();			
		}
		return opdrachten;
	}

	/**
	 * maakt een quiz aan aan de hand van een id uit de database, maar zonder quizopdrachten
	 * @param idQuiz
	 * @return
	 * @throws SQLException
	 */
	private Quiz maakQuiz(int idQuiz) throws SQLException{
		Quiz quiz = null;		
		ResultSet resultSetQuiz = null;		
		ResultSet resultSetLeerjaren = null;

		Statement statementQuiz = null;
		Statement statementLeerjaren = null;


		try {
			statementQuiz = connection.createStatement();
			resultSetQuiz = statementQuiz.executeQuery("SELECT * FROM quiz WHERE idQuiz = " + idQuiz);
			statementLeerjaren = connection.createStatement();
			resultSetLeerjaren = statementLeerjaren.executeQuery("SELECT * FROM leerjaren WHERE idQuiz = " + idQuiz);
			
			while(resultSetQuiz.next()){
				//aanmaken quiz
				quiz = new Quiz();
				quiz.setAuteur(Leraar.valueOf(resultSetQuiz.getString("auteur")));
				quiz.setDatumRegistratie(new Datum(resultSetQuiz.getString("datumRegistratie")));
				quiz.setIsTest(resultSetQuiz.getBoolean("isTest"));
				quiz.setOnderwerp(resultSetQuiz.getString("onderwerp"));
				quiz.setStatus(QuizStatus.valueOf(resultSetQuiz.getString("status")));
				//toevoegen leerjaren
				ArrayList<Integer> leerjaren = new ArrayList<Integer>();
				while(resultSetLeerjaren.next()){
					leerjaren.add(resultSetLeerjaren.getInt("leerjaar"));
				}
				quiz.setLeerjaren(leerjaren);
			}			
		} catch (NumberFormatException e) {
			System.out.println("Fout bij aanmaken quiz: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Fout bij aanmaken quiz: " + e.getMessage());
		} finally{
			resultSetQuiz.close();
			statementQuiz.close();
			resultSetLeerjaren.close();
			statementLeerjaren.close();
			//connection.close();
		}		
		
		return quiz;
		
	}
	
	/**
	 * Geeft een opdracht aan de hand van een id in de databank zonder de quizopdrachten
	 * @param idOpdracht
	 * @return
	 * @throws SQLException
	 */
	private Opdracht maakOpdracht(int idOpdracht) throws SQLException{
		Opdracht opdracht = null;
		ResultSet resultSetOpdracht = null;
		Statement statementOpdracht = null;
		ResultSet resultSetReproductie = null;
		ResultSet resultSetMeerkeuze = null;
		ResultSet resultSetAntwoordHints = null;
		Statement statementReproductie = null;
		Statement statementMeerkeuze = null;
		Statement statementAntwoordHints = null;
		
		try {
			statementOpdracht = connection.createStatement();
			resultSetOpdracht = statementOpdracht.executeQuery("SELECT * FROM opdracht WHERE idOpdracht = " + idOpdracht);
			statementReproductie = connection.createStatement();
			resultSetReproductie = statementReproductie.executeQuery("SELECT * FROM reproductie WHERE idOpdracht = " + idOpdracht);
			statementMeerkeuze = connection.createStatement();
			resultSetMeerkeuze = statementMeerkeuze.executeQuery("SELECT * FROM meerkeuze WHERE idOpdracht = " + idOpdracht);
			statementAntwoordHints = connection.createStatement();
			resultSetAntwoordHints = statementAntwoordHints.executeQuery("SELECT * FROM antwoordhints WHERE idOpdracht = " + idOpdracht);
			
			//creëren opdrachten aan de hand van discriminator "type" en toevoegen aan arraylist
			while(resultSetOpdracht.next()){
				String vraag = resultSetOpdracht.getString("vraag");
				String antwoord = resultSetOpdracht.getString("juisteAntwoord");
				int maxAantalPogingen = resultSetOpdracht.getInt("maxAantalPogingen");
				int maxAntwoordTijd = resultSetOpdracht.getInt("maxAntwoordTijd");
				String datumRegistratie = resultSetOpdracht.getString("datumRegistratie");
				String categorie = resultSetOpdracht.getString("categorie");
				String auteur = resultSetOpdracht.getString("auteur");
				int key = resultSetOpdracht.getInt("idOpdracht");
				
				switch(resultSetOpdracht.getInt("type")){
					// gewone opdracht
					case 1: 
						opdracht = null;
						try {
							opdracht = new Opdracht(vraag, antwoord, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie));
							setPogingenEnAntwoordtijd(opdracht, maxAantalPogingen, maxAntwoordTijd);
							opdracht.setAntwoordHints(setAntwoordHints(resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							opdracht.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken opdracht: " + e.getMessage());
						}
						break;
					//opsomming
					case 2:
						try {
							Boolean volgorde = resultSetOpdracht.getBoolean("isJuisteVolgorde");
							opdracht = new Opsomming(vraag, antwoord, volgorde, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie));
							setPogingenEnAntwoordtijd(opdracht, maxAantalPogingen, maxAntwoordTijd);
							opdracht.setAntwoordHints(setAntwoordHints(resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							opdracht.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken opsomming: " + e.getMessage());
						}
						break;
					//reproductie
					case 3:
						try {
							int minAantalTrefwoorden = resultSetOpdracht.getInt("minAantalJuisteTrefwoorden");
							opdracht = new Reproductie(vraag, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie), minAantalTrefwoorden);
							setPogingenEnAntwoordtijd(opdracht, maxAantalPogingen, maxAntwoordTijd);
							opdracht.setAntwoordHints(setAntwoordHints(resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							opdracht.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken reproductie: " + e.getMessage());
						}
						//Opvullen arraylist trefwoorden 
						while(resultSetReproductie.next()){
							((Reproductie)opdracht).VoegTrefwoordToe(resultSetReproductie.getString("trefwoord"));
						}
						break;
					//meerkeuze	
					case 4: 
						try {
							opdracht = new Meerkeuze(vraag, antwoord, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie));
							setPogingenEnAntwoordtijd(opdracht, maxAantalPogingen, maxAntwoordTijd);
							opdracht.setAntwoordHints(setAntwoordHints(resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							opdracht.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken meerkeuze: " + e.getMessage());
						}
						//opvullen ArrayList keuzes
						while(resultSetMeerkeuze.next()){
							((Meerkeuze)opdracht).voegKeuzeToe(resultSetMeerkeuze.getString("keuze"));
						}
						break;
					default:
						System.out.println("Verkeerd type meegegeven.");
				}
			}
		} finally{			
			try {
				resultSetOpdracht.close();
				resultSetReproductie.close();
				resultSetMeerkeuze.close(); 
				statementOpdracht.close();
				statementReproductie.close();
				statementMeerkeuze.close();
				statementAntwoordHints.close();
				//connection.close();
			} catch (Exception e) {
				System.out.println("finally error: " + e.getMessage());
			}			
		}
		return opdracht;
	}
	
	// getOpdracht(): Gebruikt om de antwoordhints in te vullen bij alle soorten opdrachten
	private ArrayList<String> setAntwoordHints(ResultSet resultSetAntwoordHints, int id) throws SQLException{
		ArrayList<String> listHints = new ArrayList<String>();
		try {
			while(resultSetAntwoordHints.next()){
				listHints.add(resultSetAntwoordHints.getString("hint"));
			}
		} finally{
			resultSetAntwoordHints.close();
		}
		return listHints;
	}
	

	
	public void voegQuizToe(Quiz quiz) throws SQLException{
		ArrayList<Quiz> quizzen = getQuizzen();
		String quizOnderwerp = quiz.getOnderwerp();
		for(Quiz q : quizzen){
			String onderwerpVergelijk = q.getOnderwerp();
			if(quizOnderwerp.equals(onderwerpVergelijk)){
				throw new IllegalArgumentException("Quiz is al toegevoegd");
			}
		}
		PreparedStatement statementVoegQuizToe = null;
		PreparedStatement statementVoegQuizOpdrachtToe = null;
		PreparedStatement statementVoegLeerjarenToe = null;
		try {
			int maxId = getMaxId("idQuiz", "quiz"); //ophalen van de hoogste id in de database
			int idQuiz = maxId + 1;
			statementVoegQuizToe = connection.prepareStatement("INSERT INTO quiz" +
					" (idQuiz, onderwerp, datumRegistratie, isTest, auteur, status)" +
					" VALUES(?, ?, ?, ?, ?, ?)");
			statementVoegQuizToe.setInt(1, idQuiz);
			statementVoegQuizToe.setString(2, quiz.getOnderwerp());
			statementVoegQuizToe.setString(3, quiz.getDatumRegistratie().getDatumInEuropeesFormaat());
			statementVoegQuizToe.setBoolean(4, quiz.getIsTest());
			statementVoegQuizToe.setString(5, quiz.getAuteur().toString());
			statementVoegQuizToe.setString(6, quiz.getStatus().toString());
			statementVoegQuizToe.executeUpdate();
			//toevoegen leerjaren
			statementVoegLeerjarenToe = connection.prepareStatement("INSERT INTO leerjaren" +
					" (idQuiz, leerjaar)" +
					" VALUES(?, ?)");
			for(int jaar : quiz.getLeerjaren()){
				statementVoegLeerjarenToe.setInt(1, idQuiz);
				statementVoegLeerjarenToe.setInt(2, jaar);
				statementVoegLeerjarenToe.executeUpdate();
			}
			statementVoegLeerjarenToe.setInt(1, idQuiz);
			
			//toevoegen quizopdrachten
			//TODO: opdracht moet bestaan in de databank, anders error
			statementVoegQuizOpdrachtToe = connection.prepareStatement("INSERT INTO quizopdracht" +
					" (idQuiz, idOpdracht, maxScore)" +
					" VALUES(?, ?, ?)");
			for(QuizOpdracht qo : quiz.getOpdrachten()){
				int idOpdracht = qo.getOpdracht().getKey(); 
				statementVoegQuizOpdrachtToe.setInt(1, idQuiz);
				statementVoegQuizOpdrachtToe.setInt(2, idOpdracht);
				statementVoegQuizOpdrachtToe.setInt(3, qo.getMaxScore());
				statementVoegQuizOpdrachtToe.executeUpdate();				
			}
		} finally {
			statementVoegQuizToe.close();
		}
	}
	
	//Gebruikt om de maximum id te bepalen van quizzen en opdrachten
	private int getMaxId(String id, String table ) throws SQLException{
		String statement = "SELECT MAX(" + id + ") AS " + id + " FROM " + table;
		int max = 0;
		Statement statementMaxID = connection.createStatement();
		ResultSet resultSetMaxId = statementMaxID.executeQuery(statement);
		while(resultSetMaxId.next()){
			max = resultSetMaxId.getInt(id);
		}
		return max;
	}
	
	// getopdracht(): gebruikt om maxAantalPogingen en MaxAntwoordTijd in te vullen bij alle soorten opdrachten
	private void setPogingenEnAntwoordtijd(Opdracht opdracht, int pogingen, int tijd){
		if(pogingen != 0){
			opdracht.setMaxAantalPogingen(pogingen);
		}
		if(tijd != 0){
			opdracht.setMaxAntwoordTijd(tijd);
		}
	}
	
	/**
	 * Toevoegen van een opdracht aan de database
	 * @param opdracht
	 * @throws SQLException
	 */
	public void voegOpdrachtToe(Opdracht opdracht) throws SQLException{
		ArrayList<Opdracht> opdrachten = getOpdrachten();
		for(Opdracht o : opdrachten){
			if(o.getKey() == opdracht.getKey()){
				throw new IllegalArgumentException("De opdracht is al toegevoegd");
			}
		}
		
		PreparedStatement insertOpdracht = null;
		PreparedStatement insertIsJuisteVolgorde = null;
		PreparedStatement insertMinAantalJuisteTrefwoorden = null;
		PreparedStatement insertReproductie = null;
		PreparedStatement insertMeerkeuze = null;
		PreparedStatement insertAntwoordHints = null;
		try {
			//Preparedstatements om velden in te vullen
			insertOpdracht = connection.prepareStatement("INSERT INTO opdracht " +
					"(idOpdracht, vraag, juisteAntwoord, maxAantalPogingen, maxAntwoordTijd, datumRegistratie, categorie, auteur, type)" +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			insertIsJuisteVolgorde = connection.prepareStatement("UPDATE opdracht" +
					" SET isJuisteVolgorde = ? " +
					"WHERE idOpdracht = ?"); 			
			insertMinAantalJuisteTrefwoorden = connection.prepareStatement("UPDATE opdracht" +
					" SET minAantalJuisteTrefwoorden = ? " +
					"WHERE idOpdracht = ?");
			insertReproductie = connection.prepareStatement("INSERT INTO reproductie " +
					"(idOpdracht, trefwoord)" +
					"VALUES(?, ?)");
			insertMeerkeuze = connection.prepareStatement("INSERT INTO meerkeuze" +
					"(idOpdracht, keuze)" +
					"VALUES(?, ?)");
			insertAntwoordHints = connection.prepareStatement("INSERT INTO antwoordhints" +
					"(idOpdracht, hint)" +
					"VALUES(?, ?)");
			//Invullen variabelen met velden meegeleverde opdracht
			int id = opdracht.getKey();
			String vraag = opdracht.getVraag();
			String juisteAntwoord = opdracht.getJuisteAntwoord();
			String categorie = opdracht.getCategorie().toString();
			String auteur = opdracht.getAuteur().toString();
			int maxAantalPogingen = opdracht.getMaxAantalPogingen();
			int maxAntwoordTijd = opdracht.getMaxAntwoordTijd();
			String datumRegistratie = opdracht.getDatumRegistratie().getDatumInEuropeesFormaat();
			int type = 0;
			boolean isJuisteVolgorde = false;
			int minAantalJuisteTrefwoorden = 0;
			//Inserten gemeenschappelijke velden van alle opdrachten
			insertOpdracht.setInt(1, id);
			insertOpdracht.setString(2,  vraag);
			insertOpdracht.setString(3, juisteAntwoord);
			insertOpdracht.setInt(4, maxAantalPogingen);
			insertOpdracht.setInt(5, maxAntwoordTijd);
			insertOpdracht.setString(6, datumRegistratie);
			insertOpdracht.setString(7, categorie);
			insertOpdracht.setString(8, auteur);
			//Invullen type
			if(!(opdracht instanceof Opsomming) && !(opdracht instanceof Reproductie) && !(opdracht instanceof Meerkeuze)){ //anders ziet hij alle opdrachten als een gewone opdracht
				type = 1;
			}
			if(opdracht instanceof Opsomming){
				type = 2;
			}
			else if(opdracht instanceof Reproductie){
				type = 3;				
			}
			else if(opdracht instanceof Meerkeuze){
				type = 4;				
			}
			//inserten type en execute algemene velden
			insertOpdracht.setInt(9, type);
			insertOpdracht.executeUpdate();
			
			//invullen specifieke velden voor verschillende opsomming en reproductie
			if(opdracht instanceof Opsomming){
				isJuisteVolgorde = ((Opsomming)opdracht).isInJuisteVolgorde();
				insertIsJuisteVolgorde.setBoolean(1, isJuisteVolgorde);
				insertIsJuisteVolgorde.setInt(2, opdracht.getKey());
				insertIsJuisteVolgorde.executeUpdate();
			}
			if(opdracht instanceof Reproductie){
				minAantalJuisteTrefwoorden = ((Reproductie)opdracht).getMinAantalJuisteTrefwoorden();
				insertMinAantalJuisteTrefwoorden.setInt(1, minAantalJuisteTrefwoorden);
				insertMinAantalJuisteTrefwoorden.setInt(2, opdracht.getKey());
				insertMinAantalJuisteTrefwoorden.executeUpdate();
			}
			
			//opvullen overige tabellen moet nadien gebeuren, anders problemen met foreign key constraint
			//invullen tabel antwoordhints
			for(String hint : opdracht.getAntwoordHints()){
				insertAntwoordHints.setInt(1, opdracht.getKey());
				insertAntwoordHints.setString(2, hint);
				insertAntwoordHints.executeUpdate();
			}
			//invullen tabel reproductie
			if(opdracht instanceof Reproductie){
				for(String trefwoord : ((Reproductie)opdracht).getTrefwoorden()){
					insertReproductie.setInt(1, id);
					insertReproductie.setString(2, trefwoord);
					insertReproductie.executeUpdate();
				}
			}
			//invullen tabel meerkeuze
			if (opdracht instanceof Meerkeuze){
				for(String keuze : ((Meerkeuze)opdracht).getKeuzes()){
					insertMeerkeuze.setInt(1, id);
					insertMeerkeuze.setString(2, keuze);
					insertMeerkeuze.executeUpdate();
				}
			}
		} finally {
			insertAntwoordHints.close();
			insertIsJuisteVolgorde.close();
			insertMeerkeuze.close();
			insertMinAantalJuisteTrefwoorden.close();
			insertOpdracht.close();
			insertReproductie.close();
			//connection.close();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/*
			Meerkeuze meerkeuze = new Meerkeuze("Bergen", "Oostnrijk",
					OpdrachtCategorie.NederlandseTaal, Leraar.Robrecht, new Datum());
			meerkeuze.addAntwoordHint("Hintje 1");
			meerkeuze.addAntwoordHint("hintje2");
			meerkeuze.voegKeuzeToe("keuze 1");
			meerkeuze.voegKeuzeToe("keuze 2");
			meerkeuze.voegKeuzeToe("keuze 3");
			meerkeuze.setKey(7);
			QuizDB database = new QuizDB();
			database.voegOpdrachtToe(meerkeuze);
			ArrayList<Opdracht> opdrachten = database.getOpdrachten();
			for(Opdracht o : opdrachten){
				System.out.println(o);
				System.out.println(o.getKey());
				for(String s : o.getAntwoordHints()){
					System.out.println(s);
				}
				if(o instanceof Reproductie){
					for(String s : ((Reproductie)o).getTrefwoorden()){
						System.out.println(s);
					}
				}
				if(o instanceof Meerkeuze){
					for(String s : ((Meerkeuze)o).getKeuzes()){
						System.out.println(s);
					}
				}
				System.out.println("*********************\n\n\n");
			}
			
			Reproductie reproductie = new Reproductie("Vraagje", OpdrachtCategorie.NederlandseTaal, Leraar.Robrecht, new Datum(), 4);
			reproductie.VoegTrefwoordToe("trefwoord 1");
			reproductie.VoegTrefwoordToe("trefwoord 2");
			reproductie.addAntwoordHint("dit is de enige hint");
			reproductie.setKey(8);
			QuizDB database = new QuizDB();
			database.voegOpdrachtToe(reproductie);
			ArrayList<Opdracht> opdrachten = database.getOpdrachten();
			for(Opdracht o : opdrachten){
				System.out.println(o);
				System.out.println(o.getKey());
				for(String s : o.getAntwoordHints()){
					System.out.println(s);
				}
				if(o instanceof Reproductie){
					for(String s : ((Reproductie)o).getTrefwoorden()){
						System.out.println(s);
					}
				}
				if(o instanceof Meerkeuze){
					for(String s : ((Meerkeuze)o).getKeuzes()){
						System.out.println(s);
					}
				}
				System.out.println("*********************\n\n\n");
			}
			
			Opdracht opdracht = new Opdracht("Waarom", "daarom",
					OpdrachtCategorie.NederlandseTaal, Leraar.Robrecht, new Datum());
			opdracht.addAntwoordHint("Slecht hint");
			opdracht.addAntwoordHint("tweede Slechte hint");
			opdracht.setKey(9);
			QuizDB database = new QuizDB();
			database.voegOpdrachtToe(opdracht);
			ArrayList<Opdracht> opdrachten = database.getOpdrachten();
			for(Opdracht o : opdrachten){
				System.out.println(o);
				System.out.println(o.getKey());
				for(String s : o.getAntwoordHints()){
					System.out.println(s);
				}
				if(o instanceof Reproductie){
					for(String s : ((Reproductie)o).getTrefwoorden()){
						System.out.println(s);
					}
				}
				if(o instanceof Meerkeuze){
					for(String s : ((Meerkeuze)o).getKeuzes()){
						System.out.println(s);
					}
				}
				System.out.println("*********************\n\n\n");
			}
			
			Opsomming opsomming = new Opsomming("xxxSom op!", "xxxomsomming1;xxxopsomming2;xxxopsomming3", true,
					OpdrachtCategorie.rekenen, Leraar.Robrecht, new Datum());
			opsomming.addAntwoordHint("xxxsom hint");
			opsomming.addAntwoordHint("xxxtweede som hint");
			opsomming.setKey(11);
			QuizDB database = new QuizDB();
			database.voegOpdrachtToe(opsomming);
			ArrayList<Opdracht> opdrachten = database.getOpdrachten();
			for(Opdracht o : opdrachten){
				System.out.println(o);
				System.out.println(o.getKey());
				for(String s : o.getAntwoordHints()){
					System.out.println(s);
				}
				if(o instanceof Reproductie){
					for(String s : ((Reproductie)o).getTrefwoorden()){
						System.out.println(s);
					}
				}
				if(o instanceof Meerkeuze){
					for(String s : ((Meerkeuze)o).getKeuzes()){
						System.out.println(s);
					}
				}
				if(o instanceof Opsomming){
					System.out.println(((Opsomming)o).getJuisteAntwoord());
				}
				System.out.println("*********************\n\n\n");
			}
			*/
			Quiz quiz = new Quiz("Aardrijkskunde", Leraar.Alain, true, 4, 4,
					5, 6);
			
			QuizDB db = new QuizDB();
			Opdracht opdracht = new Opdracht("Chelsea", "Engeland",
					OpdrachtCategorie.algemeneKennis, Leraar.Robrecht, new Datum());
			opdracht.setKey(10);
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, 10);
			db.voegQuizToe(quiz);
			db.connection.close();
			/*
			QuizDB database = new QuizDB();
			ArrayList<Quiz> quizzen = database.getQuizzen();
			for(Quiz q : quizzen){
				System.out.println("*********************************\n\n\n");
				System.out.println(q + "\n");
				for(int jaar : q.getLeerjaren()){
					System.out.println(jaar + "\n");
				}
				for(QuizOpdracht qo : q.getOpdrachten()){
					System.out.println(qo + "\n");
				}
			}
			ArrayList<Opdracht> opdrachten = database.getOpdrachten();
			System.out.println("----------------------------");
			System.out.println("----------------------------");
			System.out.println("----------------------------");
			for(Opdracht o : opdrachten){
				System.out.println(o);
				for(QuizOpdracht qo : o.getQuizOpdracten()){
					System.out.println(qo);
				}
			}
			database.connection.close();
			*/
		} catch (SQLException e) {
			System.out.println("HIERO " + e.getMessage());
		}
		

	}

}
