package atmdep.exceptions;

public class NonPositiveDepositException extends Exception {
    @Override
    public String getMessage() {
        return "You can only deposit a positive number of banknotes.";
    }
}
