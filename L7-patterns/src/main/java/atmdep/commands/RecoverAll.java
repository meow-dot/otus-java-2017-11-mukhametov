package atmdep.commands;

import atmdep.models.Department;

public class RecoverAll implements Command {

    private Department department;

    public RecoverAll (Department department) {
        this.department = department;
    }

    @Override
    public boolean execute() {
        department.recoverAllATMs();
        System.out.println("ATMs are recovering...");
        return true;
    }
}
