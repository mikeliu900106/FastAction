package org.example.service;

import org.example.model.task.Task;

import java.util.Map;
import java.util.Queue;

public interface TaskStrategy {
    String type();
    public void pickupTask(Task task);
}
