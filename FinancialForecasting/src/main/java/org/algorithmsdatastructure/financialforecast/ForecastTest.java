package org.algorithmsdatastructure.financialforecast;

public class ForecastTest {

    public static void main(String[] args) {

        System.out.println("--- Basic Forecast (5 years of past growth rates) ---");
        double presentValue = 10_000.0;
        // Example: a company's revenue grew by these rates over the last
        // 5 years; we're using that same trend to project forward.
        double[] pastGrowthRates = {0.05, 0.07, 0.03, 0.06, 0.04};

        double recursiveResult = FinancialForecaster.calculateFutureValue(presentValue, pastGrowthRates, 0);

        System.out.printf("Present value:        $%,.2f%n", presentValue);
        System.out.printf("Recursive forecast:    $%,.2f%n", recursiveResult);

    }
}
