package view;

public class Menu {
	
	private String menuTekst;
	private int stopWaarde;
	public Menu (String ... menuOpties){
		menuTekst = "";
		int i = 0;
		for (i =0;i < menuOpties.length;i++){
			menuTekst += (i+1)+"."+menuOpties[i]+"\n";
		}
		stopWaarde = i+1;
		menuTekst += stopWaarde+"."+"Stoppen"+"\n\n";
		menuTekst += "Maak je keuze (1-"+stopWaarde+")";
				
	}
	
	public int getMenuKeuze(){
		return IO.leesIntMetVenster(menuTekst,"Menu");
	}
	
	public int getStopWaarde(){
		return stopWaarde;
	}
}
