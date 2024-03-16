package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;

public class MergeSort<X extends Comparable<X>> extends SortWithHelper<X> {
    private final InsertionSort<X> insertionSort;
    private static final int CUTOFF = 7; // Cutoff to switch to insertion sort

    public MergeSort(Helper<X> helper) {
        super(helper);
        insertionSort = new InsertionSort<>(helper);
    }

    public MergeSort(int N, Config config) {
        super(DESCRIPTION + getConfigString(config), N, config);
        insertionSort = new InsertionSort<>(getHelper());
    }

    @Override
    public X[] sort(X[] xs, boolean makeCopy) {
        getHelper().init(xs.length);
        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
        X[] aux = Arrays.copyOf(result, result.length);
        sort(result, aux, 0, result.length - 1);
        return result;
    }

    @Override
    public void sort(X[] array, int from, int to) {
        // Ensure valid range
        if (from < 0 || to > array.length || from >= to) {
            throw new IllegalArgumentException("Invalid range specified");
        }
        X[] aux = Arrays.copyOf(array, array.length);
        sort(array, aux, from, to - 1); // Adjust for exclusive upper bound
    }

    private void sort(X[] a, X[] aux, int low, int high) {
        if (high <= low + CUTOFF - 1) {
            insertionSort.sort(a, low, high + 1); // Adjust for exclusive upper bound in InsertionSort
            return;
        }
        int mid = low + (high - low) / 2;
        sort(aux, a, low, mid);
        sort(aux, a, mid + 1, high);
        if (!getHelper().less(aux[mid + 1], aux[mid])) { // Optimization: check if already sorted
            System.arraycopy(aux, low, a, low, high - low + 1);
            return;
        }
        merge(a, aux, low, mid, high);
    }

    private void merge(X[] a, X[] aux, int low, int mid, int high) {
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > high) a[k] = aux[i++];
            else if (getHelper().less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static String getConfigString(Config config) {
        StringBuilder stringBuilder = new StringBuilder();
        if (config.getBoolean(MERGESORT, INSURANCE)) stringBuilder.append(" with insurance comparison");
        if (config.getBoolean(MERGESORT, NOCOPY)) stringBuilder.append(" with no copy");
        return stringBuilder.toString();
    }

    private static final String DESCRIPTION = "MergeSort";
    public static final String MERGESORT = "mergesort";
    public static final String NOCOPY = "nocopy";
    public static final String INSURANCE = "insurance";
}
