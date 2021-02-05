package com.widenetwork;

public class Main {

    public static String user = "Meiers PC";
    public static Gui gui1;
    public static TrainingsSetHandler tSH = new TrainingsSetHandler();

    public static void main(String[] args) {
        //Control c = new Control();
        //Security s = new Security();
        //s.isFocused();
        //c.steerLeft();
        //s.isFocused();

        tSH.createNN();
        tSH.trainNN(1000000);
        tSH.testData();

        //tSH.loadNN();

        //System.out.println(ProcessHandle.);

/*
        gui1 = new Gui();
        gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //könnte das hier nicht alles in den gui constructor?
        gui1.pack(); // <=========== PACK
        gui1.setVisible(true);
*/
    }

}