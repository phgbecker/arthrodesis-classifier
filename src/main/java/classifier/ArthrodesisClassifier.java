package classifier;

import classifier.instance.ArthrodesisInstance;
import classifier.instance.attribute.Diagnosis;
import classifier.model.ClassifierModel;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;

import java.io.IOException;
import java.util.HashMap;

public class ArthrodesisClassifier {
    private Logistic logistic;
    private Instances dataSet;

    public ArthrodesisClassifier loadModel(String fileName) throws IOException, ClassNotFoundException {
        logistic = new ClassifierModel<Logistic>().loadModel(fileName);

        return this;
    }

    public ArthrodesisClassifier loadDataSet(String fileName) throws IOException {
        dataSet = ClassifierModel.loadDataSetFromCsv(fileName);
        dataSet.setClassIndex(dataSet.numAttributes() - 1);

        return this;
    }

    private Instance setUpInstance(ArthrodesisInstance arthrodesisInstance) {
        Instance instance = arthrodesisInstance.getInstance(dataSet);

        System.out.println();
        System.out.print("INSTANCE: ");
        System.out.println(instance);

        return instance;
    }

    public void classifyInstance(String fileName) throws Exception {
        ArthrodesisInstance fertilityInstance = ArthrodesisInstance.deserializeFromJson(fileName);
        Instance instance = setUpInstance(fertilityInstance);
        classifyInstance(instance);
    }

    private void classifyInstance(Instance instance) throws Exception {
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
