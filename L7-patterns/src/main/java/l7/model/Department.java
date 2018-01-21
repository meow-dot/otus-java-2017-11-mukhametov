package l7.model;

import java.util.*;

public class Department {

    private ArrayList<ATM> atms = new ArrayList<>();
    private ArrayList<Memento> initials = new ArrayList<>();

    public void addATM(ATM atm) {
        atms.add(atm);
        initials.add(atm.save());
    }
    
    public void removeATM(ATM atm) {
        atms.remove(atm);
        for (Memento memento : initials) {
            if (memento.getAtm() == atm) {
                initials.remove(memento);
            }
        }
    }

    public int getOverallBalance() {
        int sum = 0;
        for (ATM atm : atms) {
            sum += atm.getBalance();
        }
        return sum;
    }

    public void recoverATM(ATM atm) {
        Memento initial = new Memento();
        for (Memento memento : initials) {
            if (memento.getAtm() == atm) {
                initial = memento;
            }
        }
        atm.restore(initial);
    }
    
    public void recoverAllATMs() {
        for (ATM a : atms) {
            recoverATM(a);
        }
    }
}