package controller;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.BeheerQuizApplicatie;

import persistenty.QuizapplicatieDAO;
import persistenty.SqlDao;
import persistenty.TxtDao;
import view.IO;
import view.Menu;

public class OpstartController {
	private Menu menu;
	private QuizapplicatieDAO quizapplicatieDAO;

	public OpstartController() {

		// de layout op nimbus zetten

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}

		// inlezen data, opvullen containers
		setQuizapplicatieDAO();

		menu = new Menu("Beheren van opdrachten (leraar)",
				"Beheren van quizzen/testen (leraar)",
				"Deelnemen aan quiz (leerling)", "Overzicht scores (leraar)",
				"Quiz rapport (deelnemer quiz)",
				"Instellingen van de quiz applicatie");
	}

	public void execute() {
		int keuze = menu.getMenuKeuze();

		switch (keuze) {
		case 2:
			@SuppressWarnings("unused")
			ToevoegenQuizController toevoegenQuizController = new ToevoegenQuizController(
					this);
			break;
		case 1:
		case 3:
		case 4:
		case 5:

			IO.toonStringMetVenster("Nog niet geïmplementeerd.");
			execute();
			break;
		case 6:
			BeheerApplicatieController beheerApplicatieController = new BeheerApplicatieController(
					this);
			break;

		case 7:
			// opslaan van de nieuwe objecten in tekstbestanden
			try {
				quizapplicatieDAO.Save();
			} catch (Exception e) {
				IO.toonStringMetVenster(e.getMessage());
			}
			break;
		default:
			if (keuze != menu.getStopWaarde()) {
				IO.toonStringMetVenster("Je hebt een verkeerde keuze gemaakt!!!");
				execute();
			}
		}
	}

	/**
	 * @return the quizapplicatieDAO
	 */
	public QuizapplicatieDAO getQuizapplicatieDAO() {
		return quizapplicatieDAO;
	}

	/**
	 * @param quizapplicatieDAO
	 *            the quizapplicatieDAO to set
	 */
	public void setQuizapplicatieDAO() {

		try {
			switch (BeheerQuizApplicatie.getDaoType()) {
			case "SqlDao":
				quizapplicatieDAO = SqlDao.getInstance();
				break;
			case "TxtDao":
				quizapplicatieDAO = TxtDao.getInstance();
				break;
			default:
				quizapplicatieDAO = TxtDao.getInstance();
				IO.toonStringMetVenster("Geen geldige opslagsetting, locale opslag gebruikt.");
				break;
			}

		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}
	}

	/**
	 * Zet een enum om in een array van strings.
	 * 
	 * @param values
	 * @return String[]
	 */
	public <T extends Enum<T>> String[] enumNameToStringArray(T[] values) {
		int i = 0;
		String[] result = new String[values.length];
		for (T value : values) {
			result[i++] = value.name();
		}
		return result;
	}

	public static void main(String[] args) {
		new OpstartController().execute();

	}

}
