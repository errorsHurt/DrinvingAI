package com.widenetwork;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.Perceptron;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrainingsSetHandler {

    public static ImageProcessing ir = new ImageProcessing();
    public NeuralNetwork neuralNetwork = new Perceptron(8, 2);

        public DataSet trainingSet = new DataSet(8, 2);


    private DateTimeFormatter dtf = null;
    private LocalDateTime now = null;
    double velocity;

    public void createNN(){
     neuralNetwork.addLayer(new Layer(5));
    }

    public void putInTrainingsSet(int l, int tll, int tl, int u, int tr, int trr, int r, int velocity) {

       
        try {

            trainingSet.add(new double[]{l, tll, tl, u, tr, trr, r, velocity }, new double[]{0, 0});
             System.out.println("Datenreihe hinzugefügt.");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Datenreihe NICHT hinzugefügt.");
        }

    }

    public void saveNeuralNetwork() {
        dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        now = LocalDateTime.now();

        neuralNetwork.save("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\TrackmaniaAI\\Neural Networks\\TrackmaniaKI_Perceptron.nnet");
        System.out.println("Neurales Netzwerk gespeichert.");
    }

    public void trainNeuralNetwork() {
        System.out.println("Lernprozess gestartet.");
        neuralNetwork.learn(trainingSet);
        System.out.println("Lernprozess beendet.");
    }

    public void saveTrainingSetData(){
        dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        now = LocalDateTime.now();
        trainingSet.save("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\TrackmaniaAI\\Training Sets\\TrainingData_" + dtf.format(now) + ".tset");
        System.out.println("Traningsdaten wurden gespeichert");
    }


}

