package com.widenetwork;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotHandler {

    private final String stockImageDirPath = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\stockImages\\";

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_S");

    public void takeStockScreenshot(int numbersOI) {

        BufferedImage bfStockImage = null;

        for (int i = 1; i <= numbersOI; i++) {
            try {
                LocalDateTime now = LocalDateTime.now();
                bfStockImage = new Robot().createScreenCapture(new Rectangle(0, 500, 1920, 500));
                ImageIO.write(bfStockImage, "png", new File(stockImageDirPath + "screenshot_" + dtf.format(now) + ".png"));
                System.out.println("Saved stock Image: " + i);
            } catch (Exception e) {
                System.out.println("Could not save stockimage: " + i);
                e.printStackTrace();
            }
        }
        System.out.println("Finished recording all " + numbersOI + " Stockimages");
        System.out.println(" ");

    }

    public void cutoutBorderImage() {

        System.out.println(" ");
        System.out.println("Start cutting out the borderImages...");


        BufferedImage stockImage;

        File dir = new File(stockImageDirPath);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {

            for (File child : directoryListing) {

                boolean successfullSaved = false;

                String fileName = child.getName();
                System.out.println("Stock filename: " + fileName);
                String date = fileName.substring(10, 28);
                String newFileName = "borderScreen" + date + ".png";

                String borderImageDirPath = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\borderScreens\\";
                try {
                    stockImage = ImageIO.read(child);   //Auslesen von Bilddatei
                    System.out.println("Cutout file...");

                    BufferedImage borderImage = stockImage.getSubimage(0, 0, 1920, 180);  //bild zuschneiden auf richtige größe
                    successfullSaved = ImageIO.write(borderImage, "png", new File(borderImageDirPath + newFileName));  // Bild in vorgesehenem Ordner speichern //gibt Wahrehitswert zurück um ein erfolgreiches Speichern nachzuvollziehen (Initialisiert mit: Speichervorgang fehlgeschlagen)
                    System.out.println("Saving file...");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (!successfullSaved) {
                    System.err.println("File [" + newFileName + "] could not be saved in " + borderImageDirPath);
                } else {
                    System.out.println("File [" + newFileName + "] successfully saved in " + borderImageDirPath);
                }

                System.out.println(" ");

            }

        } else {
            System.out.println("There are no file in this directory. [Dir:" + stockImageDirPath + "]");
        }

        System.out.println("Finished cutting out the borderImages!");

    }

    public void cutoutVelocityImage() {

        System.out.println(" ");
        System.out.println("Start cutting out the velocityImages...");

        boolean faultyOperation = false;    //Wahrehitswert um ein erfolgreiches Speichern nachzuvollziehen (Initialisiert mit: Speichervorgang fehlgeschlagen)

        BufferedImage stockImage = null;

        File dir = new File(stockImageDirPath);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {

            for (File child : directoryListing) {

                String fileName = child.getName();
                System.out.println("Stock filename: " + fileName);
                String date = fileName.substring(10, 28);
                String newFileName = "velocityScreen" + date + ".png";

                String velocityImageDirPath = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\velocityScreens\\";
                try {
                    stockImage = ImageIO.read(child);   //Auslesen von Bilddatei
                    System.out.println("Cutout file...");

                    BufferedImage velocityImage = stockImage.getSubimage(750, 390, 450, 100);    //bild zuschneiden auf richtige größe

                    velocityImage = invertImage(velocityImage);     // invert the color of velocity image

                    ImageIO.write(velocityImage, "png", new File(velocityImageDirPath + newFileName));    // Bild in vorgesehenem Ordner speichern
                    System.out.println("Saving file...");

                } catch (IOException e) {
                    e.printStackTrace();
                    faultyOperation = true;
                }

                if (faultyOperation) {
                    System.err.println("File [" + newFileName + "] could not be saved in " + velocityImageDirPath);
                } else {
                    System.out.println("File [" + newFileName + "] successfully saved in " + velocityImageDirPath);
                }

                System.out.println(" ");

            }

        } else {
            System.out.println("There are no file in this directory. [Dir:" + stockImageDirPath + "]");
        }

        System.out.println("Finished cutting out the velocityImages!");

    }

    private static BufferedImage invertImage(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                image.setRGB(x, y, -(image.getRGB(x, y)));
            }
        }
        return image;
    }

    public Boolean deleteImagesB() {
        boolean deleted = false;
        int deletedCounter = 0;
        String source = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\borderScreens\\";
        File dir = new File(source);
        long imagesInDir = dir.length();
        System.out.println(imagesInDir);
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                deleted = file.delete();
                if (deleted) {
                    deletedCounter++;
                }
            }
        }
        return deletedCounter == imagesInDir;

    }

    public Boolean deleteImagesV() {
        boolean deleted = false;
        int deletedCounter = 0;
        String source = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\velocityScreens\\";
        File dir = new File(source);
        long imagesInDir = dir.length();
        System.out.println(imagesInDir);
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                deleted = file.delete();
                if (deleted) {
                    deletedCounter++;
                }
            }
        }
        return deletedCounter == imagesInDir;

    }

    public Boolean deleteImagesS() {
        boolean deleted = false;
        int deletedCounter = 0;
        String source = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\stockImages\\";
        File dir = new File(source);
        long imagesInDir = dir.length();
        System.out.println(imagesInDir);
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                deleted = file.delete();
                if (deleted) {
                    deletedCounter++;
                }
            }
        }
        return deletedCounter == imagesInDir;

    }

}