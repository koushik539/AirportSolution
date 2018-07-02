package com.airport.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.airport.model.Path;
import com.airport.model.Vertex;

/*
 * This class contains the logic to call the routing logic and format the output
 */
public class AirportBaggageImpl implements AirportBaggageInterface{

	
	private final String WORDSEPARATION = " ";
	
	Map<String,RoutingUtil> visited = new ConcurrentHashMap<>();
	
	@Override
	public String shortestPath(String start, String destination, List<Path> pathlist) {
		
		RoutingUtil graph;
		if(visited.containsKey(start)){
			graph = visited.get(start);
		}else{
			graph = new RoutingUtil(pathlist);
			graph.dijkstra(start);
			visited.put(start, graph);
			
		}
		List<Vertex> shortestPathList = graph.getShortestPath(destination);
		return generateOutput(shortestPathList);
	}

	/*
	 * Logic to format the ouptut as per requirement
	 */
	private String generateOutput(List<Vertex> shortestPathList) {
		
		StringBuffer buf = new StringBuffer();
		for(Vertex n : shortestPathList){
			buf.append(n.getId()).append(WORDSEPARATION);
		}
		buf.append(": ").append(shortestPathList.get(shortestPathList.size()-1).getMinTimeToReachThisNode());
		return buf.toString();
	}

}
