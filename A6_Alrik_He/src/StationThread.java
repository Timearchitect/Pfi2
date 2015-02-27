import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;


public class StationThread extends Thread {

	private GUI gui;
	private volatile boolean running;
	private String searchURL;
	private Thread Animthread;
	
	public StationThread(GUI gui) {
		super();
		this.gui = gui;
		this.Animthread=gui.s;
		//this.searchURL =_searchURL;
		this.running = true;
		System.out.println("hej");
	}



	public void run(){
		
	//	Journeys journeys = Parser.getJourneys(searchURL);
	/*	for (Journey journey : journeys.getJourneys()) {
			gui.textArea.append(journey.getStartStation()+" - ");
			gui.textArea.append(journey.getEndStation()+"");
			String time = journey.getDepDateTime().get(Calendar.HOUR_OF_DAY)+":"+journey.getDepDateTime().get(Calendar.MINUTE);
			gui.textArea.append(" Departs " + time +" that is in "+journey.getTimeToDeparture()+ " minutes. And it is "+journey.getDepTimeDeviation()+" min late \n");
		} */

		ArrayList<Station> searchStations = new ArrayList<Station>();
		if(gui.toText.getText().equals("")){
			//"Malm"
		searchStations.addAll(Parser.getStationsFromURL(gui.fromText.getText()));
		System.out.println("// Stations when searching for stations containing \""+gui.fromText.getText()+"\"");
		}else{
		searchStations.addAll(Parser.getStationsFromURL(gui.toText.getText()));
		System.out.println("// Stations when searching for stations containing \""+gui.toText.getText()+"\"");
		}
		gui.s.kill();
		gui.s.interrupt();
	//	gui.s.running=false;
		gui.textArea.setText("");
		for (Station s: searchStations){
			gui.textArea.append(s.getStationName() +" number:" +s.getStationNbr()+"\n");
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
