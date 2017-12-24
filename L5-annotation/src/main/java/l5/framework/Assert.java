package l5.framework;

public class Assert {
    public static void assertEquals(Object expected, Object actual) {
        if (actual.equals(expected))
            System.out.println("The assert is true!");
         else
             System.out.println("The assert is false!");
        System.out.println("Expected: " + expected.toString());
        System.out.println("Actual: " + expected.toString());
    }
}
