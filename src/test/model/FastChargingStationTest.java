// FastChargingStationTest: Unit tests for the FastChargingStation class.

package model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.strategy.FastChargingStrategy;

public class FastChargingStationTest {

    private FastChargingStation fastChargingStation;

    @BeforeEach
    void setUp() {
        // Initializing a FastChargingStation with test data
        fastChargingStation = new FastChargingStation(
                101, 250, List.of(new OccupiedTimeSlot(9, 17)), new FastChargingStrategy()
        );
    }

    @Test
    void testFastChargingStationInitialization() {
        // Verify that the object is initialized correctly
        assertNotNull(fastChargingStation);
        assertEquals(101, fastChargingStation.getStationId());
        assertEquals(250, fastChargingStation.getCapacity());
        assertEquals(1, fastChargingStation.getForbiddenTimeSlots().size());
    }

    @Test
    void testChargingEfficiencyCalculation() {
        double chargingHours = 5.0;
        
        // Calculating expected efficiency based on known values
        double expectedEfficiency = fastChargingStation.getCapacity() * chargingHours * 1.5 / (fastChargingStation.getCapacity() * chargingHours * 1.5 * 0.50);

        // Verify that the calculated efficiency matches expected value
        assertEquals((int) expectedEfficiency, fastChargingStation.getChargingEfficiency(chargingHours));
    }

    @Test
    void testForbiddenTimeSlots() {
        // Ensure forbidden time slots are set correctly
        List<OccupiedTimeSlot> slots = fastChargingStation.getForbiddenTimeSlots();
        assertEquals(9, slots.get(0).getStartTime());
        assertEquals(17, slots.get(0).getEndTime());
    }

    @Test
    void testToStringMethod() {
        // Check if the toString method provides expected output
        String expectedOutput = "Station ID: 101, Capacity: 250 kW, Forbidden Slots: [(9 - 17)], Strategy: FastChargingStrategy";
        assertEquals(expectedOutput, fastChargingStation.toString());
    }
}
