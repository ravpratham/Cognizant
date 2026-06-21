# Linear Search — Explanation & Analysis (with Binary Search Comparison)

## What is Linear Search?

Linear Search (also called Sequential Search) is the simplest search
algorithm: it checks every element of a collection, one at a time, in
order, until it either finds the target value or reaches the end of the
collection without finding it. It makes no assumptions about how the data
is arranged — it works equally well on sorted or unsorted data, which is
its main advantage over more specialized algorithms like binary search.

## How It Works

1. Start at the first element of the array (index 0).
2. Compare the current element to the target value.
3. If it matches, return the current index — the search is done.
4. If it doesn't match, move to the next element and repeat.
5. If the end of the array is reached with no match, the target is not
   present — return `-1`.

## Implementation (`LinearSearch.java`)

```java
public class LinearSearch {

    public static int search(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId() == targetId) {
                return i;
            }
        }
        return -1;
    }
}
```

Each loop iteration performs exactly one comparison (`products[i].getProductId() == targetId`),
which is the unit of work counted in the analysis below.

---

## Time Complexity Analysis

Let `n` be the number of products in the array.

| Case | Complexity | When it happens | Comparisons performed |
|------|-----------|------------------|------------------------|
| **Best Case** | **O(1)** | The target is the very first element checked (index 0) | 1 |
| **Average Case** | **O(n)** | The target is found at a random position, so on average about half the array is scanned | ≈ n / 2 |
| **Worst Case** | **O(n)** | The target is the last element, or isn't present in the array at all | n |

### Why each case is what it is

- **Best case — O(1):** If you get lucky and the value you're looking for
  happens to sit at index 0, the loop matches on its very first comparison
  and returns immediately. This doesn't depend on the size of the array at
  all, hence constant time.
- **Average case — O(n):** Assuming the target is equally likely to be at
  any position in the array, the expected number of comparisons is the
  average of 1, 2, 3, ... n, which works out to (n + 1) / 2. Dropping the
  constant factor (since Big O ignores constants), this simplifies to O(n).
- **Worst case — O(n):** If the target is the last element, or doesn't
  exist in the array at all (e.g., searching for a product ID that was
  never added to the catalog), the algorithm has no way to know that
  without checking every single element first. This is the bound most
  commonly quoted when people refer to "the" time complexity of linear
  search.

### Growth in practice

| Catalog size (n) | Worst-case comparisons |
|---|---|
| 100 | 100 |
| 10,000 | 10,000 |
| 1,000,000 | 1,000,000 |

The number of comparisons scales **linearly** with the size of the
catalog — double the products, and you (at most) double the work. This is
the defining trait of O(n) and the reason linear search becomes
noticeably slower as a catalog grows large.

---

## Space Complexity

**O(1)** — Linear search is done in place. It only needs a single loop
counter (`i`) regardless of how large the input array is; it doesn't
allocate any additional data structures that grow with `n`.

---

## Advantages

- Works on **unsorted** data — no pre-processing or maintenance required.
- Simple to implement and easy to reason about.
- Efficient for **small** collections, where the overhead of more complex
  algorithms isn't worth it.
- Works on any data structure that can be iterated (arrays, linked lists,
  etc.), not just ones that support random access.

## Disadvantages

- Becomes slow on **large** collections, since worst-case performance
  scales directly with the number of elements.
- Doesn't take advantage of any ordering in the data, even if the data
  happens to already be sorted.

---

## Comparison with Binary Search

Binary search is the natural point of comparison: instead of checking
every element, it repeatedly checks the **middle** element of a sorted
range and discards the half that can't contain the target, cutting the
remaining search space in half on every step.

```java
public class BinarySearch {

    public static int search(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
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
```

The trade-off: binary search **requires the array to be sorted** by
`productId` first. Linear search has no such requirement.

### Time complexity comparison

| Algorithm | Best Case | Average Case | Worst Case | Requires Sorted Data? |
|---|---|---|---|---|
| **Linear Search** | O(1) | O(n) | O(n) | No |
| **Binary Search** | O(1) | O(log n) | O(log n) | Yes |

Binary search's best and worst cases sit much closer together than linear
search's, because each comparison eliminates half the remaining data
rather than just one element.

### Growth side-by-side

| Catalog size (n) | Linear Search (worst case) | Binary Search (worst case) |
|---|---|---|
| 100 | 100 comparisons | ~7 comparisons |
| 10,000 | 10,000 comparisons | ~14 comparisons |
| 100,000 | 100,000 comparisons | ~17 comparisons |
| 10,000,000 | 10,000,000 comparisons | ~24 comparisons |

### Empirical results

Running both algorithms against a 100,000-product catalog (sorted by
`productId`, with linear search run against an unsorted copy of the same
data) produced:

```
Scenario        Algorithm  Found Index   Comparisons   Time (ns)
-----------------------------------------------------------------
Best case       Linear     0             1             1237274
                Binary     50000         16            231536

Average case    Linear     64236         64237         5911441
                Binary     49164         15            7047

Worst case      Linear     -1            100000         3545865
                Binary     -1            17             8050
```

These match the theory closely: log₂(100,000) ≈ 16.6, and binary search
needed 15–17 comparisons in every scenario, while linear search needed up
to 100,000 in the worst case — over **5,800× more comparisons** on the
same catalog. That gap only widens as the catalog grows further.

### Which is more suitable for an e-commerce platform?

**Binary search is the better fit for ID-based lookups at scale.** Its
O(log n) growth keeps search times short and predictable even as a
catalog grows into the millions, which matters for consistent response
times under load. The cost is that the data must be kept sorted — either
maintained in sorted order as products are added/removed, or sorted
before each batch of searches (an O(n log n) operation that would erase
the benefit if repeated too often).

Linear search still has a place: searching by a field that isn't sorted
or indexed (e.g., scanning free-text descriptions), very small product
lists where the overhead of sorting isn't worth it, or one-off searches
on data that won't be searched again.

In practice, production e-commerce platforms rarely call a raw binary
search directly — they build on the same underlying idea through
**hash-based indexes** (O(1) average-case lookups by ID/SKU), **B-tree
database indexes** (binary search extended to disk-based storage, also
supporting range queries like "price between $10 and $50"), and
**inverted indexes** (e.g., Elasticsearch/Lucene) for free-text search
across names and descriptions. Binary search is the conceptual foundation
those structures build on.

---

## When to Use Linear Search

Linear search is the right tool when:
- The collection is small, so O(n) is fast enough in practice.
- The data is unsorted and isn't worth sorting just to perform one search.
- The search field isn't the one the data happens to be ordered by (e.g.,
  searching by product name when the array is only sorted by ID).

For large, frequently-searched, ID-ordered catalogs — like a real
e-commerce product list — a faster approach such as binary search (on
sorted data) or a hash-based index is generally preferred instead.