package model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.strategy.CostEffectiveChargingStrategy;
import service.strategy.FastChargingStrategy;

/**
 * Unit tests for the ChargingStation class, ensuring its functionality
 * meets expected behaviors for different charging station types.
 */
class ChargingStationTest {

    private FastChargingStation fastStation;
    private SlowChargingStation slowStation;

    /**
     * Sets up test data before each test case execution.
     */
    @BeforeEach
    void setUp() {
        fastStation = new FastChargingStation(1, 200, List.of(new OccupiedTimeSlot(9, 17)), new FastChargingStrategy());
        slowStation = new SlowChargingStation(2, 150, List.of(new OccupiedTimeSlot(10, 18)), new CostEffectiveChargingStrategy());
    }

    /**
     * Tests whether the charging efficiency is correctly calculated
     * based on the station type and charging hours.
     */
    @Test
    void testGetChargingEfficiency() {
        assertEquals(3, fastStation.getChargingEfficiency(5.0), "Fast station efficiency should be correct");
        assertEquals(5, slowStation.getChargingEfficiency(5.0), "Slow station efficiency should be correct");
    }

    /**
     * Verifies if the strategy of a station can be successfully changed at runtime,
     * and efficiency calculations update accordingly.
     */
    @Test
    void testChangeStrategy() {
        slowStation.setStrategy(new FastChargingStrategy());
        assertEquals(3, slowStation.getChargingEfficiency(5.0), "Slow station efficiency should update after strategy change");
    }

    /**
     * Validates the capacity retrieval functionality for charging stations.
     */
    @Test
    void testGetCapacity() {
        assertEquals(200, fastStation.getCapacity(), "Fast station capacity should be 200");
        assertEquals(150, slowStation.getCapacity(), "Slow station capacity should be 150");
    }

    /**
     * Ensures the correct retrieval of station IDs.
     */
    @Test
    void testGetStationId() {
        assertEquals(1, fastStation.getStationId(), "Fast station ID should be 1");
        assertEquals(2, slowStation.getStationId(), "Slow station ID should be 2");
    }

    /**
     * Checks if forbidden time slots are properly set and retrieved.
     */
    @Test
    void testForbiddenTimeSlots() {
        assertFalse(fastStation.getForbiddenTimeSlots().isEmpty(), "Fast station should have forbidden time slots");
        assertFalse(slowStation.getForbiddenTimeSlots().isEmpty(), "Slow station should have forbidden time slots");
    }

    /**
     * Tests the toString() method to verify that the output format matches the expected station details.
     */
    @Test
    void testToStringFormat() {
        String expectedFast = "Station ID: 1, Capacity: 200 kW, Forbidden Slots: [(9 - 17)], Strategy: FastChargingStrategy";
        String expectedSlow = "Station ID: 2, Capacity: 150 kW, Forbidden Slots: [(10 - 18)], Strategy: CostEffectiveChargingStrategy";
        
        assertEquals(expectedFast, fastStation.toString(), "Fast station toString output should match expected");
        assertEquals(expectedSlow, slowStation.toString(), "Slow station toString output should match expected");
    }
}
