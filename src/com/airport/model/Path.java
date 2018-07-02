package com.airport.model;

/*
 * Connectivity between Conveyour belts are represented as Path which is nothing but edges 
 */
public class Path {

	private Vertex src;
	private Vertex dest;
	private int time;

	public Path(Vertex s, Vertex d, int t) {
		this.src = s;
		this.dest = d;
		this.time = t;
	}
	
	public Path(String s, String d, Integer t) {
        this.src = new Vertex(s);
        this.dest = new Vertex(d);
        this.time = t;
    }
	

	public Vertex getSource() {
		return src;
	}

	public void setSource(Vertex source) {
		this.src = source;
	}

	public Vertex getDestination() {
		return dest;
	}

	public void setDestination(Vertex destination) {
		this.dest = destination;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
