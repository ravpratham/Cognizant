package org.algorithmsdatastructure.ecommercesearch;

public class BinarySearch {
    public static int binarySearch(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int midId = sortedProducts[mid].getProductId();

            if (midId == targetId) {
                return mid;
            } else if (midId < targetId) {
                    low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
