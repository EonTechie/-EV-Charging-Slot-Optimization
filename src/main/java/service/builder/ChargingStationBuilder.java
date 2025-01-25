// ChargingStationBuilder - Implements the Builder design pattern for creating ChargingStation objects.

package service.builder;

import java.util.List;

import model.ChargingStation;
import model.FastChargingStation;
import model.OccupiedTimeSlot;
import model.SlowChargingStation;
import service.strategy.ChargingStrategy;
import service.strategy.CostEffectiveChargingStrategy;
import service.strategy.FastChargingStrategy;

public class ChargingStationBuilder {
    private int stationId;
    private int capacity;
    private List<OccupiedTimeSlot> forbiddenTimeSlots;
    private String type;
    private ChargingStrategy strategy;

    /**
     * Sets the station ID.
     * 
     * @param stationId Unique identifier for the charging station.
     * @return The current instance of ChargingStationBuilder.
     */
    public ChargingStationBuilder setStationId(int stationId) {
        this.stationId = stationId;
        return this;
    }

    /**
     * Sets the capacity of the station.
     * 
     * @param capacity Maximum power capacity of the charging station (in kW).
     * @return The current instance of ChargingStationBuilder.
     */
    public ChargingStationBuilder setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    /**
     * Sets the forbidden time slots for the station.
     * 
     * @param forbiddenTimeSlots List of time slots when the station cannot be used.
     * @return The current instance of ChargingStationBuilder.
     */
    public ChargingStationBuilder setForbiddenTimeSlots(List<OccupiedTimeSlot> forbiddenTimeSlots) {
        this.forbiddenTimeSlots = forbiddenTimeSlots;
        return this;
    }

    /**
     * Sets the type of charging station and assigns the appropriate strategy.
     * 
     * @param type The type of charging station (e.g., "Fast" or "Slow").
     * @return The current instance of ChargingStationBuilder.
     */
    public ChargingStationBuilder setType(String type) {
        this.type = type;
        this.strategy = type.equalsIgnoreCase("Fast") 
                        ? new FastChargingStrategy() 
                        : new CostEffectiveChargingStrategy();
        return this;
    }

    /**
     * Builds and returns the ChargingStation object based on provided parameters.
     * 
     * @return A new instance of FastChargingStation or SlowChargingStation.
     * @throws IllegalStateException if an invalid type is provided.
     */
    public ChargingStation build() {
        if ("Fast".equalsIgnoreCase(type)) {
            return new FastChargingStation(stationId, capacity, forbiddenTimeSlots, strategy);
        } else if ("Slow".equalsIgnoreCase(type)) {
            return new SlowChargingStation(stationId, capacity, forbiddenTimeSlots, strategy);
        } else {
            throw new IllegalStateException("Invalid charging station type.");
        }
    }
}
