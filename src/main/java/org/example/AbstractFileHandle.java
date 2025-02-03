package org.example;

import java.util.Queue;

public abstract class AbstractFileHandle implements ActionIStrategy{
    protected String sourceFile;
    protected String TargetFile;
    public abstract Queue<String> getTargetDir();
    public abstract Queue<String> getSourceDir();

    public abstract String CheckUpFile(String file);
}
