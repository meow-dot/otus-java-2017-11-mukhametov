package l7.model;

import l7.exceptions.NonPositiveDepositException;
import l7.exceptions.NotEnoughMoneyException;

import java.util.*;

public class Department {

    private ArrayList<ATM> atms = new ArrayList<>();
    private Map<ATM, Integer> balances = new HashMap<>();
    private final Map<ATM, Map<Banknote, Integer>> differences = new HashMap<>();

    void registerATM (ATM a) {
        atms.add(a);
    }
    
    public void unRegisterATM (ATM a) {
        if (atms.contains(a)) {
            atms.remove(a);
        }
        if (balances.containsKey(a)) {
            balances.remove(a);
        }
        if (differences.containsKey(a)) {
            differences.remove(a);
        }
    }

    private void updateBalance(ATM atm) {
        atm.sendBalance();
    }

    private void updateDifference(ATM atm) {
        atm.sendDifference();
    }

    void putBalance(ATM atm, int balance) {
        balances.put(atm, balance);
    }

    void putDifference(ATM atm, Map<Banknote, Integer> difference) {
        differences.put(atm, difference);
    }
    
    public int getOverallBalance() {
        int sum = 0;
        for (ATM a : atms) {
            updateBalance(a);
            sum += balances.get(a);
        }
        return sum;
    }

    public void recoverATM(ATM a) {
        updateDifference(a);
        for (Banknote bn : differences.get(a).keySet()) {
            int difOfBanknote = differences.get(a).get(bn);
            if (difOfBanknote > 0) {
                for (int i = 0; i < difOfBanknote; i++) {
                    try {
                        a.getMoney(bn.getValue());
                    } catch (NotEnoughMoneyException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (difOfBanknote < 0) {
                try {
                    a.addMoney(bn, Math.abs(difOfBanknote));
                } catch (NonPositiveDepositException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void recoverAllATMs() {
        for (ATM a : atms) {
            recoverATM(a);
        }
    }
}