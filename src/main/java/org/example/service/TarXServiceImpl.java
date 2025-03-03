package org.example.service;

import org.example.model.Action;
import org.example.model.Move;
import org.example.model.Tar;
import org.example.model.Zip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TarXServiceImpl extends AbstractFileHandle{
    private static Logger logger = LoggerFactory.getLogger(TarXServiceImpl.class.getName());
    private static final TarXServiceImpl INSTANCE = new TarXServiceImpl();
    private Map<String,String> sourceToTarget = new HashMap<>();
    private static Queue<String> commands = new ConcurrentLinkedQueue<>();
    public TarXServiceImpl() {
        //這裡透過建構子抓取properties資料
        this.commands = getCommand();
    }

    public TarXServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public String checkUpFile(List<String> file) {
        return null;
    }

    @Override
    public void pickupFile() {
        try {
            logger.info("Start to move: " + commands);
            String command =  commands.poll();
            // 假設 command 是要執行的指令，例如 "ls -l"
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
            Process process = pb.start();

            // 取得執行結果
            int exitCode = process.waitFor();
            logger.info("Command exit code: " + exitCode);

        } catch (Exception e) {
            logger.error("move failed");
            logger.error("", e);
        }
    }

    @Override
    public Map<String, String> getSourceToTarget() {
        Tar tar = getTar();
        List<Zip> zips = tar.getZip();
        if(zips == null || zips.isEmpty() || zips.size() !=2){
            throw new RuntimeException("zip is empty or size is not 2");
        }
        Zip zipObj = zips.stream()
                .filter(z -> "unzip".equals(z.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No zip found in tar list"));
        return Map.of(
                "source", zipObj.getSource(),
                "target", zipObj.getTarget()
        );
    }

    @Override
    public Queue<String> getCommand() {
        for (Map.Entry<String, String> entry : sourceToTarget.entrySet()) {
            String source = entry.getKey();
            String target = entry.getValue();
            String command = Action.TAR_ZXVF.getCommand() ;
            command = command
                    .replace("<SOURCEFILE>",source)
                    .replace("<TARGETFILE>",target);
            commands.add(command);
        }
        return commands;
    }
    private Tar getTar() {
        return (Tar) ConfigService.getInstance().getConfigYmlClass("Tar");
    }
}

