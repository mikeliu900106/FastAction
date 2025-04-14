package org.example.commandBuilder;

import org.example.model.TargetConfig;
import org.example.model.task.Task;

import java.util.List;

public interface CommandBuilder {
    boolean supports(Class<?> taskType);
    List<String> buildCommand(Task task, TargetConfig targetConfig);
}
