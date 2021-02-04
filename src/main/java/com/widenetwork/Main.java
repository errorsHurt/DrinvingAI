package com.widenetwork;

import javax.swing.*;

public class Main {

    public static String user = "Meiers PC";
    public static Gui gui1;
    public static TrainingsSetHandler tSH = new TrainingsSetHandler();

    public static void main(String[] args) {

        /*
        tSH.createNN();
        tSH.saveNeuralNetwork();

         */


        gui1 = new Gui();
        gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //könnte das hier nicht alles in den gui constructor?
        gui1.pack(); // <=========== PACK
        gui1.setVisible(true);


    }

}
