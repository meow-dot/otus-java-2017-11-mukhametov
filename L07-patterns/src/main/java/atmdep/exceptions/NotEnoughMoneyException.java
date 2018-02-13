package atmdep.exceptions;

public class NotEnoughMoneyException extends Exception {
    @Override
    public String getMessage() {
        return "Not enough money in the ATM. Try to get less money.";
    }
}
