package diagnosis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import diagnosis.attribute.Age;
import diagnosis.attribute.Procedures;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import weka.core.DenseInstance;
import weka.core.Instance;

import java.io.File;
import java.io.IOException;

@Getter
@EqualsAndHashCode
public class ArthrodesisInstance {
    private Procedures procedures;
    private Age age;

    public ArthrodesisInstance() {
    }

    public ArthrodesisInstance(Procedures procedures, Age age) {
        this.procedures = procedures;
        this.age = age;
    }

    public void serializeToJson(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(
                new File(fileName),
                this
        );
    }

    public static ArthrodesisInstance deserializeFromJson(String fileName) throws IOException {
        return new ObjectMapper().readValue(
                new File(fileName),
                ArthrodesisInstance.class
        );
    }

    @JsonIgnore
    public Instance getInstance() {
        Instance arthrodesisInstance = new DenseInstance(14);

        try {
            arthrodesisInstance.setValue(0, getProcedures().getC32020023().getOccurrences());
            arthrodesisInstance.setValue(1, getProcedures().getC52010465().getOccurrences());
            arthrodesisInstance.setValue(2, getProcedures().getC52250091().getOccurrences());
            arthrodesisInstance.setValue(3, getProcedures().getC36010022().getOccurrences());
            arthrodesisInstance.setValue(4, getProcedures().getC25060082().getOccurrences());
            arthrodesisInstance.setValue(5, getProcedures().getC32020015().getOccurrences());
            arthrodesisInstance.setValue(6, getProcedures().getC32020066().getOccurrences());
            arthrodesisInstance.setValue(7, getProcedures().getC36010049().getOccurrences());
            arthrodesisInstance.setValue(8, getProcedures().getC49030086().getOccurrences());
            arthrodesisInstance.setValue(9, getProcedures().getC52010392().getOccurrences());
            arthrodesisInstance.setValue(10, getProcedures().getC49030116().getOccurrences());
            arthrodesisInstance.setValue(11, getProcedures().getC32020040().getOccurrences());
            arthrodesisInstance.setValue(12, getProcedures().getC92010016().getOccurrences());
            arthrodesisInstance.setValue(13, getAge().getNormalized());
        } catch (NullPointerException e) {
            throw new NullPointerException("Oops, a procedure has not been set. Setup the instance, and try again!");
        }

        return arthrodesisInstance;
    }
}
