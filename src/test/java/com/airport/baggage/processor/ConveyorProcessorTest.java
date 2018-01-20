package com.airport.baggage.processor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.service.impl.AirportServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ConveyorProcessorTest {

    @InjectMocks
    private ConveyorProseccor processor;

    @Test(expected = Exception.class)
    public void testprocessInput_ThrowsException_whenPassedInvalidInput() throws AirportServiceException {
        processor.processInput("xby adsa", new AirportServiceImpl());
    }

    @Test(expected = Exception.class)
    public void testprocessInput_ThrowsException_whenPassedNullInput() throws AirportServiceException {
        processor.processInput(null, new AirportServiceImpl());
    }

    @Test(expected = Exception.class)
    public void testprocessInput_ThrowsException_whenPassedEmptyInput() throws AirportServiceException {
        processor.processInput("", new AirportServiceImpl());
    }

    @Test(expected = Exception.class)
    public void testprocessInput_ThrowsException_whenPassedInvalidHourInput() throws AirportServiceException {
        processor.processInput("Concourse_A_Ticketing A5 five", new AirportServiceImpl());
    }

    @Test
    public void testprocessInput_ThrowsNoException_whenPassedValidInput() throws AirportServiceException {
        processor.processInput("Concourse_A_Ticketing A5 5", new AirportServiceImpl());
        assertTrue(true);
    }
}
