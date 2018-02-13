package l6.model;

import l6.exceptions.NotEnoughMoneyException;
import java.util.*;

public class ATM {

    private Map<Banknote, Integer> banknotes = new HashMap<>();

    public int getBalance(){
        int balance = 0;
        for (Banknote bn : banknotes.keySet()) {
            balance += bn.getValue() * banknotes.get(bn);
        }
        return balance;
    }

    public void addMoney(ArrayList<Banknote> in) {
        for (Banknote bn : in) {
            if (banknotes.containsKey(bn)) {
                banknotes.put(bn, banknotes.get(bn) + 1);
            } else {
                banknotes.put(bn, 1);
            }
        }
    }

    public ArrayList<Banknote> getMoney(int need) throws NotEnoughMoneyException {
        if (need > getBalance()) {
            throw new NotEnoughMoneyException();
        }
        ArrayList<Banknote> out = new ArrayList<>();
        int issued = 0;
        for (Banknote bn : getSortedBanknotesByValue()) {
            while (banknotes.get(bn) > 0 && issued + bn.getValue() <= need) {
                out.add(bn);
                issued += bn.getValue();
                banknotes.put(bn, banknotes.get(bn) - 1);
            }
        }
        return out;
    }

    private List<Banknote> getSortedBanknotesByValue() {
        List<Banknote> sortedList = new ArrayList<>(banknotes.keySet());
        Collections.sort(sortedList, new Comparator<Banknote>() {
            @Override
            public int compare(Banknote b1, Banknote b2) {
                return b2.getValue() - b1.getValue();
            }});
        return sortedList;
    }
}