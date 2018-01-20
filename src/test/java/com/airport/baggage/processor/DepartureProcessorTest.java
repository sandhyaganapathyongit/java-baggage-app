package com.airport.baggage.processor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.service.impl.AirportServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DepartureProcessorTest {

    @InjectMocks
    private DepartureProcessor processor;

    @Test
    public void testprocessInput_ThrowsNoException_whenPassedValidInput() throws Exception, AirportServiceException {
        processor.processInput("UA10 A1 MIA 08:00", new AirportServiceImpl());
        assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testprocessInput_ThrowsException_whenPassedInvalidInput() throws Exception, AirportServiceException {
        processor.processInput("xby adsa", new AirportServiceImpl());
    }

    @Test(expected = Exception.class)
    public void testprocessInput_ThrowsException_whenPassedNullInput() throws Exception, AirportServiceException {
        processor.processInput(null, new AirportServiceImpl());
    }

    @Test(expected = Exception.class)
    public void testprocessInput_ThrowsException_whenPassedEmptyInput() throws Exception, AirportServiceException {
        processor.processInput("", new AirportServiceImpl());
    }

    @Test
    public void testprocessInput_ThrowsNoException_whenPassedInvalidHourInput()
            throws Exception, AirportServiceException {
        processor.processInput("UA10 A1 MIA 24:00", new AirportServiceImpl());
    }

    @Test
    public void testprocessInput_ThrowsNoException_whenPassedInvalidMinutesInput()
            throws Exception, AirportServiceException {
        processor.processInput("UA10 A1 MIA 03:60", new AirportServiceImpl());
    }

}
