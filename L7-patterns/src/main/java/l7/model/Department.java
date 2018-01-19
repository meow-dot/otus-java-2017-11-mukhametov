package l7.model;

import l7.exceptions.NonPositiveDepositException;
import l7.exceptions.NotEnoughMoneyException;

import java.util.*;

public class Department {
    
    private final Map<ATM, Map<Banknote, Integer>> atms = new HashMap<>();
    
    private int balTemp = 0;
    private Map<Banknote, Integer> conTemp = new HashMap<>();

    public void registerATM (ATM atm) {
        Map<Banknote, Integer> condition = new HashMap<>();
        updateConditionTemp(atm);
        for (Banknote bn : conTemp.keySet()) {
            condition.put(bn, conTemp.get(bn));
        }
        atms.put(atm, condition);
    }
    
    public void unRegisterATM (ATM atm) {
        atms.remove(atm);
    }

    protected void updateBalanceTemp(ATM atm) {
        atm.sendBalance();
    }

    protected void updateConditionTemp(ATM atm) {
        atm.sendCondition();
    }

    void setBalanceTemp(int balance) {
        this.balTemp = balance;
    }

    void setConditionTemp(Map<Banknote, Integer> condition) {
        this.conTemp = condition;
    }
    
    public int getOverallBalance() {
        int sum = 0;
        for (ATM atm : getAllATMs()) {
            updateBalanceTemp(atm);
            sum += balTemp;
        }
        return sum;
    }
    
    public List<ATM> getAllATMs() {
        return new ArrayList<>(atms.keySet());
    }
    
    protected Map<Banknote, Integer> getDifference (ATM atm) {
        Map<Banknote, Integer> dif = new HashMap<>();
        updateConditionTemp(atm);
        for (Banknote bn : conTemp.keySet()) {
            dif.put(bn, conTemp.get(bn) - atms.get(atm).get(bn));
        }
        return dif;        
    }
    
    public void recoverATM(ATM atm) {
        for (Banknote bn : getDifference(atm).keySet()) {
            int diffOfBanknote = getDifference(atm).get(bn);
            if (diffOfBanknote > 0) {
                for (int i = 0; i < diffOfBanknote; i++) {
                    try {
                        atm.getMoney(bn.getValue());
                    } catch (NotEnoughMoneyException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (diffOfBanknote < 0) {
                try {
                    atm.addMoney(bn, Math.abs(diffOfBanknote));
                } catch (NonPositiveDepositException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void recoverAllATMs() {
        for (ATM atm : getAllATMs()) {
            recoverATM(atm);
        }
    }
}