package atmdep;

import atmdep.commands.*;
import atmdep.models.ATM;
import atmdep.models.Department;

import static atmdep.models.Banknote.*;

public class ATMDepartmentDemo {

    public static void main(String[] args) throws Exception {
        Department dep = new Department();
        ATM atm = new ATM()
                .setBanknote(Hundred, 6)
                .setBanknote(Fifty, 6)
                .setBanknote(Ten, 6)
                .setBanknote(Five, 6)
                .setBanknote(One, 10);
        dep.addATM(atm, "first");

        dep.execute(new GetOverallBalance(dep));
        dep.execute(new Deposit(atm, Ten, 100));
        dep.execute(new Withdrawal(atm,99));
        dep.execute(new RecoverAll(dep));
        dep.execute(new GetOverallBalance(dep));
    }
}