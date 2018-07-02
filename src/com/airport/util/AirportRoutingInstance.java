package com.airport.util;

/*
 * This class acts as a Factory class for actual routing implementation
 */
public class AirportRoutingInstance {

	public static AirportBaggageImpl createRoutingInstance(){
		return new AirportBaggageImpl();
	}
}
