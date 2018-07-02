package com.airport.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Destinations or A1...A10 all are represented as Vertex
 */
public class Vertex implements Comparable<Vertex>{

	
	private String id;
	private int timeRequired = Integer.MAX_VALUE;
	private Vertex parentVertex = null;
	private Map<Vertex,Integer> adjacentneighbours = new HashMap<>();
	
	public Vertex(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMinTimeToReachThisNode() {
		return timeRequired;
	}

	public void setMinTimeToReachThisNode(int minTimeToReachThisNode) {
		this.timeRequired = minTimeToReachThisNode;
	}

	public Vertex getParentVertex() {
		return parentVertex;
	}

	public void setParentVertex(Vertex parentVertex) {
		this.parentVertex = parentVertex;
	}

	public Map<Vertex, Integer> getNeighbouringNodes() {
		return adjacentneighbours;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Vertex other = (Vertex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setNeighbouringNodes(Map<Vertex, Integer> adjacentneighbours) {
		this.adjacentneighbours = adjacentneighbours;
	}

	@Override
	public int compareTo(Vertex o) {
		
		if (timeRequired == o.timeRequired)
            return id.compareTo(o.id);

        return Integer.compare(timeRequired, o.timeRequired);
	}
	
	public List<Vertex> getShortestPathTo(){
		
		List<Vertex> path = new ArrayList<>();
		path.add(this);
		Vertex node = this.getParentVertex();
		
		while(node != null && !path.contains(node)){
			path.add(node);
			node = node.getParentVertex();
		}
		
		Collections.reverse(path);
		return path;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", minTimeToReachThisNode=" + timeRequired + "]";
	}
	
}
