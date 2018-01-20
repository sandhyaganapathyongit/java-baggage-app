
package com.airport.baggage.constant;

public class Constant {

    private Constant() {

    }

    public static final String CONVEYOR_SYSTEM_SECTION = "Conveyor System";

    public static final String DEPARTURES_SECTION = "Departures";

    public static final String BAGS_SECTION = "Bags";

    public static final String SECTION_PREFIX = "# Section:";

    public static final String ARRIVAL_NODE = "ARRIVAL";

    public static final String BAGGAGE_CLAIM_NODE = "BaggageClaim";
    
    public static final String EMPTY_SPACE = " ";

    public static final String DEPARTURE_INPUT_REGEX = "\\w+\\s\\w+\\s\\w+\\s([0-2][0-3]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";

    public static final String BAG_INPUT_REGEX = "\\w+\\s\\w+\\s\\w+";

    public static final String CONVEYOR_INPUT_REGEX = "\\w+\\s\\w+\\s\\d+";

    public static final String ERROR_DEPARTMENT_SECTION = "DEPARTMENT_SECTION";

    public static final String ERROR_CONVEYOR_SECTION = "CONVEYOR_SECTION";

    public static final String ERROR_BAG_SECTION = "BAG_SECTION";

    public static final String[] VALID_SECTION = new String[] {BAGS_SECTION, CONVEYOR_SYSTEM_SECTION,
            DEPARTURES_SECTION};
}
