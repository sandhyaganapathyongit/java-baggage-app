package com.airport.baggage.service;

import com.airport.baggage.exception.NodeServiceException;
import com.airport.baggage.service.model.Node;
import com.airport.baggage.service.model.ShortPath;

public interface NodeService {
    String ADD_NODE_ERROR = "Error in adding node : Source and Destination Node should not be null/empty/same";

    void addNode(String nodeName, String destNodeName, int hours) throws NodeServiceException;

    Node getNode(String node);

    ShortPath getShortPath(String srcNode, String destNode);

    void calculateShortPath();

    boolean hasValidData();
}
