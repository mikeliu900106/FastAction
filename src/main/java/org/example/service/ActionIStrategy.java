package org.example.service;

import java.util.Map;
import java.util.Queue;

public interface ActionIStrategy {
    public void pickupFile();
    public Map<String,String> getSourceToTarget();
    public Queue<String> getCommand();
}
