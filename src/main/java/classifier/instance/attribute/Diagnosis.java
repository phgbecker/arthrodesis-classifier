package classifier.instance.attribute;

public enum Diagnosis {
    NEGATIVE(0),
    POSITIVE(1);

    private final int value;

    Diagnosis(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
