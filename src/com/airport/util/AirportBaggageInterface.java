package com.airport.util;

import java.util.List;

import com.airport.model.Path;


public interface AirportBaggageInterface {

	public String shortestPath(String start, String destination, List<Path> pathlist); 
}
