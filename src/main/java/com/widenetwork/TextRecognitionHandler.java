package com.widenetwork;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;


public class TextRecognitionHandler {

    private final String tessDataPath = "C:\\Program Files\\Tesseract-OCR\\tessdata";
    private final String tessLanguage = "digits";
    private final String tessDpi = "300";
    private final String tessSetting = "tessuser_defined_dpi";

    private final TrainingsSetHandler tSH = new TrainingsSetHandler();
    private final String velocityDirPath = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\velocityScreens\\";
    public Tesseract tess = new Tesseract();

    public int[] velocityRunHolder;

    public void velocityRecognition() {

        int fileCounter = 0;

        File dir = new File(velocityDirPath);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {

            for (File child : directoryListing) {

                tess.setDatapath(tessDataPath);
                tess.setTessVariable("tessedit_char_whitelist", "0123456789");
                tess.setLanguage(tessLanguage);

                String recognizedText = "-1";


                try {
                    tess.setTessVariable(tessSetting, tessDpi);
                    recognizedText = tess.doOCR(new File(child.getAbsolutePath()));

                    String resultString = recognizedText.replaceAll("[^\\d]", "");
                    velocityRunHolder[fileCounter] = Integer.parseInt(resultString);

                } catch (TesseractException e) {
                    velocityRunHolder[fileCounter] = 130;                                                                                   //WICHTIG!!! Ändern auf vorherigen Wert!!!
                    System.out.println("Durch Standartwert ersetzt.");
                    e.printStackTrace();
                }

                System.out.println("Detected Text/Number: " + velocityRunHolder[fileCounter]);

                fileCounter++;
            }

        } else {
            System.out.println("There are no file in this directory. [Dir:" + velocityDirPath + "]");
        }

    }

}
