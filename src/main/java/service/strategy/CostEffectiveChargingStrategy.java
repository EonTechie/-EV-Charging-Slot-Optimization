// CostEffectiveChargingStrategy - Implements a cost-efficient charging strategy for electric vehicle stations.

package service.strategy;

import model.ChargingStation;

public class CostEffectiveChargingStrategy implements ChargingStrategy {

    // Cost per kilowatt-hour (kWh) in USD
    private static final double COST_PER_KW = 0.20; // 20 cents per kW

    /**
     * Calculates the charging efficiency based on cost-effectiveness.
     * The efficiency is determined by dividing the station's capacity by the total cost.
     * 
     * @param station       The charging station for which efficiency is calculated.
     * @param chargingHours The number of hours the station is utilized for charging.
     * @return The efficiency value, representing power output per cost.
     */
    @Override
    public double calculateEfficiency(ChargingStation station, double chargingHours) {
        double totalCost = station.getCapacity() * chargingHours * COST_PER_KW;
        return station.getCapacity() / totalCost;  // Efficiency: Power per cost
    }
}
