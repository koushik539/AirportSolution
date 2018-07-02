package com.airport.model;

/*
 * Actual Baggage which is represented as Luggage
 */
		
public class Luggage {

	private String id;
	private String entry;
	private String destination;
	
	public Luggage(String id, String entryPointOfBag, String flightToBeSent) {
		super();
		this.id = id;
		this.entry = entryPointOfBag;
		this.destination = flightToBeSent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntryPointOfBag() {
		return entry;
	}

	public void setEntryPointOfBag(String entryPointOfBag) {
		this.entry = entryPointOfBag;
	}

	public String getFlightToBeSent() {
		return destination;
	}

	public void setFlightToBeSent(String flightToBeSent) {
		this.destination = flightToBeSent;
	}
}
