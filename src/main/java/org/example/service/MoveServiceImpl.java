//package org.example.service;
//
//import org.example.model.Action;
//import org.example.model.Move;
//import org.example.model.Mysql;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.FileOutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
//public class MoveServiceImpl extends AbstractFileHandle{
//    private static Logger logger = LoggerFactory.getLogger(MoveServiceImpl.class.getName());
//    private static final MoveServiceImpl INSTANCE = new MoveServiceImpl();
//    private Map<String,String> sourceToTarget = new HashMap<>();
//    private static Queue<String> commands = new ConcurrentLinkedQueue<>();
//
//    public MoveServiceImpl getInstance() {
//        return INSTANCE;
//    }
//    @Override
//    public String checkUpFile(List<String> file) {
//        return null;
//    }
//    public MoveServiceImpl() {
//        //這裡透過建構子抓取properties資料
//        this.commands = getCommand();
//    }
//    @Override
//    public void execute() {
//        try {
//            logger.info("Start to move: " + commands);
//            String command =  commands.poll();
//            // 假設 command 是要執行的指令，例如 "ls -l"
//            ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
//            Process process = pb.start();
//
//            // 取得執行結果
//            int exitCode = process.waitFor();
//            logger.info("org.example.model.command.Command exit code: " + exitCode);
//
//        } catch (Exception e) {
//            logger.error("move failed");
//            logger.error("", e);
//        }
//    }
//
//
//    @Override
//    public Map<String, String> getSourceToTarget() {
//        Move move = getMove();
//        String source = move.getSource();
//        String target = move.getTarget();
//        if(source == null || source.isEmpty())
//            throw new RuntimeException("Source is empty");
//        if (target == null || target.isEmpty())
//            throw new RuntimeException("Target is empty");
//        if(!isFile(target)){
//            List<String> sourceFile = getAllFiles(target,"move");
//            for (int i = 0; i < sourceFile.size(); i++) {
//                sourceToTarget.put(sourceFile.get(i),target);
//            }
//        }else{
//            sourceToTarget.put(source,target);
//        }
//        return sourceToTarget;
//    }
//
//    @Override
//    public Queue<String> getCommand() {
//        for (Map.Entry<String, String> entry : sourceToTarget.entrySet()) {
//            String source = entry.getKey();
//            String target = entry.getValue();
//            String command = Action.MOVE.getCommand() ;
//            command = command
//                    .replace("<SOURCEFILE>",source)
//                    .replace("<TARGETFILE>",target);
//            commands.add(command);
//        }
//        return commands;
//    }
//
//    private Move getMove() {
//        return (Move) ConfigService.getInstance().getConfigYmlClass("move");
//    }
//}
