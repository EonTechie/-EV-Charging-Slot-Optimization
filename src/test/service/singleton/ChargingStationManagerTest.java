// ChargingStationManagerTest - Unit tests for the ChargingStationManager singleton class.

package service.singleton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;
import service.strategy.FastChargingStrategy;

class ChargingStationManagerTest {

    private ChargingStationManager manager;
    private ChargingStation fastStation;
    private ChargingStation slowStation;

    /**
     * Sets up the test environment before each test execution.
     * Initializes the ChargingStationManager instance and test stations.
     */
    @BeforeEach
    void setUp() {
        manager = ChargingStationManager.getInstance();
        manager.printAllStations(); // Clear stations from previous tests if necessary.

        fastStation = new FastChargingStation(101, 250, 
                List.of(new OccupiedTimeSlot(9, 17)), new FastChargingStrategy());

        slowStation = new FastChargingStation(102, 150, 
                List.of(new OccupiedTimeSlot(10, 20)), new FastChargingStrategy());
    }

    /**
     * Tests whether the singleton instance is correctly initialized and remains the same across calls.
     */
    @Test
    void testSingletonInstance() {
        ChargingStationManager anotherInstance = ChargingStationManager.getInstance();
        assertSame(manager, anotherInstance, "Only one instance of ChargingStationManager should exist.");
    }

    /**
     * Tests adding stations to the manager and ensures they are stored correctly.
     */
    @Test
    void testAddStation() {
        manager.addStation(fastStation);
        manager.addStation(slowStation);

        List<ChargingStation> stations = manager.filterStationsByCapacity(100);
        assertEquals(2, stations.size(), "There should be 2 stations added.");
        assertEquals(250, stations.get(0).getCapacity(), "The first station should have a capacity of 250 kW.");
    }

    /**
     * Tests if the manager correctly calculates the total capacity of all charging stations.
     */
    @Test
    void testGetTotalCapacity() {
        manager.addStation(fastStation);
        manager.addStation(slowStation);

        int totalCapacity = manager.getTotalCapacity();
        assertEquals(400, totalCapacity, "Total capacity should be the sum of all station capacities.");
    }

    /**
     * Tests filtering of stations based on their capacity and ensures correct results are returned.
     */
    @Test
    void testFilterStationsByCapacity() {
        manager.addStation(fastStation);
        manager.addStation(slowStation);

        List<ChargingStation> highCapacityStations = manager.filterStationsByCapacity(200);
        assertEquals(1, highCapacityStations.size(), "Only one station should have a capacity of 200 kW or more.");
    }

    /**
     * Tests the behavior of printing all stations when no stations are added.
     */
    @Test
    void testPrintAllStationsWithNoStations() {
        manager.printAllStations();
        assertEquals(0, manager.filterStationsByCapacity(0).size(), 
                "There should be no stations if none are added.");
    }
}
