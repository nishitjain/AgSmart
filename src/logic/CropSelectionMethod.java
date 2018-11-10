/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.WekaPackageManager;

/**
 *
 * @author user
 */
public class CropSelectionMethod 
{
    public void classifyJ48(String trainData, String testData, String output)
    {
        try {
            BufferedReader buff = null;
            buff = new BufferedReader(new FileReader(trainData));
            Instances train = new Instances(buff);
            train.setClassIndex(train.numAttributes()-1);
            buff = new BufferedReader(new FileReader(testData));
            Instances test = new Instances(buff);
            test.setClassIndex(train.numAttributes()-1);
            buff.close();
            J48 j48 = new J48();
            j48.buildClassifier(train);
            Instances labeled = new Instances(test);
            for(int i=0;i<test.numInstances();i++)
            {
                double classLabel = j48.classifyInstance(test.instance(i));
                labeled.instance(i).setClassValue(classLabel);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write(labeled.toString());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void classifyNaiveBayes(String trainData, String testData, String output)
    {
        try {
            BufferedReader buff = null;
            buff = new BufferedReader(new FileReader(trainData));
            Instances train = new Instances(buff);
            train.setClassIndex(train.numAttributes()-1);
            buff = new BufferedReader(new FileReader(testData));
            Instances test = new Instances(buff);
            test.setClassIndex(train.numAttributes()-1);
            buff.close();
            NaiveBayes nb = new NaiveBayes();
            nb.buildClassifier(train);
            Instances labeled = new Instances(test);
            for(int i=0;i<test.numInstances();i++)
            {
                double classLabel = nb.classifyInstance(test.instance(i));
                labeled.instance(i).setClassValue(classLabel);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write(labeled.toString());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void classifySVM(String trainData, String testData, String output)
    {
        try {
            BufferedReader buff = null;
            buff = new BufferedReader(new FileReader(trainData));
            Instances train = new Instances(buff);
            train.setClassIndex(train.numAttributes()-1);
            buff = new BufferedReader(new FileReader(testData));
            Instances test = new Instances(buff);
            test.setClassIndex(train.numAttributes()-1);
            buff.close();
            SMO svm = new SMO();
            svm.buildClassifier(train);
            Instances labeled = new Instances(test);
            for(int i=0;i<test.numInstances();i++)
            {
                double classLabel = svm.classifyInstance(test.instance(i));
                labeled.instance(i).setClassValue(classLabel);
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write(labeled.toString());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CropSelectionMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
