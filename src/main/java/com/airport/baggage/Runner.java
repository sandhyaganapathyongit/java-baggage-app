package com.airport.baggage;

import com.airport.baggage.service.impl.AirportServiceImpl;
import com.airport.baggage.service.impl.ServiceLaunch;

/**
 * Hello world!
 *
 */
public class Runner {
    public static void main(String[] args) {
        System.out.println("Welcome to Denver Airport Baggage Service\n");
        System.out.println("Please Enter each section in below format\n");
        System.out.println("# Section: Conveyor System" + "\n" + "SourceNode DestinationNode TravelTime");
        System.out.println("# Section: Departures" + "\n" + "FlightId FlightGate Destination FligthTime");
        System.out.println("# Section: Bags" + "\n" + "BagNumber EntryPoint FlightId");
        System.out.println("\nNote : Please hit Enter(Empty line) at last to start processing");
        ServiceLaunch launch = new ServiceLaunch(System.in, new AirportServiceImpl());
        launch.processInput();
        launch.printOptimalPath();
    }
}
