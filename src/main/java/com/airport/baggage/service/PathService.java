package com.airport.baggage.service;

import java.util.Map;

import com.airport.baggage.service.model.Node;
import com.airport.baggage.service.model.ShortPath;

public interface PathService {
    int ZERO_PATH_LENGTH = 0;

    String PATH_SEPARATOR = " ";

    public ShortPath[][] calculateShortPath(Map<String, Node> nodeMap);
}
