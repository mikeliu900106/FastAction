package org.example.service;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractFileHandle implements ActionIStrategy {
    public abstract Queue<String> getTarget();
    public abstract Queue<String> getSource();
    protected AtomicBoolean isFile(String path){
        File file = new File(path);
        return new AtomicBoolean(file.isFile());
    }
    public abstract String CheckUpFile(String file);
}
