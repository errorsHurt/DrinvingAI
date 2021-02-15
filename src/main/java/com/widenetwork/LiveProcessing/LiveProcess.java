package com.widenetwork.LiveProcessing;

import com.widenetwork.ImageEditing;
import com.widenetwork.ScreenshotHandler;
import com.widenetwork.TextRecognitionHandler;
import com.widenetwork.TrainingsSetHandler;

public class LiveProcess {

    public static ScreenshotHandler sH = new ScreenshotHandler();
    public static TextRecognitionHandler trH = new TextRecognitionHandler();
    public static ImageEditing iE = new ImageEditing();
    public static TrainingsSetHandler tsH = new TrainingsSetHandler();

    public void doLiveProcessing(int screenshots) {

        System.out.println("Trying to delete all images...");
        sH.deleteImagesB();
        sH.deleteImagesS();
        sH.deleteImagesV();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < screenshots - 1; i++) {
            System.out.println("Round: " + (i + 1) + " of " + screenshots);

            System.out.println("Take screenshot");
            sH.takeStockScreenshot(1);
            System.out.println("Cut border screenshot");
            sH.cutoutBorderImage();
            System.out.println("Cut velocity screenshot");
            sH.cutoutVelocityImage();

            System.out.println("Do velocity recognition");
            trH.velocityRecognition();
            System.out.println("Calculate values for spots");
            double[] values = iE.getSpots();

            System.out.println("Send data to neural network.");
            System.out.println(" ");
            System.out.print("Processing");
            System.out.print(".");
            System.out.print(".");
            System.out.println(".");
            System.out.println("Processed data: ");
            tsH.calcSingleData(values);
            System.out.println(" ");

            System.out.println("Trying to delete all images...");
            sH.deleteImagesB();
            sH.deleteImagesS();
            sH.deleteImagesV();

            System.out.println("Ready for next screenshot!");

        }

    }
}
