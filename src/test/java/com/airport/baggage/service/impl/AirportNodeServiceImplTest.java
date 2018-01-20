package com.airport.baggage.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.airport.baggage.exception.NodeServiceException;
import com.airport.baggage.service.NodeService;
import com.airport.baggage.service.model.ShortPath;

@RunWith(MockitoJUnitRunner.class)
public class AirportNodeServiceImplTest {

    @InjectMocks
    private AirportNodeServiceImpl airportNodeServiceImpl;

    @Test(expected = NodeServiceException.class)
    public void testAddNode_ThrowsException_whenSrcDestNodePassedNull() throws NodeServiceException {
        airportNodeServiceImpl.addNode(null, null, 0);
    }

    @Test(expected = NodeServiceException.class)
    public void testAddNode_ThrowsException_whenSrcDestNodePassedEmpty() throws NodeServiceException {
        airportNodeServiceImpl.addNode("", "", 0);
    }

    @Test(expected = NodeServiceException.class)
    public void testAddNode_ThrowsException_whenSrcNodePassedNull() throws NodeServiceException {
        airportNodeServiceImpl.addNode(null, "A5", 0);
    }

    @Test(expected = NodeServiceException.class)
    public void testAddNode_ThrowsException_whenSrcNodePassedEmpty() throws NodeServiceException {
        airportNodeServiceImpl.addNode("", "A5", 0);
    }

    @Test(expected = NodeServiceException.class)
    public void testAddNode_ThrowsException_whenSrcAndDestNodeSame() throws NodeServiceException {
        airportNodeServiceImpl.addNode("A5", "A5", 0);
    }

    @Test(expected = NodeServiceException.class)
    public void testAddNode_ThrowsException_whenDestNodePassedNull() throws NodeServiceException {
        airportNodeServiceImpl.addNode("A5", null, 0);
    }

    @Test(expected = NodeServiceException.class)
    public void testAddNode_ThrowsException_whenDestNodePassedEmpty() throws NodeServiceException {
        airportNodeServiceImpl.addNode("A5", " ", 0);
    }

    @Test
    public void testAddNode_ThrowsNoException_whenPassedValidInput() throws NodeServiceException {
        airportNodeServiceImpl.addNode("A5", "A1", 0);
        assertTrue(true);
    }

    @Test
    public void testAddNode_hasTwoNodes_whenSrcAndDestNodesAdded() throws NodeServiceException {
        airportNodeServiceImpl = new AirportNodeServiceImpl();
        airportNodeServiceImpl.addNode("A5", "A1", 2);
        assertNotNull(airportNodeServiceImpl.getNode("A5"));
        assertNotNull(airportNodeServiceImpl.getNode("A1"));
        int length = airportNodeServiceImpl.getNode("A5").getConnectedNodes().get("A1");
        assertEquals(2, length);
        length = airportNodeServiceImpl.getNode("A1").getConnectedNodes().get("A5");
        assertEquals(2, length);
    }

    @Test
    public void testHasValidData_ReturnsFalse_whenNoNodesAdded() throws NodeServiceException {
        airportNodeServiceImpl = new AirportNodeServiceImpl();
        assertFalse(airportNodeServiceImpl.hasValidData());
    }

    @Test
    public void testHasValidData_ReturnsTrue_whenNodesAdded() throws NodeServiceException {
        airportNodeServiceImpl = new AirportNodeServiceImpl();
        airportNodeServiceImpl.addNode("A1", "A2", 2);
        assertTrue(airportNodeServiceImpl.hasValidData());
    }

    @Test
    public void testGetNode_ReturnsNull_whenNoNodesAdded() throws NodeServiceException {
        airportNodeServiceImpl = new AirportNodeServiceImpl();
        assertNull(airportNodeServiceImpl.getNode("A1"));
    }

    @Test
    public void testGetNode_ReturnsNode_whenNodesAdded() throws NodeServiceException {
        airportNodeServiceImpl = new AirportNodeServiceImpl();
        airportNodeServiceImpl.addNode("A2", "A3", 2);
        assertNotNull(airportNodeServiceImpl.getNode("A2"));
        assertNotNull(airportNodeServiceImpl.getNode("A3"));
    }

    @Test
    public void testGetShortPath_ReturnsNull_whenShortPathNotCalulated() throws NodeServiceException {
        airportNodeServiceImpl = new AirportNodeServiceImpl();
        airportNodeServiceImpl.addNode("A1", "A2", 2);
        assertNotNull(airportNodeServiceImpl.getNode("A1"));
        assertNotNull(airportNodeServiceImpl.getNode("A2"));
        ShortPath sp = airportNodeServiceImpl.getShortPath("A1", "A2");
        assertNull(sp);
    }

    @Test
    public void testGetShortPath_ReturnsValidPath_whenShortPathCalulated() throws NodeServiceException {
        NodeService nodeService = new AirportNodeServiceImpl();
        nodeService.addNode("A1", "A2", 5);
        assertNotNull(nodeService.getNode("A1"));
        assertNotNull(nodeService.getNode("A2"));
        nodeService.calculateShortPath();
        ShortPath sp = nodeService.getShortPath("A1", "A2");
        assertNotNull(sp);
        assertEquals(5, sp.getPathLength());
        String path = new String("A1 A2 ");
        assertTrue(path.equals(sp.getShortPath()));
    }

    @Test
    public void testcalculateShortPath_TimeOut_whenNoNodesAdded() throws NodeServiceException {
        NodeService nodeService = new AirportNodeServiceImpl();
        nodeService.calculateShortPath();
    }

    @Test
    public void testcalculateShortPath_TimeOut_whenTwoNodesAdded() throws NodeServiceException {
        NodeService nodeService = new AirportNodeServiceImpl();
        nodeService.addNode("A1", "A2", 1);
        nodeService.calculateShortPath();
    }

    @Test
    public void testcalculateShortPath_TimeOut_whenFiveNodesAdded() throws NodeServiceException {
        NodeService nodeService = new AirportNodeServiceImpl();
        nodeService.addNode("A1", "A2", 1);
        nodeService.addNode("A2", "A3", 1);
        nodeService.addNode("A4", "A5", 1);
        nodeService.addNode("A5", "A6", 1);
        nodeService.addNode("A6", "A7", 1);
        nodeService.calculateShortPath();
    }

    @Test
    public void testcalculateShortPath_TimeOut_whenTenNodesAdded() throws NodeServiceException {
        NodeService nodeService = new AirportNodeServiceImpl();
        for (int i = 1; i <= 10; i++) {
            String srcNode = "A" + i;
            String destNode = "A" + (i + 1);
            nodeService.addNode(srcNode, destNode, i + 1);
        }
        nodeService.calculateShortPath();
    }

    @Test
    public void testcalculateShortPath_TimeOut_when100NodesAdded() throws NodeServiceException {
        NodeService nodeService = new AirportNodeServiceImpl();
        for (int i = 1; i <= 100; i++) {
            String srcNode = "A" + i;
            String destNode = "A" + (i + 1);
            nodeService.addNode(srcNode, destNode, i + 1);
        }
        nodeService.calculateShortPath();
    }
}
