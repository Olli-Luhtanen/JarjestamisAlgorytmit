import java.util.*;

public class SortTest {

    private interface FunctionPointer{
        void signature(int[] data);
    }

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
    
    public static void quickSort(int[] a, int lo, int hi)
    {
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

        Random gen = new Random();

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
            (arr) -> SortTest.mergeSort(arr, 0, arr.length - 1)
        };

        String[] functionNames = new String[] {
            "Insertion Sort",
            "Bubble Sort",
            "Array Sort",
            "Shell Sort",
            "Quick Sort",
            "Merge Sort"
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
        int[] data = original.clone();
        long start = System.nanoTime();
        sorter.signature(data);
        long stop = System.nanoTime();
        return stop-start;
    }
}