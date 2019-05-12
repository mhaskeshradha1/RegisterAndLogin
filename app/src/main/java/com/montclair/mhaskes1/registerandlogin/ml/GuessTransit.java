package com.montclair.mhaskes1.registerandlogin.ml;

import android.util.Log;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;

/**
 * This class loads Real Estate Data data model.
 * Once classifier is trained, its tested against test data to verify accuracy
 *
 */
public class GuessTransit {

    public static Classifier ibk;
    public static LibSVM libSVM;
    private static double accuracy;
    static Instances data = null;

    static Map<Double, String> result = new HashMap<>();

    static{
        result.put(0d, "60 + or Cancelled");
        result.put(1d, "upto 20");
        result.put(2d, "40 - 60");
        result.put(0d, "20 - 40");
    }


    /**
     * Loads model; then trains and tests data.
     *
     * @param reader
     * @param test
     * @throws Exception
     */
    public static void loadResource(Reader reader, Reader test, Reader svm) throws Exception{
/*

        data = new Instances(reader);
        data.setClassIndex(data.numAttributes() - 1);

        //do not use first and second
        Instance first = data.instance(99);
        Instance second = data.instance(150);
        data.delete(0);
        data.delete(1);

        ibk = new IBk();
        ibk.buildClassifier(data);

        double class1 = ibk.classifyInstance(first);
        double class2 = ibk.classifyInstance(second);

        Log.d("Guess Transit ", "first: " + class1 + "\nsecond: " + class2);
*/


        return;

    }

    /**
     *
     * @param line
     * @param station
     * @param day
     * @param hour
     * @return
     */
    public static String predictString(Reader reader, String line, String station, String day, String hour) throws Exception{

        Log.d("Guess Transit ", "line: " + line + "\nstation: " + station + "\nday: " + day + "\nhour: " + hour);


        data = new Instances(reader);
        data.setClassIndex(data.numAttributes() - 1);


        Instance newInstance  = data.firstInstance();
        for(int i = 0 ; i < data.numAttributes() ; i++)
        {

            if(i == 0)
                newInstance.setValue(i , line);
            if(i == 1)
                newInstance.setValue(i , station);
            if(i == 2)
                newInstance.setValue(i , day);
            if(i == 3)
                newInstance.setValue(i , new Double(hour).doubleValue());
            //i is the index of attribute
            //value is the value that you want to set
        }

        data.delete(0);


        /*ibk = new IBk();
        ((IBk) ibk).setKNN(15);
        ibk.buildClassifier(data);


        double d = ibk.classifyInstance(newInstance);*/

        NaiveBayes bayes = new NaiveBayes();

        bayes.buildClassifier(data);


        double d = bayes.classifyInstance(newInstance);

        String res;
        res = result.get(d);

        Log.d("Guess Transit ", "Result: " + res);


        return res;

    }
}
