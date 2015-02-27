import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;


public class ParserThread extends Thread {

	private GUI gui;
	private volatile boolean running;
	private String searchURL;
	private Thread Animthread;
	
	public ParserThread(GUI gui,String _searchURL) {
		super();
		this.gui = gui;
		this.Animthread=gui.s;
		this.searchURL =_searchURL;
		this.running = true;
		System.out.println("hej");
	}



	public void run(){
		
		
		Journeys journeys = Parser.getJourneys(searchURL);
		gui.s.kill();
		gui.s.interrupt();
	//	gui.s.running=false;
		gui.textArea.setText("");
		for (Journey journey : journeys.getJourneys()) {
			gui.textArea.append(journey.getStartStation()+" - ");
			gui.textArea.append(journey.getEndStation()+"");
			String time = journey.getDepDateTime().get(Calendar.HOUR_OF_DAY)+":"+journey.getDepDateTime().get(Calendar.MINUTE);
			gui.textArea.append(" Avgår " + time +" om "+journey.getTimeToDeparture()+ " minuter. Försenad i "+((journey.getDepTimeDeviation()=="")?"0":journey.getDepTimeDeviation())+" minuter \n");
		} 
		
	   System.out.println("// Stations when searching for stations containing \"Malm\"");
		ArrayList<Station> searchStations = new ArrayList<Station>(); 
		searchStations.addAll(Parser.getStationsFromURL("Malm"));
		for (Station s: searchStations){
			System.out.println(s.getStationName() +" number:" +s.getStationNbr());
		}
		
		
		

	}
	/*
	public Journeys getJourneys(String searchURL){
		
		return Parser.getJourneys(searchURL);
		
	}
	
	 public  List<Station> getStationsFromURL(String searchStart){
		
		return Parser.getStationsFromURL(searchStart);
		
	}*/
	
}
