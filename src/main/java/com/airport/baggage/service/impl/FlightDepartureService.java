package com.airport.baggage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.airport.baggage.exception.DepartureServiceException;
import com.airport.baggage.service.DepartureService;
import com.airport.baggage.service.model.FlightDepartureDetail;

public class FlightDepartureService implements DepartureService {
    private Map<String, FlightDepartureDetail> departureDetails;

    public FlightDepartureService() {
        departureDetails = new HashMap<>();
    }

    @Override
    public void addDeparture(String flightId, String gate, String destination, String flightTime)
            throws DepartureServiceException {
        if (!StringUtils.isEmpty(flightId) && !StringUtils.isEmpty(gate) && !StringUtils.isEmpty(destination)
                && !StringUtils.isEmpty(flightTime)) {
            FlightDepartureDetail flightDepartureDetail = new FlightDepartureDetail(flightId, gate, destination,
                    flightTime);
            departureDetails.put(flightId, flightDepartureDetail);
        } else {
            throw new DepartureServiceException(ADD_DEPARTURE_ERROR_MSG);
        }
    }

    @Override
    public FlightDepartureDetail getFlightDepartureDetail(String flightId) {
        return departureDetails.get(flightId);
    }

    @Override
    public boolean hasValidData() {
        if (departureDetails != null && departureDetails.size() > 0)
            return true;
        return false;
    }

}
