package l7.model;

public enum  Banknote {
    Hundred(100),
    Fifty(50),
    Ten(10),
    Five(5),
    One(1);

    final private int value;

    Banknote(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
