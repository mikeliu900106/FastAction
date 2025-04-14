package org.example.commandBuilder;

import org.example.model.TargetConfig;
import org.example.model.task.MoveTask;
import org.example.model.task.Task;

import java.util.List;

public class MoveCommandBuilder implements CommandBuilder{
    @Override
    public boolean supports(Class<?> taskType) {
        return MoveTask.class.equals(taskType);
    }

    @Override
    public List<String> buildCommand(Task task, TargetConfig targetConfig) {
        if (osType == OSType.LINUX) {
            return List.of("mv", task.getSource(), task.getTarget());
        } else if (osType == OSType.WINDOWS) {
            return List.of("cmd", "/c", "move", task.getSource(), task.getTarget());
        }
        throw new UnsupportedOperationException("Unsupported OS");
    }
}
