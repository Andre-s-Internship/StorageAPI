package org.example.util;

import org.example.model.Directory;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<Directory> system;

    private Data() {
        system = new ArrayList<>();
    }

    public static List<Directory> getSystem() {
        if(system == null) {
            new Data();
        }
        return system;
    }

    //This method is not usable in the project, It is created for running all tests at once without errors
    public static void clearSystem() {
        system = null;
    }

}
