package org.algorithmsdatastructure.ecommercesearch;

public class SearchTest {
    public static void main(String[] args) {
        Product[] products = {
                new Product(101, "Wireless Mouse", "Electronics"),
                new Product(102, "Bluetooth Speaker", "Electronics"),
                new Product(103, "Cookware Set", "Home & Kitchen"),
                new Product(104, "Running Shoes", "Clothing"),
                new Product(105, "Java Programming Book", "Books"),
                new Product(106, "Building Blocks Set", "Toys"),
                new Product(107, "Office Chair", "Home & Kitchen"),
                new Product(108, "Smart Watch", "Electronics")
        };

        System.out.println("Linear Search Test:");
        testLinearSearch(products, 104); // exists
        testLinearSearch(products, 999); // does not exist

        System.out.println("\nBinary Search Test:");
        testBinarySearch(products, 104); // exists
        testBinarySearch(products, 999); // does not exist
    }

    private static void testLinearSearch(Product[] products, int targetId) {
        int index = LinearSearch.linearSearch(products, targetId);
        printResult("Linear", targetId, products, index);
    }

    private static void testBinarySearch(Product[] sortedProducts, int targetId) {
        int index = BinarySearch.binarySearch(sortedProducts, targetId);
        printResult("Binary", targetId, sortedProducts, index);
    }

    private static void printResult(String algorithm, int targetId, Product[] products, int index) {
        if (index != -1) {
            System.out.println(algorithm + " search for ID " + targetId
                    + " -> FOUND at index " + index + ": " + products[index]);
        } else {
            System.out.println(algorithm + " search for ID " + targetId + " -> NOT FOUND");
        }
    }
}
