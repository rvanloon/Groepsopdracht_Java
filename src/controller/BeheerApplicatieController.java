package controller;

import java.io.IOException;

import model.BeheerQuizApplicatie;
import model.SoortenScores;
import view.BeheerApplicatieView;
import view.IO;

public class BeheerApplicatieController {

	OpstartController opstartController;
	BeheerApplicatieView view;

	/**
	 * @param opstartController
	 */
	public BeheerApplicatieController(OpstartController opstartController) {
		this.opstartController = opstartController;
		view = new BeheerApplicatieView(this);
		view.setVisible(true);
		view.setSoortScores();
		setDaoInView();
	}

	public String[] getSoortenScores() {
		return opstartController.enumNameToStringArray(SoortenScores.values());
	}

	public String getSoortSores() {
		String result = "";
		try {
			result = BeheerQuizApplicatie.getQuizscoreStrategy();
		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}
		return result;
	}

	public void setSoortScores() {
		String oud = getSoortSores();
		String nieuw = view.getSoortScores();
		if (!(oud.equals(nieuw))) {
			try {
				BeheerQuizApplicatie.setQuizscoreStrategy(nieuw);
			} catch (IOException e) {
				IO.toonStringMetVenster(e.getMessage());
			}
		}
	}

	public void setDaoInView() {
		String daoType;
		try {
			daoType = BeheerQuizApplicatie.getDaoType();

			if (daoType.equals("SqlDao")) {
				view.setNetwerDatabankSelected(true);
			} else if (daoType.equals("TxtDao")) {
				view.setLokaalTextbestandSelected(true);
			} else {
				throw new Exception("Geen geldige opslagsetting.");
			}
		} catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}
	}

	public void setDao() {
		try {
			String daoType = BeheerQuizApplicatie.getDaoType();
			if (daoType.equals("SqlDao") && view.isLokaalTextbestandSelected()) {
				BeheerQuizApplicatie.setDaoType("TxtDao");
				opstartController.setQuizapplicatieDAO();
			} else if (daoType.equals("TxtDao")
					&& view.isNetwerDatabankSelected()) {
				BeheerQuizApplicatie.setDaoType("SqlDao");
				opstartController.setQuizapplicatieDAO();
			}
		}

		catch (Exception e) {
			IO.toonStringMetVenster(e.getMessage());
		}
	}

	public void toonMenu() {
		view.setVisible(false);
		opstartController.execute();
	}

}
