package l6.model;

import l6.exceptions.InvalidBanknotesException;
import l6.exceptions.NotEnoughMoneyException;
import static l6.model.Banknote.*;
import java.util.ArrayList;

public class ATM {

    private final static Banknote[] VALUE_ORDER = {Hundred, Fifty, Ten, Five, One};

    private int oneBanknoteNumber = 0;
    private int fiveBanknoteNumber = 0;
    private int tenBanknoteNumber = 0;
    private int fiftyBanknoteNumber = 0;
    private int hundredBanknoteNumber = 0;

    public int getBalance(){
        return oneBanknoteNumber +
                5*fiveBanknoteNumber +
                10*tenBanknoteNumber +
                50*fiftyBanknoteNumber +
                100*hundredBanknoteNumber;
    }

    public void addMoney(ArrayList<Object> in) throws InvalidBanknotesException {
        ArrayList<Object> invalidBanknotes = new ArrayList<>();
        for (Object bn : in) {
            if (bn.equals(One) || bn.equals(Five) || bn.equals(Ten) || bn.equals(Fifty) || bn.equals(Hundred)) {
                editNumberOfBanknotes((Banknote) bn, 1);
            }
            else {
                invalidBanknotes.add(bn);
            }
        }
        if (invalidBanknotes.size() > 0) throw new InvalidBanknotesException(invalidBanknotes);
    }

    public ArrayList<Banknote> getMoney(int need) throws NotEnoughMoneyException {
        if (need > getBalance()) {
            throw new NotEnoughMoneyException();
        }
        ArrayList<Banknote> banknotes = new ArrayList<>();
        int issued = 0;
        for (Banknote bn : VALUE_ORDER) {
            while (getNumberOfBanknotes(bn) > 0 && issued + bn.getValue() <= need) {
                banknotes.add(bn);
                issued += bn.getValue();
                editNumberOfBanknotes(bn, -1);
            }
        }
        return banknotes;
    }

    private void editNumberOfBanknotes(Banknote banknote, int diff) {
        this.setNumberOfBanknotes(banknote, this.getNumberOfBanknotes(banknote) + diff);
    }

    public ATM setNumberOfBanknotes(Banknote banknote, int amount) {
        switch (banknote) {
            case One:       this.oneBanknoteNumber = amount;
                            break;
            case Five:      this.fiveBanknoteNumber = amount;
                            break;
            case Ten:       this.tenBanknoteNumber = amount;
                            break;
            case Fifty:     this.fiftyBanknoteNumber = amount;
                            break;
            case Hundred:   this.hundredBanknoteNumber = amount;
                            break;
        }
        return this;
    }

    private int getNumberOfBanknotes(Banknote banknote) {
        switch (banknote) {
            case One:       return oneBanknoteNumber;
            case Five:      return fiveBanknoteNumber;
            case Ten:       return tenBanknoteNumber;
            case Fifty:     return fiftyBanknoteNumber;
            case Hundred:   return hundredBanknoteNumber;
        }
        return 0;
    }
}