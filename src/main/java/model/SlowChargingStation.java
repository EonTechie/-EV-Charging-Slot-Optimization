// SlowChargingStation - Represents a charging station with slower charging speed and cost-effective strategy.

package model;
import java.util.List;

import service.strategy.ChargingStrategy;

public class SlowChargingStation extends ChargingStation {

    /**
     * Constructor for creating a slow charging station.
     * 
     * @param stationId         Unique identifier for the charging station.
     * @param capacity          Maximum capacity of the station in kilowatts (kW).
     * @param forbiddenTimeSlots List of time slots when the station cannot be used.
     * @param strategy          The charging strategy applied to the station.
     */
    public SlowChargingStation(int stationId, int capacity, List<OccupiedTimeSlot> forbiddenTimeSlots, ChargingStrategy strategy) {
        super(stationId, capacity, forbiddenTimeSlots, strategy);
    }

    /**
     * Calculates the charging efficiency for the given number of hours.
     * This method uses the assigned charging strategy to determine efficiency.
     *
     * @param chargingHours Number of hours the station is in use.
     * @return The calculated charging efficiency as an integer value.
     */
    @Override
    public int getChargingEfficiency(double chargingHours) {
        return (int) strategy.calculateEfficiency(this, chargingHours);
    }
}
