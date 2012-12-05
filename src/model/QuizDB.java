package model;

import java.sql.*;
import java.util.ArrayList;

import utils.Datum;

public class QuizDB {
	// 1 gewoon, 2 opsomming, 3 reproductie, 4 meerkeuze
	private static final String URL = "jdbc:mysql://localhost/QuizDB";
	private static final String login = "root";
	private static final String paswoord = "javatest";
	Connection connection;
	
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
		Statement statementMeerkeuze = null;
		PreparedStatement statementAntwoordHints = null;
		try {
			statementOpdracht = connection.createStatement();
			statementReproductie = connection.createStatement();
			statementMeerkeuze = connection.createStatement();
			statementAntwoordHints = connection.prepareStatement("SELECT * FROM antwoordhints WHERE idOpdracht = ?");
			//alle informatie over de verschillende soorten opdrachten ophalen
			resultSetOpdracht = statementOpdracht.executeQuery("SELECT * FROM opdracht");
			resultSetReproductie = statementReproductie.executeQuery("SELECT * FROM reproductie");
			resultSetMeerkeuze = statementMeerkeuze.executeQuery("SELECT * FROM meerkeuze");
			
			
			//creëren opdrachten aan de hand van discriminator "type" en toevoegen aan arraylist
			while(resultSetOpdracht.next()){
				String vraag = resultSetOpdracht.getString("vraag");
				String antwoord = resultSetOpdracht.getString("juisteAntwoord");
				int maxAantalPogingen = resultSetOpdracht.getInt("maxAantalPogingen");
				int maxAntwoordTijd = resultSetOpdracht.getInt("maxAntwoordTijd");
				String datumRegistratie = resultSetOpdracht.getString("datumRegistratie");
				String categorie = resultSetOpdracht.getString("categorie");
				String auteur = resultSetOpdracht.getString("auteur");
				switch(resultSetOpdracht.getInt("type")){
					// gewone opdracht
					case 1: 
						Opdracht opdracht = null;
						try {
							opdracht = new Opdracht(vraag, antwoord, OpdrachtCategorie.valueOf(categorie), Leraar.valueOf(auteur), new Datum(datumRegistratie));
							setPogingenEnAntwoordtijd(opdracht, maxAantalPogingen, maxAntwoordTijd);
							opdracht.setAntwoordHints(setAntwoordHints(statementAntwoordHints, resultSetOpdracht.getInt("idOpdracht")));
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
						} catch (IllegalArgumentException e) {
							System.out.println("Probleem met aanmaken meerkeuze: " + e.getMessage());
						}
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
	
	
	public void voegOpdrachtToe(Opdracht opdracht){
		//TODO
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			QuizDB database = new QuizDB();
			ArrayList<Opdracht> opdrachten = database.getOpdrachten();
			for(Opdracht o : opdrachten){
				System.out.println(o);
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
