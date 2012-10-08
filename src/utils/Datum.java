package utils;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 
 * @author java
 * 
 * Uitleg waar de klasse voor dient. Wordt in HTML vertaald. 
 *
 */
public class Datum implements Comparable<Datum>
{
	
	private int dag;
	private int maand;
	private int jaar;	
	
	public int getDag() {
		return dag;
	}

	public int getMaand() {
		return maand;
	}

	public int getJaar() {
		return jaar;
	}
	
	
	/**
	 * 
	 */
	public Datum()
	{
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
	
	public Datum(Datum date) throws IllegalArgumentException
	{
		if(date != null) {
		this.dag = date.dag;
		this.maand = date.maand;
		this.jaar = date.jaar;
		}
		else {
			throw new IllegalArgumentException("Datum mag niet null zijn");
		}
	}
	
	public Datum(int dag, int maand, int jaar) throws IllegalArgumentException
	{
		if(dag <= 0) {
			throw new IllegalArgumentException("Dag moet groter zijn dan 0");
		}
		
		if (maand <= 0 || maand > 12){
			throw new IllegalArgumentException("Maand mag niet kleiner zijn dan 1 of groter dan 12");
		}
		
		if (maand == 1 || maand == 3 || maand == 5 || maand == 7 || maand == 8 || maand == 10 || maand == 12){
			if(dag > 31) throw new IllegalArgumentException("Deze maand telt maximum 31 dagen");
		}
		if (maand == 4 || maand == 6 || maand == 9 || maand == 11)	{
			if (dag > 30) throw new IllegalArgumentException("Deze maand telt maximum 30 dagen");
		}		
		if(maand == 2) 	{
			//schrikkeljaar
			if((jaar % 400 == 0) || ((jaar % 4 == 0) && (jaar % 100 != 0))) {
				if(dag > 29) throw new IllegalArgumentException("Februari telt maximum 29 dagen in een schrikkeljaar");
			}
			else { //geen schrikkeljaar
				if(dag > 28) {
					throw new IllegalArgumentException("Februari telt slechts 28 dagen");
				}
			}
		}
		//invoer is correct
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}
	
	public Datum(String datum) throws IllegalArgumentException
	{
		if(datum.length() > 10 || datum.length() < 10) {
			throw new IllegalArgumentException("Datum niet in juiste formaat");
		}
		
		String eersteScheidingsteken = datum.substring(2, 3);
		String tweedeScheidingsteken = datum.substring(5, 6);
		
		if(eersteScheidingsteken.compareTo("/") != 0 || tweedeScheidingsteken.compareTo("/") != 0) {
			throw new IllegalArgumentException("Formaat datum: DD/MM/YYYY");
		}
		String dagDatum = datum.substring(0,2);
		String maandDatum = datum.substring(3,5);
		String jaarDatum = datum.substring(6,10);
				
		try {
			int dagInt = Integer.parseInt(dagDatum);
			int maandInt = Integer.parseInt(maandDatum);
			int jaarInt = Integer.parseInt(jaarDatum);
			// dag en maand testen door andere constructor aan te roepen
			Datum invoerDatum = new Datum(dagInt, maandInt, jaarInt);
			this.dag = invoerDatum.dag;
			this.maand = invoerDatum.maand;
			this.jaar = invoerDatum.jaar;
		} 
		catch (NumberFormatException nfe){
			throw new IllegalArgumentException("Formaat datum: DD/MM/YYYY");
		}		
	}
	
	/**
	 * 
	 * @param dag Wat moeten de gebruikers weten
	 * @param maand
	 * @param jaar
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean setDatum(int dag, int maand, int jaar) throws IllegalArgumentException
	{
		Datum nieuweDatum = new Datum(dag, maand, jaar);
		this.dag = nieuweDatum.dag;
		this.maand = nieuweDatum.maand;
		this.jaar = nieuweDatum.jaar;
		return true;
	}
	
	public String getDatumInAmerikaansFormaat()
	{
		return this.jaar + "/" + this.maand + "/" + this.dag;
	}
	
	public String getDatumInEuropeesFormaat()
	{
		return this.dag + "/" + this.maand + "/" + this.jaar;
	}
	
	public boolean kleinerDan(Datum d)
	{
		if(this.jaar < d.jaar){
			return true;
		}
		else if(this.jaar > d.jaar) {
			return false;
		}
		else {
			if(this.maand < d.maand){
				return true;
			}
			else if(this.maand > d.maand){
				return false;
			}
			else{
				if(this.dag < d.dag){
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	private boolean isLeapYear()
	{
		if((this.jaar % 400 == 0) || ((this.jaar % 4 == 0) && (this.jaar % 100 != 0))) {
			return true;
		}
		else
			return false;
	}
	
	
	private int dagenInDatum()
	{
		int aantalDagen = 0;
		
		for(int i = 1; i < this.jaar; i++){
			if((i % 400 == 0) || ((i % 4 == 0) && (i % 100 != 0))){
				aantalDagen += 366;
			}
			else{
				aantalDagen += 365;
			}
		}
		
		switch (this.maand){
			case 2:
				aantalDagen += 31;
				break;
			case 3:
				 if(this.isLeapYear()) {
					 aantalDagen += (31+29);
				 }
				 else {
					 aantalDagen += (31+28);
				 }
				 break;
			case 4:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31);
				 }
				 else {
					 aantalDagen += (31+28+31);
				 }
				break;
			case 5:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30);
				 }
				 else {
					 aantalDagen += (31+28+31+30);
				 }
				break;
			case 6:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30+31);
				 }
				 else {
					 aantalDagen += (31+28+31+30+31);
				 }
				break;
			case 7:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30+31+30);
				 }
				 else {
					 aantalDagen += (31+28+31+30+31+30);
				 }
				break;
			case 8:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30+31+30+31);
				 }
				 else {
					 aantalDagen += (31+28+31+30+31+30+31);
				 }
				break;
			case 9:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30+31+30+31+31);
				 }
				 else {
					 aantalDagen += (31+28+31+30+31+30+31+31);
				 }
				break;
			case 10:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30+31+30+31+31+30);
				 }
				 else {
					 aantalDagen += (31+28+31+30+31+30+31+31+30);
				 }
				break;
			case 11:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30+31+30+31+31+30+31);
				 }
				 else {
					 aantalDagen += (31+28+31+30+31+30+31+31+30+31);
				 }
				break;
			case 12:
				if(this.isLeapYear()) {
					 aantalDagen += (31+29+31+30+31+30+31+31+30+31+30);
				 }
				 else {
					 aantalDagen += (31+28+31+30+31+30+31+31+30+31+30);
				 }
				break;
		}
		
		aantalDagen += this.dag;
		
		return aantalDagen;		
	}
	
	public int verschilInJaren(Datum d)
	{
		int verschilInJaren = (int) ((this.dagenInDatum() - d.dagenInDatum())/365.25);
		return Math.abs(verschilInJaren);
	}
	
	
	public int verschilInMaanden(Datum d)
	{
		int verschilInMaanden = (this.dagenInDatum() - d.dagenInDatum())/30;
		return Math.abs(verschilInMaanden);
	}
	
	public int verschilInDagen(Datum d)
	{
		return Math.abs(this.dagenInDatum() - d.dagenInDatum());
	}
	
	public void veranderDatum(int aantalDagen)
	{
		//Dagen optellen
		if(aantalDagen > 0) { 
			int dag1 = this.dag; //kopie van dag, te gebruiken omdat de dag éénmalig op nul gezet moet worden
			
			while(aantalDagen > 0) { 
				if(this.maand == 1 || this.maand == 3 || this.maand == 5 || this.maand == 7 || this.maand == 8 || this.maand == 10 || this.maand == 12) {
					if(aantalDagen > (31 - dag1)) {
						this.maand++;
						aantalDagen -= (31 - dag1);
						dag1 = 0;
					}
					else{
						this.dag = aantalDagen;
						aantalDagen = 0;
					}
				}
				else if(this.maand == 4 || this.maand == 6 || this.maand == 9 || this.maand == 11){
					if(aantalDagen > (30 - dag1)) {
						this.maand++;
						aantalDagen -= (30 - dag1);
						dag1 = 0;
					}
					else{
						this.dag = aantalDagen;
						aantalDagen = 0;
					}
				}
				else{  
					if(this.isLeapYear()){
						if(aantalDagen > (29 - dag1)) {
							this.maand++;
							aantalDagen -= (29 - dag1);
							dag1 = 0;
						}
						else{
							this.dag = aantalDagen;
							aantalDagen = 0;
						}
					}
					else
					{
						if(aantalDagen > (28 - dag1)) {
							this.maand++;
							aantalDagen -= (28 - dag1);
						}
						else{
							this.dag = aantalDagen;
							aantalDagen = 0;
						}
					}
				}
				if(this.maand == 13)  {
					this.maand = 1;
					this.jaar++;
					}
			}// end while
		}
		//Dagen verminderen
		else if(aantalDagen < 0) {
			if(Math.abs(aantalDagen) >= this.dag){
				aantalDagen += this.dag; //huidige dag moet in mindering gebracht worden
				boolean retry = true; 
				do{
					this.maand--;
					if(this.maand == 0){
						this.maand = 12;
						this.jaar--;
					}
					if(this.maand == 1 || this.maand == 3 || this.maand == 5 || this.maand == 7 || this.maand == 8 || this.maand == 10 || this.maand == 12){
						if(Math.abs(aantalDagen) >= 31){
							aantalDagen += 31;
						}
						else{
							this.dag = 31 + aantalDagen;
							retry = false;
						}
					}
					else if(this.maand == 4 || this.maand == 6 || this.maand == 9 || this.maand == 11) {
						if(Math.abs(aantalDagen) >= 30){
							aantalDagen += 30;
						}
						else{
							this.dag = 30 + aantalDagen;
							retry = false;
						}
					}
					else
					{
						if(this.isLeapYear()){
							if(Math.abs(aantalDagen) >= 29){
								aantalDagen += 29;
							}
							else{
								this.dag = 29 + aantalDagen;
								retry = false;
							}
						}
						else {
							if(Math.abs(aantalDagen) >= 28){
								aantalDagen += 28;
							}
							else{
								this.dag = 28 + aantalDagen;
								retry = false;
							}
						}
					}
					
				}while(retry);			
			}
			else{
				this.dag += aantalDagen;
			}
		}
	}
	
	public Datum veranderdeDatum(int aantalDagen)
	{
		Datum verhoogdeDatum = new Datum(this.dag, this.maand, this.jaar);
		verhoogdeDatum.veranderDatum(aantalDagen);
		return verhoogdeDatum;
	}
	
	
	
	@Override
	public String toString()
	{
		return this.dag + " " + maanden.values()[this.maand-1] + " " + this.jaar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try	{
			String datum1 = "25/01/2011";
			String datum2 = "26/04/2015";
			Datum d1 = new Datum(datum1);
			Datum d2 = d1.veranderdeDatum(1015);
			d1.veranderDatum(-20000);
			System.out.println(d2);			
			

		}
		catch (IllegalArgumentException iae){
			System.out.println(iae);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public int compareTo(Datum o) {
		if(this.jaar < o.jaar)	{
			return -1;
		}
		else if(this.jaar > o.jaar)	{
			return 1;
		}
		else {
			if(this.maand < o.maand){
				return -1;
			}
			else if(this.maand > o.maand) {
				return 1;
			}
			else {
				if(this.dag < o.dag){
					return -1;
				}
				else if (this.dag > o.dag) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
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
}


