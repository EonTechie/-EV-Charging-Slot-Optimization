// RenewableEnergyDecorator - Implements the Decorator pattern to enhance charging station functionality with renewable energy support.

package service.decorator;

import model.ChargingStation;

public class RenewableEnergyDecorator extends ChargingStation {

    // The original charging station being decorated with additional functionality.
    private final ChargingStation decoratedStation;

    /**
     * Constructor to wrap an existing ChargingStation with renewable energy enhancements.
     * 
     * @param station The charging station to be decorated.
     */
    public RenewableEnergyDecorator(ChargingStation station) {
        super(station.getStationId(), station.getCapacity(), station.getForbiddenTimeSlots(), station.getStrategy());
        this.decoratedStation = station;
    }

    /**
     * Calculates the charging efficiency of the station with an added efficiency boost 
     * due to the inclusion of renewable energy.
     * 
     * @param chargingHours Number of hours the station is in use.
     * @return Enhanced efficiency value by adding an extra boost.
     */
    @Override
    public int getChargingEfficiency(double chargingHours) {
        return decoratedStation.getChargingEfficiency(chargingHours) + 50;
    }

    /**
     * Provides a string representation of the charging station, 
     * indicating that it includes renewable energy support.
     * 
     * @return Formatted string with renewable energy enhancement label.
     */
    @Override
    public String toString() {
        return decoratedStation.toString() + " + Renewable Energy";
    }
}
