package com.airport.baggage.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.service.BaggageService;
import com.airport.baggage.service.DepartureService;
import com.airport.baggage.service.NodeService;

public class AirportServiceImplTest {

    private AirportServiceImpl airportService;

    private BaggageService baggageService;

    private DepartureService departureService;

    private NodeService nodeService;

    @Before
    public void before() {

        baggageService = new BaggageDetailService();
        nodeService = new AirportNodeServiceImpl();
        departureService = new FlightDepartureService();

        airportService = new AirportServiceImpl(baggageService, departureService, nodeService);

    }

    @Test(expected = AirportServiceException.class)
    public void testAddNode_throwsException_whenPassedInputsNull() throws AirportServiceException {
        airportService.addNode(null, null, 0);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddNode_throwsException_whenPassedInputsEmpty() throws AirportServiceException {
        airportService.addNode("", "", 0);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddNode_throwsException_whenPassedSrcNodeNull() throws AirportServiceException {
        airportService.addNode(null, "DestNode", 0);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddNode_throwsException_whenPassedSrcNodeEmpty() throws AirportServiceException {
        airportService.addNode("", "DestNode", 0);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddNode_throwsException_whenPassedDestNodeNull() throws AirportServiceException {
        airportService.addNode("srcNode", null, 0);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddNode_throwsException_whenPassedDestNodeEmpty() throws AirportServiceException {
        airportService.addNode("srcNode", "", 0);
    }

    @Test
    public void testAddNode_throwsNoException_whenPassedValidInput() throws AirportServiceException {
        airportService.addNode("srcNode", "DestNode", 0);
        assertTrue(true);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedInputsNull() throws AirportServiceException {
        airportService.addDeparture(null, null, null, null);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedInputsEmpty() throws AirportServiceException {
        airportService.addDeparture("", "", "", "");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedFlightIdNull() throws AirportServiceException {
        airportService.addDeparture(null, "Gate", "Destn", "00:33");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedFlightIdEmpty() throws AirportServiceException {
        airportService.addDeparture("", "Gate", "Destn", "00:33");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedGateNull() throws AirportServiceException {
        airportService.addDeparture("Flight", null, "Destn", "00:33");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedGateEmpty() throws AirportServiceException {
        airportService.addDeparture("Flight", "", "Destn", "00:33");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedDestnNull() throws AirportServiceException {
        airportService.addDeparture("Flight", "Gate", null, "00:33");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedDestnEmpty() throws AirportServiceException {
        airportService.addDeparture("Flight", "Gate", "", "00:33");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedTimeNull() throws AirportServiceException {
        airportService.addDeparture("Flight", "Gate", "Destn", null);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddDeparture_throwsException_whenPassedTimeEmpty() throws AirportServiceException {
        airportService.addDeparture("Flight", "Gate", "Destn", "");
    }

    @Test
    public void testAddDeparture_throwsNoException_whenPassedValidInput() throws AirportServiceException {
        airportService.addDeparture("Flight", "Gate", "Destn", "03:44");
        assertTrue(true);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedInputsNull() throws AirportServiceException {
        airportService.addBaggage(null, null, null);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedInputsEmpty() throws AirportServiceException {
        airportService.addBaggage("", "", "");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedBagIdNull() throws AirportServiceException {
        airportService.addBaggage(null, "EntryGate", "Flight");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedBagIdEmpty() throws AirportServiceException {
        airportService.addBaggage("", "EntryGate", "Flight");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedEntryPointNull() throws AirportServiceException {
        airportService.addBaggage("Bag1", null, "Flight");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedEntryPointEmpty() throws AirportServiceException {
        airportService.addBaggage("Bag1", "", "Flight");
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedFlightNull() throws AirportServiceException {
        airportService.addBaggage("Bag1", "EntryGate", null);
    }

    @Test(expected = AirportServiceException.class)
    public void testAddBaggage_throwsException_whenPassedFlightEmpty() throws AirportServiceException {
        airportService.addBaggage("Bag1", "EntryGate", "");
    }

    @Test
    public void testAddBaggage_throwsNoException_whenPassedValidInput() throws AirportServiceException {
        airportService.addBaggage("Bag1", "EntryGate", "Flight");
        assertTrue(true);
    }

    @Test
    public void testHasValidData_returnsFalse_whenAddedNoInput() {
        airportService = new AirportServiceImpl();
        assertFalse(airportService.hasValidData());
    }

    @Test
    public void testHasValidData_returnsTrue_whenAddedValidInput() throws AirportServiceException {
        airportService = new AirportServiceImpl();
        airportService.addNode("srcNode", "DestNode", 1);
        airportService.addDeparture("Flight", "Gate", "Destn", "03:44");
        airportService.addBaggage("Bag1", "EntryGate", "Flight");
        assertTrue(airportService.hasValidData());
    }

    public void testGetAllBaggageShortestPath_returnsEmptyList_whenNoInputsAdded() throws AirportServiceException {
        airportService = new AirportServiceImpl();
        List<String> bagsLst = airportService.getAllBaggageShortestPath();
        assertEquals(0, bagsLst.size());
    }

    public void testGetAllBaggageShortestPath_returnsNonZeroList_whenInputsAdded() throws AirportServiceException {
        airportService = new AirportServiceImpl();
        List<String> bagsLst = airportService.getAllBaggageShortestPath();
        airportService.addNode("srcNode", "DestNode", 1);
        airportService.addDeparture("Flight", "Gate", "Destn", "03:44");
        airportService.addBaggage("Bag1", "EntryGate", "Flight");
        assertEquals(1, bagsLst.size());
    }
}
