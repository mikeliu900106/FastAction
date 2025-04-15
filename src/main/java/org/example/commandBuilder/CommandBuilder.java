package org.example.commandBuilder;

import org.example.model.TargetConfig;
import org.example.model.task.Task;

import java.util.List;

public interface CommandBuilder {
    boolean supports(Class<? extends Task> taskType);
    List<String>  buildCommand(Task task);
    default boolean ipIsLocal(String ip){
        if(ip.equals("localhost") || ip.equals("127.0.0.1")){
            return  true;
        }
        return false;
    }
}
