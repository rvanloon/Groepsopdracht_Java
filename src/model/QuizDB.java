package model;

import java.sql.*;
import java.util.ArrayList;

import utils.Datum;

public class QuizDB {
	private static final String URL = "jdbc:mysql://localhost/QuizDB";
	private static final String login = "root";
	private static final String paswoord = "javatest";
	private Connection connection;
	
	//TODO: wanneer connection sluiten?
	
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
		//TODO: quizOpdrachten
		ArrayList<Quiz> quizzen = new ArrayList<Quiz>();
		ResultSet resultSetQuiz = null;
		ResultSet resultSetLeerjaren = null;
		Statement statementQuiz = null;
		PreparedStatement statementLeerjaren = null;

		try {
			statementQuiz = connection.createStatement();
			resultSetQuiz = statementQuiz.executeQuery("SELECT * FROM quiz");
			while(resultSetQuiz.next()){
				//aanmaken quiz
				Quiz quiz = new Quiz();
				quiz.setAuteur(Leraar.valueOf(resultSetQuiz.getString("auteur")));
				quiz.setDatumRegistratie(new Datum(resultSetQuiz.getString("datumRegistratie")));
				quiz.setIsTest(resultSetQuiz.getBoolean("isTest"));
				quiz.setOnderwerp(resultSetQuiz.getString("onderwerp"));
				quiz.setStatus(QuizStatus.valueOf(resultSetQuiz.getString("status")));
				//toevoegen leerjaren
				ArrayList<Integer> leerjaren = new ArrayList<Integer>();
				statementLeerjaren = connection.prepareStatement("SELECT * FROM leerjaren WHERE idQuiz = ?");
				statementLeerjaren.setInt(1, resultSetQuiz.getInt("idQuiz"));
				resultSetLeerjaren = statementLeerjaren.executeQuery();
				while(resultSetLeerjaren.next()){
					leerjaren.add(resultSetLeerjaren.getInt("leerjaar"));
				}
				quiz.setLeerjaren(leerjaren);
				//toevoegen quiz aan ArrayList
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
	

	
	public void voegQuizToe(Quiz quiz) throws SQLException{
		PreparedStatement statementVoegQuizToe = null;
		try {
			int maxId = getMaxId("idQuiz", "quiz");
			statementVoegQuizToe = connection.prepareStatement("INSERT INTO quiz" +
					" (idQuiz, onderwerp, datumRegistratie, isTest, auteur, status)" +
					" VALUES(?, ?, ?, ?, ?, ?)");
			statementVoegQuizToe.setInt(1, (maxId + 1));
			statementVoegQuizToe.setString(2, quiz.getOnderwerp());
			statementVoegQuizToe.setString(3, quiz.getDatumRegistratie().getDatumInEuropeesFormaat());
			statementVoegQuizToe.setBoolean(4, quiz.getIsTest());
			statementVoegQuizToe.setString(5, quiz.getAuteur().toString());
			statementVoegQuizToe.setString(6, quiz.getStatus().toString());
			statementVoegQuizToe.executeUpdate();
			//TODO QuizOprachten
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
	
	/**
	 * Geeft een ArrayList van opdrachten die aanwezig zijn in de databank
	 * @return ArrayList<Opdracht>
	 * @throws SQLException
	 */
	public ArrayList<Opdracht> getOpdrachten() throws SQLException{
		//TODO: quizopdrachten
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
		ResultSet resultSetOpdracht = null;
		ResultSet resultSetReproductie = null;
		ResultSet resultSetMeerkeuze = null;
		ResultSet resultSetAntwoordHints = null;
		Statement statementOpdracht = null;
		PreparedStatement statementReproductie = null;
		PreparedStatement statementMeerkeuze = null;
		PreparedStatement statementAntwoordHints = null;
		
		try {
			statementOpdracht = connection.createStatement();
			resultSetOpdracht = statementOpdracht.executeQuery("SELECT * FROM opdracht");	
			statementReproductie = connection.prepareStatement("SELECT * FROM reproductie WHERE idOpdracht = ?");
			statementMeerkeuze = connection.prepareStatement("SELECT * FROM meerkeuze WHERE idOpdracht = ?");
			statementAntwoordHints = connection.prepareStatement("SELECT * FROM antwoordhints WHERE idOpdracht = ?");	
			
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
						Opdracht opdracht = null;
						try {
							opdracht = new Opdracht(vraag, antwoord, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie));
							setPogingenEnAntwoordtijd(opdracht, maxAantalPogingen, maxAntwoordTijd);
							opdracht.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							opdracht.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken opdracht: " + e.getMessage());
						}
						opdrachten.add(opdracht);
						break;
					//opsomming
					case 2:
						Opsomming opsomming = null;
						try {
							Boolean volgorde = resultSetOpdracht.getBoolean("isJuisteVolgorde");
							opsomming = new Opsomming(vraag, antwoord, volgorde, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie));
							setPogingenEnAntwoordtijd(opsomming, maxAantalPogingen, maxAntwoordTijd);
							opsomming.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							opsomming.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken opsomming: " + e.getMessage());
						}
						opdrachten.add(opsomming);
						break;
					//reproductie
					case 3:
						Reproductie reproductie = null;
						try {
							int minAantalTrefwoorden = resultSetOpdracht.getInt("minAantalJuisteTrefwoorden");
							reproductie = new Reproductie(vraag, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie), minAantalTrefwoorden);
							setPogingenEnAntwoordtijd(reproductie, maxAantalPogingen, maxAntwoordTijd);
							reproductie.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							reproductie.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken reproductie: " + e.getMessage());
						}
						//Opvullen arraylist trefwoorden
						statementReproductie.setInt(1, key);
						resultSetReproductie = statementReproductie.executeQuery(); 
						while(resultSetReproductie.next()){
							reproductie.VoegTrefwoordToe(resultSetReproductie.getString("trefwoord"));
						}
						opdrachten.add(reproductie);
						break;
					//meerkeuze	
					case 4: 
						Meerkeuze meerkeuze = null;
						try {
							meerkeuze = new Meerkeuze(vraag, antwoord, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie));
							setPogingenEnAntwoordtijd(meerkeuze, maxAantalPogingen, maxAntwoordTijd);
							meerkeuze.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							meerkeuze.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken meerkeuze: " + e.getMessage());
						}
						//opvullen ArrayList keuzes
						statementMeerkeuze.setInt(1, key);
						resultSetMeerkeuze = statementMeerkeuze.executeQuery();
						while(resultSetMeerkeuze.next()){
							meerkeuze.voegKeuzeToe(resultSetMeerkeuze.getString("keuze"));
						}
						opdrachten.add(meerkeuze);
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
				//resultSetAntwoordHints.close();
				statementOpdracht.close();
				statementReproductie.close();
				statementMeerkeuze.close();
				statementAntwoordHints.close();
				//connection.close();
			} catch (Exception e) {
				System.out.println("finally error: " + e.getMessage());
			}			
		}
		return opdrachten;
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
	
