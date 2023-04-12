import java.util.Date;
import java.util.Random;

import javax.sql.StatementEventListener;
import javax.swing.plaf.synth.SynthScrollBarUI;

public class App {
    public static void main(String[] args) throws Exception {
        // for (int i = 10000; i <= 100000; i = i + 10000) {
        // int[] arr = new int[i];
        // for (int j = 0; j < arr.length; j++) {
        // arr[j] = (int) (Math.random() * 10000);
        // }
        // int[] arr2 = arr.clone();

        // Date startDate = new Date();
        // bubbleSort(arr);
        // Date endDate = new Date();
        // long bubbleSortDuration = endDate.getTime() - startDate.getTime();

        // startDate = new Date();
        // quickSort(arr2, 0, arr2.length - 1);
        // ;
        // endDate = new Date();
        // long quickSortDuration = endDate.getTime() - startDate.getTime();

        // System.out.printf("i, %s, bubble sort duration: %s, Quick sort duration:
        // %s\n", i, bubbleSortDuration,
        // quickSortDuration);
        // }
        int[] newArray = new int[10];
        for (int index = 0; index < newArray.length; index++) {
            newArray[index] = new Random().nextInt(0, 10);
        }
        for (int value : newArray) {
            System.out.print(value + " ");
        }
        int val = 9; // newArray[newArray.length/2];
        System.out.println("Find value " + val);
        quickSort(newArray, 0, newArray.length - 1);
        for (int value : newArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println(binarySearch(newArray, val, 0, newArray.length - 1));

    }

    public static void quickSort(int[] array, int startPosition, int endPosition) {
        int leftPosition = startPosition;
        int rightPosition = endPosition;
        int pivot = array[(endPosition + startPosition) / 2];
        do {
            while (array[leftPosition] < pivot) {
                leftPosition++;
            }
            while (array[rightPosition] > pivot) {
                rightPosition--;
            }
            if (leftPosition <= rightPosition) {
                if (leftPosition < rightPosition) {
                    int temp = array[leftPosition];
                    array[leftPosition] = array[rightPosition];
                    array[rightPosition] = temp;
                }
                leftPosition++;
                rightPosition--;
            }
        } while (leftPosition <= rightPosition);
        if (leftPosition < endPosition) {
            quickSort(array, leftPosition, endPosition);
        }
        if (startPosition < rightPosition) {
            quickSort(array, startPosition, rightPosition);
        }
    }

    public static void bubbleSort(int[] array) {
        boolean needSort;
        do {
            needSort = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    needSort = true;
                }
            }
        } while (needSort);
    }

    public static int binarySearch(int[] array, int value, int minPos, int maxPos) {
        if (minPos > maxPos) {
            return -1;
        }
        int midPoint = minPos + (maxPos - minPos) / 2;
        if (array[midPoint] == value) {
            return midPoint;
        } else if (array[midPoint] < value) {
            return binarySearch(array, value, midPoint + 1, maxPos);
        } else {
            return binarySearch(array, value, minPos, midPoint - 1);
        }
    }

    public static int lineSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static void heapify(int[] array, int sizeHeap, int rootIndex) {
        int largestInd = rootIndex;
        int leftChildInd = 2 * rootIndex + 1;
        int rightChildInd = 2 * rootIndex + 2;

        if(leftChildInd < sizeHeap && array[leftChildInd]>array[largestInd]){
            largestInd = leftChildInd;
        }

        if(rightChildInd < sizeHeap && array[leftChildInd]>array[largestInd]){
            largestInd = leftChildInd;
        }

        if(largestInd != rootIndex){
            int temp = array[rootIndex];
            array[rootIndex] = array[largestInd];
            array[largestInd] = temp;
            heapify(array, sizeHeap, largestInd);
        }        
    }

    public static void heapifySort(int[] array) {
        for(int i=array.length/2-1; i>=0; i--){
            heapify(array, array.length, i);
        }
        for (int i = array.length-1; i <=0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

}
