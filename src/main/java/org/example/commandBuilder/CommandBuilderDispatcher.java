package org.example.commandBuilder;

import lombok.Builder;
import org.example.model.task.Task;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
@Builder
public class CommandBuilderDispatcher {
    private final List<CommandBuilder> commandBuilders;
    private final Queue<List<String>> queue = new ConcurrentLinkedQueue<>();

    public CommandBuilderDispatcher(List<CommandBuilder> builders) {
        this.commandBuilders = builders;
    }

    public void buildCommand(Task task) {
        for (CommandBuilder builder : commandBuilders) {
            if (builder.supports(task.getClass())) {
                queue.add(builder.buildCommand(task));
            }else {
                throw new IllegalArgumentException("No builder found for task: " + task.getClass().getSimpleName());
            }
        }
    }

    public Queue<List<String>> getQueue() {
        return queue;
    }
}
