package com.widenetwork;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;


public class TextRecognitionHandler {

    private final String tessDataPath = "C:\\Program Files\\Tesseract-OCR\\tessdata";
    private final String tessLanguage = "eng";
    private final String tessDpi = "300";
    private final String tessSetting = "tessuser_defined_dpi";

    private final String velocityDirPath = "C:\\Users\\" + Main.user + "\\Desktop\\ImageRecognition\\velocityScreens";
    public static Tesseract tess = new Tesseract();

    public int[] velocityRunHolder;

    public void velocityRecognition() {

        int fileCounter = 0;

        File dir = new File(velocityDirPath);
        File[] directoryListing = dir.listFiles();
        System.out.println("1");
        tess.setDatapath(tessDataPath);
        System.out.println("2");
        tess.setLanguage(tessLanguage);
        System.out.println("3");
        tess.setTessVariable("tessedit_char_whitelist", "0123456789");
        System.out.println("4");
        tess.setTessVariable("TESSDATA_PREFIX", "C:\\Program Files\\Tesseract-OCR");
        System.out.println("5");
        tess.setOcrEngineMode(3);
        System.out.println("5.5");

        if (directoryListing != null) {
            System.out.println("6");

            for (File child : directoryListing) {
                System.out.println("7");


                String recognizedText = "-1";

                try {
                    System.out.println("8");
                    tess.setTessVariable(tessSetting, tessDpi);
                    System.out.println("9");

                    recognizedText = tess.doOCR(new File(child.getAbsolutePath()));
                    System.out.println("10");

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
