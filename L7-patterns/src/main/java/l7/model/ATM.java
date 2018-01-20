package l7.model;

import java.util.HashMap;
import java.util.Map;

public class ATM extends AbstractATM {

    private Department department;
    private Map<Banknote, Integer> initialCondition = new HashMap<>();

    public ATM(){
        super();
    }

    public ATM setBanknote(Banknote bn, int amount) {
        banknotes.put(bn, amount);
        return this;
    }
    
    public void registerDepartment(Department dep) {
        initialCondition.clear();
        this.department = dep;
        department.registerATM(this);
        for (Banknote bn : getNumberOfBanknotes().keySet()) {
            initialCondition.put(bn, getNumberOfBanknotes().get(bn));
        }
    }
    
    public void unRegisterDepartment() {
        department.unRegisterATM(this);
        this.department = null;
    }

    void sendBalance() {
        department.putBalance(this, getBalance());
    }

    void sendDifference() {
        department.putDifference(this, getDifference());
    }

    private Map<Banknote, Integer> getDifference() {
        Map<Banknote, Integer> dif = new HashMap<>();
        for (Banknote bn : getNumberOfBanknotes().keySet()) {
            dif.put(bn, getNumberOfBanknotes().get(bn) - initialCondition.get(bn));
        }
        return dif;
    }

    private Map<Banknote, Integer> getNumberOfBanknotes() {
        return banknotes;
    }
}