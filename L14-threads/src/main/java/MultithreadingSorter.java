import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultithreadingSorter {

    private final int numberOfThreads;

    public MultithreadingSorter(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public int[] sortArray(int[] array) {
        return joinArrays(sortArrays(splitArray(array)));
    }

    private int[][] splitArray(int[] initialArray) {
        int lengthOfOne = initialArray.length / numberOfThreads;
        int lengthOfLast = initialArray.length - lengthOfOne * (numberOfThreads - 1);
        int[][] splittedArrays = new int[numberOfThreads][];

        int i = 0;
        for (int k = 0; k < numberOfThreads - 1; k++) {
            int[] temp = new int[lengthOfOne];
            System.arraycopy(initialArray, i, temp, 0, lengthOfOne);
            splittedArrays[k] = temp;
            i += lengthOfOne;
        }

        int[] last = new int[lengthOfLast];
        System.arraycopy(initialArray, i, last, 0, lengthOfLast);
        splittedArrays[numberOfThreads - 1] = last;

        return splittedArrays;
    }

    private int[][] sortArrays(int[][] splittedArrays) {
        List<Thread> threads = new ArrayList<>();

        for (int[] array : splittedArrays) {
            Thread thread = new Thread(() -> Arrays.sort(array));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return splittedArrays;
    }

    private int[] joinArrays(int[][] splittedArrays) {
        int[] result = splittedArrays[0];
        for (int i = 1; i < splittedArrays.length; i++) {
            result = joinArrays(result, splittedArrays[i]);
        }
        return result;
    }

    private int[] joinArrays(int[] left, int[]right) {
        int leftIndex = 0;
        int rightIndex = 0;

        int[] result = new int[left.length + right.length];
        int i = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] > right[rightIndex]) {
                result[i] = right[rightIndex];
                rightIndex++;
            } else {
                result[i] = left[leftIndex];
                leftIndex++;
            }
            i++;
        }

        if (leftIndex < left.length) {
            System.arraycopy(left, leftIndex, result, i, left.length - leftIndex);
        }
        if (rightIndex < right.length) {
            System.arraycopy(right, rightIndex, result, i, right.length - rightIndex);
        }

        return result;
    }
}
