package l6.exceptions;

import java.util.ArrayList;

public class InvalidBanknotesException extends Exception {

    private ArrayList<Object> list = new ArrayList<>();

    public InvalidBanknotesException(ArrayList<Object> list) {
        this.list = list;
    }

    @Override
    public String getMessage() {
        return "Недействительные банкноты: " + list.toString();
    }
}
