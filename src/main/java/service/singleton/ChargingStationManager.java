// ChargingStationManager - Implements the Singleton pattern to manage all charging stations centrally.

package service.singleton;

import java.util.ArrayList;
import java.util.List;

import model.ChargingStation;

public class ChargingStationManager {
    
    // Singleton instance of ChargingStationManager
    private static ChargingStationManager instance;

    // List to store all registered charging stations
    private final List<ChargingStation> stations;

    /**
     * Private constructor to prevent direct instantiation from outside the class.
     * Initializes the stations list.
     */
    private ChargingStationManager() {
        stations = new ArrayList<>();
    }

    /**
     * Provides a global point of access to the singleton instance of ChargingStationManager.
     * If an instance does not already exist, it is created.
     * 
     * @return The singleton instance of ChargingStationManager.
     */
    public static ChargingStationManager getInstance() {
        if (instance == null) {
            instance = new ChargingStationManager();
        }
        return instance;
    }

    /**
     * Adds a charging station to the manager's list.
     * 
     * @param station The charging station to be added.
     */
    public void addStation(ChargingStation station) {
        stations.add(station);
    }

    /**
     * Prints all charging stations managed by the singleton instance.
     * If no stations are available, a message is printed.
     */
    public void printAllStations() {
        if (stations.isEmpty()) {
            System.out.println("No charging stations available.");
        } else {
            for (ChargingStation station : stations) {
                System.out.println(station);
            }
        }
    }

    /**
     * Calculates and returns the total capacity of all charging stations managed.
     * 
     * @return The total charging capacity in kilowatts (kW).
     */
    public int getTotalCapacity() {
        return stations.stream().mapToInt(ChargingStation::getCapacity).sum();
    }

    /**
     * Filters and returns a list of charging stations that have a capacity greater than or equal to the specified minimum.
     * 
     * @param minCapacity The minimum capacity threshold for filtering stations.
     * @return A list of charging stations that meet the capacity criteria.
     */
    public List<ChargingStation> filterStationsByCapacity(int minCapacity) {
        List<ChargingStation> filteredStations = new ArrayList<>();
        for (ChargingStation station : stations) {
            if (station.getCapacity() >= minCapacity) {
                filteredStations.add(station);
            }
        }
        return filteredStations;
    }
}
