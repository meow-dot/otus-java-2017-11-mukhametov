package l7;

import l7.model.ATM;
import l7.model.Department;
import org.junit.Before;
import org.junit.Test;

import static l6.model.Banknote.*;
import static org.junit.Assert.*;

public class ATMDepartmentTest {

    private Department department = new Department();
    private ATM atm1 = new ATM(1, 2, 4, 2, 10);
    private ATM atm2 = new ATM(2, 2, 8, 2, 20);

    @Before
    public void setUp() {
        atm1.registerDepartment(department);
        atm2.registerDepartment(department);
    }

    @Test
    public void Test1() throws Exception {
        assertEquals(670, department.getOverallBalance());
    }

    @Test
    public void Test2() throws Exception {
        atm1.addMoney(Hundred, 2);
        atm2.addMoney(Hundred, 1);
        assertEquals(970, department.getOverallBalance());
    }

    @Test
    public void Test3() throws Exception {
        atm1.getMoney(200);
        atm2.getMoney(300);
        assertEquals(170, department.getOverallBalance());
    }

    @Test
    public void Test4() throws Exception {
        atm1.addMoney(Hundred,1);
        atm2.getMoney(300);
        department.recoverAllATMs();
        assertEquals(670, department.getOverallBalance());
    }
}