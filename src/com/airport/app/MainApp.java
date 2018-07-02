package com.airport.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.airport.model.Luggage;
import com.airport.model.Path;
import com.airport.util.AirportBaggageImpl;
import com.airport.util.AirportRoutingInstance;

/*
 * Entry point for this Solution
 * Run this class and enter complete when your input is completed
 */
public class MainApp {
	
	private final static String HEADER="# Section:";
	private final static String STARTINGPOINT ="ARRIVAL";
    private final static String CLAIM ="BaggageClaim";

	public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		System.out.println("Please enter input and enter complete : \n");
		List<Path> edges = parseInputGraph(sc);
		AirportBaggageImpl algo = AirportRoutingInstance.createRoutingInstance();
		
		Map<String,String> departuresMap=parseInputDepartures(sc); 
		List<Luggage> luggage = parseInputBgs(sc);
		sc.close();
		for(Luggage bag : luggage){
			String bagId = bag.getId();
			String bagentry = bag.getEntryPointOfBag();
			String flight = bag.getFlightToBeSent();
			
			String destination;
			if(flight.equals(STARTINGPOINT)){
				destination = CLAIM; 
			}else{
				destination = departuresMap.get(flight);
			}
			String path = algo.shortestPath(bagentry, destination, edges);
			System.out.println(bagId+" "+path);
			
		}
	}

	private static List<Luggage> parseInputBgs(Scanner sc) {
		String strings ;
        List<Luggage> bags= new ArrayList<>();
        do{
            strings = sc.nextLine();
            String [] parts = strings.trim().split("\\s+");
            if(parts.length >=3){
                Luggage bag= new Luggage(parts[0],parts[1],parts[2]);
                bags.add(bag);
            }else{
                sc.close();
                break;
            }
        }while(sc.hasNextLine());
        return bags;
    }	

	private static Map<String, String> parseInputDepartures(Scanner sc) {
		String next  = sc.nextLine();
		Map<String,String> departure = new HashMap<>();
		while(!next.startsWith(HEADER)){
			String [] strings = next.trim().split("\\s+");
			if(strings.length >= 2){
				departure.put(strings[0],strings[1]);
            }else{
                throw new IllegalArgumentException("Illegal arguments or inputs.");
            }
            next=sc.nextLine();
		}
		return departure;
	}

	private static List<Path> parseInputGraph(Scanner sc) {
		
		String next = sc.nextLine();
		List<Path> edges = new ArrayList<>();
		next = sc.nextLine();
		while(!next.startsWith(HEADER)){
			String[] strings = next.trim().split("\\s+");
			if(strings.length >= 3){
				Path edge = new Path(strings[0], strings[1], Integer.valueOf(strings[2]));
				edges.add(edge);
				
				Path reverseEdge = new Path(strings[1],strings[0],Integer.valueOf(strings[2]));
                edges.add(reverseEdge);
			}else{
				throw new IllegalArgumentException("Wrong input data is given");
			}
			next = sc.nextLine();
		}
		return edges;
		

	}

}
