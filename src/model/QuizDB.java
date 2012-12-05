package model;

import java.sql.*;
import java.util.ArrayList;

import utils.Datum;

public class QuizDB {
	// 1 gewoon, 2 opsomming, 3 reproductie, 4 meerkeuze
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
	 * Geeft een ArrayList van opdrachten die aanwezig zijn in de databank
	 * @return ArrayList<Opdracht>
	 * @throws SQLException
	 */
	public ArrayList<Opdracht> getOpdrachten() throws SQLException{
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
		ResultSet resultSetOpdracht = null;
		ResultSet resultSetReproductie = null;
		ResultSet resultSetMeerkeuze = null;
		Statement statementOpdracht = null;
		Statement statementReproductie = null;
		PreparedStatement statementMeerkeuze = null;
		PreparedStatement statementAntwoordHints = null;
		try {
			statementOpdracht = connection.createStatement();
			statementReproductie = connection.createStatement();
			statementMeerkeuze = connection.prepareStatement("SELECT * FROM meerkeuze WHERE idOpdracht = ?");
			statementAntwoordHints = connection.prepareStatement("SELECT * FROM antwoordhints WHERE idOpdracht = ?");
			
			//alle informatie over de verschillende soorten opdrachten ophalen
			resultSetOpdracht = statementOpdracht.executeQuery("SELECT * FROM opdracht");
			resultSetReproductie = statementReproductie.executeQuery("SELECT * FROM reproductie");			
			
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
							opdracht.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
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
							opsomming.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
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
							reproductie.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							reproductie.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken reproductie: " + e.getMessage());
						}
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
							meerkeuze.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
							meerkeuze.setKey(key);
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken meerkeuze: " + e.getMessage());
						}
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
				statementOpdracht.close();
				statementReproductie.close();
				statementMeerkeuze.close();				
				connection.close();
			} catch (Exception e) {
				System.out.println("finally error: " + e.getMessage());
			}			
		}
		return opdrachten;
	}
	
	// gebruikt om maxAantalPogingen en MaxAntwoordTijd in te vullen bij alle soorten opdrachten
	private void setPogingenEnAntwoordtijd(Opdracht opdracht, int pogingen, int tijd){
		if(pogingen != 0){
			opdracht.setMaxAantalPogingen(pogingen);
		}
		if(tijd != 0){
			opdracht.setMaxAntwoordTijd(tijd);
		}
	}
	
	//Gebruikt om de antwoordhints in te vullen bij alle soorten opdrachten
	private ArrayList<String> setAntwoordHints(PreparedStatement antwoordHints, int id) throws SQLException{
		ArrayList<String> listHints = new ArrayList<String>();
		antwoordHints.setString(1, ((Integer)id).toString());
		ResultSet resultSetHints = antwoordHints.executeQuery();
		while(resultSetHints.next()){
			listHints.add(resultSetHints.getString("hint"));
		}
		return listHints;
	}
	
	
	public void voegOpdrachtToe(Opdracht opdracht) throws SQLException{
		//Preparedstatements om velden in te vullen
		PreparedStatement insertOpdracht = connection.prepareStatement("INSERT INTO opdracht " +
				"(idOpdracht, vraag, juisteAntwoord, maxAantalPogingen, maxAntwoordTijd, datumRegistratie, categorie, auteur, type)" +
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		PreparedStatement insertIsJuisteVolgorde = connection.prepareStatement("INSERT INTO opdracht" +
				"(isJuisteVolgorde" +
				"VALUES(?)"); 
		PreparedStatement insertMinAantalJuisteTrefwoorden = connection.prepareStatement("INSERT INTO opdracht" +
				"(minAantalJuisteTrefwoorden)" +
				"VALUES(?)");
		PreparedStatement insertReproductie = connection.prepareStatement("INSERT INTO reproductie " +
				"(idOpdracht, trefwoord)" +
				"VALUES(?, ?)");
		PreparedStatement insertMeerkeuze = connection.prepareStatement("INSERT INTO meerkeuze" +
				"(idOpdracht, keuze)" +
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
		//Inserten van niet-gemeenschappelijke velden
		if(!(opdracht instanceof Opsomming) || !(opdracht instanceof Reproductie) || !(opdracht instanceof Meerkeuze)){ //anders ziet hij alle opdrachten als een gewone opdracht
			type = 1;
		}
		if(opdracht instanceof Opsomming){
			type = 2;
			isJuisteVolgorde = ((Opsomming)opdracht).isInJuisteVolgorde();
			insertIsJuisteVolgorde.setBoolean(1, isJuisteVolgorde);
			insertIsJuisteVolgorde.executeUpdate();
		}
		else if(opdracht instanceof Reproductie){
			type = 3;
			minAantalJuisteTrefwoorden = ((Reproductie)opdracht).getMinAantalJuisteTrefwoorden();
			insertMinAantalJuisteTrefwoorden.setInt(1, minAantalJuisteTrefwoorden);
			insertMinAantalJuisteTrefwoorden.executeUpdate();
			
		}
		else if(opdracht instanceof Meerkeuze){
			type = 4;
			
		}
		insertOpdracht.setInt(9, type);
		insertOpdracht.executeUpdate();
		
		//opvullen overige tabellen moet nadien gebeuren, anders problemen met foreign key constraint
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Meerkeuze meerkeuze = new Meerkeuze("aaaa", "bbbbb",
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
			}
			
		} catch (SQLException e) {
			System.out.println("HIERO " + e.getMessage());
		}
		

	}

}
