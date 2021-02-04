package com.widenetwork;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ImageProcessing {

    private static final Tesseract tess = new Tesseract();
    public static String text = "";
    public static int velocity = 0;
    static int bildcounter = 1;
    public TrainingsSetHandler trainingsSetHandler = new TrainingsSetHandler();
    int blackValue = 45;
    private DateTimeFormatter dtf = null;
    private LocalDateTime now = null;

    public void takeScreensVelo(int iterations, int timeoutMS) {
        System.out.println("Screenshotaufnahme läuft...");
        for (int i = 0; i < iterations; i++) {
            System.out.println(bildcounter);
            takeScreenshotVelocity();
            try {
                TimeUnit.MILLISECONDS.sleep(timeoutMS);
            } catch (Exception e) {
                System.err.print(e);
            }
            bildcounter++;
        }
        System.out.println("Erledigt!");
        bildcounter = 1;
    }

    public void takeScreensBrd(int iterations, int timeoutMS) {
        System.out.println("Screenshotaufnahme läuft...");
        for (int i = 0; i < iterations; i++) {
            System.out.println(bildcounter);
            takeScreenshotBorder();
            try {
                TimeUnit.MILLISECONDS.sleep(timeoutMS);
            } catch (Exception e) {
                System.err.print(e);
            }
            bildcounter++;
        }
        System.out.println("Erledigt!");
        bildcounter = 1;
    }

    public void takeScreensBoth(int iterations, int timeoutMS) {
        tess.setTessVariable("user_defined_dpi", "300");
        System.out.println("Screenshotaufnahme läuft...");
        for (int i = 0; i < iterations; i++) {
            System.out.println(bildcounter);


            takeScreenshotVelocity();
            ocrImageVeloDirectory();
            deleteImagesV();
            takeScreenshotBorder();
            editImageBorderDirectory();
            deleteImagesB();

            try {
                TimeUnit.MILLISECONDS.sleep(timeoutMS);
            } catch (Exception e) {
                System.err.print(e);
            }
            bildcounter++;
        }
        bildcounter = 1;


        trainingsSetHandler.saveTrainingSetData();
        trainingsSetHandler.createNN();
        trainingsSetHandler.saveNeuralNetwork();
    }

    private BufferedImage takeScreenshotVelocity() {
        BufferedImage imageVelocity = null;
        try {
            dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_S");
            now = LocalDateTime.now();
            imageVelocity = new Robot().createScreenCapture(new Rectangle(850, 890, 220, 90));
            ImageIO.write(imageVelocity, "png", new File("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\veloScreens\\screenshot_" + dtf.format(now) + ".png"));
        } catch (Exception e) {
            System.err.print(e);
        }
        return imageVelocity;
    }

    private BufferedImage takeScreenshotBorder() {
        BufferedImage imageBorder = null;
        try {
            dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_S");
            now = LocalDateTime.now();
            imageBorder = new Robot().createScreenCapture(new Rectangle(0, 500, 1920, 180));
            ImageIO.write(imageBorder, "png", new File("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\borderScreens\\screenshot_" + dtf.format(now) + ".png"));
        } catch (Exception e) {
            System.err.print(e);
        }
        return imageBorder;
    }

    public void ocrImageVeloDirectory() {
        Path p = Paths.get("C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\stockImages\\");
        FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                String t = file.toString();
                tess.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
                tess.setLanguage("eng");
                text = "-1";
                try {
                    text = tess.doOCR(new File(t));
                    try {
                        velocity = Integer.parseInt(text);

                    } catch (Exception e) {
                        velocity = 100;
                        System.out.println("Durch Standartwert ersetzt.");
                    }


                } catch (TesseractException e) {
                    e.printStackTrace();
                }
                System.out.println("Detected Text/Number: " + text);
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(p, fv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editImageBorderDirectory() {
        System.out.println("Bildbearbeitung läuft...");
        String source = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\borderScreens\\";
        BufferedImage image = null;
        File dir = new File(source);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                System.out.println(bildcounter);
                String absolutePath = child.getAbsolutePath();
                try {
                    image = ImageIO.read(child);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //grayscaleImage(image, absolutePath);
                searchForBlackSpots(image, absolutePath);
                bildcounter++;
            }
        }
        System.out.println("Erledigt!");
        bildcounter = 1;
    }

    private void grayscaleImage(BufferedImage img, String outputPath) {
        int width;
        int height;
        try {
            width = img.getWidth();
            height = img.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(img.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(red + green + blue,
                            red + green + blue, red + green + blue);
                    //img.setRGB(j, i, newColor.getRGB());
                }
            }
            safeImage(img, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchForBlackSpots(BufferedImage image, String outputPath) {

        int y = 160;
        int x = 960;

        int[] left = drawLeft(image, x, y);
        int[] right = drawRight(image, x, y);
        int[] up = drawUp(image, x, y);
        int[] topLeft = drawTopLeft(image, x, y);
        int[] topLeftLeft = drawTopLeftLeft(image, x, y);
        int[] topRight = drawTopRight(image, x, y);
        int[] topRightRight = drawTopRightRight(image, x, y);

        trainingsSetHandler.putInTrainingsSet(left[0], topLeftLeft[0], topLeft[0], up[0], topRight[0], topRightRight[0], right[0], velocity); //von links nach rechts

        markResults(image, left, right, up, topLeft, topLeftLeft, topRight, topRightRight);

        safeImage(image, outputPath);


    }

    private int[] drawLeft(BufferedImage image, int x, int y) {
        x -= 450;
        do {
            x -= 1;

            if (extractR(image.getRGB(x, y)) < blackValue) {
                if (((image.getRGB(x - 1, y) + image.getRGB(x - 2, y) + image.getRGB(x - 3, y) + image.getRGB(x - 1, y - 1) + image.getRGB(x - 2, y - 1) + image.getRGB(x - 3, y - 1)
                        + image.getRGB(x - 1, y + 1) + image.getRGB(x - 2, y + 1) + image.getRGB(x - 3, y + 1)) / 9) < 20000) {
                    return new int[]{(960 - x), x, y};
                }
            }
            // image.setRGB(x, y, blackValue);
        } while (x > 5);
        return new int[]{960, x, y};
    }

    private int[] drawRight(BufferedImage image, int x, int y) {
        x += 450;
        do {
            x += 1;
            if (extractR(image.getRGB(x, y)) < blackValue) {
                if (((image.getRGB(x + 1, y) + image.getRGB(x + 2, y) + image.getRGB(x + 3, y) + image.getRGB(x + 1, y - 1) + image.getRGB(x + 2, y - 1) + image.getRGB(x + 3, y - 1)
                        + image.getRGB(x + 1, y + 1) + image.getRGB(x + 2, y + 1) + image.getRGB(x + 3, y + 1)) / 9) < 20000) {
                    return new int[]{(x - 960), x, y};
                }
            }
            //  image.setRGB(x, y, blackValue);
        } while (x < 1915);     //Rand rechts, minus 3 um sicherzugehen das die Abfrage nicht out of bounds geht


        return new int[]{960, x, y};
    }

    private int[] drawUp(BufferedImage image, int x, int y) {
        y -= 45;
        do {
            y -= 1;

            if (extractR(image.getRGB(x, y)) < blackValue) {
                if (((image.getRGB(x - 1, y - 1) + image.getRGB(x - 1, y - 2) + image.getRGB(x - 1, y - 3) + image.getRGB(x + 1, y - 1) + image.getRGB(x + 1, y - 2) + image.getRGB(x + 1, y - 3)
                        + image.getRGB(x, y - 1) + image.getRGB(x, y - 2) + image.getRGB(x, y - 3)) / 9) < 20000) {
                    return new int[]{(160 - y), x, y};
                }
            }
            // image.setRGB(x, y, blackValue);
        } while (y > 5);        //Rand oben, plus 3 um sicherzugehen das die Abfrage nicht out of bounds geht
        return new int[]{40, x, y};
    }

    private int[] drawTopRight(BufferedImage image, int x, int y) {
        y -= 45;
        x += 45;
        do {
            y -= 1;
            x += 1;

            if (extractR(image.getRGB(x, y)) < blackValue) {
                if (((image.getRGB(x + 1, y - 1) + image.getRGB(x + 1, y - 2) + image.getRGB(x + 1, y - 3) + image.getRGB(x + 2, y - 1) + image.getRGB(x + 2, y - 2) + image.getRGB(x + 2, y - 3)
                        + image.getRGB(x + 3, y - 1) + image.getRGB(x + 3, y - 2) + image.getRGB(x + 3, y - 3)) / 9) < 20000) {
                    return new int[]{(int) Math.sqrt((x - 960) * (x - 960) + (160 - y) * (160 - y)), x, y};
                }
            }
            //  image.setRGB(x, y, blackValue);
        } while (y > 5);     //Rand oben, plus 3 um sicherzugehen das die Abfrage nicht out of bounds geht
        return new int[]{57, x, y};
    }

    private int[] drawTopLeft(BufferedImage image, int x, int y) {
        x -= 45;
        y -= 45;
        do {
            y -= 1;
            x -= 1;

            if (extractR(image.getRGB(x, y)) < blackValue) {
                if (((image.getRGB(x - 1, y - 1) + image.getRGB(x - 1, y - 2) + image.getRGB(x - 1, y - 3) + image.getRGB(x - 2, y - 1) + image.getRGB(x - 2, y - 2) + image.getRGB(x - 2, y - 3)
                        + image.getRGB(x - 3, y - 1) + image.getRGB(x - 3, y - 2) + image.getRGB(x - 3, y - 3)) / 9) < 20000) {

                    return new int[]{(int) Math.sqrt((960 - x) * (960 - x) + (160 - y) * (160 - y)), x, y};
                }
            }


            //  image.setRGB(x, y, blackValue);
        } while (y > 5);     //Rand oben, plus 3 um sicherzugehen das die Abfrage nicht out of bounds geht
        return new int[]{57, x, y};
    }

    private int[] drawTopRightRight(BufferedImage image, int x, int y) {
        x += 100;
        y -= 45;
        do {
            y -= 1;
            x += 5;

            if (extractR(image.getRGB(x, y)) < blackValue) {
                if (((image.getRGB(x + 1, y - 1) + image.getRGB(x + 1, y - 2) + image.getRGB(x + 1, y - 3) + image.getRGB(x + 2, y - 1) + image.getRGB(x + 2, y - 2) + image.getRGB(x + 2, y - 3)
                        + image.getRGB(x + 3, y - 1) + image.getRGB(x + 3, y - 2) + image.getRGB(x + 3, y - 3)) / 9) < 20000) {
                    return new int[]{(int) Math.sqrt((x - 960) * (x - 960) + (160 - y) * (160 - y)), x, y};
                }
            }
            // image.setRGB(x, y, blackValue);
        } while (y > 5);            //Rand oben, plus 3 um sicherzugehen das die Abfrage nicht out of bounds geht
        return new int[]{209, x, y};
    }

    private int[] drawTopLeftLeft(BufferedImage image, int x, int y) {
        x -= 100;
        y -= 45;
        do {
            y -= 1;
            x -= 5;

            if (extractR(image.getRGB(x, y)) < blackValue) {
                if (((image.getRGB(x - 1, y - 1) + image.getRGB(x - 1, y - 2) + image.getRGB(x - 1, y - 3) + image.getRGB(x - 2, y - 1) + image.getRGB(x - 2, y - 2) + image.getRGB(x - 2, y - 3)
                        + image.getRGB(x - 3, y - 1) + image.getRGB(x - 3, y - 2) + image.getRGB(x - 3, y - 3)) / 9) < 20000) {

                    return new int[]{(int) Math.sqrt((960 - x) * (960 - x) + (160 - y) * (160 - y)), x, y};
                }
            }


            // image.setRGB(x, y, blackValue);
        } while (y > 5);     //Rand oben, plus 3 um sicherzugehen das die Abfrage nicht out of bounds geht
        return new int[]{209, x, y};
    }

    private void safeImage(BufferedImage img, String outputPath) {
        File outputFile = new File(outputPath);
        try {
            ImageIO.write(img, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void markResults(BufferedImage img, int[] l, int[] r, int[] u, int[] tl, int[] tll, int[] tr, int[] trr) {

        Graphics2D g = (Graphics2D) img.getGraphics();


        g.setColor(Color.red);
        g.setStroke(new BasicStroke(5));

        if (l[1] > 5) {
            g.drawOval(l[1] - 15, 160 - 15, 30, 30); // Links
        }
        if (r[1] < 1915) {
            g.drawOval(r[1] - 15, 160 - 15, 30, 30); // Rechts
        }
        if (u[2] > 5) {
            g.drawOval(960 - 15, u[2] - 15, 30, 30); // Oben         +15 aufgrund des Offsets, da x,y im linken oberen Eck liegt
        }
        if (tl[2] > 5) {
            g.drawOval(tl[1] - 15, tl[2] - 15, 30, 30); //Links Oben
        }
        if (tl[2] > 5) {
            g.drawOval(tll[1] - 15, tll[2] - 15, 30, 30); //Links Links Oben
        }
        if (tr[2] > 5) {
            g.drawOval(tr[1] - 15, tr[2] - 15, 30, 30); //Rechts Oben
        }
        if (trr[2] > 5) {
            g.drawOval(trr[1] - 15, trr[2] - 15, 30, 30); //Rechts Rechts Oben
        }

    }

    public int extractR(int pixel) {
        return (pixel & 0x00ff0000) >> 16;
    }

    private Boolean deleteImagesB() {
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

    private Boolean deleteImagesV() {
        boolean deleted = false;
        int deletedCounter = 0;
        String source = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\veloScreens\\";
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