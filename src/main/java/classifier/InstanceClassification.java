package classifier;

public class InstanceClassification {

    public static void main(String[] args) throws Exception {
        ArthrodesisClassifier classifier = new ArthrodesisClassifier();

        classifier
                .loadModel("ortopedia_coluna.model")
                .loadDataSet("ortopedia_coluna_normalizada.csv")
                .classifyInstance("ortopedia_coluna_instancia.json");
    }
}
