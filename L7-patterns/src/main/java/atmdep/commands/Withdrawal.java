package atmdep.commands;

import atmdep.exceptions.NotEnoughMoneyException;
import atmdep.models.ATM;

public class Withdrawal implements Command {
    
    protected ATM atm;
    protected int amount;

    public Withdrawal (ATM atm, int amount) {
        this.atm = atm;
        this.amount = amount;
    }
        
    @Override
    public boolean execute() {
        try {
            atm.getMoney(amount);
            System.out.println(atm.getName() + " withdrawal " + amount);
        } catch (NotEnoughMoneyException e) {
            e.getMessage();
            return false;
        }
        return true;
    }
}