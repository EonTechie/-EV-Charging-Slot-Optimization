// ChargingStrategyTest - Unit tests for different charging strategy implementations.

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
import model.SlowChargingStation;

class ChargingStrategyTest {

    private ChargingStrategy fastChargingStrategy;
    private ChargingStrategy costEffectiveChargingStrategy;
    private ChargingStation fastStation;
    private ChargingStation slowStation;

    /**
     * Sets up test data before each test execution.
     * Initializes different charging strategies and sample charging stations.
     */
    @BeforeEach
    void setUp() {
        fastChargingStrategy = new FastChargingStrategy();
        costEffectiveChargingStrategy = new CostEffectiveChargingStrategy();

        fastStation = new FastChargingStation(101, 200, 
                List.of(new OccupiedTimeSlot(9, 17)), fastChargingStrategy);

        slowStation = new SlowChargingStation(102, 150, 
                List.of(new OccupiedTimeSlot(10, 20)), costEffectiveChargingStrategy);
    }

    /**
     * Tests whether the fast charging strategy calculates efficiency correctly.
     */
    @Test
    void testFastChargingStrategyEfficiency() {
        double efficiency = fastChargingStrategy.calculateEfficiency(fastStation, 5.0);
        assertTrue(efficiency > 0, "Efficiency should be a positive value.");
        assertEquals(3.0, efficiency, 0.01, "Expected efficiency calculation for fast charging.");
    }

    /**
     * Tests whether the cost-effective charging strategy calculates efficiency correctly.
     */
    @Test
    void testCostEffectiveChargingStrategyEfficiency() {
        double efficiency = costEffectiveChargingStrategy.calculateEfficiency(slowStation, 5.0);
        assertTrue(efficiency > 0, "Efficiency should be a positive value.");
        assertEquals(1.5, efficiency, 0.01, "Expected efficiency calculation for cost-effective charging.");
    }

    /**
     * Tests if changing the strategy at runtime updates the efficiency calculation.
     */
    @Test
    void testStrategyChangeEffect() {
        slowStation.setStrategy(fastChargingStrategy);
        double newEfficiency = slowStation.getChargingEfficiency(5.0);
        assertEquals(3.0, newEfficiency, 0.01, "Efficiency should match fast charging after strategy change.");
    }

    /**
     * Tests if passing zero charging hours results in zero efficiency.
     */
    @Test
    void testZeroChargingHours() {
        double efficiency = fastChargingStrategy.calculateEfficiency(fastStation, 0.0);
        assertEquals(0.0, efficiency, "Efficiency should be zero for zero charging hours.");
    }

    /**
     * Tests if negative charging hours result in an exception being thrown.
     */
    @Test
    void testNegativeChargingHours() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fastChargingStrategy.calculateEfficiency(fastStation, -5.0);
        });
        assertEquals("Charging hours must be positive.", exception.getMessage());
    }
}
