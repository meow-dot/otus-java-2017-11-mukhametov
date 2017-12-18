package l5;

import l5.framework.annotations.Test;

import static l5.framework.Assert.assertEquals;

public class MyBinaryOperatorTest {

    @Test
    public void testSum() {
        MyBinaryOperator myBinaryOperator = new MyBinaryOperator();
        assertEquals(3, myBinaryOperator.sum(1, 2));
    }

    @Test
    public void testSum2() {
        MyBinaryOperator myBinaryOperator = new MyBinaryOperator();
        assertEquals(4, myBinaryOperator.sum(2, 3));
    }
}