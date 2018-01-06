package l6;

import l6.exceptions.*;
import l6.model.ATM;
import l6.model.Banknote;
import java.util.ArrayList;

import static l6.model.Banknote.*;

public class Main {

    public static void main(String[] args) {

        ATM atm = new ATM();
        atm.setNumberOfBanknotes(One, 10)
                .setNumberOfBanknotes(Five, 2)
                .setNumberOfBanknotes(Ten, 2)
                .setNumberOfBanknotes(Fifty, 1)
                .setNumberOfBanknotes(Hundred, 0);
        System.out.println(atm.getBalance());


        ArrayList<Object> in = new ArrayList<>();
        in.add(Ten);
        in.add("Two");
        try {
            atm.addMoney(in);
        } catch (InvalidBanknotesException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(atm.getBalance());


        ArrayList<Banknote> out = new ArrayList<>();
        try {
            out = atm.getMoney(99);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(out);
        System.out.println(atm.getBalance());
    }
}