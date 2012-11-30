package collections ;
import java.util.*;
import java.util.Map.Entry;

import view.IO;
public class Opgave1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map <String,List<String>> deelname = new HashMap<String,List<String>>();
		List <String >deelnemers = null;
		String quizNaam = null;
		// quiz1
		quizNaam = "Optellen";
		deelnemers = new ArrayList <String>();
		deelnemers.add("Piet");deelnemers.add("Rikske");deelnemers.add("Wiske");
		deelname.put(quizNaam, deelnemers);
		// quiz2
		quizNaam = "Aftrekken";
		deelnemers = new ArrayList <String>();
		deelnemers.add("Jonas");deelnemers.add("Rikske");deelnemers.add("Wiske");
		deelname.put(quizNaam, deelnemers);
		// quiz3
		quizNaam = "Rekenen";
		deelnemers = new ArrayList <String>();
		deelnemers.add("Florke");deelnemers.add("Rikske");deelnemers.add("Wiske");
		deelname.put(quizNaam, deelnemers);
		// toon deelnemers
		IO.toonStringMetVenster(toonDeelnemers(deelname));
		// toon deelnames
		IO.toonStringMetVenster(toonDeelnames(deelname));
	}
	
	public static String toonDeelnemers(Map <String,List<String>> deelname){
		Set <String> quizNamen  = deelname.keySet();
		String quizNaam = IO.leesStringMetVenster("Kies quiznaam uit volgende lijst\n"+quizNamen.toString(), "Deelnemers aan quiz");
		List <String> deelnemers = deelname.get(quizNaam);
		String terug = deelnemers == null?"?????":deelnemers.toString();
		return terug;
	}
	
	public static String toonDeelnames(Map <String,List<String>> deelname){
		Set <Entry<String,List<String>>> entries = deelname.entrySet();
		String deelnemer = IO.leesStringMetVenster("Typ naam deelnemer", "Deelnames aan quiz");
		List <String> deelnames = new ArrayList<String>();
		for (Entry<String,List<String>> entry:entries){
			List <String> deelnemers = (ArrayList<String>)entry.getValue();	
			int index = deelnemers.indexOf(deelnemer);
			if (index > -1){
				deelnames.add(entry.getKey().toString());
			}
		}
		String terug = deelnames == null?"?????":deelnames.toString();
		return terug;
	}		

}
