package classifier;

import classifier.instance.ArthrodesisInstance;
import classifier.instance.attribute.Age;
import classifier.instance.attribute.Procedures;
import classifier.instance.attribute.procedure.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArthrodesisInstanceTest {
    private ArthrodesisInstance arthrodesisInstance;

    @Before
    public void setUp() {
        arthrodesisInstance = new ArthrodesisInstance(
                new Procedures(
                        new C32020023(1),
                        new C52010465(0),
                        new C52250091(5),
                        new C36010022(0),
                        new C25060082(0),
                        new C32020015(0),
                        new C32020066(2),
                        new C36010049(0),
                        new C49030086(0),
                        new C52010392(0),
                        new C49030116(6),
                        new C32020040(0),
                        new C92010016(1)
                ),
                new Age(25)
        );
    }

    @Test
    public void givenArthrodesisInstance_whenSerializeToJson_thenWriteToFile() throws IOException {
        arthrodesisInstance.serializeToJson("ortopedia_coluna_instancia.json");
    }

    @Test
    public void givenJsonFile_whenDeserialize_thenIsEquals() throws IOException {
        assertThat(ArthrodesisInstance.deserializeFromJson("ortopedia_coluna_instancia.json")).isEqualTo(arthrodesisInstance);
    }
}