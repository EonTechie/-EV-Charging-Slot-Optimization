// ChargingStrategy - Defines a strategy interface for calculating the charging efficiency of a charging station.

package service.strategy;

import model.ChargingStation;

public interface ChargingStrategy {

    /**
     * Calculates the charging efficiency of a given charging station over a specified duration.
     * 
     * @param station       The charging station for which efficiency is calculated.
     * @param chargingHours The number of hours the station is utilized for charging.
     * @return The calculated efficiency as a double value.
     */
    double calculateEfficiency(ChargingStation station, double chargingHours);
}
