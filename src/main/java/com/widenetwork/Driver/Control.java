package com.widenetwork.Driver;

import java.awt.*;
import java.util.Arrays;

public class Control {

    public static Robot r;

    public static int sleepTime = 100;

    public void decisionMaker(double[] nnetOutput) {

        int i = 1;
        accelerate(1000);

        if (Arrays.equals(nnetOutput, new double[]{0, 0, 0, 0})) {
            while (i != 0) {
                //Nothing
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{0, 0, 0, 1})) {
            while (i != 0) {
                accelerate();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{0, 0, 1, 0})) {
            while (i != 0) {
                brake();
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{0, 0, 1, 1})) {
            while (i != 0) {
                //Nothing

                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{0, 1, 0, 0})) {
            while (i != 0) {
                steerRight();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{0, 1, 0, 1})) {
            while (i != 0) {
                steerRight();
                accelerate();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{0, 1, 1, 0})) {
            while (i != 0) {
                steerRight();
                brake();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{0, 1, 1, 1})) {
            while (i != 0) {
                steerRight();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{1, 0, 0, 0})) {
            while (i != 0) {
                steerLeft();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{1, 0, 0, 1})) {
            while (i != 0) {
                steerLeft();
                accelerate();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{1, 0, 1, 0})) {
            while (i != 0) {
                steerLeft();
                brake();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{1, 0, 1, 1})) {
            while (i != 0) {
                steerLeft();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{1, 1, 0, 0})) {
            while (i != 0) {
                //Nothing
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{1, 1, 0, 1})) {
            while (i != 0) {
                accelerate();
                i--;
            }
            return;
        } else if (Arrays.equals(nnetOutput, new double[]{1, 1, 1, 0})) {
            while (i != 0) {
                brake();
                i--;
            }
        } else if (Arrays.equals(nnetOutput, new double[]{1, 1, 1, 1})) {
            while (i != 0) {
                //Nothing
                i--;
            }
            return;
        } else {
            System.out.println("Hier ist was deutlich schief gelaufen!!! {'Contorl.decisionMaker()'}");
        }

    }

    public void accelerate() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            r.keyPress(38);
            Thread.sleep(sleepTime);
            r.keyRelease(38);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void accelerate(int sleep) {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            r.keyRelease(38);
            r.keyPress(38);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void brake() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            r.keyRelease(40);
            r.keyPress(40);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void steerLeft() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            r.keyRelease(37);
            r.keyPress(37);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void steerRight() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            r.keyRelease(39);
            r.keyPress(39);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
