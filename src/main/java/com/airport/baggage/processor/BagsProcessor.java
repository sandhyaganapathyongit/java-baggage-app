package com.airport.baggage.processor;

import static com.airport.baggage.constant.Constant.BAG_INPUT_REGEX;
import static com.airport.baggage.constant.Constant.EMPTY_SPACE;
import static com.airport.baggage.constant.Constant.ERROR_BAG_SECTION;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.service.AirportService;

public class BagsProcessor extends AbstractProcessor {


    @Override
    public void processInput(String input, AirportService service) throws AirportServiceException {
        validate(input, BAG_INPUT_REGEX, ERROR_BAG_SECTION);
        String[] inputs = input.split(EMPTY_SPACE);
        if (service != null)
            service.addBaggage(inputs[0], inputs[1], inputs[2]);
    }

}
