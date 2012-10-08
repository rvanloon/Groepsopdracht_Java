package utils;

import java.util.Date;

public class Datum implements Comparable<Datum> {

	private int dag;
	private int maand;
	private int jaar;

	public Datum() {
		Date date = new Date();
		setJaar(date.getYear());
		setMaand(date.getMonth());
		setDag(date.getDay());
	}

	public Datum(Datum datum) {
		if (datum == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		setJaar(datum.jaar);
		setMaand(datum.maand);
		setDag(datum.dag);
	}

	public Datum(int dag, int maand, int jaar) throws IllegalArgumentException {
		setJaar(jaar);
		setMaand(maand);
		setDag(dag);
	}

	public Datum(String datumString) throws IllegalArgumentException,
			NumberFormatException {
		String[] datumDelen = datumString.split("/");

		try {
			setDag(Integer.parseInt(datumDelen[0]));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Ongeldige dag tekst");
		}

		try {
			setMaand(Integer.parseInt(datumDelen[1]));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Ongeldige maand tekst");
		}

		try {
			setJaar(Integer.parseInt(datumDelen[2]));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Ongeldige jaar tekst");
		}

	}

	private void setDag(int dag) {
		if (dag < 1 || dag > 31) {
			throw new IllegalArgumentException("Ongeldige dag");
		}
		this.dag = dag;
	}

	private void setMaand(int maand) {
		if (maand < 1 || maand > 12) {
			throw new IllegalArgumentException("Ongeldige maand");
		}
		this.maand = maand;
	}

	private void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public Boolean setDatum(int dag, int maand, int jaar) {
		setJaar(jaar);
		setMaand(maand);
		setDag(dag);
		return true;
	}

	public String getDatumInAmerikaansFormaat() {
		return jaar + "/" + maand + "/" + dag;
	}

	public String getDatumInEuropeesFormaat() {
		return dag + "/" + maand + "/" + jaar;
	}

	public boolean kleinerDan(Datum d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		if (compareTo(d) < 0) {
			return true;
		}
		return false;
	}

	public int verschilInJaren(Datum d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}

		int result;
		Datum grootste = this;
		Datum kleinste = d;

		if (grootste.kleinerDan(kleinste)) {
			grootste = d;
			kleinste = this;
		}

		result = grootste.jaar - kleinste.jaar;
		if (grootste.maand < kleinste.maand) {
			result--;
		} else if (grootste.maand == kleinste.maand) {
			if (grootste.dag < kleinste.dag) {
				result--;
			}
		}
		return result;
	}

	public int verschilInMaanden(Datum d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}

		int result;
		Datum grootste = this;
		Datum kleinste = d;

		if (grootste.kleinerDan(kleinste)) {
			grootste = d;
			kleinste = this;
		}

		result = (grootste.jaar - kleinste.jaar) * 12 + grootste.maand
				- kleinste.maand;
		if (grootste.dag < kleinste.dag) {
			result--;
		}
		return result;
	}

	public int verschilInDagen(Datum d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}

		Datum grootste = this;
		Datum kleinste = d;

		if (grootste.kleinerDan(kleinste)) {
			grootste = d;
			kleinste = this;
		}

		Date dateGrootste = new Date(grootste.jaar, grootste.maand,
				grootste.dag);
		Date dateKleinste = new Date(kleinste.jaar, kleinste.maand,
				kleinste.dag);

		return (int) (dateGrootste.getTime() - dateKleinste.getTime())
				/ (1000 * 60 * 60 * 24);

	}

	public void veranderDatum(int aantalDagen) {
		Date date = new Date(jaar, maand, dag);
		Date newDate = new Date(date.getTime() + aantalDagen * 24 * 60 * 60
				* 1000);
		setJaar(newDate.getYear());
		setMaand(newDate.getMonth());
		setDag(newDate.getDate());
	}

	public Datum getveranderdeDatum(int aantalDagen) {
		Datum result = new Datum(this);
		result.veranderDatum(aantalDagen);
		return result;
	}

	@Override
	public String toString() {
		return dag + " " + maanden.values()[maand - 1].toString() + " " + jaar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dag;
		result = prime * result + jaar;
		result = prime * result + maand;
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
		Datum other = (Datum) obj;
		if (dag != other.dag)
			return false;
		if (jaar != other.jaar)
			return false;
		if (maand != other.maand)
			return false;
		return true;
	}

	@Override
	public int compareTo(Datum d) {
		if (d == null) {
			throw new IllegalArgumentException("Datum in null");
		}
		int dateInt1 = jaar * 10000 + maand * 100 + dag;
		int dateInt2 = d.jaar * 10000 + d.maand * 100 + d.dag;
		return dateInt1 - dateInt2;
	}

	public static void main(String[] args) {
		Datum dat1 = new Datum("01/08/2012");
		Datum dat2 = new Datum("01/08/2012");

		int test = dat1.compareTo(dat2);

		System.out.println(dat1.kleinerDan(dat2));
		System.out.println(test);
	}
}
