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

        System.out.println("\n--- Long Forecast (1,000 periods of small growth) ---");
        double[] longRates = buildConstantRates(1000, 0.005); // e.g. monthly compounding ~ 83 years
        double longRecursive = FinancialForecaster.calculateFutureValue(1000, longRates, 0);

        System.out.printf("Recursive (1,000 periods): $%,.2f%n", longRecursive);

        System.out.println("\n--- Stress Test: Why the recursive version doesn't scale ---");
        int hugePeriods = 100_000;
        double[] hugeRates = buildConstantRates(hugePeriods, 0.0001);

        System.out.println("Attempting recursive calculation over " + hugePeriods + " periods...");
        try {
            FinancialForecaster.calculateFutureValue(1000, hugeRates, 0);
            System.out.println("Recursive version succeeded (unexpected on most JVMs).");
        } catch (StackOverflowError e) {
            System.out.println("Recursive version FAILED: StackOverflowError");
            System.out.println("(Each pending call stays on the call stack until the base");
            System.out.println(" case is reached, so " + hugePeriods + " periods means " + hugePeriods);
            System.out.println(" stack frames alive at once.)");
        }

        System.out.println("Attempting the same calculation iteratively...");
    }

    private static double[] buildConstantRates(int count, double rate) {
        double[] rates = new double[count];
        for (int i = 0; i < count; i++) {
            rates[i] = rate;
        }
        return rates;
    }
}
