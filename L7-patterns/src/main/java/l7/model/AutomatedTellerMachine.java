package l7.model;

import l7.exceptions.NonPositiveDepositException;
import l7.exceptions.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

import static l7.model.Banknote.*;

public class AutomatedTellerMachine {

    private final Map<Banknote, Integer> banknotes = new HashMap<>();
    
    public AutomatedTellerMachine() {
        banknotes.put(One, 0);
        banknotes.put(Five, 0);
        banknotes.put(Ten, 0);
        banknotes.put(Fifty, 0);
        banknotes.put(Hundred, 0);
    }
    
    public AutomatedTellerMachine(int hundreds, int fifties, int tens, int fives, int ones) {
        banknotes.put(One, ones);
        banknotes.put(Five, fives);
        banknotes.put(Ten, tens);
        banknotes.put(Fifty, fifties);
        banknotes.put(Hundred, hundreds);
    }
    
    protected int getBalance(){
        int balance = 0;
        for (Banknote bn : banknotes.keySet()) {
            balance += bn.getValue() * banknotes.get(bn);
        }
        return balance;
    }

    public void addMoney(Banknote banknote, int amount) throws NonPositiveDepositException {
        if (amount <= 0) {
            throw new NonPositiveDepositException();
        }
        banknotes.put(banknote, banknotes.get(banknote) + amount);
    }

    public Map<Banknote, Integer> getMoney(int need) throws NotEnoughMoneyException {
        if (need > getBalance()) {
            throw new NotEnoughMoneyException();
        }
        Map<Banknote, Integer> out = new HashMap<>();
        int issued = 0;
        for (Banknote bn : Banknote.values()) {
            out.put(bn, 0);
            while (banknotes.get(bn) > 0 && issued + bn.getValue() <= need) {
                out.put(bn, out.get(bn) + 1);
                issued += bn.getValue();
                banknotes.put(bn, banknotes.get(bn) - 1);
            }
        }
        return out;
    }
    
    protected Map<Banknote, Integer> getNumberOfBanknotes() {
        return banknotes;
    }
}