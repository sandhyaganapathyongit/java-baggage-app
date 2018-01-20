package com.airport.baggage.processor;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FactoryTest {

    @InjectMocks
    private Factory factory;

    @Test
    public void testgetInputHandler_ReturnNull_WhenPassedNull() {
        AbstractProcessor AbstractProcessor = factory.getInputHandler(null);
        assertNull(AbstractProcessor);
    }

    @Test
    public void testgetInputHandler_ReturnNull_WhenPassedEmptyString() {
        AbstractProcessor AbstractProcessor = factory.getInputHandler("");
        assertNull(AbstractProcessor);
    }

    @Test
    public void testgetInputHandler_Return_BagsAbstractProcessor_WhenPassedBags() {
        AbstractProcessor AbstractProcessor = factory.getInputHandler("Bags");
        assertTrue(AbstractProcessor instanceof BagsProcessor);
    }

    @Test
    public void testgetInputHandler_Return_ConveyorAbstractProcessor_WhenPassedConveyorSystem() {
        AbstractProcessor AbstractProcessor = factory.getInputHandler("Conveyor System");
        assertTrue(AbstractProcessor instanceof ConveyorProseccor);
    }

    @Test
    public void testgetInputHandler_Return_BagsAbstractProcessor_WhenPassedDepartures() {
        AbstractProcessor AbstractProcessor = factory.getInputHandler("Departures");
        assertTrue(AbstractProcessor instanceof DepartureProcessor);
    }

    @Test
    public void testgetInputHandler_Return_BagsAbstractProcessor_WhenPassedOtherValues() {
        AbstractProcessor AbstractProcessor = factory.getInputHandler("xyz");
        assertNull(AbstractProcessor);
    }
}
