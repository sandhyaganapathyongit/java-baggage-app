package com.airport.baggage.service;

import java.util.List;

import com.airport.baggage.exception.AirportServiceException;

public interface AirportService {
    void addNode(String srcNode, String destNode, int hours) throws AirportServiceException;

    void addDeparture(String flightId, String gate, String destination, String flightTime)
            throws AirportServiceException;

    void addBaggage(String baggageId, String entryPoint, String flightId) throws AirportServiceException;

    List<String> getAllBaggageShortestPath() throws AirportServiceException;

    boolean hasValidData();
}
