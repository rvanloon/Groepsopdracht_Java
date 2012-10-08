package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author Robrecht Van Loon
 * @version 1
 * 
 * Datumklasse die Gregorian kalender gebruikt.
 * 
 */
public class DatumGregorian implements Comparable<DatumGregorian> {

	private GregorianCalendar calender;

	/**
	 * 
	 * Maakt een nieuw DatumGregorian object aan met als inhoud de datum van vandaag.
	 * 
	 */
	public DatumGregorian() {
		calender = new GregorianCalendar();
	}

	/**
	 * 
	 * Maakt een nieuw DatumGregorian object aan op basis van een meegegeven DatumGregorian object.
	 * 
	 * @param d 
	 * een DatumGregorian object
	 *            
	 * @throws IllegalArgumentException wanneer d null is.
	 * 
	 */
	public DatumGregorian(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		calender = new GregorianCalendar(d.calender.get(Calendar.YEAR),
				d.calender.get(Calendar.MONTH),
				d.calender.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 
	 * Geeft een nieuw DatumGregorian object.
	 * 
	 * @param dag
	 * int
	 * 
	 * @param maand
	 * int
	 * 
	 * @param jaar
	 * int
	 * 
	 * @throws IllegalArgumentException
	 * 
	 */
	public DatumGregorian(int dag, int maand, int jaar)
			throws IllegalArgumentException {
		calender = new GregorianCalendar(jaar, maand - 1, dag);
		calender.setLenient(false);
		calender.getTime();
	}

	/**
	 * 
	 * Maakt een nieuw DatumGregorian object aan de hand van een datum-string.
	 * 
	 * @param datumString
	 * String
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @throws NumberFormatException
	 * 
	 */
	public DatumGregorian(String datumString) throws IllegalArgumentException,
			NumberFormatException {

		calender = new GregorianCalendar();
		calender.setLenient(false);
		String[] datumDelen = datumString.split("/");

		try {
			calender.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datumDelen[0]));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Ongeldige dag tekst");
		}

		try {
			calender.set(Calendar.MONTH, Integer.parseInt(datumDelen[1]));
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
	 * int
	 * 
	 * @param maand
	 * int
	 * 
	 * @param jaar
	 * int
	 * 
	 * @return
	 * Boolean
	 * 
	 */
	public Boolean setDatum(int dag, int maand, int jaar) {
		calender.setLenient(false);
		calender.set(jaar, maand - 1, dag);
		calender.getTime();
		return true;
	}

	/**
	 * 
	 * Geeft een datum in Amerikaans formaat terug.
	 * 
	 * @return
	 * String
	 * 
	 */
	public String getDatumInAmerikaansFormaat() {
		return String.format("%1$tY/%1$tm/%1$te", calender);
	}

	/**
	 * 
	 * Geeft een datum in Europees formaat terug.
	 * 
	 * @return
	 * string
	 * 
	 */
	public String getDatumInEuropeesFormaat() {
		return String.format("%1$te/%1$tm/%1$tY", calender);
	}

	/**
	 * 
	 * Bepaald of een datum d kleiner is dan het huidig datumobject.
	 * 
	 * @param d
	 * DatumGregorian
	 * 
	 * @return
	 * Boolean
	 * 
	 */
	public boolean kleinerDan(DatumGregorian d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		return calender.before(d);
	}

	/**
	 * 
	 * Bepaald het virschil in volledige jaren tussen datum d en het huidig datumobject.
	 * 
	 * @param d
	 * DatumGregorian
	 * 
	 * @return
	 * int
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
	 * Bepaald het virschil in volledige maanden tussen datum d en het huidig datumobject.
	 * 
	 * @param d
	 * DatumGregorian
	 * 
	 * @return
	 * int
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
	 * Bepaald het virschil in dagen tussen datum d en het huidig datumobject.
	 * 
	 * @param d
	 * DatumGregorian
	 * 
	 * @return
	 * int
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
		while (grootste.after(clone)) {
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
	 * int
	 * 
	 */
	public void veranderDatum(int aantalDagen) {
		calender.add(Calendar.DATE, aantalDagen);
	}

	public DatumGregorian getveranderdeDatum(int aantalDagen) {
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
		} else if (!calender.equals(other.calender))
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
		DatumGregorian d1 = new DatumGregorian();
		DatumGregorian d2 = new DatumGregorian(5, 12, 1999);

		d1.setDatum(11, 07, 2013);

		System.out.println(d2.getDatumInAmerikaansFormaat());
		System.out.println(d1.verschilInMaanden(d2));
	}

}
