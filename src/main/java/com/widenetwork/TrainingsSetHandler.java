package com.widenetwork;


import com.widenetwork.Driver.Control;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.learning.error.MeanAbsoluteError;
import org.neuroph.core.learning.error.MeanSquaredError;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TrainingsSetHandler {

    public static MultiLayerPerceptron neuralNetwork;// = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 8, 6, 5, 4);
    public static DataSet trainingSet = new DataSet(8, 4);
    public static Control c = new Control();
    private DateTimeFormatter dtf = null;
    private LocalDateTime now = null;

    public void createNN() {


        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 8, 6, 5, 4);
        //neuralNetwork.randomizeWeights();
        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNetwork.getLearningRule();
        learningRule.setLearningRate(0.2);
        learningRule.setMaxError(0.01);
        saveNeuralNetwork(neuralNetwork);

    }

    public void trainNN(int iterations) {

        NeuralNetwork nn = NeuralNetwork.load("C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\TrackmaniaAI\\Neural Networks\\TrackmaniaKI_Perceptron.nnet");

        MomentumBackpropagation learningRule = (MomentumBackpropagation) nn.getLearningRule();

        DataSet ds = DataSet.load("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\TrackmaniaAI\\Training Sets\\TrainingData_20210204_210008.tset");

        learningRule.setMaxIterations(iterations);
        System.out.println("Start");
        nn.learn(ds);
        System.out.println("Ende");
        saveNeuralNetwork(nn);
    }

    public void testData() {

        NeuralNetwork nn = NeuralNetwork.load("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\TrackmaniaAI\\Neural Networks\\TrackmaniaKI_Perceptron.nnet");

        DataSet ds = DataSet.load("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\TrackmaniaAI\\Training Sets\\TrainingData_20210204_210008.tset");

        MeanSquaredError mse = new MeanSquaredError();
        MeanAbsoluteError mae = new MeanAbsoluteError();

        for (DataSetRow testSetRow : ds.getRows()) {
            nn.setInput(testSetRow.getInput());
            nn.calculate();
            double[] networkOutput = nn.getOutput();
            double[] desiredOutput = testSetRow.getDesiredOutput();

            System.out.print("Input: " + Arrays.toString(testSetRow.getInput()));
            System.out.print(" | Output: " + Arrays.toString(networkOutput));
            System.out.println(" | Desired output" + Arrays.toString(testSetRow.getDesiredOutput()));

            mse.addPatternError(networkOutput, desiredOutput);
            mae.addPatternError(networkOutput, desiredOutput);
        }

        System.out.println("Mean squared error is: " + mse.getTotalError());
        System.out.println("Mean absolute error is: " + mae.getTotalError());

    }

    public void calcSingleData(double[] values) {

        NeuralNetwork nn = NeuralNetwork.load("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\TrackmaniaAI\\Neural Networks\\TrackmaniaKI_Perceptron.nnet");


        nn.setInput(values);
        nn.calculate();

        double[] networkOutput = nn.getOutput();

        for (int i = 0; i <= 3; i++) {
            double d = Math.pow(10, 1);

            networkOutput[i] = Math.rint(networkOutput[i] * d) / d;
            if (networkOutput[i] < 0.5) {
                networkOutput[i] = 0.0;
            } else {
                networkOutput[i] = 1.0;
            }
        }
        c.decisionMaker(networkOutput);


        System.out.println(" ");
        System.err.print("Input: " + Arrays.toString(values));
        System.err.print(" | Output: " + Arrays.toString(networkOutput));
        System.out.println(" ");

    }


    public void trainNeuralNetwork() {
        System.out.println("Lernprozess gestartet.");
        try {
            neuralNetwork.learn(DataSet.load("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\TrackmaniaAI\\Training Sets\\TrainingData_20210204_210008.tset"));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        neuralNetwork.stopLearning();
        System.out.println("Lernprozess beendet.");
    }

    public void saveNeuralNetwork(NeuralNetwork neuralNetwork) {
        //dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        //now = LocalDateTime.now();
        neuralNetwork.save("C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\TrackmaniaAI\\Neural Networks\\TrackmaniaKI_Perceptron.nnet");
        System.out.println("Neurales Netzwerk gespeichert.");
    }

    public void putInTrainingsSet(int l, int tll, int tl, int u, int tr, int trr, int r, int velocity, int o1, int o2, int o3, int o4) {
        try {
            trainingSet.add(new DataSetRow(new double[]{l, tll, tl, u, tr, trr, r, velocity}, new double[]{o1, o2, o3, o4}));
            System.out.println("Datenreihe hinzugefügt.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Datenreihe NICHT hinzugefügt.");
        }

    }

    public void saveTrainingSetData() {
        dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        now = LocalDateTime.now();
        trainingSet.save("C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\TrackmaniaAI\\Training Sets\\TrainingData_" + dtf.format(now) + ".tset");
        System.out.println("Traningsdaten wurden gespeichert");
    }

}

