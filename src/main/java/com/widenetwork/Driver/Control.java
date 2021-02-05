package com.widenetwork.Driver;

import java.awt.*;

public class Control {

    public static Robot r;

    public void accelerate() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(38);
    }

    public void brake() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(40);
    }

    public void steerLeft() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(37);
    }

    public void steerRight() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(39);
    }

}
