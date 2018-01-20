package com.airport.baggage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.airport.baggage.constant.Constant;
import com.airport.baggage.exception.BaggageServiceException;
import com.airport.baggage.service.BaggageService;
import com.airport.baggage.service.DepartureService;
import com.airport.baggage.service.NodeService;
import com.airport.baggage.service.model.BaggageDetail;
import com.airport.baggage.service.model.FlightDepartureDetail;
import com.airport.baggage.service.model.ShortPath;

public class BaggageDetailService implements BaggageService {

    private Map<String, BaggageDetail> baggageDetails;

    private List<String> baggageOrder;

    public BaggageDetailService() {
        baggageDetails = new HashMap<>();
        baggageOrder = new ArrayList<>();
    }

    @Override
    public void addBaggage(String baggageId, String entryPoint, String flightId) throws BaggageServiceException {
        if (!StringUtils.isEmpty(baggageId) && !StringUtils.isEmpty(entryPoint) && !StringUtils.isEmpty(flightId)) {
            BaggageDetail baggageDetail = new BaggageDetail(baggageId, entryPoint, flightId, null);
            baggageDetails.put(baggageId, baggageDetail);
            baggageOrder.add(baggageId);
        } else {
            throw new BaggageServiceException(ADD_ERROR_MSG);
        }
    }

    @Override
    public ShortPath getBaggageShortPath(String baggageId) {
        if (baggageDetails.containsKey(baggageId))
            return baggageDetails.get(baggageId).getShortPath();
        return null;
    }

    private String formatPath(BaggageDetail detail) {
        StringBuffer sb = new StringBuffer();
        sb.append(detail.getBaggageId()).append(" ");
        if (detail.getShortPath() != null) {
            sb.append(detail.getShortPath().getShortPath()).append(":");
            sb.append(detail.getShortPath().getPathLength());
        } else {
            sb.append(NO_OPTIMAL_ROUTE_FOUND);
        }
        return sb.toString();
    }

    @Override
    public List<String> getAllBaggageShortestPath() throws BaggageServiceException {
        try {
            List<String> baggageShortPaths = new ArrayList<>();

            baggageOrder.forEach(baggageId -> {
                BaggageDetail baggageDetail = baggageDetails.get(baggageId);
                baggageShortPaths.add(formatPath(baggageDetail));
            });
            return baggageShortPaths;
        } catch (Exception e) {
            throw new BaggageServiceException(OPTIMAL_ROUTE_FETCH_ERROR, e);
        }
    }

    @Override
    public void assignShortPathToBaggage(NodeService nodeService, DepartureService departureService)
            throws BaggageServiceException {
        try {
            if (nodeService != null && departureService != null)
                baggageDetails.forEach((id, baggage) -> {
                    String bggEntryNode = baggage.getEntryPoint();
                    FlightDepartureDetail deptDetl = departureService.getFlightDepartureDetail(baggage.getFlightId());
                    ShortPath path = null;
                    if (deptDetl != null)
                        path = nodeService.getShortPath(bggEntryNode, deptDetl.getGate());
                    if (Constant.ARRIVAL_NODE.equalsIgnoreCase(baggage.getFlightId()))
                        path = nodeService.getShortPath(bggEntryNode, Constant.BAGGAGE_CLAIM_NODE);
                    baggage.setShortPath(path);
                });
        } catch (Exception e) {
            throw new BaggageServiceException(OPTIMAL_ROUTE_ASSIGN_ERROR, e);
        }
    }

    @Override
    public boolean hasValidData() {
        if (baggageDetails != null && baggageDetails.size() > 0)
            return true;
        return false;
    }

    /**
     * @return the baggageOrder
     */
    public List<String> getBaggageOrder() {
        return baggageOrder;
    }

    /**
     * @param baggageOrder the baggageOrder to set
     */
    public void setBaggageOrder(List<String> baggageOrder) {
        this.baggageOrder = baggageOrder;
    }
}
