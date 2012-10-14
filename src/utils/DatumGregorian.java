package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author Robrecht Van Loon
 * @version 1
 * 
 *          Datumklasse die Gregorian kalender gebruikt.
 * 
 */
public class DatumGregorian implements Comparable<DatumGregorian> {

	private GregorianCalendar calender;

	/**
	 * 
	 * Maakt een nieuw DatumGregorian object met als inhoud de datum van
	 * vandaag.
	 * 
	 */
	public DatumGregorian() {
		GregorianCalendar hulp = new GregorianCalendar();
		calender = new GregorianCalendar();
		calender.clear();
		calender.set(hulp.get(GregorianCalendar.YEAR),
				hulp.get(GregorianCalendar.MONTH),
				hulp.get(GregorianCalendar.DATE));
	}

	/**
	 * 
	 * Maakt een nieuw DatumGregorian object aan op basis van een meegegeven
	 * DatumGregorian object.
	 * 
	 * @param d
	 *            een DatumGregorian object
	 * 
	 * @throws IllegalArgumentException
	 *             wanneer d null is.
	 * 
	 */
	public DatumGregorian(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		calender = new GregorianCalendar();
		calender.clear();
		calender.set(d.calender.get(Calendar.YEAR),
				d.calender.get(Calendar.MONTH),
				d.calender.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 
	 * Geeft een nieuw DatumGregorian object.
	 * 
	 * @param dag
	 *            int
	 * 
	 * @param maand
	 *            int
	 * 
	 * @param jaar
	 *            int
	 * 
	 * @throws IllegalArgumentException
	 * 
	 */
	public DatumGregorian(int dag, int maand, int jaar)
			throws IllegalArgumentException {
		calender = new GregorianCalendar();
		calender.clear();
		calender.set(jaar, maand - 1, dag);

		calender.setLenient(false);
		calender.getTime();
	}

	/**
	 * 
	 * Maakt een nieuw DatumGregorian object aan de hand van een datum-string.
	 * 
	 * @param datumString
	 *            String
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @throws NumberFormatException
	 * 
	 */
	public DatumGregorian(String datumString) throws IllegalArgumentException,
			NumberFormatException {

		calender = new GregorianCalendar();
		calender.clear();
		calender.setLenient(false);
		String[] datumDelen = datumString.split("/");

		try {
			calender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datumDelen[0]));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Ongeldige dag tekst");
		}

		try {
			calender.set(Calendar.MONTH, Integer.parseInt(datumDelen[1]) - 1);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Ongeldige maand tekst");
		}

		try {
			calender.set(Calendar.YEAR, Integer.parseInt(datumDelen[2]));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Ongeldige jaar tekst");
		}
		calender.getTime();

	}

	/**
	 * 
	 * Geeft een datum object een geldige waarde.
	 * 
	 * @param dag
	 *            int
	 * 
	 * @param maand
	 *            int
	 * 
	 * @param jaar
	 *            int
	 * 
	 * @return Boolean
	 * 
	 */
	public Boolean setDatum(int dag, int maand, int jaar) {
		calender.clear();
		calender.set(jaar, maand - 1, dag);

		calender.setLenient(false);
		calender.getTime();
		return true;
	}

	/**
	 * 
	 * Geeft een datum in Amerikaans formaat terug.
	 * 
	 * @return String
	 * 
	 */
	public String getDatumInAmerikaansFormaat() {
		return calender.get(Calendar.YEAR) + "/" + (calender.get(Calendar.MONTH)+1)
				+ "/" + calender.get(Calendar.DATE);
	}

	/**
	 * 
	 * Geeft een datum in Europees formaat terug.
	 * 
	 * @return string
	 * 
	 */
	public String getDatumInEuropeesFormaat() {
		return calender.get(Calendar.DATE) + "/" + (calender.get(Calendar.MONTH)+1)
				+ "/" + calender.get(Calendar.YEAR);
	}

	/**
	 * 
	 * Bepaald of een datum d kleiner is dan het huidig datumobject.
	 * 
	 * @param d
	 *            DatumGregorian
	 * 
	 * @return Boolean
	 * 
	 */
	public boolean kleinerDan(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		Boolean result;
		result = (compareTo(d) < 0 ? false : true);
		return result;
	}

	/**
	 * 
	 * Bepaald het virschil in volledige jaren tussen datum d en het huidig
	 * datumobject.
	 * 
	 * @param d
	 *            DatumGregorian
	 * 
	 * @return int
	 * 
	 */
	public int verschilInJaren(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		if (calender.before(d.calender)) {
			return verschil(calender, d.calender, Calendar.YEAR);
		}
		return verschil(d.calender, calender, Calendar.YEAR);
	}

	/**
	 * 
	 * Bepaald het virschil in volledige maanden tussen datum d en het huidig
	 * datumobject.
	 * 
	 * @param d
	 *            DatumGregorian
	 * 
	 * @return int
	 * 
	 */
	public int verschilInMaanden(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		if (calender.before(d.calender)) {
			return verschil(calender, d.calender, Calendar.MONTH);
		}
		return verschil(d.calender, calender, Calendar.MONTH);
	}

	/**
	 * 
	 * Bepaald het verschil in dagen tussen datum d en het huidig datumobject.
	 * 
	 * @param d
	 *            DatumGregorian
	 * 
	 * @return int
	 * 
	 */
	public int verschilInDagen(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		if (calender.before(d.calender)) {
			return verschil(calender, d.calender, Calendar.DATE);
		}
		return verschil(d.calender, calender, Calendar.DATE);
	}

	private int verschil(Calendar kleinste, Calendar grootste, int CALENDAR_TYPE) {

		Calendar clone = (Calendar) kleinste.clone();
		int elapsed = -1;
		while (clone.compareTo(grootste) <= 0) {
			clone.add(CALENDAR_TYPE, 1);
			elapsed++;
		}
		return elapsed;
	}

	/**
	 * 
	 * Verhoog of verlaag de datum met een aantal dagen.
	 * 
	 * @param aantalDagen
	 *            int
	 * 
	 */
	public void veranderDatum(int aantalDagen) {
		calender.add(Calendar.DATE, aantalDagen);
	}

	public DatumGregorian getVeranderdeDatum(int aantalDagen) {
		DatumGregorian result = new DatumGregorian(this);
		result.veranderDatum(aantalDagen);
		return result;
	}

	@Override
	public String toString() {
		return String.format("%1$te %1$tB %1$tY", calender);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((calender == null) ? 0 : calender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatumGregorian other = (DatumGregorian) obj;
		if (calender == null) {
			if (other.calender != null)
				return false;
		} else if (!(compareTo(other) == 0))
			return false;
		return true;
	}

	@Override
	public int compareTo(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		return calender.compareTo(d.calender);
	}

	public static void main(String[] args) {
		Datum d = new Datum(18, 02, 1980);
		Datum d2 = new Datum(19, 03, 1980);
		System.out.println(d.verschilInMaanden(d2));
	}

}
