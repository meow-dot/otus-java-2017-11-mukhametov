import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        MultithreadingSorter sorter1 = new MultithreadingSorter(3);
        MultithreadingSorter sorter2 = new MultithreadingSorter(4);
        int[] arr1 = {345, 47, 3567, 5, 567, 345, 37, 47567, 1234, 3456, 2356};
        int[] arr2 = {51, 47, 53, 5, 85, 345, 486, 47567, 385, 26};
        System.out.println(Arrays.toString(sorter1.sortArray(arr1)));
        System.out.println(Arrays.toString(sorter2.sortArray(arr1)));
        System.out.println(Arrays.toString(sorter1.sortArray(arr2)));
        System.out.println(Arrays.toString(sorter2.sortArray(arr2)));
    }
}
