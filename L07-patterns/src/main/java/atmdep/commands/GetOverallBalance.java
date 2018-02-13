package atmdep.commands;

import atmdep.models.Department;

public class GetOverallBalance implements Command {

    private Department department;

    public GetOverallBalance(Department department) {
        this.department = department;
    }    

    @Override
    public void execute() {
        System.out.println("Overall balance : " + department.getOverallBalance());
    }
}