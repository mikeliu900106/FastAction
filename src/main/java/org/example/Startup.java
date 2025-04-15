package org.example;

import org.example.commandBuilder.CommandBuilderDispatcher;
import org.example.commandBuilder.MoveCommandBuilder;
import org.example.model.task.Task;
import org.example.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.model.task.Task.getTaskList;

public class Startup {
    private static int threadCount = 3;
    //之後要改成config吃配置文件
    private static final ExecutorService TASK_EXECUTOR = Executors.newFixedThreadPool(threadCount);
    private static Logger logger = LoggerFactory.getLogger(Startup.class.getName());
    private CommandBuilderDispatcher commandBuilderDispatcher;

    private TaskStrategy strategy;
    public static void main(String[] args)  {
        try {
            List<Task> tasks = getTaskList("config.yaml");
            CommandBuilderDispatcher commandBuilderDispatcher =
                    CommandBuilderDispatcher
                            .builder()
                            .commandBuilders(
                                List.of(
                                        new MoveCommandBuilder()
                                )
                            )
                            .build();
            for (Task task : tasks){
                commandBuilderDispatcher.buildCommand(task);
            }
            Queue<String> queue = commandBuilderDispatcher.getQueue();

        } catch (IOException e) {
            throw new RuntimeException("讀取檔案錯誤");
        }

    }

    private void submitTask() {
        for (int i = 0; i < threadCount; i++) {
            TASK_EXECUTOR.execute(() -> {
                this.strategy.execute();
            });
        }
    }

    public void setStrategy(String actionType) {
        switch (actionType){
//            case "A":
//                this.strategy = new add();
//                break;
//            case "B":
//                this.strategy = new RestoreServiceImpl();
//                break;
//            case "C":
//                this.strategy = new divide();
//                break;
//            case "D":
//                this.strategy = new multyply();
//                break;
//            case "E":
//                this.strategy = new multyply();
//                break;
//            case "F":
//                this.strategy = new multyply();
//                break;
//            case "G":
//                this.strategy = new multyply();
//                break;
            case "H":
                this.strategy = new RestoreServiceImpl();
                break;
            case "O":
                this.strategy = new TarCServiceImpl();
                break;
            case "P":
                this.strategy = new MoveServiceImpl();
                break;
            case "Q":
                this.strategy = new TarXServiceImpl();
                break;
//            case "I":
//                this.strategy = new multyply();
//                break;
//            case "J":
//                this.strategy = new multyply();
//                break;
//            case "K":
//                this.strategy = new multyply();
//                break;
        }
    }
}