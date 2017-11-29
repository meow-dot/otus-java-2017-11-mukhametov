package l2;

public class Main {

    public static void main(String[] args) throws Exception {

        new MemoryCalculator("");
        new MemoryCalculator(1);
        new MemoryCalculator(new Object());
        new MemoryCalculator(new int[0]);
    }
}