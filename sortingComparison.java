import java.io.*;
import java.util.*;

/**
 *
 * @author ethan
 */
public class sortingComparison {
    
    public static int[] list1;
    public static int[] list2;
    public static int[] list3;
    public static int[] list4;
    public static int[] list5;
    public static int[] list6;
    
    public static void verifySorts(int list[][], int numElements) {
        for (int n = 0; n < 6; n++) {
            for (int i = 0; i < numElements-1; i++) {
                if (list[n][i] > list[n][i+1]) {
                    System.out.printf("Sort %n failed!", n+1);
                    System.exit(2);
                }
            }
        }
        System.out.println("      Sort validated");
        return;
    }
    
    /**
     * Bubble-Sort-Algorithm sorting method.
     * 
     * @param list 
     */
    private static void bubbleSort(int[] list) {
        int n = list.length;
        for (int i = 0; i < n -1; i++) {
            for (int j = 0; j < ((n -1) -i); j++) {
                if(list[j] > list[j+1]) {
                    int temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
    }

    /**
     * Selection-Sort-Algorithm sorting method.
     *
     * @param list
     */
    private static void selectionSort(int[] list) {
        int n = list.length;
        for (int fill = 0; fill < n-1; fill++) {
            int posMin = fill;
            for (int next = fill+1; next < n; next++) {
                if (list[next] < list[posMin]) {
                    posMin = next;
                }
            }
            int temp = list[fill];
            list[fill] = list[posMin];
            list[posMin] = temp;
        }
    }
    
    /**
     * Insertion-Sort-Algorithm sorting method.
     * 
     * @param list 
     */
    private static void insertionSort(int[] list) {
        for (int nextPos = 1; nextPos < list.length; nextPos++) {
            int nextVal = list[nextPos];
            while (nextPos > 0 && nextVal < list[nextPos -1]) {
                list[nextPos] = list[nextPos -1];
                nextPos--;
            }
            list[nextPos] = nextVal;
        }
    }
    
    /**
     * Helper method for mergeSort
     *
     * @param list
     * @param list
     * @param list
     */
    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            }
            else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < left.length) {
            result[k++] = right[j++];
        }
    }
    
    /**
     * Merge-Sort-Algorithm sorting method.
     *
     * @param list
     */
    private static void mergeSort(int[] list) {
        if (list.length > 1) {
            int mid = list.length/2;
            int[] left = new int[mid];
            int[] right = new int[list.length - mid];
            System.arraycopy(list, 0, left, 0, mid);
            System.arraycopy(list, mid, right, 0, list.length-mid);
            mergeSort(left);
            mergeSort(right);
            merge(list, left, right);
        } 
    }
    
    /**
     * Helper method used in shellSort.
     * NOT used in insertionSort.
     *
     * @param list
     * @param int
     * @param int
     */
    private static void insert(int[] list, int nextPos, int gap) {
        int nextVal = list[nextPos];
        while ((nextPos > gap-1) && (nextVal < list[nextPos - gap])) {
            list[nextPos] = list[nextPos - gap];
            nextPos -= gap;
        }
        list[nextPos] = nextVal;
    }
    
    /**
     * Shell-Sort-Algorithm sorting method.
     *
     * @param list
     */
    private static void shellSort(int[] list) {
        int gap = list.length / 2;
        while (gap > 0) {
            for (int nextPos = gap; nextPos < list.length; nextPos++) {
                insert(list, nextPos, gap);   
            }
            if (gap == 2) {
                gap = 1;
            }
            else {
                gap = (int) (gap / 2.2);
            }
        }
    }
    
    /**
     * Helper method for quickSort
     * 
     * @param list
     * @param first
     * @param last
     * @return 
     */
    private static int partition(int[] list, int first, int last) {
        int pivot = list[first];
        int up = first;
        int down = last;
        do {
            while ((up < last) && (pivot >= list[up])) {
                up++;
            }
            while (pivot < list[down]) {
                down--;
            }
            if (up < down) {
                int temp = list[up];
                list[up] = list[down];
                list[down] = temp;
            }
        } while (up < down);
        int temp = list[first];
        list[first] = list[down];
        list[down] = temp;
        return down;
    }
    
    /**
     * Quick-Sort-Algorithm sorting method
     * 
     * @param list
     * @param first
     * @param last 
     */
    private static void quickSort(int[] list, int first, int last) {
        if (first < last) {
            int partitionIndex = partition(list, first, last);
            quickSort(list, first, partitionIndex -1);
            quickSort(list, partitionIndex +1, last);
        }
    }
    
