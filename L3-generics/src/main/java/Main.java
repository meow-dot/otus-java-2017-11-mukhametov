import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> firstList = new MyArrayList<>();
        firstList.add("C");
        firstList.add("A");
        firstList.add("B");
        Collections.sort(firstList);

        MyArrayList<String> secondList = new MyArrayList<>();
        secondList.add("");
        secondList.add("");
        secondList.add("");
        Collections.copy(secondList, firstList); //do not work

        Collections.addAll(secondList,"F","E","D");
        Collections.sort(secondList);
        for (String string : secondList) {
            System.out.println(string);
        }
    }
}
