package atmdep.commands;

import atmdep.exceptions.CriticalAmountMoneyException;
import atmdep.models.Department;

public class CheckOverallBalance implements Command {
    
    protected Department department;
    protected int min;
    protected int max;

    public CheckOverallBalance(Department department, int min, int max) {
        this.department = department;
        this.min = min;
        this.max = max;
    }    

    @Override
    public boolean execute() {
        int sum = department.getOverallBalance();
        System.out.println("Overall balance : " + sum);
        if (sum > max || sum < min) {
            try {
                throw new CriticalAmountMoneyException();
            } catch (CriticalAmountMoneyException e) {
                e.getMessage();
                return false;
            }
        }
        return true;
    }
}