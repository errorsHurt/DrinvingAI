package com.widenetwork;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.Perceptron;
import org.neuroph.util.NeuralNetworkType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrainingsSetHandler {

    public NeuralNetwork neuralNetwork = new Perceptron(8, 2);
    public Layer networkLayer1 = new Layer(15);
    public Layer networkLayer2 = new Layer(5);
    public DataSet trainingSet = new DataSet(8, 2);
    double velocity;
    private DateTimeFormatter dtf = null;
    private LocalDateTime now = null;

    public void createNN() {
        neuralNetwork.setNetworkType(NeuralNetworkType.PERCEPTRON);
        neuralNetwork.addLayer(0, networkLayer1);
        neuralNetwork.addLayer(1, networkLayer2);
        neuralNetwork.randomizeWeights();

    }


    public void putInTrainingsSet(int l, int tll, int tl, int u, int tr, int trr, int r, int velocity) {
        try {
            trainingSet.add(new double[]{l, tll, tl, u, tr, trr, r, velocity}, new double[]{0, 0});
            System.out.println("Datenreihe hinzugefügt.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Datenreihe NICHT hinzugefügt.");
        }

    }

    public void saveNeuralNetwork() {
        dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        now = LocalDateTime.now();

        neuralNetwork.save("C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\TrackmaniaAI\\Neural Networks\\TrackmaniaKI_Perceptron.nnet");
        System.out.println("Neurales Netzwerk gespeichert.");
    }

    public void trainNeuralNetwork() {
        System.out.println("Lernprozess gestartet.");
        neuralNetwork.learn(trainingSet);
        System.out.println("Lernprozess beendet.");
    }

    public void saveTrainingSetData() {
        dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        now = LocalDateTime.now();
        trainingSet.save("C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\TrackmaniaAI\\Training Sets\\TrainingData_" + dtf.format(now) + ".tset");
        System.out.println("Traningsdaten wurden gespeichert");
    }


}

