package com.widenetwork.Driver;


public class Security {


    public boolean isFocused(Process process) {

        try {

            Runtime r = Runtime.getRuntime();
            process = r.exec("notepad.exe");
            process.waitFor();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }


}