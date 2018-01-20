package com.airport.baggage.service.impl;

import static com.airport.baggage.constant.Constant.SECTION_PREFIX;
import static com.airport.baggage.constant.Constant.VALID_SECTION;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.airport.baggage.exception.AirportServiceException;
import com.airport.baggage.processor.Factory;
import com.airport.baggage.processor.AbstractProcessor;
import com.airport.baggage.service.AirportService;

public class ServiceLaunch {

    private Scanner scanner;

    private AirportService airportService;

    private Factory handlerFactory;

    private String parsingSection;

    public ServiceLaunch() {
        this(System.in, new AirportServiceImpl());
    }

    public ServiceLaunch(InputStream stream, AirportService airportService) {
        scanner = new Scanner(stream);
        this.airportService = airportService;
        handlerFactory = new Factory();
    }

    public void processInput() {
        System.out.println("\nPlease start entering Section inputs :");
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (StringUtils.isEmpty(nextLine)) {
                break;
            }
            if (StringUtils.startsWith(nextLine.toLowerCase(), SECTION_PREFIX.toLowerCase())) {
                setParsingSection(StringUtils.substringAfter(nextLine, SECTION_PREFIX));
                continue;
            }
            try {
                processSectionInput(nextLine);
            } catch (AirportServiceException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
    }

    public void printOptimalPath() {
        try {
            if (airportService.hasValidData()) {
                List<String> shortPaths = airportService.getAllBaggageShortestPath();
                System.out.println("Baggage optimal route\n");
                shortPaths.forEach((path) -> System.out.println(path));
            } else {
                System.out.println("No valid data found to calculate optimal route");
            }
        } catch (AirportServiceException e) {
            System.out.println(" Error : " + e.getMessage());
        }
    }

    private boolean isValidSection() {
        String section = getParsingSection().trim();

        if (null != section && ArrayUtils.contains(VALID_SECTION, section)) {
            return true;
        }
        return false;
    }

    private void processSectionInput(String input) throws AirportServiceException {

        String section = getParsingSection().trim();

        if (StringUtils.isEmpty(section) || !isValidSection()) {
            System.out.println("Not valid section found, ignoring input");
            return;
        }
        AbstractProcessor handler = handlerFactory.getInputHandler(section);
        if (handler != null)
            handler.processInput(input, airportService);
        else
            System.out.println("No valid input handler found");
    }

    /**
     * @return the parsingSection
     */
    public String getParsingSection() {
        return parsingSection;
    }

    /**
     * @param parsingSection the parsingSection to set
     */
    public void setParsingSection(String parsingSection) {
        this.parsingSection = parsingSection;
    }

}
