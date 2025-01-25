// ReportGenerator: A utility class to generate reports for charging stations.

package app;

import model.ChargingStation;

public class ReportGenerator {

    /**
     * Generates and prints a report for the given charging station.
     * 
     * @param station The charging station for which the report will be generated.
     */
    public static void generateReport(ChargingStation station) {
        System.out.println("Generating report for: " + station);
    }
}
