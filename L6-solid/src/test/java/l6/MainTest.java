package l6;

import l6.exceptions.NotEnoughMoneyException;
import l6.model.*;
import org.junit.*;
import java.util.ArrayList;

import static l6.model.Banknote.*;
import static org.junit.Assert.*;

public class MainTest {

    private ATM atm = new ATM();

    @Test
    public void Test1() {
        ArrayList<Banknote> in = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            in.add(Hundred);
            in.add(Fifty);
            in.add(Ten);
            in.add(Five);
            in.add(One);
        }
        atm.addMoney(in);
        assertEquals(498, atm.getBalance());
    }

    @Test
    public void Test2() {
        ArrayList<Banknote> in = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            in.add(Five);
            in.add(One);
        }
        atm.addMoney(in);
        ArrayList<Banknote> out = null;
        try {
            out = atm.getMoney(20);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }
        Banknote[] expected = {Five, Five, Five, Five};
        assertArrayEquals(expected, out != null ? out.toArray() : new Object[0]);
    }
}