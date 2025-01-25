// ChargingStationBuilderTest - Unit tests for the ChargingStationBuilder class.

package service.builder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;
import model.SlowChargingStation;

class ChargingStationBuilderTest {

    /**
     * Tests the successful creation of a FastChargingStation using the builder pattern.
     */
    @Test
    void testBuildFastChargingStation() {
        ChargingStation station = new ChargingStationBuilder()
                .setStationId(101)
                .setCapacity(250)
                .setForbiddenTimeSlots(List.of(new OccupiedTimeSlot(9, 17)))
                .setType("Fast")
                .build();

        assertNotNull(station, "The created station should not be null");
        assertTrue(station instanceof FastChargingStation, "The station should be an instance of FastChargingStation");
        assertEquals(250, station.getCapacity(), "The station capacity should be 250 kW");
    }

    /**
     * Tests the successful creation of a SlowChargingStation using the builder pattern.
     */
    @Test
    void testBuildSlowChargingStation() {
        ChargingStation station = new ChargingStationBuilder()
                .setStationId(202)
                .setCapacity(150)
                .setForbiddenTimeSlots(List.of(new OccupiedTimeSlot(10, 20)))
                .setType("Slow")
                .build();

        assertNotNull(station, "The created station should not be null");
        assertTrue(station instanceof SlowChargingStation, "The station should be an instance of SlowChargingStation");
        assertEquals(150, station.getCapacity(), "The station capacity should be 150 kW");
    }

    /**
     * Tests that an IllegalStateException is thrown when an invalid station type is provided.
     */
    @Test
    void testInvalidChargingStationType() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new ChargingStationBuilder()
                    .setStationId(303)
                    .setCapacity(200)
                    .setForbiddenTimeSlots(List.of(new OccupiedTimeSlot(8, 16)))
                    .setType("Unknown")
                    .build();
        });

        assertEquals("Invalid charging station type.", exception.getMessage(), 
                "Exception message should indicate invalid station type.");
    }

    /**
     * Tests that all properties are correctly set in the ChargingStation object.
     */
    @Test
    void testBuilderSetsAllPropertiesCorrectly() {
        ChargingStation station = new ChargingStationBuilder()
                .setStationId(404)
                .setCapacity(180)
                .setForbiddenTimeSlots(List.of(new OccupiedTimeSlot(7, 19)))
                .setType("Fast")
                .build();

        assertNotNull(station);
        assertEquals(404, station.getStationId(), "Station ID should match the set value.");
        assertEquals(180, station.getCapacity(), "Capacity should match the set value.");
        assertEquals(1, station.getForbiddenTimeSlots().size(), "Forbidden time slots should contain 1 element.");
    }
}
