// CostEffectiveChargingStrategyTest - Unit tests for cost-effective charging strategy implementation.

package service.strategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChargingStation;
import model.OccupiedTimeSlot;
import model.SlowChargingStation;

class CostEffectiveChargingStrategyTest {

    private CostEffectiveChargingStrategy strategy;
    private ChargingStation slowChargingStation;

    /**
     * Sets up test data before each test execution.
     * Initializes the cost-effective charging strategy and a sample charging station.
     */
    @BeforeEach
    void setUp() {
        strategy = new CostEffectiveChargingStrategy();
        slowChargingStation = new SlowChargingStation(102, 150, 
                List.of(new OccupiedTimeSlot(10, 20)), strategy);
    }

    /**
     * Tests if the efficiency calculation produces the expected value.
     */
    @Test
    void testCalculateEfficiency() {
        double chargingHours = 5.0;
        double expectedEfficiency = (150) / (150 * chargingHours * 0.20);
        assertEquals(expectedEfficiency, strategy.calculateEfficiency(slowChargingStation, chargingHours), 0.01, 
                "Efficiency should match expected cost-effective calculation.");
    }

    /**
     * Tests if efficiency calculation with zero charging hours results in an exception.
     */
    @Test
    void testZeroChargingHours() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            strategy.calculateEfficiency(slowChargingStation, 0);
        });
        assertEquals("/ by zero", exception.getMessage(), "Should throw divide by zero exception.");
    }

    /**
     * Tests if negative charging hours result in an exception being thrown.
     */
    @Test
    void testNegativeChargingHours() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculateEfficiency(slowChargingStation, -5.0);
        });
        assertEquals("Charging hours must be positive.", exception.getMessage(), 
                "Negative charging hours should not be allowed.");
    }

    /**
     * Tests the efficiency calculation when station capacity is zero.
     */
    @Test
    void testZeroCapacity() {
        ChargingStation zeroCapacityStation = new SlowChargingStation(103, 0, 
                List.of(new OccupiedTimeSlot(8, 16)), strategy);
        double efficiency = strategy.calculateEfficiency(zeroCapacityStation, 5.0);
        assertEquals(0.0, efficiency, "Efficiency should be zero for zero capacity.");
    }

    /**
     * Tests the behavior when maximum charging hours are provided.
     */
    @Test
    void testHighChargingHours() {
        double efficiency = strategy.calculateEfficiency(slowChargingStation, 10000);
        assertTrue(efficiency > 0, "Efficiency should be positive for high charging hours.");
    }
}
