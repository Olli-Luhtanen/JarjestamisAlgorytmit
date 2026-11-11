import java.util.*;

public class SortTest {

    private interface FunctionPointer{
        void signature(int[] data);
    }

    private static final int CUTOFF = 24;

    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            int key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
    }

    public static void bubbleSort(int arr[]){
        int n=arr.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false)
                break;
        }
    }
    
    public static void arraySort(int[] data){
        Arrays.sort(data);
    }
    
    public static void shellSort(int[] data, int n){
        int i,j,k,h,v;
        int[] cols = { 1391376, 463792, 198768, 86961, 33936, 13776, 4592, 1968, 861, 336, 112, 48, 21, 7, 3, 1 };

        for(k=0; k<16; k++){
            h = cols[k];
            for(i=h; i<n; i++){
                v = data[i];
                j = i;
                while(j>=h && data[j-h]>v){
                    data[j] = data[j-h];
                    j = j - h;
                }
                data[j] = v;
            }
        }

    }

    //Helper functions for Optimized versions
    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    // Used in quicksort and mergesort for optimization
    private static void insertionSort(int[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++) {
                int v = a[i];
                int j = i - 1;
                while (j >= lo && a[j] > v) {
                    a[j + 1] = a[j];
                    j--;
                }
                a[j + 1] = v;
            }
    }

    public static void quickSort(int[] a, int lo, int hi){
        int i = lo, j = hi, h;

        int x = a[(lo + hi) / 2];

        do
        {
            while (a[i] < x) i++;
            while (a[j] > x) j--;
            if (i <= j)
            {
                h = a[i];
                a[i] = a[j];
                a[j] = h;
                i++; j--;
            }
        } while (i <= j);

        if (lo < j) quickSort(a, lo, j);
        if (i < hi) quickSort(a, i, hi);
    }

    public static void optimizedQuickSort(int[] a, int lo, int hi) {
        while (hi - lo >= CUTOFF) {

            // Kolmen mediaani: a[lo] <= a[mid] <= a[hi]
            int mid = (lo + hi) >>> 1;
            if (a[mid] < a[lo]) swap(a, mid, lo);
            if (a[hi]  < a[lo]) swap(a, hi, lo);
            if (a[hi]  < a[mid]) swap(a, hi, mid);
            int x = a[mid];

            int i = lo, j = hi;
            do {
                while (a[i] < x) i++;
                while (a[j] > x) j--;
                if (i <= j) {
                    swap(a, i, j);
                    i++; j--;
                }
            } while (i <= j);

            // Rekursio pienempään osaan, silmukka suurempaan
            if (j - lo < hi - i) {
                if (lo < j) optimizedQuickSort(a, lo, j);
                lo = i;
            } else {
                if (i < hi) optimizedQuickSort(a, i, hi);
                hi = j;
            }
        }
        insertionSort(a, lo, hi);
    }
    
    static void merge(int arr[], int l, int m, int r){
        
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int arr[], int l, int r){
        
        if (l < r) {

            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void optimizedMergeSort(int[] a) {
        if (a.length < 2) return;
        int[] tmp = new int[a.length / 2 + 1]; // varataan vain kerran
        optimizedMergeSort(a, tmp, 0, a.length - 1);
    }

    private static void optimizedMergeSort(int[] a, int[] tmp, int l, int r) {
        if (r - l < CUTOFF) {
            insertionSort(a, l, r);
            return;
        }
        int m = (l + r) >>> 1;
        optimizedMergeSort(a, tmp, l, m);
        optimizedMergeSort(a, tmp, m + 1, r);

        if (a[m] <= a[m + 1]) return;   // jo järjestyksessä

        optimizedMerge(a, tmp, l, m, r);
    }

    private static void optimizedMerge(int[] a, int[] tmp, int l, int m, int r) {
        int n1 = m - l + 1;
        System.arraycopy(a, l, tmp, 0, n1);   // vain vasen puolisko

        int i = 0, j = m + 1, k = l;
        while (i < n1 && j <= r) {
            if (tmp[i] <= a[j]) a[k++] = tmp[i++];
            else                a[k++] = a[j++];
        }
        while (i < n1) a[k++] = tmp[i++];
        // Oikean puoliskon jäännös on jo oikealla paikallaan
    }

    public static void printData(int[] data) {
        for (int x : data) {
            System.out.print(x + " ");
        }
        System.out.println("--------------------------");
        System.out.println();
    }

    public static int[] createAscendingData(int n) {
        int[] data = new int[n];

        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        return data;
    }

    public static int[] createDescendingData(int n) {
        int[] data = new int[n];

        for (int i = 0; i < data.length; i++) {
            data[i] = n - i;
        }

        return data;
    }

    public static int[] createRandomData(int n) {
        int[] data = new int[n];

        //Preset random seed for consistency
        Random gen = new Random(42);

        for (int i = 0; i < data.length; i++) {
            data[i] = gen.nextInt(n);
        }

        return data;
    }

    public static void main(String[] args) {
        int size = 100000;
        int[] randomData = createRandomData(size);
        int[] accendingData = createAscendingData(size);
        int[] decendingData = createDescendingData(size);

        FunctionPointer[] pointerArray = new  FunctionPointer[] {
            SortTest::insertionSort,
            SortTest::bubbleSort,
            SortTest::arraySort,
            (arr) -> SortTest.shellSort(arr, arr.length),
            (arr) -> SortTest.quickSort(arr, 0, arr.length - 1),
            (arr) -> SortTest.mergeSort(arr, 0, arr.length - 1),
            //Optimized last
            (arr) -> SortTest.optimizedQuickSort(arr, 0, arr.length - 1),
            SortTest::optimizedMergeSort

        };

        String[] functionNames = new String[] {
            "Insertion Sort",
            "Bubble Sort",
            "Array Sort",
            "Shell Sort",
            "Quick Sort",
            "Merge Sort",
            "Optimized Quick Sort",
            "Optimized Merge Sort"
        };

        for (int i = 0; i < pointerArray.length; i++) {
            FunctionPointer function = pointerArray[i];
            String functionName = functionNames[i];

            System.out.println(functionName);
            long time = SortMethodTester(function, randomData);
            System.out.println("Random data sorting took: " + time + " nanoseconds");

            time = SortMethodTester(function, accendingData);
            System.out.println("Ascending data sorting took " + time + " nanoseconds");

            time = SortMethodTester(function, decendingData);
            System.out.println("Descending data sorting took " + time + " nanoseconds \n");
        }

    }

    public static long SortMethodTester(FunctionPointer sorter, int[] original) {
        for (int i = 0; i < 200; i++) sorter.signature(original.clone()); // warmup

        long[] t = new long[30];
        for (int i = 0; i < t.length; i++) {
            int[] data = original.clone();
            long start = System.nanoTime();
            sorter.signature(data);
            t[i] = System.nanoTime() - start;
            if (!isSorted(data)) throw new IllegalStateException("Not in order!");

        }
        Arrays.sort(t);
        return t[t.length / 2]; // median
    }
    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }
}