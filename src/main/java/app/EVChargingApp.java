// EVChargingApp: The main application that demonstrates different design patterns
package app;

import java.util.List;

import model.ChargingStation;
import model.OccupiedTimeSlot;
import service.builder.ChargingStationBuilder;
import service.decorator.RenewableEnergyDecorator;
import service.factory.ChargingStationFactory;
import service.singleton.ChargingStationManager;
import service.strategy.CostEffectiveChargingStrategy;
import service.strategy.FastChargingStrategy;

public class EVChargingApp {
    public static void main(String[] args) {

        // Define the charging duration in hours
        double chargingHours = 5.0;

        // 1. Factory Pattern Usage - Creating charging stations using a factory
        ChargingStation fastStation = ChargingStationFactory.createStation(
                "Fast", 101, 250, List.of(new OccupiedTimeSlot(9, 17))
        );

        ChargingStation slowStation = ChargingStationFactory.createStation(
                "Slow", 102, 150, List.of(new OccupiedTimeSlot(10, 20))
        );

        System.out.println("Created Stations using Factory:");
        System.out.println(fastStation);
        System.out.println(slowStation);

        System.out.println("Fast station efficiency: " + fastStation.getChargingEfficiency(chargingHours));
        System.out.println("Slow station efficiency: " + slowStation.getChargingEfficiency(5.0));

        // 2. Builder Pattern Usage - Constructing charging stations step-by-step
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

        System.out.println("\nCreated Stations using Builder:");
        System.out.println(builtFastStation);
        System.out.println(builtSlowStation);

        System.out.println("Built Fast station efficiency: " + builtFastStation.getChargingEfficiency(5.0));
        System.out.println("Built Slow station efficiency: " + builtSlowStation.getChargingEfficiency(5.0));

        // 3. Strategy Pattern Usage - Changing strategy dynamically at runtime
        slowStation.setStrategy(new FastChargingStrategy());
        System.out.println("\nSlow station efficiency after strategy change: " + slowStation.getChargingEfficiency(5.0));

        // 4. Singleton Pattern Usage - Managing all stations centrally
        ChargingStationManager manager = ChargingStationManager.getInstance();
        manager.addStation(fastStation);
        manager.addStation(slowStation);
        manager.addStation(builtFastStation);
        manager.addStation(builtSlowStation);

        System.out.println("\nAll Stations Managed by Singleton:");
        manager.printAllStations();

        // 5. Decorator Pattern Usage - Enhancing functionality with renewable energy
        ChargingStation renewableStation = new RenewableEnergyDecorator(fastStation);
        System.out.println("\nDecorated Fast Station with Renewable Energy:");
        System.out.println(renewableStation);
        System.out.println("Renewable Station Efficiency: " + renewableStation.getChargingEfficiency(5.0));

        // Demonstrate the efficiency calculations for the stations
        System.out.println("Efficiency of Fast Station for 5 hours: " + fastStation.getChargingEfficiency(5.0));
        System.out.println("Efficiency of Slow Station for 5 hours: " + slowStation.getChargingEfficiency(5.0));

        // Changing the strategy dynamically to cost-effective strategy
        System.out.println("\nApplying cost-effective strategy to fast station...");
        fastStation.setStrategy(new CostEffectiveChargingStrategy());
        System.out.println("Fast station efficiency after strategy change: " + fastStation.getChargingEfficiency(5.0)+ "\n");

        // Add report generation in the main application
        ReportGenerator.generateReport(fastStation);
        ReportGenerator.generateReport(slowStation);

    }
}
