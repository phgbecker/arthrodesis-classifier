package classifier.model;

import weka.classifiers.AbstractClassifier;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClassifierModel<T extends AbstractClassifier> {

    public T loadModel(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream classifier = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) classifier.readObject();
        }
    }

    public static Instances loadDataSetFromCsv(String fileName) throws IOException {
        CSVLoader csvLoader = new CSVLoader();
        csvLoader.setSource(new File(fileName));

        return csvLoader.getDataSet();
    }
}
