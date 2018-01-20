package com.airport.baggage.processor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.service.AirportService;

public abstract class AbstractProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract void processInput(String input, AirportService service) throws AirportServiceException;

    protected void validate(String input, String regex, String section) {
        if (StringUtils.isBlank(input) && !input.matches(regex)) {
            logger.error("Incorrect input in {}", section);
            throw new RuntimeException("Incorrect input");
        }
    }
}
