// FastChargingStation - Represents a fast charging station for electric vehicles

package model;

import java.util.List;

import service.strategy.ChargingStrategy;

public class FastChargingStation extends ChargingStation {
    
    /**
     * Constructor to initialize a fast charging station with given parameters.
     * 
     * @param stationId Unique identifier for the station
     * @param capacity Charging capacity in kilowatts (kW)
     * @param forbiddenTimeSlots List of time slots when charging is not allowed
     * @param strategy Charging strategy to be used for efficiency calculation
     */
    public FastChargingStation(int stationId, int capacity, List<OccupiedTimeSlot> forbiddenTimeSlots, ChargingStrategy strategy) {
        super(stationId, capacity, forbiddenTimeSlots, strategy);
    }

    /**
     * Calculates the charging efficiency based on the assigned strategy.
     * 
     * @param chargingHours Number of hours the station is used for charging
     * @return Calculated efficiency value as an integer
     */
    @Override
    public int getChargingEfficiency(double chargingHours) {
        return (int) strategy.calculateEfficiency(this, chargingHours);
    }
}
