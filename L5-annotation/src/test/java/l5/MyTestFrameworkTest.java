package l5;

import l5.framework.annotations.*;

import static l5.framework.Assert.assertEquals;

public class MyTestFrameworkTest {

    @Test
    public void test1() {
        assertEquals(3, 3*2);
    }

    @Test
    public void test2() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test3() {
        assertEquals("Hello world!", new String("Hello world!"));

    }
}