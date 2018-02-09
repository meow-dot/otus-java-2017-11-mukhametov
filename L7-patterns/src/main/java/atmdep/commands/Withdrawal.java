package atmdep.commands;

import atmdep.exceptions.NotEnoughMoneyException;
import atmdep.models.ATM;

public class Withdrawal implements Command {

    private ATM atm;
    private int amount;

    public Withdrawal (ATM atm, int amount) {
        this.atm = atm;
        this.amount = amount;
    }
        
    @Override
    public void execute() {
        try {
            atm.getMoney(amount);
            System.out.println(atm.getName() + " withdrawal " + amount);
        } catch (NotEnoughMoneyException e) {
            e.getMessage();
        }
    }
}