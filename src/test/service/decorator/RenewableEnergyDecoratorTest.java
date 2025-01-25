// RenewableEnergyDecoratorTest - Unit tests for the RenewableEnergyDecorator class.

package service.decorator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;
import service.strategy.FastChargingStrategy;

class RenewableEnergyDecoratorTest {

    private ChargingStation fastStation;
    private ChargingStation renewableStation;

    /**
     * Initializes test data before each test case.
     */
    @BeforeEach
    void setUp() {
        fastStation = new FastChargingStation(101, 250, List.of(new OccupiedTimeSlot(9, 17)), new FastChargingStrategy());
        renewableStation = new RenewableEnergyDecorator(fastStation);
    }

    /**
     * Tests whether the decorator correctly wraps an existing charging station.
     */
    @Test
    void testDecoratorInitialization() {
        assertNotNull(renewableStation, "The renewable energy decorator should not be null.");
        assertEquals(101, renewableStation.getStationId(), "Station ID should match the decorated station.");
        assertEquals(250, renewableStation.getCapacity(), "Capacity should match the decorated station.");
        assertEquals(fastStation.getForbiddenTimeSlots(), renewableStation.getForbiddenTimeSlots(), 
                "Forbidden time slots should match the decorated station.");
    }

    /**
     * Tests the enhanced efficiency calculation provided by the decorator.
     */
    @Test
    void testChargingEfficiencyWithRenewableEnergy() {
        double chargingHours = 5.0;
        int expectedEfficiency = fastStation.getChargingEfficiency(chargingHours) + 50;
        
        assertEquals(expectedEfficiency, renewableStation.getChargingEfficiency(chargingHours),
                "Efficiency should include the renewable energy boost.");
    }

    /**
     * Tests if the decorated charging station correctly overrides the toString() method.
     */
    @Test
    void testToStringMethod() {
        String expectedString = fastStation.toString() + " + Renewable Energy";
        
        assertEquals(expectedString, renewableStation.toString(),
                "The string representation should indicate renewable energy addition.");
    }
}
