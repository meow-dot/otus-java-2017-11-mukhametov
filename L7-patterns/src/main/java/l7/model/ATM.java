package l7.model;

import l6.model.AutomatedTellerMachine;

public class ATM extends AutomatedTellerMachine {

    private Department department;
    
    public ATM(int hundreds, int fifties, int tens, int fives, int ones) {
        super(hundreds, fifties, tens, fives, ones);
    }
    
    public void registerDepartment(Department dep) {
        this.department = dep;
        department.registerATM(this);
    }
    
    public void unRegisterDepartment(Department dep) {
        this.department = dep;
        department.unRegisterATM(this);
    }

    void sendBalance() {
        department.setBalanceTemp(getBalance());
    }

    void sendCondition() {
        department.setConditionTemp(getNumberOfBanknotes());
    }
}