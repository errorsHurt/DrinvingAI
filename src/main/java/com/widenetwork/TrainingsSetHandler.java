package com.widenetwork;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrainingsSetHandler {

    public static MultiLayerPerceptron neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 8, 10, 4);
    public static DataSet trainingSet = new DataSet(8, 4);
    private DateTimeFormatter dtf = null;
    private LocalDateTime now = null;

    public void createNN() {


        neuralNetwork.randomizeWeights();

    }

    public void trainNeuralNetwork() {
        System.out.println("Lernprozess gestartet.");
        neuralNetwork.learn(trainingSet);
        System.out.println("Lernprozess beendet.");
    }

    public void saveNeuralNetwork() {
        dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        now = LocalDateTime.now();

        neuralNetwork.save("C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\TrackmaniaAI\\Neural Networks\\TrackmaniaKI_Perceptron.nnet");
        System.out.println("Neurales Netzwerk gespeichert.");
    }

    public void putInTrainingsSet(int l, int tll, int tl, int u, int tr, int trr, int r, int velocity) {
        try {
            trainingSet.add(new DataSetRow(new double[]{l, tll, tl, u, tr, trr, r, velocity}, new double[]{0, 0, 0, 0}));
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

