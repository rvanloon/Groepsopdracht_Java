package utils;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 
 * @author sven
 * 
 *         Datumklasse from scratch, waarbij enkel gebruik gemaakt is van de
 *         date-klasse
 * 
 * 
 */
public class Datum implements Comparable<Datum> {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Maakt een Datum-object aan de hand van de systeemdatum
	 */
	public Datum() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = dateFormat.format((date));
		String stringDag = currentDate.substring(0, 2);
		this.dag = Integer.parseInt(stringDag);
		String stringMaand = currentDate.substring(3, 5);
		this.maand = Integer.parseInt(stringMaand);
		String stringJaar = currentDate.substring(6, 10);
		this.jaar = Integer.parseInt(stringJaar);
	}

	/**
	 * Maakt een Datum-object aan op basis van een meegegeven Datum-object
	 * 
	 * @param date
	 *            Een Datum-object
	 * @throws IllegalArgumentException
	 *             Als een datum-object wordt meegegeven dat null is, wordt een
	 *             IllegalArgumentException gegooid
	 */
	public Datum(Datum date) throws IllegalArgumentException {
		if (date != null) {
			this.dag = date.dag;
			this.maand = date.maand;
			this.jaar = date.jaar;
		} else {
			throw new IllegalArgumentException("Datum mag niet null zijn");
		}
	}

	/**
	 * Maakt een Datum-object aan op basis van integers dag, maand en jaar in
	 * die volgorde
	 * 
	 * @param dag
	 *            integer voor de dag-waarde
	 * @param maand
	 *            integer voor de maand-waarde
	 * @param jaar
	 *            integer voor de jaar-waarde
	 * @throws IllegalArgumentException
	 *             Indien de meegegeven waarden geen geldige datum opleveren,
	 *             wordt een IllegalArgumentException gegooid
	 */
	public Datum(int dag, int maand, int jaar) throws IllegalArgumentException {
		if (dag <= 0) {
			throw new IllegalArgumentException("Dag moet groter zijn dan 0");
		}

		if (maand <= 0 || maand > 12) {
			throw new IllegalArgumentException(
					"Maand mag niet kleiner zijn dan 1 of groter dan 12");
		}

		if (maand == 1 || maand == 3 || maand == 5 || maand == 7 || maand == 8
				|| maand == 10 || maand == 12) {
			if (dag > 31)
				throw new IllegalArgumentException(
						"Deze maand telt maximum 31 dagen");
		}
		if (maand == 4 || maand == 6 || maand == 9 || maand == 11) {
			if (dag > 30)
				throw new IllegalArgumentException(
						"Deze maand telt maximum 30 dagen");
		}
		if (maand == 2) {
			// schrikkeljaar
			if ((jaar % 400 == 0) || ((jaar % 4 == 0) && (jaar % 100 != 0))) {
				if (dag > 29)
					throw new IllegalArgumentException(
							"Februari telt maximum 29 dagen in een schrikkeljaar");
			} else { // geen schrikkeljaar
				if (dag > 28) {
					throw new IllegalArgumentException(
							"Februari telt slechts 28 dagen");
				}
			}
		}
		// invoer is correct
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}

	/**
	 * Maakt een Datum-object op basis van een String
	 * 
	 * @param datum
	 *            Een String in het formaat "DD/MM/YYYY" of "D/MM/YYYY"
	 * @throws IllegalArgumentException
	 *             Indien de string geen geldige datum oplevert, wordt een
	 *             IllegalArgumentException gegooid
	 */

	public Datum(String datum) throws IllegalArgumentException {
		//controleren of ingevoerde string niet te groot of te klein is
		if (datum.length() > 10 || datum.length() < 9) {
			throw new IllegalArgumentException("Datum niet in juiste formaat");
		}		
		String dagDatum = "";
		String maandDatum = "";
		String jaarDatum = "";
		// controleren of de dag ingevoerd is met één cijfer
		if(datum.substring(1, 2).compareTo("/") == 0){
			String eersteScheidingsteken = datum.substring(1, 2);
			String tweedeScheidingsteken = datum.substring(4, 5);
			
			if (eersteScheidingsteken.compareTo("/") != 0
					|| tweedeScheidingsteken.compareTo("/") != 0) {
				throw new IllegalArgumentException("Formaat datum: DD/MM/YYYY of D/MM/YYYY");
			}
			dagDatum = datum.substring(0, 1);
			maandDatum = datum.substring(2, 4);
			jaarDatum = datum.substring(5, 9);
		}
		else{// dag ingevoerd met twee cijfers
			String eersteScheidingsteken = datum.substring(2, 3);
			String tweedeScheidingsteken = datum.substring(5, 6);

			if (eersteScheidingsteken.compareTo("/") != 0
					|| tweedeScheidingsteken.compareTo("/") != 0) {
				throw new IllegalArgumentException("Formaat datum: DD/MM/YYYY of D/MM/YYYY");
			}
			dagDatum = datum.substring(0, 2);
			maandDatum = datum.substring(3, 5);
			jaarDatum = datum.substring(6, 10);
		}
		
		try{	
			int dagInt = Integer.parseInt(dagDatum);
			int maandInt = Integer.parseInt(maandDatum);
			int jaarInt = Integer.parseInt(jaarDatum);
			// dag en maand testen door andere constructor aan te roepen
			Datum invoerDatum = new Datum(dagInt, maandInt, jaarInt);
			this.dag = invoerDatum.dag;
			this.maand = invoerDatum.maand;
			this.jaar = invoerDatum.jaar;
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Formaat datum: DD/MM/YYYY of D/MM/YYYY");
		}
	}

	/**
	 * Functie die de waarde van een Datum-object wijzigt op basis van de
	 * integers dag, maand en jaar (in die volgorde)
	 * 
	 * @param dag
	 *            Integer die de dagwaarde invult
	 * @param maand
	 *            Integer die de maandwaarde invult
	 * @param jaar
	 *            Integer die de jaarwaarde invult
	 * @return De functie geeft "true" terug als de invulwaarden een geldige
	 *         datum opleveren
	 * @throws IllegalArgumentException
	 *             Indien de invulwaarden geen geldige datum opleveren, wordt
	 *             een IllegalArgumentException gegooid
	 */
	public boolean setDatum(int dag, int maand, int jaar)
			throws IllegalArgumentException {
		Datum nieuweDatum = new Datum(dag, maand, jaar);
		this.dag = nieuweDatum.dag;
		this.maand = nieuweDatum.maand;
		this.jaar = nieuweDatum.jaar;
		return true;
	}

	/**
	 * Functie die de datum in Amerikaans formaat teruggeeft in String-formaat
	 * (YYYY/MM/DD)
	 * 
	 * @return De functie geeft een String terug, een voorstelling van het
	 *         Datum-object in String-formaat
	 */

	public String getDatumInAmerikaansFormaat() {
		return this.jaar + "/" + this.maand + "/" + this.dag;
	}

	/**
	 * Functie die de datum in Europees formaat teruggeeft in String-formaat
	 * 
	 * @return De functie geeft een String terug, een voorstelling van het
	 *         Datum-object in String-formaat
	 */

	public String getDatumInEuropeesFormaat() {
		return this.dag + "/" + this.maand + "/" + this.jaar;
	}

	/**
	 * Functie die het Datum-object vergelijkt met meegegeven Datum-object.
	 * Indien het Datum-object kleiner is dan het meegeven Datum-object geeft de
	 * functie true terug, anders false
	 * 
	 * @param d
	 *            Meegegeve Datum-object waarmee het Datum-object vergeleken
	 *            wordt
	 * @return De functie geeft true terug als het Datum-object kleiner is dan
	 *         het Datum-object waarmee de functie vergeleken wordt. Anders
	 *         geeft de functie false terug.
	 */

	public boolean kleinerDan(Datum d) {
		if (this.jaar < d.jaar) {
			return true;
		} else if (this.jaar > d.jaar) {
			return false;
		} else {
			if (this.maand < d.maand) {
				return true;
			} else if (this.maand > d.maand) {
				return false;
			} else {
				if (this.dag < d.dag) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * Functie die aangeeft of de datum valt in een schrikkeljaar, of niet.
	 * Indien de datum valt in een schrikkeljaar geeft de functie true terug,
	 * anders false
	 * 
	 * @return De functie geeft true terug als de datum in een schrikkeljaar
	 *         valt, anders geeft de functie false terug
	 */

	private boolean isLeapYear() {
		if ((this.jaar % 400 == 0)
				|| ((this.jaar % 4 == 0) && (this.jaar % 100 != 0))) {
			return true;
		} else
			return false;
	}

	private int dagenInDatum() {
		int aantalDagen = 0;
		int[] dagenSchrikkeljaar = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31,
				30, 31 };
		int[] dagenNietSchrikkeljaar = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
				31, 30, 31 };

		// Dagen optellen van de jaren voorgaande aan het laatste jaar
		for (int i = 1; i < this.jaar; i++) {
			if ((i % 400 == 0) || ((i % 4 == 0) && (i % 100 != 0))) {
				aantalDagen += 366;
			} else {
				aantalDagen += 365;
			}
		}

		// Dagen optellen van het laatste jaar tot aan de laatste maand
		if (this.maand >= 2) { //vanaf februari
			for (int i = 1; i < this.maand; i++) {
				if (this.isLeapYear()) {
					aantalDagen += dagenSchrikkeljaar[i - 1];
				}
				else
				{
					aantalDagen += dagenNietSchrikkeljaar[i -1];
				}
			}
		}
		// Dagen in de laatste maand
		aantalDagen += this.dag;
		
		return aantalDagen;
		

	}

	public int verschilInJaren(Datum d) {
		int verschilInJaren = (int) ((this.dagenInDatum() - d.dagenInDatum()) / 365.25);
		return Math.abs(verschilInJaren);
	}

	public int verschilInMaanden(Datum d) {
		int verschilInMaanden = (this.dagenInDatum() - d.dagenInDatum()) / 30;
		return Math.abs(verschilInMaanden);
	}

	public int verschilInDagen(Datum d) {
		return Math.abs(this.dagenInDatum() - d.dagenInDatum());
	}

	/**
	 * Functie die een aantal dagen bij de datum optelt of aftrekt. Indien een
	 * positieve integer wordt meegeven, worden de dagen er bij geteld. Indien
	 * een negatieve integer wordt meegeven, worden de dagen van de datum
	 * afgetrokken
	 * 
	 * @param aantalDagen
	 *            Integer, het aantal dagen dat bij de datum opgeteld of
	 *            afgetrokken moet worden (positief voor optellen, negatief voor
	 *            aftrekken)
	 */

	public void veranderDatum(int aantalDagen) {
		// Dagen optellen
		if (aantalDagen > 0) {
			int dag1 = this.dag; // kopie van dag, te gebruiken omdat de dag
									// éénmalig op nul gezet moet worden

			while (aantalDagen > 0) {
				if (this.maand == 1 || this.maand == 3 || this.maand == 5
						|| this.maand == 7 || this.maand == 8
						|| this.maand == 10 || this.maand == 12) {
					if (aantalDagen > (31 - dag1)) {
						this.maand++;
						aantalDagen -= (31 - dag1);
						dag1 = 0;
					} else {
						this.dag = aantalDagen;
						aantalDagen = 0;
					}
				} else if (this.maand == 4 || this.maand == 6
						|| this.maand == 9 || this.maand == 11) {
					if (aantalDagen > (30 - dag1)) {
						this.maand++;
						aantalDagen -= (30 - dag1);
						dag1 = 0;
					} else {
						this.dag = aantalDagen;
						aantalDagen = 0;
					}
				} else {
					if (this.isLeapYear()) {
						if (aantalDagen > (29 - dag1)) {
							this.maand++;
							aantalDagen -= (29 - dag1);
							dag1 = 0;
						} else {
							this.dag = aantalDagen;
							aantalDagen = 0;
						}
					} else {
						if (aantalDagen > (28 - dag1)) {
							this.maand++;
							aantalDagen -= (28 - dag1);
						} else {
							this.dag = aantalDagen;
							aantalDagen = 0;
						}
					}
				}
				if (this.maand == 13) {
					this.maand = 1;
					this.jaar++;
				}
			}// end while
		}
		// Dagen verminderen
		else if (aantalDagen < 0) {
			if (Math.abs(aantalDagen) >= this.dag) {
				aantalDagen += this.dag; // huidige dag moet in mindering
											// gebracht worden
				boolean retry = true;
				do {
					this.maand--;
					if (this.maand == 0) {
						this.maand = 12;
						this.jaar--;
					}
					if (this.maand == 1 || this.maand == 3 || this.maand == 5
							|| this.maand == 7 || this.maand == 8
							|| this.maand == 10 || this.maand == 12) {
						if (Math.abs(aantalDagen) >= 31) {
							aantalDagen += 31;
						} else {
							this.dag = 31 + aantalDagen;
							retry = false;
						}
					} else if (this.maand == 4 || this.maand == 6
							|| this.maand == 9 || this.maand == 11) {
						if (Math.abs(aantalDagen) >= 30) {
							aantalDagen += 30;
						} else {
							this.dag = 30 + aantalDagen;
							retry = false;
						}
					} else {
						if (this.isLeapYear()) {
							if (Math.abs(aantalDagen) >= 29) {
								aantalDagen += 29;
							} else {
								this.dag = 29 + aantalDagen;
								retry = false;
							}
						} else {
							if (Math.abs(aantalDagen) >= 28) {
								aantalDagen += 28;
							} else {
								this.dag = 28 + aantalDagen;
								retry = false;
							}
						}
					}

				} while (retry);
			} else {
				this.dag += aantalDagen;
			}
		}
	}

	/**
	 * Functie die een Datumobject teruggeeft met als waarde de datum van het
	 * Datum-object met het meegeven aantal dagen daarbij opgeteld of
	 * afgetrokken. Als de meegeven integer positief is, worden het aantal dagen
	 * er bij opgeteld, als de meegegeven integer negatief is worden de dagen er
	 * afgetrokken
	 * 
	 * @param aantalDagen
	 *            Integer, het aantal dagen dat bij de datum opgeteld of
	 *            afgetrokken moet worden (positief voor optellen, negatief voor
	 *            aftrekken)
	 * @return De functie geeft een Datum-object terug
	 */

	public Datum getVeranderdeDatum(int aantalDagen) {
		Datum verhoogdeDatum = new Datum(this.dag, this.maand, this.jaar);
		verhoogdeDatum.veranderDatum(aantalDagen);
		return verhoogdeDatum;
	}

	/**
	 * Functie die de datumwaarde teruggeeft in het formaat DD/MM/YYY
	 */

	@Override
	public String toString() {
		return this.dag + " " + maanden.values()[this.maand - 1] + " "
				+ this.jaar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			String datum1 = "5/01/2011";
			

		} catch (IllegalArgumentException iae) {
			System.out.println(iae);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Functie de compareTo-method override. Het Datumobject wordt vergeleken
	 * met een ander Datum-object. Indien het Datum-object kleiner is dan het
	 * meegegeven Datum-object, dan wordt een negatief getal teruggegeven.
	 * Indien het Datum-object groter is dan het meegegeven Datum-object, dan
	 * wordt een positief getal teruggegeven. Als de twee Datum-objecten gelijk
	 * zijn, dan wordt nul meegegeven.
	 * 
	 * @param o
	 *            Datum-object waarmee vergeleken wordt
	 */
	@Override
	public int compareTo(Datum o) {
		if (this.jaar < o.jaar) {
			return -1;
		} else if (this.jaar > o.jaar) {
			return 1;
		} else {
			if (this.maand < o.maand) {
				return -1;
			} else if (this.maand > o.maand) {
				return 1;
			} else {
				if (this.dag < o.dag) {
					return -1;
				} else if (this.dag > o.dag) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * Functie die de hashCode-functie override
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dag;
		result = prime * result + jaar;
		result = prime * result + maand;
		return result;
	}

	/**
	 * Functie die de equals-functie override. Indien het meegegeven object
	 * gelijk is aan het Datum-object wordt true teruggegeven, anders false
	 * 
	 * @param obj
	 *            Object waarmee het Datum-object vergeleken wordt
	 */
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
}
