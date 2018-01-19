package l7;

import l7.model.ATM;
import l7.model.Department;
import org.junit.Before;
import org.junit.Test;

import static l7.model.Banknote.*;
import static org.junit.Assert.*;

public class ATMDepartmentTest {

    private Department department = new Department();
    private ATM atm1 = new ATM()
            .setBanknote(Hundred, 2)
            .setBanknote(Fifty, 2)
            .setBanknote(Ten, 2)
            .setBanknote(Five, 2)
            .setBanknote(One, 2);
    private ATM atm2 = new ATM()
            .setBanknote(Hundred, 3)
            .setBanknote(Fifty, 3)
            .setBanknote(Ten, 3)
            .setBanknote(Five, 3)
            .setBanknote(One, 3);

    @Before
    public void setUp() {
        atm1.registerDepartment(department);
        atm2.registerDepartment(department);
    }

    @Test
    public void Test1() throws Exception {
        assertEquals(830, department.getOverallBalance());
    }

    @Test
    public void Test2() throws Exception {
        atm1.addMoney(Hundred, 2);
        atm2.getMoney(300);
        assertEquals(730, department.getOverallBalance());
    }

    @Test
    public void Test3() throws Exception {
        atm1.getMoney(200);
        atm2.addMoney(Hundred, 3);
        assertEquals(930, department.getOverallBalance());
    }

    @Test
    public void Test4() throws Exception {
        assertEquals(830, department.getOverallBalance());
        atm1.addMoney(Hundred,1);
        atm2.getMoney(300);
        department.recoverAllATMs();
        assertEquals(830, department.getOverallBalance());
    }
}