	// getOpdracht(): Gebruikt om de antwoordhints in te vullen bij alle soorten opdrachten
	private ArrayList<String> setAntwoordHints(PreparedStatement statementAntwoordHints, ResultSet resultSetAntwoordHints, int id) throws SQLException{
		ArrayList<String> listHints = new ArrayList<String>();
		statementAntwoordHints.setString(1, ((Integer)id).toString());
		resultSetAntwoordHints = statementAntwoordHints.executeQuery();
		while(resultSetAntwoordHints.next()){
			listHints.add(resultSetAntwoordHints.getString("hint"));
		}
		return listHints;
	}
	
	/**
	 * Toevoegen van een opdracht aan de database
	 * @param opdracht
	 * @throws SQLException
	 */
	public void voegOpdrachtToe(Opdracht opdracht) throws SQLException{
		//TODO: oplossing voor de key, quizopdrachten
		
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
			db.voegQuizToe(quiz);
			db.connection.close();
			/*
			ArrayList<Quiz> quizzen = database.getQuizzen();
			for(Quiz q : quizzen){
				System.out.println(q + "\n");
				for(int jaar : q.getLeerjaren()){
					System.out.println(jaar + "\n");
				}
			}
			ArrayList<Opdracht> opdrachten = database.getOpdrachten();
			for(Opdracht o : opdrachten){
				System.out.println(o);
			}
			*/
			
		} catch (SQLException e) {
			System.out.println("HIERO " + e.getMessage());
		}
		

	}

}
