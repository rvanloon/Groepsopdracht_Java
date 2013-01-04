package model;

public class RapportSlotRegels extends RapportDecorator {

	private RapporteerbaarObject rapporteerbaarObject;
	private QuizDeelname deelname;

	public RapportSlotRegels(RapporteerbaarObject qr, QuizDeelname deelname) {
		this.rapporteerbaarObject = qr;
		this.deelname = deelname;
	}

	@Override
	public String getRapport() {
		String slotTekst = "\n\n";
		slotTekst += deelname.getDeelnameScore() >= 7 ? "Gefeliciteerd!"
				: deelname.getDeelnameScore() >= 5 ? "Geslaagd."
						: "Dit moet beter in de toekomst.";
		slotTekst += "\n(De gemiddelde score op de quiz was "
				+ (int) deelname.getGemiddeldeScore() + ")\n";
		return rapporteerbaarObject.getRapport() + slotTekst;
	}

}
