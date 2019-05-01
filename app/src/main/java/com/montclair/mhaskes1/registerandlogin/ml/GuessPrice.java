package com.montclair.mhaskes1.registerandlogin.ml;

import android.util.Log;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

import java.io.Reader;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils;

/**
 * This class loads Real Estate Data data model.
 * Once classifier is trained, its tested against test data to verify accuracy
 *
 */
public class GuessPrice {

    public static Classifier knn;
    public static LibSVM libSVM;
    private static double accuracy;

    /**
     * Loads model; then trains and tests data.
     *
     * @param reader
     * @param test
     * @throws Exception
     */
    public static void loadResource(Reader reader, Reader test, Reader svm) throws Exception{

        /* Load a data set */

        Dataset data = FileHandler.load(reader, 3, ",");
        /*
         * Contruct a KNN classifier that uses 5 neighbors to make a decision.
         */
        knn = new KNearestNeighbors(20);

        knn.buildClassifier(data);

        /*
         * Load a data set for evaluation, this can be a different one, but we
         * will use the same one.
         */
        Dataset dataForClassification = FileHandler.load(test, 3, ",");
        /* Counters for correct and wrong predictions. */
        int correct = 0, wrong = 0;
        /* Classify all instances and check with the correct class values */
        for (Instance inst : dataForClassification) {
            Object predictedClassValue = knn.classify(inst);
            Object realClassValue = inst.classValue();
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else
                wrong++;
        }

        accuracy = 100 * correct/(correct+wrong);

        Log.d("Guess Price ", "KNN accuracy " + accuracy);

        /*libSVM = new LibSVM();

        libSVM.setKernelType(new SelectedTag(LibSVM.KERNELTYPE_LINEAR, LibSVM.TAGS_KERNELTYPE));
        libSVM.setSVMType(new SelectedTag(LibSVM.SVMTYPE_EPSILON_SVR, LibSVM.TAGS_SVMTYPE));
        libSVM.setProbabilityEstimates(true);

        Instances dataset = new Instances(svm);

        dataset.setClassIndex(dataset.numAttributes() - 1);

        libSVM.buildClassifier(dataset);

        Evaluation svmregeval = new Evaluation(dataset);
        svmregeval.evaluateModel(libSVM, dataset);

        Log.d("Guess Price ", "SVM Evolution " + svmregeval.toSummaryString());
*/

        return;

    }


    /**
     *
     * @param age
     * @param station
     * @param store
     * @return
     */
    public static Double predict(double age, double station, double store){
        double[] doubles = new double[3];
        doubles[0] = age;
        doubles[1] = station;
        doubles[2] = store;

        Instance instance = new DenseInstance(doubles);

        Object predictedClassValue = knn.classify(instance);

        return new Double(predictedClassValue.toString());
    }


    /**
     *
     * @param age
     * @param station
     * @param store
     * @return
     */
    public static Double predictSVM(double age, double station, double store){

        weka.core.Instance instance = new weka.core.Instance(3);
        instance.setValue(1, age);
        instance.setValue(2, station);
        instance.setValue(3, store);

        try {
            return 0.0; //libSVM.classifyInstance( instance);
        } catch (Exception e) {
            e.printStackTrace();

            return 0d;
        }

    }
}
