package com.airport.baggage.service.impl;

import java.util.List;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.exception.BaggageServiceException;
import com.airport.baggage.exception.DepartureServiceException;
import com.airport.baggage.exception.NodeServiceException;
import com.airport.baggage.service.AirportService;
import com.airport.baggage.service.BaggageService;
import com.airport.baggage.service.DepartureService;
import com.airport.baggage.service.NodeService;

public class AirportServiceImpl implements AirportService {
    private BaggageService baggageService;

    private DepartureService departureService;

    private NodeService nodeService;

    public AirportServiceImpl() {
        this(new BaggageDetailService(), new FlightDepartureService(), new AirportNodeServiceImpl());
    }

    public AirportServiceImpl(BaggageService baggageService, DepartureService departureService,
            NodeService nodeService) {
        this.baggageService = baggageService;
        this.departureService = departureService;
        this.nodeService = nodeService;
    }

    @Override
    public void addNode(String srcNode, String destNode, int duration) throws AirportServiceException {
        try {
            nodeService.addNode(srcNode, destNode, duration);
        } catch (NodeServiceException e) {
            throw new AirportServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void addDeparture(String flightId, String gate, String destination, String flightTime)
            throws AirportServiceException {
        try {
            departureService.addDeparture(flightId, gate, destination, flightTime);
        } catch (DepartureServiceException e) {
            throw new AirportServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void addBaggage(String baggageId, String entryPoint, String flightId) throws AirportServiceException {
        try {
            baggageService.addBaggage(baggageId, entryPoint, flightId);
        } catch (BaggageServiceException e) {
            throw new AirportServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getAllBaggageShortestPath() throws AirportServiceException {
        List<String> baggageShortPaths = null;
        try {
            nodeService.calculateShortPath();
            baggageService.assignShortPathToBaggage(nodeService, departureService);
            baggageShortPaths = baggageService.getAllBaggageShortestPath();
        } catch (BaggageServiceException e) {
            throw new AirportServiceException(e.getMessage(), e);
        }
        return baggageShortPaths;
    }

    @Override
    public boolean hasValidData() {
        if (nodeService.hasValidData() && departureService.hasValidData() && baggageService.hasValidData())
            return true;
        return false;
    }

    public BaggageService getBaggageService() {
        return baggageService;
    }

    public void setBaggageService(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    public DepartureService getDepartureService() {
        return departureService;
    }

    public void setDepartureService(DepartureService departureService) {
        this.departureService = departureService;
    }

    public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

}
