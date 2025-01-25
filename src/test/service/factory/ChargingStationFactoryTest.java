// ChargingStationFactoryTest - Unit tests for the ChargingStationFactory class.

package service.factory;

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
import service.strategy.CostEffectiveChargingStrategy;
import service.strategy.FastChargingStrategy;

class ChargingStationFactoryTest {

    /**
     * Tests whether the factory correctly creates a FastChargingStation instance.
     */
    @Test
    void testCreateFastChargingStation() {
        ChargingStation station = ChargingStationFactory.createStation(
                "Fast", 101, 250, List.of(new OccupiedTimeSlot(9, 17))
        );

        assertNotNull(station, "The factory should return a non-null object.");
        assertTrue(station instanceof FastChargingStation, "The station should be of type FastChargingStation.");
        assertEquals(101, station.getStationId(), "Station ID should match the provided value.");
        assertEquals(250, station.getCapacity(), "Capacity should match the provided value.");
        assertEquals(1, station.getForbiddenTimeSlots().size(), "Forbidden time slots should be set correctly.");
        assertTrue(station.getStrategy() instanceof FastChargingStrategy, "The strategy should be FastChargingStrategy.");
    }

    /**
     * Tests whether the factory correctly creates a SlowChargingStation instance.
     */
    @Test
    void testCreateSlowChargingStation() {
        ChargingStation station = ChargingStationFactory.createStation(
                "Slow", 102, 150, List.of(new OccupiedTimeSlot(10, 20))
        );

        assertNotNull(station, "The factory should return a non-null object.");
        assertTrue(station instanceof SlowChargingStation, "The station should be of type SlowChargingStation.");
        assertEquals(102, station.getStationId(), "Station ID should match the provided value.");
        assertEquals(150, station.getCapacity(), "Capacity should match the provided value.");
        assertEquals(1, station.getForbiddenTimeSlots().size(), "Forbidden time slots should be set correctly.");
        assertTrue(station.getStrategy() instanceof CostEffectiveChargingStrategy, "The strategy should be CostEffectiveChargingStrategy.");
    }

    /**
     * Tests whether the factory throws an exception for an invalid station type.
     */
    @Test
    void testInvalidStationType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ChargingStationFactory.createStation("InvalidType", 103, 200, List.of(new OccupiedTimeSlot(12, 18)));
        });

        assertEquals("Unknown charging station type: InvalidType", exception.getMessage(),
                "An exception should be thrown for an unknown station type.");
    }
}
