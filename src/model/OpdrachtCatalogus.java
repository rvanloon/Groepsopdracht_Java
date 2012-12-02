package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

import utils.Datum;
import utils.maanden;

/**
 * 
 * @author rvanloon
 * @version 1
 * 
 */
public class OpdrachtCatalogus extends FileContainer implements
		Iterable<Opdracht>, PersisteerbaarAlsTekst {

	// private ArrayList<Opdracht> opdrachten;
	private Hashtable<Integer, Opdracht> opdrachten;

	/**
	 * Default constructor.
	 */
	public OpdrachtCatalogus() {
		opdrachten = new Hashtable<Integer, Opdracht>();
	}

	/**
	 * Geeft een Hashtable terug met alle opdrachten.
	 * 
	 * @return Hashtable<int, Opdracht>
	 */
	public Hashtable<Integer, Opdracht> getOpdrachten() {
		return opdrachten;
	}

	private void setOpdrachten(Hashtable<Integer, Opdracht> opdrachten) {
		this.opdrachten = opdrachten;
	}

	/**
	 * Voeg een opdracht toe aan de catalogus. geeft een fout als de opdracht
	 * null is of reeds in de catalogus zit.
	 * 
	 * @param opdracht
	 *            Opdracht
	 */
	public void addOpdracht(Opdracht opdracht) throws IllegalArgumentException {
		if (opdracht == null) {
			throw new IllegalArgumentException("Opdracht is null.");
		}
		if (opdrachten.contains(opdracht)) {
			throw new IllegalArgumentException("Opdracht reeds in catalogus.");
		}

		int key = 0;

		try {
			key = Collections.max(opdrachten.keySet());
		} catch (Exception e) {
			key = 0;
		}

		key++;

		opdracht.setKey(key);

		opdrachten.put(key, opdracht);
	}

	/**
	 * Verwijder een opdracht uit de catalogus. Dit kan enkel als deze nog niet
	 * aan een quiz is gekoppeld.
	 * 
	 * @param opdracht
	 *            Opdracht die verwijderd moet worden
	 * @throws IllegalArgumentException
	 */
	public void verwijderOpdracht(Opdracht opdracht)
			throws IllegalArgumentException {
		if (!(opdrachten.contains(opdracht))) {
			throw new IllegalArgumentException(
					"Deze opdracht zit niet in deze catalogus.");
		}
		if (!(opdracht.getQuizOpdracten().isEmpty())) {
			throw new IllegalArgumentException(
					"Deze opdracht is aan een quiz gekoppeld.");
		}
		opdrachten.remove(opdracht.getKey());
	}

	/**
	 * Geeft een opdracht uit de catalogus met de juiste id.
	 * 
	 * @param id
	 * @return
	 */
	public Opdracht getOpdrachtById(int id) {
		if (opdrachten.containsKey(id)) {
			return opdrachten.get(id);
		} else {
			throw new IllegalArgumentException("Deze id bestaat niet");
		}
	}

	/**
	 * Maakt een nieuw opdrachtobject met de meegeleverde velden en voegt die
	 * toe aan de catalogus.
	 */
	@Override
	public void maakObjectVanLijn(String[] velden) {
		try {
			// Eerst alle velden naar het juiste type omzetten.
			String type = velden[0];
			int id = Integer.parseInt(velden[1]);
			String vraag = velden[2];
			String antwoord = velden[3];
			Leraar auteur = Leraar.valueOf(velden[4]);
			OpdrachtCategorie categorie = OpdrachtCategorie.valueOf(velden[5]);
			String[] datumString = velden[6].split(" ");
			Datum datum = new Datum(Integer.parseInt(datumString[0]),
					maanden.valueOf(datumString[1]),
					Integer.parseInt(datumString[2]));
			int maxAantalPogingen = Integer.parseInt(velden[7]);
			int maxAtwoordtijd = Integer.parseInt(velden[8]);
			String[] hints = velden[9].split("§");

			String[] keuzes = type.equals(Meerkeuze.class.getSimpleName()) ? velden[10]
					.split("§") : null;
			Boolean inJuisteVolgorde = type.equals(Opsomming.class
					.getSimpleName()) && velden[10].equals("true") ? true
					: false;
			String[] trefwoorden = type.equals(Reproductie.class
					.getSimpleName()) ? velden[10].split("§") : null;
			int minAantalTrefwworden = type.equals(Reproductie.class
					.getSimpleName()) ? Integer.parseInt(velden[11]) : 0;

			// Het juiste object aanmaken a.h.v. het type en de bijhorende
			// specifieke velden invullen.
			Opdracht opdracht;

			if (type.equals(Meerkeuze.class.getSimpleName())) {
				opdracht = new Meerkeuze(vraag, antwoord, categorie, auteur,
						datum);
				for (String keuze : keuzes) {
					if (!(keuze.equals("")))
						((Meerkeuze) opdracht).voegKeuzeToe(keuze);
				}

			} else if (type.equals(Opsomming.class.getSimpleName())) {
				opdracht = new Opsomming(vraag, antwoord, inJuisteVolgorde,
						categorie, auteur, datum);

			} else if (type.equals(Reproductie.class.getSimpleName())) {
				opdracht = new Reproductie(vraag, categorie, auteur, datum,
						minAantalTrefwworden);
				for (String trefwoord : trefwoorden) {
					if (!(trefwoord.equals("")))
						((Reproductie) opdracht).VoegTrefwoordToe(trefwoord);
				}

			} else if (type.equals(Opdracht.class.getSimpleName())) {
				opdracht = new Opdracht(vraag, antwoord, categorie, auteur,
						datum);

			} else {
				throw new Exception("type niet ondersteund");
			}

			// Nu de overige velden invullen;
			for (String hint : hints) {
				if (!(hint.equals("")))
					opdracht.addAntwoordHint(hint);
			}
			opdracht.setMaxAantalPogingen(maxAantalPogingen);
			opdracht.setMaxAntwoordTijd(maxAtwoordtijd);
			opdracht.setKey(id);

			opdrachten.put(id, opdracht);

		} catch (Exception e) {
			throw new IllegalArgumentException("de velden voldoen niet: \n"
					+ e.getMessage());
		}

	}

	/**
	 * geeft een string met alle waarden van de meegegeven opdracht om deze weg
	 * te schrijven.
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public String MaakLijnVanObject(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("o mag niet null zijn");
		}
		if (!(o instanceof Opdracht)) {
			throw new IllegalArgumentException(
					"Meegeleverde object moet een opdracht zijn");
		}

		Opdracht opdracht;
		String lijn = "";
		// Eerst type wegschrijven
		lijn += o.getClass().getSimpleName() + splitteken;

		// Dan alle algemene velden wegschrijven
		opdracht = (Opdracht) o;

		lijn += opdracht.getKey() + splitteken;
		lijn += opdracht.getVraag() + splitteken;
		lijn += opdracht.getJuisteAntwoord() + splitteken;
		lijn += opdracht.getAuteur() + splitteken;
		lijn += opdracht.getCategorie() + splitteken;
		lijn += opdracht.getDatumRegistratie() + splitteken;
		lijn += opdracht.getMaxAantalPogingen() + splitteken;
		lijn += opdracht.getMaxAntwoordTijd() + splitteken;
		String s = "";
		for (String antwoordhint : opdracht.getAntwoordHints()) {
			s += antwoordhint + "§";
		}
		lijn += s.length() < 1 ? splitteken : s.substring(0, s.length() - 1)
				+ splitteken;

		// Nu alle overervende elementen wegschrijven.
		if (opdracht instanceof Meerkeuze) {
			s = "";
			for (String keuze : ((Meerkeuze) opdracht).getKeuzes()) {
				s += keuze + "§";
			}
			lijn += s.length() < 1 ? splitteken : s
					.substring(0, s.length() - 1) + splitteken;
		}

		if (opdracht instanceof Opsomming) {
			lijn += ((Opsomming) opdracht).isInJuisteVolgorde() + splitteken;
		}

		if (opdracht instanceof Reproductie) {
			s = "";
			for (String trefwoord : ((Reproductie) opdracht).getTrefwoorden()) {
				s += trefwoord + "§";
			}
			lijn += s.length() < 1 ? splitteken : s
					.substring(0, s.length() - 1) + splitteken;
			lijn += ((Reproductie) opdracht).getMinAantalJuisteTrefwoorden()
					+ splitteken;
		}

		// nog een spatie toevoegen aan het einde van de lijn. Anders wordt het
		// laatste veld overgeslagen als dit leeg is.
		lijn += " ";

		return lijn;
	}

	/**
	 * Geeft de locatie van een textfile terug die gebruikt wordt als opslag.
	 */
	@Override
	public String getFile() {
		return "TextFiles\\Opdrachtcatalogus.txt";
	}

	/**
	 * Maakt een nieuw opdrachtobject met de meegeleverde string en voegt die
	 * toe aan de catalogus.
	 */
	@Override
	public void toevoegenLijn(String lijn) {
		try {
			String[] velden = lijn.split(splitteken);
			maakObjectVanLijn(velden);
		} catch (Exception e) {
			throw new IllegalArgumentException("Fout bij deze lijn: \n" + lijn);
		}
	}

	/**
	 * Schrijft de volledige catalogus weg in een textfile.
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public void schrijfCatalogusNaarFile() throws Exception {
		ArrayList<String> lijnen = new ArrayList<String>();

		for (Opdracht opdracht : opdrachten.values()) {
			lijnen.add(MaakLijnVanObject(opdracht));
		}

		try {
			schrijven(lijnen);
		} catch (Exception e) {
			throw new Exception("Probleem bij wegschrijven file.");
		}
	}

	@Override
	public Iterator<Opdracht> iterator() {
		return opdrachten.values().iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((opdrachten == null) ? 0 : opdrachten.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpdrachtCatalogus other = (OpdrachtCatalogus) obj;
		if (opdrachten == null) {
			if (other.opdrachten != null)
				return false;
		} else if (!opdrachten.equals(other.opdrachten))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		OpdrachtCatalogus catalogus = new OpdrachtCatalogus();
		catalogus.setOpdrachten(opdrachten);
		return catalogus;
	}

	public static void main(String[] args) {

		OpdrachtCatalogus cat = new OpdrachtCatalogus();
		try {
			cat.lezen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Opdracht o1 = new Opdracht("aaa", "bbb",
		// OpdrachtCategorie.algemeneKennis, Leraar.Alain, new Datum());
		//
		// Opdracht o2 = new Opdracht("cccc", "dddd",
		// OpdrachtCategorie.FranseTaal, Leraar.Sven, new Datum());
		// o2.addAntwoordHint("na de c");
		// o2.addAntwoordHint("voor de e");
		//
		// Meerkeuze o3 = new Meerkeuze("ooo", "xxx", OpdrachtCategorie.rekenen,
		// Leraar.Robrecht, new Datum());
		// o3.voegKeuzeToe("YYY");
		// o3.voegKeuzeToe("xxx");
		//
		// Reproductie o4 = new Reproductie("ppp", OpdrachtCategorie.FranseTaal,
		// Leraar.Alain, new Datum(), 4);
		// o4.VoegTrefwoordToe("jn");
		// o4.VoegTrefwoordToe("ok");
		// o4.VoegTrefwoordToe("sdse");
		//
		// Opsomming o5 = new Opsomming("lplp", "aaa;bbb;ccc", true,
		// OpdrachtCategorie.NederlandseTaal, Leraar.Sven, new Datum());
		//
		// // OpdrachtCatalogus cat = new OpdrachtCatalogus();
		// cat.addOpdracht(o1);
		// cat.addOpdracht(o2);
		// cat.addOpdracht(o3);
		// cat.addOpdracht(o4);
		// cat.addOpdracht(o5);

		for (Opdracht opdracht : cat) {
			opdracht.setMaxAntwoordTijd(opdracht.getMaxAntwoordTijd() + 1);
		}

		try {
			cat.schrijfCatalogusNaarFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
