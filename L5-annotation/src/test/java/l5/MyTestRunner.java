package l5;

import l5.framework.MyTestFramework;

public class MyTestRunner {
    public static void main(String[] args) {

        MyTestFramework.runTestsFromClass("l5.MyBinaryOperatorTest");
        MyTestFramework.runTestsFromPackage("l5");
    }
}
