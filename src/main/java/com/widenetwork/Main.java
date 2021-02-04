package com.widenetwork;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {


    private static final LocalDateTime now = null;
    public static DateTimeFormatter dtf = null;
    public static ImageEditing iE = new ImageEditing();
    private static String imgText;
    public final TrainingsSetHandler tSH = new TrainingsSetHandler();

    public static void main(String[] args) throws InterruptedException, AWTException {

        Gui gui1 = new Gui();
        gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui1.pack(); // <=========== PACK
        gui1.setVisible(true);

    }


}
