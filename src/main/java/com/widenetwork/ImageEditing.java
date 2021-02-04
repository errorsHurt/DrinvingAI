package com.widenetwork;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEditing {

    public String borderImageDirPath = "C:\\Users\\Meiers PC\\Desktop\\ImageRecognition\\borderScreens\\";
    public TrainingsSetHandler trainingsSetHandler = new TrainingsSetHandler();
    private int velocity = 0;
    private int blackValue = 35;

    public void markSpots() {

        BufferedImage image = null;

        File dir = new File(borderImageDirPath);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {

                String absolutePath = child.getAbsolutePath();
                try {
                    image = ImageIO.read(child);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                grayscaleImage(image, absolutePath);
                lookAndMarkSpots(image, absolutePath);

            }
        }
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

    private void lookAndMarkSpots(BufferedImage image, String outputPath) {

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

    private int extractR(int pixel) {
        return (pixel & 0x00ff0000) >> 16;
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

    private void safeImage(BufferedImage img, String outputPath) {
        File outputFile = new File(outputPath);
        try {
            ImageIO.write(img, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
