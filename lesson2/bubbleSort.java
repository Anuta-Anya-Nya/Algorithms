package lesson2;

public class bubbleSort {
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

    public static void main(String[] args) {
        int[] array = { 1, 4, 6, 8, 10, 99, 75, 3 };
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        bubbleSort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
