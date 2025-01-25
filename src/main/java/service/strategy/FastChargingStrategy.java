// FastChargingStrategy - Implements a fast charging strategy with higher speed and cost.

package service.strategy;

import model.ChargingStation;

public class FastChargingStrategy implements ChargingStrategy {

    // Cost per kilowatt-hour (kWh) in USD for fast charging
    private static final double COST_PER_KW = 0.50; // 50 cents per kW

    // Multiplier to indicate that fast charging is 50% quicker than normal
    private static final double SPEED_MULTIPLIER = 1.5;  // 50% faster charging

    /**
     * Calculates the charging efficiency based on the fast charging strategy.
     * Efficiency is determined by dividing the total energy provided by the total cost incurred.
     * 
     * @param station       The charging station for which efficiency is calculated.
     * @param chargingHours The number of hours the station is utilized for charging.
     * @return The efficiency value, representing energy output per cost.
     */
    @Override
    public double calculateEfficiency(ChargingStation station, double chargingHours) {
        // Calculate the total energy provided considering the speed multiplier
        double totalEnergy = station.getCapacity() * chargingHours * SPEED_MULTIPLIER;

        // Calculate the total cost incurred for the energy provided
        double totalCost = totalEnergy * COST_PER_KW;

        // Efficiency: Energy provided per cost
        return totalEnergy / totalCost;
    }
}
