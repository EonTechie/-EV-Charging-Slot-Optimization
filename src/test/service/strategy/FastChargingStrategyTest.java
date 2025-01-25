// FastChargingStrategyTest - Unit tests for the fast charging strategy implementation.

package service.strategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;

class FastChargingStrategyTest {

    private FastChargingStrategy strategy;
    private ChargingStation fastChargingStation;

    /**
     * Sets up test data before each test execution.
     * Initializes the fast charging strategy and a sample charging station.
     */
    @BeforeEach
    void setUp() {
        strategy = new FastChargingStrategy();
        fastChargingStation = new FastChargingStation(201, 300,
                List.of(new OccupiedTimeSlot(9, 17)), strategy);
    }

    /**
     * Tests if the efficiency calculation produces the expected value.
     */
    @Test
    void testCalculateEfficiency() {
        double chargingHours = 5.0;
        double expectedEfficiency = (300 * chargingHours * 1.5) / (300 * chargingHours * 1.5 * 0.50);
        assertEquals(expectedEfficiency, strategy.calculateEfficiency(fastChargingStation, chargingHours), 0.01,
                "Efficiency should match expected fast charging calculation.");
    }

    /**
     * Tests if efficiency calculation with zero charging hours results in an exception.
     */
    @Test
    void testZeroChargingHours() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            strategy.calculateEfficiency(fastChargingStation, 0);
        });
        assertEquals("/ by zero", exception.getMessage(), "Should throw divide by zero exception.");
    }

    /**
     * Tests if negative charging hours result in an exception being thrown.
     */
    @Test
    void testNegativeChargingHours() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculateEfficiency(fastChargingStation, -5.0);
        });
        assertEquals("Charging hours must be positive.", exception.getMessage(),
                "Negative charging hours should not be allowed.");
    }

    /**
     * Tests the efficiency calculation when station capacity is zero.
     */
    @Test
    void testZeroCapacity() {
        ChargingStation zeroCapacityStation = new FastChargingStation(202, 0,
                List.of(new OccupiedTimeSlot(8, 16)), strategy);
        double efficiency = strategy.calculateEfficiency(zeroCapacityStation, 5.0);
        assertEquals(0.0, efficiency, "Efficiency should be zero for zero capacity.");
    }

    /**
     * Tests the behavior when maximum charging hours are provided.
     */
    @Test
    void testHighChargingHours() {
        double efficiency = strategy.calculateEfficiency(fastChargingStation, 10000);
        assertTrue(efficiency > 0, "Efficiency should be positive for high charging hours.");
    }
}
