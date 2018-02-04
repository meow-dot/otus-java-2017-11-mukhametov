package atmdep.commands;

import atmdep.models.Department;

public class CheckOverallBalance implements Command {

    private static int MIN_OVERALL_BALANCE = 500;
    private static int MAX_OVERALL_BALANCE = 10000;
    
    private Department department;

    public CheckOverallBalance(Department department) {
        this.department = department;
    }    

    @Override
    public boolean execute() {
        int sum = department.getOverallBalance();
        System.out.println("Overall balance : " + sum);
        return (sum >= MIN_OVERALL_BALANCE || sum <= MAX_OVERALL_BALANCE);
    }
}