// ChargingStationFactory - Implements the Factory design pattern to create different types of charging stations.

package service.factory;

import java.util.List;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;
import model.SlowChargingStation;
import service.strategy.CostEffectiveChargingStrategy;
import service.strategy.FastChargingStrategy;

public class ChargingStationFactory {

    /**
     * Creates and returns an instance of a charging station based on the provided type.
     * This method follows the Factory Pattern to encapsulate object creation logic.
     * 
     * @param type The type of charging station to create (e.g., "Fast" or "Slow").
     * @param stationId Unique identifier for the station.
     * @param capacity Maximum power capacity of the station in kilowatts (kW).
     * @param forbiddenSlots List of time slots when the station cannot be used.
     * @return A new instance of either FastChargingStation or SlowChargingStation.
     * @throws IllegalArgumentException if an unknown type is provided.
     */
    public static ChargingStation createStation(String type, int stationId, int capacity, List<OccupiedTimeSlot> forbiddenSlots) {
        if ("Fast".equalsIgnoreCase(type)) {
            return new FastChargingStation(stationId, capacity, forbiddenSlots, new FastChargingStrategy());
        } else if ("Slow".equalsIgnoreCase(type)) {
            return new SlowChargingStation(stationId, capacity, forbiddenSlots, new CostEffectiveChargingStrategy());
        } else {
            throw new IllegalArgumentException("Unknown charging station type: " + type);
        }
    }
}
