package org.example.commandBuilder;

import org.example.model.OsType;
import org.example.model.command.Command;
import org.example.model.task.MoveTask;
import org.example.model.task.Task;

import java.util.List;
import java.util.logging.FileHandler;

public class MoveCommandBuilder implements CommandBuilder , FileHandler {
    @Override
    public boolean supports(Class<? extends Task> taskType) {
        return MoveTask.class.equals(taskType);
    }

    @Override
    public List<String> buildCommand(Task task) {
        MoveTask moveTask = (MoveTask) task;
        String os = moveTask.getTargetConfig().getOs();
        String ip = moveTask.getTargetConfig().getIp();
        if(ipIsLocal(ip)){

        }
        if (OsType.WINDOWS.getValue().equalsIgnoreCase(os)) {
            return Command.MOVE_WINDOWS.buildCommand(
                    moveTask.getSource(), moveTask.getTarget()
            );
        } else if (OsType.LINUX.getValue().equalsIgnoreCase(os)) {
            return Command.MOVE_LINUX.buildCommand(
                    moveTask.getSource(), moveTask.getTarget()
            );
        } else {
            throw new UnsupportedOperationException("Unsupported OS: " + os);
        }
    }
}
