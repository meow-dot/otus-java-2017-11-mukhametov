package atmdep.models;

import java.util.HashMap;
import java.util.Map;

public class Memento {

    private ATM atm;
    private Map<Banknote, Integer> numberOfBanknotes = new HashMap<>();

    ATM getAtm() {
        return atm;
    }

    void setAtm(ATM atm) {
        this.atm = atm;
    }

    Map<Banknote, Integer> getNumberOfBanknotes() {
        return numberOfBanknotes;
    }

    void setNumberOfBanknotes(Map<Banknote, Integer> numberOfBanknotes) {
        this.numberOfBanknotes = numberOfBanknotes;
    }
}
