// SlowChargingStationTest - Unit tests for the SlowChargingStation class.

package model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import service.strategy.CostEffectiveChargingStrategy;
import service.strategy.FastChargingStrategy;

/**
 * Unit tests for the SlowChargingStation class to verify its functionality and behavior.
 */
public class SlowChargingStationTest {

    /**
     * Tests the successful creation of a SlowChargingStation instance with valid parameters.
     */
    @Test
    void testSlowChargingStationCreation() {
        SlowChargingStation station = new SlowChargingStation(102, 150, 
                List.of(new OccupiedTimeSlot(10, 20)), new CostEffectiveChargingStrategy());

        assertNotNull(station, "SlowChargingStation object should not be null");
        assertEquals(102, station.getStationId(), "Station ID should match");
        assertEquals(150, station.getCapacity(), "Capacity should match");
        assertEquals(1, station.getForbiddenTimeSlots().size(), "There should be one forbidden time slot");
        assertTrue(station.getStrategy() instanceof CostEffectiveChargingStrategy, 
                "The assigned strategy should be CostEffectiveChargingStrategy");
    }

    /**
     * Tests the calculation of charging efficiency using the assigned cost-effective strategy.
     */
    @Test
    void testChargingEfficiencyCalculation() {
        SlowChargingStation station = new SlowChargingStation(102, 150, 
                List.of(new OccupiedTimeSlot(10, 20)), new CostEffectiveChargingStrategy());

        double chargingHours = 5.0;
        int efficiency = station.getChargingEfficiency(chargingHours);

        assertTrue(efficiency > 0, "Efficiency should be greater than 0");
    }

    /**
     * Tests the strategy change functionality by switching to a different charging strategy.
     */
    @Test
    void testChangingStrategy() {
        SlowChargingStation station = new SlowChargingStation(102, 150, 
                List.of(new OccupiedTimeSlot(10, 20)), new CostEffectiveChargingStrategy());

        station.setStrategy(new FastChargingStrategy());

        assertTrue(station.getStrategy() instanceof FastChargingStrategy, 
                "Strategy should be changed to FastChargingStrategy");
    }

    /**
     * Tests the string representation of the SlowChargingStation instance.
     */
    @Test
    void testToStringMethod() {
        SlowChargingStation station = new SlowChargingStation(102, 150, 
                List.of(new OccupiedTimeSlot(10, 20)), new CostEffectiveChargingStrategy());

        String expected = "Station ID: 102, Capacity: 150 kW, Forbidden Slots: [(10 - 20)], Strategy: CostEffectiveChargingStrategy";
        assertEquals(expected, station.toString(), "String representation should match expected format");
    }
}
