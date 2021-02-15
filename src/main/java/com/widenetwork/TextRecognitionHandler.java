package com.widenetwork;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.Arrays;


public class TextRecognitionHandler {

    private final String tessDataPath = "C:\\Program Files\\Tesseract-OCR\\tessdata";
    private final String tessLanguage = "eng";
    private final String tessDpi = "300";
    private final String tessSetting = "tessuser_defined_dpi";

    private final String velocityDirPath = "C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\velocityScreens";
    public static Tesseract tess = new Tesseract();

    public static int[] velocityCache;

    public void velocityRecognition() {
        Gui gRef = Main.gui1;       //multi screenshot mode

        System.out.println("gRef.i:" + gRef.i);
        velocityCache = new int[(int) gRef.i];        //multi screenshot mode
        //velocityCache = new int[1];     //single screenshot mode

        int fileCounter = 0;

        File dir = new File(velocityDirPath);
        File[] directoryListing = dir.listFiles();

        tess.setDatapath(tessDataPath);
        tess.setLanguage(tessLanguage);
        tess.setTessVariable("tessedit_char_whitelist", "0123456789");
        tess.setTessVariable("TESSDATA_PREFIX", "C:\\Program Files\\Tesseract-OCR");
        tess.setOcrEngineMode(3);

        if (directoryListing != null) {

            for (File child : directoryListing) {

                String recognizedText = "-1";

                try {
                    tess.setTessVariable(tessSetting, tessDpi);
                    recognizedText = tess.doOCR(new File(child.getAbsolutePath()));
                    String resultString = recognizedText.replaceAll("[^\\d]", "");
                    System.out.println("FileCounter: " + fileCounter);
                    velocityCache[fileCounter] = Integer.parseInt(resultString);            //Multiscreenshot mode
                    //velocityCache[0] = Integer.parseInt(resultString);            //Singlescreenshot mode

                } catch (TesseractException e) {
                    velocityCache[fileCounter] = 130;                                                                                   //WICHTIG!!! Ändern auf vorherigen Wert!!!
                    System.out.println("Durch Standartwert ersetzt.");
                    e.printStackTrace();
                }

                fileCounter++;
            }
            System.out.println("Whole array: " + Arrays.toString(velocityCache));

        } else {
            System.out.println("There are no file in this directory. [Dir:" + velocityDirPath + "]");
        }

    }

}
