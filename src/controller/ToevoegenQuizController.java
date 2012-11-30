/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.DefaultListModel;

import model.Leraar;
import model.Meerkeuze;
import model.Opdracht;
import model.OpdrachtCategorie;
import model.Opsomming;
import model.Quiz;
import model.QuizOpdracht;
import model.QuizStatus;
import model.Reproductie;
import persistenty.QuizapplicatieDAO;
import utils.Datum;
import view.IO;
import view.ToevoegenQuizView;

/**
 * @author rvanloon
 * @version 1
 * 
 */
public class ToevoegenQuizController {

	private OpstartController opstartcontroller;
	private QuizapplicatieDAO quizapplicatieDAO;
	private ToevoegenQuizView view;
	private Quiz quiz;

	/**
	 * @param OpstartController
	 */
	public ToevoegenQuizController(OpstartController opstartcontroller) {
		try {
			quiz = new Quiz();
			this.opstartcontroller = opstartcontroller;
			quizapplicatieDAO = opstartcontroller.getQuizapplicatieDAO();
			view = new ToevoegenQuizView(this);
			view.setVisible(true);
		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}
	}

	public String[] getLeerjaren() {
		String[] leerjaren = { "1", "2", "3", "4", "5", "6" };
		return leerjaren;
	}

	public String[] getLeeraars() {
		return enumNameToStringArray(Leraar.values());
	}

	public String[] getQuizStatussen() {
		return enumNameToStringArray(QuizStatus.values());
	}

	public String[] getOpdrachtCategorieen() {
		String[] terug = enumNameToStringArray(OpdrachtCategorie.values());
		terug = Arrays.copyOf(terug, terug.length + 1);
		Arrays.sort(terug, new Comparator<String>() {
			public int compare(String o1, String o2) {
				if (o1 == null)
					return -1;
				return o1.compareTo(o2);
			};
		});
		return terug;

	}

	public String[] getOpdrachttypes() {
		String[] opdrachtTypes = { null, Opdracht.class.getSimpleName(),
				Meerkeuze.class.getSimpleName(),
				Opsomming.class.getSimpleName(),
				Reproductie.class.getSimpleName() };
		return opdrachtTypes;
	}

	/**
	 * Geeft een lijst met opdrachten terug. Leest de filters uit de view en
	 * gebruikt deze. Filtert ook de opdrachten die aan de quiz zijn toegevoegd.
	 * 
	 * @return
	 */
	public ArrayList<Opdracht> getOpdrachten() {
		ArrayList<Opdracht> opdrachten = quizapplicatieDAO.getOpdrachten();
		ArrayList<Opdracht> teVerwijderenOpdrachten = new ArrayList<Opdracht>();

		try {
			if (view != null) { // View is de eerste keer dat dit opgeroepen
								// wordt is nog null.
				if (view.GetOpdrachtfilterCategorie() != null) {
					String s = (String) view.GetOpdrachtfilterCategorie();
					OpdrachtCategorie cat = OpdrachtCategorie.valueOf(s);
					for (Opdracht opdracht : opdrachten) {
						if (opdracht.getCategorie() != cat) {
							teVerwijderenOpdrachten.add(opdracht);
						}
					}
				}
				if (view.GetOpdrachtfilterType() != null) {
					String s = (String) view.GetOpdrachtfilterType();
					for (Opdracht opdracht : opdrachten) {
						if (!(opdracht.getClass().getSimpleName().equals(s))) {
							teVerwijderenOpdrachten.add(opdracht);
						}
					}
				}
			}
		} catch (Exception e) {
			IO.toonStringMetVenster("Fout bij toepassen filters.\n"
					+ e.getMessage());
		}
		for (Opdracht opdracht : teVerwijderenOpdrachten) {
			opdrachten.remove(opdracht);
		}

		// nu de opdrachte die reeds aan de quiz gekoppeld zijn uit de lijst
		// halen.
		teVerwijderenOpdrachten = getOpdrachtenVanQuiz();
		for (Opdracht opdracht : teVerwijderenOpdrachten) {
			opdrachten.remove(opdracht);
		}
		return opdrachten;
	}

