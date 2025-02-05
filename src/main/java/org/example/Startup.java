package org.example;

import org.example.model.ActionType;
import org.example.service.ActionIStrategy;
import org.example.service.RestoreServiceImpl;
import org.example.util.JarLocationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Startup {
    private static int threadCount = 3;
    //之後要改成config吃配置文件
    private static final ExecutorService TASK_EXECUTOR = Executors.newFixedThreadPool(threadCount);
    private static Logger logger = LoggerFactory.getLogger(Startup.class.getName());

    private ActionIStrategy strategy;
    public static void main(String[] args) {
        String allActionType = ActionType.getAllActionType();
        if (args.length == 0) {
            logger.error("Please input the action type.");
            return;
        }
        for (int i = 0; i < args[0].length(); i++) {
             String actionType = args[0].substring(i, i + 1);
             Startup startup = new Startup();
             if(allActionType.contains(actionType)){
                 startup.setStrategy(actionType);
                 startup.submitTask();
             }else {
                 System.out.println("The action type is not exist.");
             }
        }
        TASK_EXECUTOR.shutdown();
    }

    private void submitTask() {
        for (int i = 0; i < threadCount; i++) {
            TASK_EXECUTOR.execute(() -> {
                strategy.pickupFile();
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