    public static double[] sortingRun(java.util.Random RNG, int numElements) {
        
        double[] results = new double[6];
        double tempStartTime;
        double tempEndTime;
        
        //assign new, random integers to each place in list
        for (int i = 0; i < numElements; i++) {
            list1[i] = list2[i] = list3[i] = RNG.nextInt(25000);
        }
        //perform the actual sorts and record timing results
        tempStartTime = System.currentTimeMillis();
        bubbleSort(list1);
        tempEndTime = System.currentTimeMillis();
        results[0] = (tempEndTime - tempStartTime);
        System.out.println("   Bubble Sort time: " + results[0]);

        tempStartTime = System.currentTimeMillis();
        selectionSort(list2);
        tempEndTime = System.currentTimeMillis();
        results[1] = (tempEndTime - tempStartTime);
        System.out.println("   Selection Sort time: " + results[1]);
        
        tempStartTime = System.currentTimeMillis();
        insertionSort(list3);
        tempEndTime = System.currentTimeMillis();
        results[2] = (tempEndTime - tempStartTime);
        System.out.println("   Insertion Sort time: " + results[2]);

        tempStartTime = System.currentTimeMillis();
        mergeSort(list4);
        tempEndTime = System.currentTimeMillis();
        results[3] = (tempEndTime - tempStartTime);
        System.out.println("   Merge Sort time: " + results[3]);

        tempStartTime = System.currentTimeMillis();
        shellSort(list5);
        tempEndTime = System.currentTimeMillis();
        results[4] = (tempEndTime - tempStartTime);
        System.out.println("   Shell Sort time: " + results[4]);
        
        tempStartTime = System.currentTimeMillis();
        quickSort(list6, 0, numElements-1);
        tempEndTime = System.currentTimeMillis();
        results[5] = (tempEndTime - tempStartTime);
        System.out.println("   Quick Sort time: " + results[5]);
        
        //prepare and send sorted lists to be validated
        int allLists[][] = new int[6][];
        allLists[0] = list1;
        allLists[1] = list2;
        allLists[2] = list3;
        allLists[3] = list4;
        allLists[4] = list5;
        allLists[5] = list6;
        verifySorts(allLists, numElements);
        return results;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.util.Random RNG = new java.util.Random();
        
        final String OUTPUT_FILE = "output/sortResults.txt";
        final int numElements = 12500;
        list1 = new int[numElements];
        list2 = new int[numElements];
        list3 = new int[numElements];
        list4 = new int[numElements];
        list5 = new int[numElements];
        list6 = new int[numElements];
        double[][] results = new double[4][6];
        
        //Run 1
        System.out.println("Starting sort #1");
        results[0] = sortingRun(RNG, numElements);
        //Run 2
        System.out.println("\nStarting sort #2");
        results[1] = sortingRun(RNG, numElements);
        //Run 3
        System.out.println("\nStarting sort #3");
        results[2] = sortingRun(RNG, numElements);
        //averages Results
        results[3][0] = (results[0][0] + results[1][0] + results[2][0])/3;
        results[3][1] = (results[0][1] + results[1][1] + results[2][1])/3;
        results[3][2] = (results[0][2] + results[1][2] + results[2][2])/3;
        results[3][3] = (results[0][3] + results[1][3] + results[2][3])/3;
        results[3][4] = (results[0][4] + results[1][4] + results[2][4])/3;
        results[3][5] = (results[0][5] + results[1][5] + results[2][5])/3;
        //Print results to file
        try {
            FileWriter fileWriter = new FileWriter(OUTPUT_FILE);
            PrintWriter outputWriter = new PrintWriter(fileWriter);
            
            outputWriter.printf("%15s%n", "SORTING RESULTS");
            outputWriter.printf("%-15s%27s%n", "12500 Integers",
                                "Time in Miliseconds");
            outputWriter.println("---------------");
            outputWriter.printf("%-15s%9s%9s%9s%10s%n",
                                "", "Run 1", "Run 2", "Run 3", "Average");
            outputWriter.printf("%-15s%9.1f%9.1f%9.1f%10.1f%n",
                                "Bubble Sort", results[0][0], results[1][0],
                                results[2][0], results[3][0]);
            outputWriter.printf("%-15s%9.1f%9.1f%9.1f%10.1f%n",
                                "Selection Sort", results[0][1], results[1][1],
                                results[2][1], results[3][1]);
            outputWriter.printf("%-15s%9.1f%9.1f%9.1f%10.1f%n",
                                "Insertion Sort", results[0][2], results[1][2],
                                results[2][2], results[3][2]);
            outputWriter.printf("%-15s%9.1f%9.1f%9.1f%10.1f%n",
                                "Merge Sort", results[0][3], results[1][3],
                                results[2][3], results[3][3]);
            outputWriter.printf("%-15s%9.1f%9.1f%9.1f%10.1f%n",
                                "Shell Sort", results[0][4], results[1][4],
                                results[2][4], results[3][4]);
            outputWriter.printf("%-15s%9.1f%9.1f%9.1f%10.1f%n",
                                "Quick Sort", results[0][5], results[1][5],
                                results[2][5], results[3][5]);
            outputWriter.printf("");
            outputWriter.close();
        }
        catch (IOException IOE) {
            System.out.println(IOE.getMessage());
            System.exit(3);
        }
    }
}
