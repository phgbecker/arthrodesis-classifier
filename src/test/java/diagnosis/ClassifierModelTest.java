package diagnosis;

import org.junit.Test;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassifierModelTest {

    @Test
    public void givenFileName_whenLoadModel_thenIsInstanceOfLogistic() throws IOException, ClassNotFoundException {
        assertThat(
                new ClassifierModel<>().loadModel("ortopedia_coluna.model")
        ).isInstanceOf(Logistic.class);
    }

    @Test
    public void givenFileName_whenLoadDataSetFromCsv_thenIsInstanceOfInstances() throws IOException {
        assertThat(
                new ClassifierModel<>().loadDataSetFromCsv("ortopedia_coluna_normalizada.csv")
        ).isInstanceOf(Instances.class);
    }
}