// EVChargingAppTest - Unit tests for the electric vehicle charging station application.

package app;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;
import model.SlowChargingStation;
import service.builder.ChargingStationBuilder;
import service.decorator.RenewableEnergyDecorator;
import service.factory.ChargingStationFactory;
import service.singleton.ChargingStationManager;
import service.strategy.FastChargingStrategy;

/**
 * Unit tests for the EVChargingApp to validate the core functionalities 
 * of different design patterns such as Factory, Builder, Strategy, 
 * Singleton, and Decorator.
 */
public class EVChargingAppTest {

    private ChargingStation fastStation;
    private ChargingStation slowStation;

    /**
     * Initializes test data before each test execution.
     */
    @BeforeEach
    void setUp() {
        fastStation = ChargingStationFactory.createStation(
                "Fast", 101, 250, List.of(new OccupiedTimeSlot(9, 17))
        );

        slowStation = ChargingStationFactory.createStation(
                "Slow", 102, 150, List.of(new OccupiedTimeSlot(10, 20))
        );
    }

    /**
     * Tests the Factory Pattern to ensure correct creation of 
     * fast and slow charging stations.
     */
    @Test
    void testFactoryPatternCreatesCorrectStations() {
        assertNotNull(fastStation, "Fast station should not be null");
        assertNotNull(slowStation, "Slow station should not be null");
        assertTrue(fastStation instanceof FastChargingStation, "Fast station should be of type FastChargingStation");
        assertTrue(slowStation instanceof SlowChargingStation, "Slow station should be of type SlowChargingStation");
    }

    /**
     * Tests the Builder Pattern to verify that stations are created with 
     * correct attributes and configurations.
     */
    @Test
    void testBuilderPatternCreatesCorrectStations() {
        ChargingStation builtFastStation = new ChargingStationBuilder()
                .setStationId(201)
                .setCapacity(300)
                .setForbiddenTimeSlots(List.of(new OccupiedTimeSlot(8, 16)))
                .setType("Fast")
                .build();

        ChargingStation builtSlowStation = new ChargingStationBuilder()
                .setStationId(202)
                .setCapacity(180)
                .setForbiddenTimeSlots(List.of(new OccupiedTimeSlot(12, 20)))
                .setType("Slow")
                .build();

        assertNotNull(builtFastStation, "Built fast station should not be null");
        assertNotNull(builtSlowStation, "Built slow station should not be null");
        assertEquals(300, builtFastStation.getCapacity(), "Built fast station capacity should be 300");
        assertEquals(180, builtSlowStation.getCapacity(), "Built slow station capacity should be 180");
    }

    /**
     * Tests the Strategy Pattern to ensure correct efficiency calculations 
     * for different charging strategies.
     */
    @Test
    void testStrategyPatternEfficiencyCalculation() {
        double chargingHours = 5.0;

        assertEquals(2, fastStation.getChargingEfficiency(chargingHours), 0.01, 
                     "Fast station efficiency should be calculated correctly");
        assertEquals(1, slowStation.getChargingEfficiency(chargingHours), 0.01, 
                     "Slow station efficiency should be calculated correctly");

        slowStation.setStrategy(new FastChargingStrategy());
        assertEquals(2, slowStation.getChargingEfficiency(chargingHours), 0.01, 
                     "Slow station efficiency should update after strategy change");
    }

    /**
     * Tests the Singleton Pattern to verify that only one instance of the 
     * ChargingStationManager is created and that stations can be managed properly.
     */
    @Test
    void testSingletonPatternFunctionality() {
        ChargingStationManager manager = ChargingStationManager.getInstance();
        manager.addStation(fastStation);
        manager.addStation(slowStation);

        assertEquals(2, manager.filterStationsByCapacity(100).size(), 
                     "Singleton should manage two stations with capacity over 100");
        assertEquals(400, manager.getTotalCapacity(), 
                     "Total capacity should sum up correctly");
    }

    /**
     * Tests the Decorator Pattern to verify that additional functionalities 
     * (renewable energy) are correctly applied to the station.
     */
    @Test
    void testDecoratorPatternAddsRenewableEnergy() {
        ChargingStation renewableStation = new RenewableEnergyDecorator(fastStation);

        assertNotNull(renewableStation, "Decorated station should not be null");
        assertEquals(fastStation.getChargingEfficiency(5.0) + 50, 
                     renewableStation.getChargingEfficiency(5.0), 
                     "Efficiency should increase with renewable energy addition");
    }

    /**
     * Tests the validity of the OccupiedTimeSlot class by ensuring correct 
     * values are stored and exceptions are thrown for invalid inputs.
     */
    @Test
    void testOccupiedTimeSlotValidity() {
        OccupiedTimeSlot slot = new OccupiedTimeSlot(8, 16);
        assertEquals(8, slot.getStartTime(), "Start time should be 8");
        assertEquals(16, slot.getEndTime(), "End time should be 16");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new OccupiedTimeSlot(18, 16);
        });

        assertEquals("Start time must be less than end time", exception.getMessage(), 
                     "Exception message should match expected");
    }
}
