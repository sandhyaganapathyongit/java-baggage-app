package com.airport.baggage.processor;

import static com.airport.baggage.constant.Constant.CONVEYOR_INPUT_REGEX;
import static com.airport.baggage.constant.Constant.EMPTY_SPACE;
import static com.airport.baggage.constant.Constant.ERROR_CONVEYOR_SECTION;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.service.AirportService;

public class ConveyorProseccor extends AbstractProcessor {

    @Override
    public void processInput(String input, AirportService service) throws AirportServiceException {
        validate(input, CONVEYOR_INPUT_REGEX, ERROR_CONVEYOR_SECTION);

        String[] inputs = input.split(EMPTY_SPACE);
        int hour = Integer.parseInt(inputs[2]);
        if (service != null)
            service.addNode(inputs[0], inputs[1], hour);
    }
}
