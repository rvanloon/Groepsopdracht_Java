package model;

import java.util.ArrayList;
import java.util.Iterator;

import utils.Datum;
import utils.maanden;

/**
 * 
 * @author sven
 * 
 */
public class QuizCatalogus extends FileContainer implements Iterable<Quiz>,
		Cloneable, PersisteerbaarAlsTekst {

	private ArrayList<Quiz> quizzen;

	/**
	 * Geeft een ArrayList terug met daarin de aanwezige quizzen
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Quiz> getQuizzen() {
		return quizzen;
	}

	/**
	 * Functie waarmee men een ArrayList van quizzen kan meegeven aan de
	 * catalogus
	 * 
	 * @param quizLijst
	 *            ArrayList
	 * @throws IllegalArgumentException
	 *             Gooit een exception als de lijst leeg of null is
	 */
	public void setQuizzen(ArrayList<Quiz> quizLijst)
			throws IllegalArgumentException {
		if (quizLijst == null || quizLijst.isEmpty()) {
			throw new IllegalArgumentException(
					"Quizlijst mag niet null of leeg zijn zijn");
		}
		this.quizzen = quizLijst;
	}

	/**
	 * Constructor om een nieuwe QuizCatalogus aan te maken
	 */
	public QuizCatalogus() {
		quizzen = new ArrayList<Quiz>();
	}

	/**
	 * Functie om een enkele quiz toe te voegen aan de catalogus
	 * 
	 * @param quiz
	 *            Quiz
	 * @throws IllegalArgumentException
	 *             gooit een exception als de meegegeven quiz null is of als er
	 *             al een quiz aanwezig is met hetzelfde onderwerp
	 */
	public void voegQuizToe(Quiz quiz) throws IllegalArgumentException {
		if (quiz == null) {
			throw new IllegalArgumentException("quiz mag niet null zijn");
		} else if (bestaatOnderwerp(quiz.getOnderwerp())) {
			throw new IllegalArgumentException("Onderwerp quiz bestaat al");
		}
		quiz.setDatumRegistratie(new Datum());
		quizzen.add(quiz);
	}

	/**
	 * Functie om een enkele quiz te verwijderen uit de catalogus
	 * 
	 * @param quiz
	 *            Quiz
	 * @throws IllegalArgumentException
	 *             Gooit een exception als de meegegeven quiz null is, als de
	 *             quiz niet aanwezig is of als de status van de quiz
	 *             verwijdering niet toelaat
	 */
	public void verwijderQuiz(Quiz quiz) throws IllegalArgumentException {
		int index = quizzen.indexOf(quiz);
		if (index == -1) {
			throw new IllegalArgumentException("Quiz is niet aanwezig in lijst");
		} else if ( !(quiz.getStatus() instanceof InConstructieStatus)
				&& !(quiz.getStatus() instanceof AfgewerktStatus)) {
			throw new IllegalArgumentException(
					"Quiz kan niet verwijderd worden wegens status.");
		} else {
			quizzen.remove(index);
		}
	}

	private boolean bestaatOnderwerp(String titel) {
		boolean test = false;
		for (Quiz q : quizzen) {
			if (q.getOnderwerp().compareToIgnoreCase(titel) == 0) {
				test = true;
			}
		}
		return test;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String output = "";
		for (Quiz quiz : quizzen) {
			output += quiz + "\n";
		}
		return output;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quizzen == null) ? 0 : quizzen.hashCode());
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
		QuizCatalogus other = (QuizCatalogus) obj;
		if (quizzen == null) {
			if (other.quizzen != null)
				return false;
		} else if (!quizzen.equals(other.quizzen))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public QuizCatalogus clone() {
		QuizCatalogus catalogusClone = new QuizCatalogus();
		catalogusClone.setQuizzen(this.getQuizzen());
		return catalogusClone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Quiz> iterator() {
		return quizzen.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.FileContainer#getFile()
	 */
	@Override
	public String getFile() {
		return "TextFiles\\QuizCatalogus.txt";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.FileContainer#toevoegenLijn(java.lang.String)
	 */
	@Override
	public void toevoegenLijn(String lijn) {
		String[] velden = lijn.split(splitteken);
		maakObjectVanLijn(velden);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.PersisteerbaarAlsTekst#maakObjectVanLijn(java.lang.String[])
	 */
	@Override
	public void maakObjectVanLijn(String[] velden) {
		if (velden == null) {
			throw new IllegalArgumentException("Velden mag niet null zijn");
		}
		try {
			// Maken quizobject
			String onderwerp = velden[0];
			Leraar auteur = Leraar.valueOf(velden[1]);
			Boolean test = Boolean.parseBoolean(velden[2]);
			String[] leerjarenString = velden[3].split(",");
			int[] leerjarenInt = new int[leerjarenString.length];
			int i = 0;
			for (String leerjaar : leerjarenString) {
				leerjarenInt[i] = Integer.parseInt(leerjaar);
				i++;
			}
			Quiz quiz = new Quiz(onderwerp, auteur, test, leerjarenInt);
			String[] datumString = velden[4].split(" ");
			quiz.setDatumRegistratie(new Datum(
					Integer.parseInt(datumString[0]), maanden
							.valueOf(datumString[1]), Integer
							.parseInt(datumString[2])));
			String status = velden[5];
			QuizStatus.setStatus(quiz, status);
			quizzen.add(quiz);
			// maken quizopdrachten
			String[] qoString = velden[6].split(";");
			OpdrachtCatalogus opdrachtenCatalogus = new OpdrachtCatalogus();
			opdrachtenCatalogus.lezen();
			for (String quizOpdrachtString : qoString) {
				String[] qoSplitString = quizOpdrachtString.split(",");
				int key = Integer.parseInt(qoSplitString[0]);
				int score = Integer.parseInt(qoSplitString[1]);
				Opdracht opdracht = opdrachtenCatalogus.getOpdrachtById(key);
				QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, score);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Probleem met het aanmaken van de quiz" + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.PersisteerbaarAlsTekst#MaakLijnVanObject(java.lang.Object)
	 */
	@Override
	public String MaakLijnVanObject(Object o) throws IllegalArgumentException {
		if (o == null) {
			throw new IllegalArgumentException("Quiz mag niet null zijn");
		}
		if (!(o instanceof Quiz)) {
			throw new IllegalArgumentException(
					"Meegegeven object moet een quiz zijn");
		}
		Quiz quiz = (Quiz) o;
		String quizString = "";
		quizString += quiz.getOnderwerp() + splitteken;
		quizString += quiz.getAuteur() + splitteken;
		quizString += quiz.getIsTest() + splitteken;
		String leerjarenString = "";
		for (int i : quiz.getLeerjaren()) {
			leerjarenString += i + ",";
		}
		quizString += leerjarenString
				.substring(0, leerjarenString.length() - 1) + splitteken;
		quizString += quiz.getDatumRegistratie() + splitteken;
		quizString += quiz.getStatus().toString() + splitteken;
		String qoString = "";
		for (QuizOpdracht qo : quiz.getOpdrachten()) {
			qoString += qo.getOpdracht().getKey() + "," + qo.getMaxScore()
					+ ";";
		}
		quizString += qoString.substring(0, qoString.length() - 1) + splitteken;
		return quizString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.FileContainer#schrijfCatalogusNaarFile()
	 */
	@Override
	public void schrijfCatalogusNaarFile() throws Exception {
		ArrayList<String> lijnen = new ArrayList<String>();
		for (Quiz quiz : quizzen) {
			lijnen.add(MaakLijnVanObject(quiz));
		}
		try {
			schrijven(lijnen);
		} catch (Exception e) {
			throw new Exception("Probleem opgetreden met schrijven van file");
		}
	}

	public static void main(String[] args) {

		QuizCatalogus cq = new QuizCatalogus();

		try {
			cq.lezen();
		} catch (Exception e) {
			System.out.println("Fout: " + e.getMessage());
		}

		System.out.println(cq);
		System.out.println(cq.getQuizzen().get(0).getOpdrachten().get(0)
				.getMaxScore());
		System.out.println(cq.getQuizzen().get(0).getOpdrachten().get(0)
				.getOpdracht().getVraag());
		/*
		 * OpdrachtCatalogus oc = new OpdrachtCatalogus(); try { oc.lezen(); }
		 * catch (Exception e) { System.out.println(); }
		 */
	}
}
