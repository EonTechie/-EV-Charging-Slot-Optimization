// ChargingStation - Abstract class representing an electric vehicle charging station

package model;

import java.util.List;

import service.strategy.ChargingStrategy;

public abstract class ChargingStation {

    // Unique identifier for the charging station
    protected final int stationId;
    
    // Maximum charging capacity in kilowatts (kW)
    protected final int capacity;  
    
    // List of time slots when the station is not available for charging
    protected final List<OccupiedTimeSlot> forbiddenTimeSlots;
    
    // Charging strategy applied to the station (e.g., fast charging, cost-effective charging)
    protected ChargingStrategy strategy;  

    /**
     * Constructor to initialize the charging station with essential parameters.
     * 
     * @param stationId Unique identifier for the station
     * @param capacity Charging capacity in kW
     * @param forbiddenTimeSlots Time slots when charging is not allowed
     * @param strategy Charging strategy to be used
     */
    protected ChargingStation(int stationId, int capacity, List<OccupiedTimeSlot> forbiddenTimeSlots, ChargingStrategy strategy) {
        this.stationId = stationId;
        this.capacity = capacity;
        this.forbiddenTimeSlots = forbiddenTimeSlots;
        this.strategy = strategy;
    }

    /**
     * Sets a new charging strategy for the station at runtime.
     * 
     * @param strategy New charging strategy to be applied
     */
    public void setStrategy(ChargingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Abstract method to calculate the charging efficiency based on the provided hours.
     * Concrete classes must implement this method.
     * 
     * @param chargingHours Number of hours the station is used for charging
     * @return Efficiency score of the charging process
     */
    public abstract int getChargingEfficiency(double chargingHours);

    /**
     * Retrieves the charging capacity of the station.
     * 
     * @return The capacity in kW
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Retrieves the unique identifier of the station.
     * 
     * @return The station ID
     */
    public int getStationId() {
        return stationId;
    }

    /**
     * Retrieves the list of time slots when the station is unavailable.
     * 
     * @return List of occupied time slots
     */
    public List<OccupiedTimeSlot> getForbiddenTimeSlots() {
        return forbiddenTimeSlots;
    }

    /**
     * Retrieves the current charging strategy applied to the station.
     * 
     * @return The charging strategy in use
     */
    public ChargingStrategy getStrategy() {
        return strategy;
    }

    /**
     * Provides a string representation of the charging station details.
     * 
     * @return Formatted string containing station details
     */
    @Override
    public String toString() {
        return String.format("Station ID: %d, Capacity: %d kW, Forbidden Slots: %s, Strategy: %s",
                stationId, capacity, forbiddenTimeSlots, strategy.getClass().getSimpleName());
    }
}
