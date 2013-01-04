package model;

public class RapportKopRegels extends RapportDecorator {

	private RapporteerbaarObject rapporteerbaarObject;
	private QuizDeelname deelname;

	public RapportKopRegels(RapporteerbaarObject qr, QuizDeelname deelname) {
		this.rapporteerbaarObject = qr;
		this.deelname = deelname;
	}

	@Override
	public String getRapport() {
		String kopTekst = "";
		kopTekst += "DatumDeelname: " + deelname.getDatumDeelname();
		kopTekst += "\tAuteur quiz: " + deelname.getAuteur() + "\n\n";
		return kopTekst + rapporteerbaarObject.getRapport();
	}
}
