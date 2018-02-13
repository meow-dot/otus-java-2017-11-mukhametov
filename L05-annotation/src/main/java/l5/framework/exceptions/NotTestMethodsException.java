package l5.framework.exceptions;

public class NotTestMethodsException extends Exception{

    @Override
    public String getMessage() {
        return "There are not test methods";
    }
}
