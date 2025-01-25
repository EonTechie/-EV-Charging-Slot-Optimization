// ReportGeneratorTest: Unit tests for the ReportGenerator utility class.

package app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;
import service.strategy.FastChargingStrategy;

public class ReportGeneratorTest {

    private ChargingStation testStation;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Redirect system output for testing
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create a test charging station
        testStation = new FastChargingStation(
                101, 250, List.of(new OccupiedTimeSlot(9, 17)), new FastChargingStrategy()
        );
    }

    @Test
    void testGenerateReportOutputsCorrectData() {
        // Act
        ReportGenerator.generateReport(testStation);

        // Assert
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Generating report for:"));
        assertTrue(output.contains("Station ID: 101"));
        assertTrue(output.contains("Capacity: 250 kW"));
        assertTrue(output.contains("Forbidden Slots: [(9 - 17)]"));
        assertTrue(output.contains("Strategy: FastChargingStrategy"));
    }
}
