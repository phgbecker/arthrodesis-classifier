package classifier.instance.attribute.procedure;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Procedure {
    private int occurrences;

    public Procedure() {
    }

    public Procedure(int occurrences) {
        this.occurrences = occurrences;
    }

    public int getOccurrences() {
        return occurrences;
    }

    abstract String getDescription();
}
