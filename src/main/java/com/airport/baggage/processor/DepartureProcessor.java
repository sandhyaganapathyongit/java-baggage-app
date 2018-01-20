package com.airport.baggage.processor;

import static com.airport.baggage.constant.Constant.DEPARTURE_INPUT_REGEX;
import static com.airport.baggage.constant.Constant.EMPTY_SPACE;
import static com.airport.baggage.constant.Constant.ERROR_DEPARTMENT_SECTION;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.service.AirportService;

public class DepartureProcessor extends AbstractProcessor {

    @Override
    public void processInput(String input, AirportService service) throws AirportServiceException {
        validate(input, DEPARTURE_INPUT_REGEX, ERROR_DEPARTMENT_SECTION);
        String[] inputs = input.split(EMPTY_SPACE);
        if (service != null)
            service.addDeparture(inputs[0], inputs[1], inputs[2], inputs[3]);
    }

}
