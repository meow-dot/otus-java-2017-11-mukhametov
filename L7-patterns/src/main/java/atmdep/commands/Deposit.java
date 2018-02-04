package atmdep.commands;

import atmdep.exceptions.NonPositiveDepositException;
import atmdep.models.ATM;
import atmdep.models.Banknote;

public class Deposit implements Command {

    private ATM atm;
    private Banknote banknote;
    private int amount;

    public Deposit (ATM atm, Banknote banknote, int amount) {
        this.atm = atm;
        this.banknote = banknote;
        this.amount = amount;
    }
        
    @Override
    public boolean execute() {
        try {
            atm.addMoney(banknote, amount);
            System.out.println(atm.getName() + " deposit " + amount*banknote.getValue());
        } catch (NonPositiveDepositException e) {
            e.getMessage();
            return false;
        }
        return true;
    }
}
