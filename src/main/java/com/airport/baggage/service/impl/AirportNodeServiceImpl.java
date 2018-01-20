package com.airport.baggage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.airport.baggage.exception.NodeServiceException;
import com.airport.baggage.service.NodeService;
import com.airport.baggage.service.PathService;
import com.airport.baggage.service.model.Node;
import com.airport.baggage.service.model.ShortPath;

public class AirportNodeServiceImpl implements NodeService {
    private Map<String, Node> nodeMap = null;

    private int nodeIndex = 0;

    private ShortPath[][] shortPaths;

    private PathService shortPathService;

    public AirportNodeServiceImpl() {
        this(new ShortPathService());
    }

    public AirportNodeServiceImpl(PathService pathService) {
        nodeMap = new HashMap<>();
        shortPathService = pathService;
    }

    private void createNode(String nodeName, String destNodeName, int duration) {
        if (nodeMap.containsKey(nodeName))
            nodeMap.get(nodeName).addConnectedNode(destNodeName, duration);
        else
            nodeMap.put(nodeName, new Node(nodeName, destNodeName, nodeIndex++, duration));
    }

    public void addNode(String srcNode, String destNode, int duration) throws NodeServiceException {
        if (StringUtils.isNotBlank(srcNode) && StringUtils.isNotBlank(destNode)
                && !srcNode.equalsIgnoreCase(destNode)) {
            createNode(srcNode, destNode, duration);
            createNode(destNode, srcNode, duration);
        } else
            throw new NodeServiceException(ADD_NODE_ERROR);
    }

    @Override
    public void calculateShortPath() {
        if (shortPathService != null)
            shortPaths = shortPathService.calculateShortPath(nodeMap);
    }

    private int getNodeIndex(String node) {
        return nodeMap.get(node).getIndex();
    }

    @Override
    public ShortPath getShortPath(String srcNode, String destNode) {
        if (shortPaths != null)
            return shortPaths[getNodeIndex(srcNode)][getNodeIndex(destNode)];
        return null;
    }

    @Override
    public Node getNode(String node) {
        return nodeMap.get(node);
    }

    @Override
    public boolean hasValidData() {
        if (nodeMap != null && nodeMap.size() > 0)
            return true;
        return false;
    }
}
