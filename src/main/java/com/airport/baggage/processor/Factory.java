package com.airport.baggage.processor;

import com.airport.baggage.constant.Constant;

public class Factory {

    public Factory() {

    }

    public AbstractProcessor getInputHandler(String section) {
        if (Constant.BAGS_SECTION.equalsIgnoreCase(section))
            return new BagsProcessor();
        if (Constant.DEPARTURES_SECTION.equalsIgnoreCase(section))
            return new DepartureProcessor();
        if (Constant.CONVEYOR_SYSTEM_SECTION.equalsIgnoreCase(section))
            return new ConveyorProseccor();
        return null;
    }
}