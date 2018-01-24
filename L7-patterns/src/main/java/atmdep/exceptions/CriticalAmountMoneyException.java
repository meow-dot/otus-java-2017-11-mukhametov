package atmdep.exceptions;

public class CriticalAmountMoneyException extends Exception{
    @Override
    public String getMessage() {
        return "You need recover ATMs.";
    }
}

