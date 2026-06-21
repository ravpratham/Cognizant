package org.algorithmsdatastructure.financialforecast;

public class FinancialForecaster {
    public static double calculateFutureValue(double presentValue, double[] growthRates, int period) {
        // Base case
        if (period == growthRates.length) {
            return presentValue;
        }

        // Apply this period's growth, then recurse on what remains.
        double grownValue = presentValue * (1 + growthRates[period]);
        return calculateFutureValue(grownValue, growthRates, period + 1);
    }

}
