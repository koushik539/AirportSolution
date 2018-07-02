package com.airport.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.airport.model.Path;
import com.airport.model.Vertex;


public class RoutingUtil {

	
	private  Map<String,Vertex> graph;

	public RoutingUtil(List<Path> pathes) {
		
		this.graph = new HashMap<String, Vertex>();
		
		for(Path e : pathes){
			
			if(!graph.containsKey(e.getSource().getId())){
				graph.put(e.getSource().getId(), new Vertex(e.getSource().getId()));
			}
			if(!graph.containsKey(e.getDestination().getId())){
				graph.put(e.getDestination().getId(), new Vertex(e.getDestination().getId()));
			}
		}
		
		for(Path e : pathes){
			Vertex node = graph.get(e.getSource().getId());
			Map<Vertex, Integer> neighbouringNodes = node.getNeighbouringNodes();
			neighbouringNodes.put(graph.get(e.getDestination().getId()), e.getTime());
		}
	}
	
	public void dijkstra(String source){
		if(!graph.containsKey(source)){
			throw new IllegalArgumentException("The graph does not contain vertex named "+source); 
		}
		
		Vertex node = graph.get(source);
		NavigableSet<Vertex> queue = new TreeSet<>();
		
		for(Vertex n : graph.values()){
			n.setParentVertex(n == node ? node : null);
			n.setMinTimeToReachThisNode(n == node ? 0 : Integer.MAX_VALUE);
			queue.add(n);
		}
		
		routing(queue);
	}
	
	/*
	 * Finds shortest path between nodes by relaxing cost of each and every edge
	 * Using Dijkstras way of solving shortest paths
	 */
	private void routing(NavigableSet<Vertex> queue){
		Vertex source,neighbour;
		
		while(!queue.isEmpty()){
			source = queue.pollFirst();
			
			if(source.getMinTimeToReachThisNode() == Integer.MAX_VALUE){
				break;
			}
			
			for(Map.Entry<Vertex, Integer> nodes : graph.get(source.getId()).getNeighbouringNodes().entrySet()){
				neighbour = nodes.getKey();
				
				int anothertime = source.getMinTimeToReachThisNode() + nodes.getValue();
				if(anothertime < neighbour.getMinTimeToReachThisNode()){
					queue.remove(neighbour);
					neighbour.setMinTimeToReachThisNode(anothertime);
					neighbour.setParentVertex(source);
					queue.add(neighbour);
				}
			}
		}
	}
	
	/*
	 * call to the vertex class method which provides the required path and output cost
	 */
	public List<Vertex> getShortestPath(String endName){
		if(!graph.containsKey(endName)){
			throw new IllegalArgumentException("Graph do not have end vertex "+endName);
		}
		return graph.get(endName).getShortestPathTo();
	}
}
