package diagnosis;

import diagnosis.attribute.Diagnosis;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;

import java.io.IOException;
import java.util.HashMap;

public class InstanceClassification {

    public static void main(String[] args) throws Exception {
        // Load classifier model
        ClassifierModel<Logistic> classifierModel = new ClassifierModel<>();
        Logistic simpleLogistic = loadModel(classifierModel);

        // Load dataset
        Instances dataSet = loadDataSet(classifierModel);

        // Parse a JSON file to an Instance
        ArthrodesisInstance arthrodesisInstance = ArthrodesisInstance.deserializeFromJson("ortopedia_coluna_instancia.json");
        Instance instance = setUpInstance(dataSet, arthrodesisInstance);

        // Classify instance
        classifyInstance(simpleLogistic, instance);
    }

    private static Logistic loadModel(ClassifierModel<Logistic> classifierModel) throws IOException, ClassNotFoundException {
        Logistic simpleLogistic = classifierModel.loadModel("ortopedia_coluna.model");

        System.out.println("MODEL:");
        System.out.println(simpleLogistic);

        return simpleLogistic;
    }

    private static Instances loadDataSet(ClassifierModel<Logistic> classifierModel) throws IOException {
        Instances dataSet = classifierModel.loadDataSetFromCsv("ortopedia_coluna_normalizada.csv");
        dataSet.setClassIndex(dataSet.numAttributes() - 1);

        System.out.println("DATASET:");
        System.out.println(dataSet);

        return dataSet;
    }

    private static Instance setUpInstance(Instances dataSet, ArthrodesisInstance arthrodesisInstance) {
        Instance instance = arthrodesisInstance.getInstance();
        instance.setDataset(dataSet);

        System.out.println();
        System.out.print("INSTANCE: ");
        System.out.println(instance);

        return instance;
    }

    private static void classifyInstance(Logistic logistic, Instance instance) throws Exception {
        HashMap<Integer, Diagnosis> diagnosisMap = new HashMap<>();
        diagnosisMap.put(0, Diagnosis.NEGATIVE);
        diagnosisMap.put(1, Diagnosis.POSITIVE);

        System.out.println();
        System.out.print("DIAGNOSIS: ");
        System.out.println(diagnosisMap.get((int) logistic.classifyInstance(instance)));

        double[] distributionForInstance = logistic.distributionForInstance(instance);
        diagnosisMap.forEach((index, diagnosis) ->
                System.out.println(diagnosis + "\t-> " + distributionForInstance[index])
        );
    }
}
