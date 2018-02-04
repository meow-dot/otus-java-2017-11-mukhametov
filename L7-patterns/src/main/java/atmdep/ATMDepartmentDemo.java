package atmdep;

import atmdep.commands.*;
import atmdep.models.ATM;
import atmdep.models.Department;

import static atmdep.models.Banknote.*;

public class ATMDepartmentDemo {

    public static void main(String[] args) throws Exception {
        Department dep = new Department();
        ATM atm1 = new ATM()
                .setBanknote(Hundred, 6)
                .setBanknote(Fifty, 6)
                .setBanknote(Ten, 6)
                .setBanknote(Five, 6)
                .setBanknote(One, 10);
        ATM atm2 = new ATM()
                .setBanknote(Hundred, 12)
                .setBanknote(Fifty, 12)
                .setBanknote(Ten, 12)
                .setBanknote(Five, 12)
                .setBanknote(One, 20);
        dep.addATM(atm1, "first");
        dep.addATM(atm2, "second");

        for (int i = 0; i < 100; i++) {

            Command in = new Deposit(atm1, Hundred, (int) (Math.random() * 5));
            Command out = new Withdrawal(atm2, (int) (Math.random() * 1000));
            Command recover = new RecoverAll(dep);
            Command check = new CheckOverallBalance(dep);

            if (!out.execute()) {
                recover.execute();
            }

            in.execute();

            if (!check.execute()) {
                recover.execute();
            }

            Thread.sleep(3000);
        }
    }
}