package com.widenetwork;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static DateTimeFormatter dtf = null;
    private static LocalDateTime now = null;

    public static ImageProcessing ir = new ImageProcessing();


    public static void main(String[] args) throws InterruptedException, AWTException {
        
        Gui gui1 = new Gui();
        gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui1.pack(); // <=========== PACK
        gui1.setVisible(true);
        
        //ir.takeScreensVelo(50, 30);
        //ir.ocrImageVeloDirectory();

        //ir.takeScreensBorder(50,15);
        //ImageProcessing.bildcounter = 0;
        //ir.editImageBorderDirectory();
        
    }
}
