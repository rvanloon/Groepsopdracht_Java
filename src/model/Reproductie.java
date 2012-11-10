package model;

import java.util.ArrayList;

import utils.Datum;

public class Reproductie extends Opdracht {

	private ArrayList<String> trefwoorden;
	private int minAantalJuisteTrefwoorden;

	public Reproductie(String vraag, String juisteAntwoord,
			OpdrachtCategorie categorie, Leraar auteur, Datum datumRegistratie) {
		super(vraag, juisteAntwoord, categorie, auteur, datumRegistratie);
		// TODO Auto-generated constructor stub
	}

	private void setTrefwoorden(ArrayList<String> trefwoorden) {
		if (trefwoorden == null) {
			throw new IllegalArgumentException("trefwoorden mag niet null zijn");
		}
		this.trefwoorden = trefwoorden;
	}

	private ArrayList<String> getTrefwoorden() {
		return this.trefwoorden;
	}

	public void setMaxAantalPogingen(int maxAantalPogingen) {
		if (maxAantalPogingen <= 0) {
			throw new IllegalArgumentException(
					"max. aantal pogingen moet minstens één zijn.");
		}
		this.maxAantalPogingen = maxAantalPogingen;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
