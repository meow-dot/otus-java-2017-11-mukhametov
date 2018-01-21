package l7.model;

public class ATM extends AbstractATM {

    public ATM(){
        super();
    }

    public ATM setBanknote(Banknote bn, int amount) {
        banknotes.put(bn, amount);
        return this;
    }

    Memento save() {
        Memento memento = new Memento();
        memento.setAtm(this);
        for (Banknote bn : banknotes.keySet()) {
            memento.getNumberOfBanknotes().put(bn, banknotes.get(bn));
        }
        return memento;
    }

    void restore(Memento memento) {
        for (Banknote bn : memento.getNumberOfBanknotes().keySet()) {
            banknotes.put(bn, memento.getNumberOfBanknotes().get(bn));
        }
    }
}