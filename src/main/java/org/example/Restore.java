package org.example;

import java.util.Queue;

public class Restore extends AbstractFileHandle {
    sourceFile = "source";
    public void pickupFile() {
        System.out.println("Restore");
    }

    @Override
    public Queue<String> getSourceDir() {
        return null;
    }

    @Override
    public Queue<String> getTargetDir() {
        return null;
    }

    @Override
    public String CheckUpFile(String file) {
        return null;
    }
}
