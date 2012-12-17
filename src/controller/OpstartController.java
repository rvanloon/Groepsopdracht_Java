package controller;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import persistenty.QuizapplicatieDAO;
import persistenty.SqlDao;
import persistenty.TxtDao;
import view.IO;
import view.Menu;

public class OpstartController {
	private Menu menu;
	@SuppressWarnings("unused")
	private ToevoegenQuizController toevoegenQuizController;
	private QuizapplicatieDAO quizapplicatieDAO;

	public OpstartController() {

		//de layout op nimbus zetten
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		
		// inlezen tekstbestanden, opvullen containers
		try {
			quizapplicatieDAO = SqlDao.getInstance(); // TODO: bepalen of txtDao
														// of sqlDao gebruikt
														// moeten worden.
		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}

		menu = new Menu("Beheren van opdrachten (leraar)",
				"Beheren van quizzen/testen (leraar)",
				"Deelnemen aan quiz (leerling)", "Overzicht scores (leraar)",
				"Quiz rapport (deelnemer quiz)",
				"Instellingen van de quiz applicatie");
	}

	/**
	 * @return the quizapplicatieDAO
	 */
	public QuizapplicatieDAO getQuizapplicatieDAO() {
		return quizapplicatieDAO;
	}

	public void execute() {
		int keuze = menu.getMenuKeuze();

		switch (keuze) {
		case 2:
			toevoegenQuizController = new ToevoegenQuizController(this);
			break;
		case 1:
		case 3:
		case 4:
		case 5:
		case 6:
			IO.toonStringMetVenster("Nog niet geïmplementeerd.");
			execute();
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

	public static void main(String[] args) {
		new OpstartController().execute();

	}

}
