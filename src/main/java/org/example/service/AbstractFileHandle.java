package org.example.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileHandle implements TaskStrategy {
    protected boolean isFile(String path){
        File file = new File(path);
        return  file.isFile();
    }
    protected List<String> getAllFiles(String path ,String type) {
        File directory = new File(path);
        List<String> items = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null || files.length > 0){
            for (File file : files) {
                if(!file.isDirectory())
                    items.add(file.getName());
            }
        }else {
            throw new RuntimeException("No file to " + type + " in " + path);
        }
        return items;
    }
    public abstract String checkUpFile(List<String> file);
}