	/**
	 * Geeft een lijst terug van alle opdrachten die reeds aan de quiz gekoppeld
	 * zijn.
	 * 
	 * @return
	 */
	public ArrayList<Opdracht> getOpdrachtenVanQuiz() {
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
		ArrayList<QuizOpdracht> quizOpdrachten = quiz.getOpdrachten();
		for (QuizOpdracht quizOpdracht : quizOpdrachten) {
			opdrachten.add(quizOpdracht.getOpdracht());
		}
		return opdrachten;
	}

	public void herlaadOpdrachten() {
		ArrayList<Opdracht> opdrachten = getOpdrachten();
		DefaultListModel<Opdracht> model = new DefaultListModel<Opdracht>();
		for (Opdracht opdracht : opdrachten) {
			model.addElement(opdracht);
		}
		view.getList_Opdrachten().setModel(model);
	}

	public void herlaadQuizOpdrachten() {
		ArrayList<QuizOpdracht> quizOpdrachten = quiz.getOpdrachten();
		DefaultListModel<QuizOpdracht> model = new DefaultListModel<QuizOpdracht>();
		for (QuizOpdracht quizOpdracht : quizOpdrachten) {
			model.addElement(quizOpdracht);
		}
		view.getList_QuizOpdrachten().setModel(model);
		view.setTotaleScore(String.valueOf(quiz.getTotaleMaximumScore()));
	}

	public void voegOpdrachtToeAanQuiz() {
		if (view.getList_Opdrachten().getSelectedValue() == null) {
			IO.toonStringMetVenster("Gelieve eerst een opdracht te selecteren.");
			return;
		}
		Opdracht opdracht = (Opdracht) view.getList_Opdrachten()
				.getSelectedValue();

		int score;
		try {
			score = Integer.parseInt(view.getMaxScore());
			QuizOpdracht.koppelOpdrachtAanQuiz(quiz, opdracht, score);
			herlaadQuizOpdrachten();
			herlaadOpdrachten();
		} catch (NumberFormatException e) {
			IO.toonStringMetVenster("Max. score niet geldig");
		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}

	}

	public void verwijderOpdrachtVanQuiz() {
		if (view.getList_QuizOpdrachten().getSelectedValue() == null) {
			IO.toonStringMetVenster("Gelieve eerst een opdracht te selecteren.");
			return;
		}
		QuizOpdracht qo = (QuizOpdracht) view.getList_QuizOpdrachten()
				.getSelectedValue();
		qo.ontKoppelOpdrachtVanQuiz();
		herlaadOpdrachten();
		herlaadQuizOpdrachten();
	}

	/**
	 * Vult de velden van de quiz in en voegt deze toe aan de container. Sluit
	 * dan deze frame af.
	 */
	public void voegQuizToeAanCatalogus() {
		try {
			quiz.setOnderwerp(view.getOnderwerp());
			quiz.setAuteur(Leraar.valueOf(view.getAuteur()));
			quiz.setDatumRegistratie(new Datum());
			quiz.setIsTest(view.isTest());
			quiz.setStatus(QuizStatus.valueOf(view.getStatus()));

			ArrayList<Integer> list = new ArrayList<Integer>();
			int van = view.getLeerjaarVan();
			int tot = view.getLeerjaarTot();
			for (int i = van; i <= tot; i++) {
				list.add(i);
			}
			quiz.setLeerjaren(list);

			quizapplicatieDAO.voegQuizToe(quiz);
			toonMenu();
		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}
	}

	public void toonMenu() {
		view.setVisible(false);
		opstartcontroller.execute();
	}

	/**
	 * Zet een enum om in een array van strings.
	 * 
	 * @param values
	 * @return String[]
	 */
	public static <T extends Enum<T>> String[] enumNameToStringArray(T[] values) {
		int i = 0;
		String[] result = new String[values.length];
		for (T value : values) {
			result[i++] = value.name();
		}
		return result;
	}

}
