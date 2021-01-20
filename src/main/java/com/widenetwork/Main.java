 package com.widenetwork;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

 public class Main {

     public static DateTimeFormatter dtf = null;
     private static LocalDateTime now = null;

     public static final ImageProcessing ir = new ImageProcessing();
     public final TrainingsSetHandler tSH = new TrainingsSetHandler();


     public static void main(String[] args) throws InterruptedException, AWTException {


         ScreenshotHandler sh = new ScreenshotHandler();
         TextRecognitionHandler trH = new TextRecognitionHandler();
         ImageEditing iE = new ImageEditing();


         //sh.takeStockScreenshot(10);
         //sh.cutoutBorderImage();
         //sh.cutoutVelocityImage();

         //trH.velocityRecognition();
         //iE.markSpots();


        /*
        Gui gui1 = new Gui();
        gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui1.pack(); // <=========== PACK
        gui1.setVisible(true);
        */


         //ir.takeScreensVelo(50, 30);
         //ir.ocrImageVeloDirectory();

         //ir.takeScreensBorder(50,15);
         //ImageProcessing.bildcounter = 0;
         //ir.editImageBorderDirectory();

     }
}
