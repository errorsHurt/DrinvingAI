package com.widenetwork;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {


    private static final LocalDateTime now = null;
    public static DateTimeFormatter dtf = null;
    public static ImageEditing iE = new ImageEditing();
    private static String imgText;
    public final TrainingsSetHandler tSH = new TrainingsSetHandler();

    public static String user = "Meiers PC";

    public static void main(String[] args) {

        Gui gui1 = new Gui();
        gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //könnte das hier nicht alles in den gui constructor?
        gui1.pack(); // <=========== PACK
        gui1.setVisible(true);

    }

}
