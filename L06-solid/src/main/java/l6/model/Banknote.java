package l6.model;

public enum  Banknote {
    One(1), Five(5), Ten(10), Fifty(50), Hundred(100);

    final private int value;

    Banknote(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
