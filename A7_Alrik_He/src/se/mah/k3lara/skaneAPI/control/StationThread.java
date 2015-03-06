package se.mah.k3lara.skaneAPI.control;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Line;
import se.mah.k3lara.skaneAPI.model.Lines;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;


public class StationThread extends Thread {

	private GUI gui;
	private volatile boolean running;
	private String searchURL;
	private Thread Animthread;
	private Lines lines;
	private Calendar cal;
	private int noticeTime=1;
	
	public StationThread(GUI gui) {
		super();
		this.gui = gui;
		this.Animthread=gui.s;
		//this.searchURL =_searchURL;
		this.running = true;
		cal=Calendar.getInstance();
		System.out.println("hej");
	}



	public void run(){
		while(true){
		cal=Calendar.getInstance();
	//	Journeys journeys = Parser.getJourneys(searchURL);
	/*	for (Journey journey : journeys.getJourneys()) {
			gui.textArea.append(journey.getStartStation()+" - ");
			gui.textArea.append(journey.getEndStation()+"");
			String time = journey.getDepDateTime().get(Calendar.HOUR_OF_DAY)+":"+journey.getDepDateTime().get(Calendar.MINUTE);
			gui.textArea.append(" Departs " + time +" that is in "+journey.getTimeToDeparture()+ " minutes. And it is "+journey.getDepTimeDeviation()+" min late \n");
		} */

	//	ArrayList<Station> searchStations = new ArrayList<Station>();
		/*if(gui.toText.getText().equals("")){

			 lines = Parser.getStationResults(new Station(gui.fromText.getText()));
		searchStations.addAll(Parser.getStationsFromURL(gui.fromText.getText()));
		System.out.println("// Stations when searching for stations containing \""+gui.fromText.getText()+"\"");
		}else{
			 lines = Parser.getStationResults(new Station(gui.toText.getText()));
		searchStations.addAll(Parser.getStationsFromURL(gui.toText.getText()));
		System.out.println("// Stations when searching for stations containing \""+gui.toText.getText()+"\"");
		}*/


//		for (Station s: searchStations){
//			gui.textArea.append(s.getStationName() +" number:" +s.getStationNbr()+"\n");
//		}
//		
		lines = Parser.getStationResults(new Station("80046"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui.s.kill();
	//	gui.s.interrupt();
		//gui.s.running=false;
		gui.towardsTxtArea.setText("");
		for (Line l : lines.getLines()) {

			System.out.println(cal.get(Calendar.MINUTE));
			System.out.println(l.getDepTime().get(Calendar.MINUTE));
			
			if(cal.get(Calendar.HOUR_OF_DAY)==l.getDepTime().get(Calendar.HOUR_OF_DAY) && cal.get(Calendar.MINUTE)+noticeTime>=l.getDepTime().get(Calendar.MINUTE)){
			gui.starsTextArea.append("*\n");
			if((l.getDepTime().get(Calendar.MINUTE)-cal.get(Calendar.MINUTE))<=0){
				gui.departTextArea.append("0 min\n");
			}else{
			gui.departTextArea.append(l.getDepTime().get(Calendar.MINUTE)-cal.get(Calendar.MINUTE)+" min\n" );
			}
			}else{
			gui.starsTextArea.append("\n");
			gui.departTextArea.append(String.format("%02d", l.getDepTime().get(Calendar.HOUR_OF_DAY)) +":"+String.format("%02d",l.getDepTime().get(Calendar.MINUTE)) +"\n" );
			}
			gui.stationTextArea.append(l.getLine()+"\n");
			gui.towardsTxtArea.append(l.getDestination()+"\n");
			
		}
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui.s.running=true;
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
