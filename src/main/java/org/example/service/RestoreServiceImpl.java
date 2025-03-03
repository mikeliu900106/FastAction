package org.example.service;

import org.example.model.*;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestoreServiceImpl extends AbstractFileHandle implements BackupRestoreService {
    private static Logger logger = LoggerFactory.getLogger(RestoreServiceImpl.class.getName());
    private static final RestoreServiceImpl INSTANCE = new RestoreServiceImpl();

    private static Map<String,String> SourceToTarget = new HashMap<>();
    private static Queue<String> commands = new ConcurrentLinkedQueue<>();
    private static final String HOST_PREFIX = "-h ";
    private static final String PORT_PREFIX = "-P ";
    private static final String SOCKET_PREFIX = "--socket=";
    private static final String DATABASE_PREFIX = "--databases ";
    private static final String TABLE_PREFIX = "--tables ";

    public RestoreServiceImpl() {
        //這裡透過建構子抓取properties資料
//        this.sourceDir = getSource();
//        this.targetDir = getTarget();
        this.commands = getCommand();
    }
    public RestoreServiceImpl getInstace() {
        return INSTANCE;
    }
    public void pickupFile() {

            try {
                logger.info("Start to restore: " + commands);
                String command =  commands.poll();
                // 假設 command 是要執行的指令，例如 "ls -l"
                ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
                Process process = pb.start();

                // 取得執行結果
                int exitCode = process.waitFor();
                logger.info("Command exit code: " + exitCode);

            } catch (Exception e) {
                logger.error("Restore failed");
                logger.error("", e);
            }

    }

    public List<Map<String,Optional<String>>> getDatabaseAndTable() {
        List<String> files = getFiles();
        if (files.isEmpty()) {
            try {
                logger.error("No file to restore");
                throw new RuntimeException("No file to restore");
            }
            catch (RuntimeException e) {
                logger.error("No file to restore");
                logger.error("", e);
                System.exit(0);
            }
        }
        return files.stream().map(
                file -> {
                    String databaseName = "";
                    String tableName = "";
                    String path = file;
                    String name = file.substring(file.lastIndexOf("/") + 1,file.lastIndexOf(".") );
                    String prefix = file.substring(file.lastIndexOf("."));
                    String[] split = name.split("-");
                    databaseName = DATABASE_PREFIX.concat(split[0]);
                    if(split.length > 1) {
                        tableName = TABLE_PREFIX.concat(split[1]);
                    }
                    name = name.concat(prefix);
                    return Map.of("path", Optional.of(path), "name",Optional.of(name) ,"databaseName", Optional.of(databaseName), "tableName", Optional.of(tableName));
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<String> getFiles() {
        List<String> yamlFiles = getRestore().getFile();
        List<String> realFiles = new ArrayList<>();
        try {
            if (yamlFiles.isEmpty()) {
                throw new RuntimeException("No file to restore");
            }
        } catch (RuntimeException e) {
            logger.error("No file to restore");
            logger.error("", e);
            System.exit(0);
        }
        for (int i = 0; i < yamlFiles.size(); i++) {
            if(isFile(yamlFiles.get(i))) {
                realFiles.add(yamlFiles.get(i));
            }else{
                realFiles.addAll(getAllFiles(yamlFiles.get(i), "restore"));
            }
        }
        return realFiles;
    }

    @Override
    public String getUserName() {
        return getMysql().getUsername();
    }

    @Override
    public String getPassword() {
        return getMysql().getPassword();
    }

    @Override
    public String getHost() {
        return HOST_PREFIX.concat(getMysql().getHost());
    }

    @Override
    public String getPort() {
        return PORT_PREFIX.concat(getMysql().getPort());
    }

    public String getSocket() {
        return SOCKET_PREFIX.concat(getRestore().getSocket());
    }

    public Optional<String> getFeature() {
        List<String> featureList = getRestore().getFeature().getName();
        String feature = "";
        if(featureList.isEmpty()) {
            return Optional.empty();
        }
        for (int i = 0; i < featureList.size(); i++) {
            feature = feature.concat(featureList.get(i));
            feature = feature.concat(" ");
        }
        return Optional.of(feature);
    }

    public Queue<String> getCommand() {
        Queue<String> tmpSourceCommand = new ConcurrentLinkedQueue<>();
        List<Map<String, Optional<String>>> databasesAndTables = getDatabaseAndTable();
        String userName = getUserName();
        String password = getPassword();
        String host = getHost();
        String port = getPort();
        String socket = getSocket();
        String feature = getFeature().orElse(" ");
       for (int i = 0; i < databasesAndTables.size(); i++) {
             Map<String, Optional<String>> databaseAndTable = databasesAndTables.get(i);
              String databaseName = databaseAndTable.get("databaseName").orElse("");
              String tableName = databaseAndTable.get("tableName").orElse("");
              String path = databaseAndTable.get("path").orElse("");
              String name = databaseAndTable.get("name").orElse("");
              String prefix = name.substring(name.lastIndexOf("."));
              String command = Action.MYSQL_RESTORE.getCommand().replace("<USER>", userName)
                      .replace("<PASSWORD>", password)
                      .replace("<HOST>", host)
                      .replace("<PORT>", port)
                      .replace("<SOCKET>", socket)
                      .replace("<FEATURE>", feature)
                      .replace("<DATABASE>", databaseName)
                      .replace("<TABLE>", tableName)
                      .replace("<FILE>", path);
           tmpSourceCommand.add(command);
       }
       return tmpSourceCommand;
    }

    public Map<String,String>  getSourceToTarget() {
        return null;
    }
    @Override
    public String checkUpFile(List<String> file) {
        return null;
    }
    private Restore getRestore() {
        return (Restore) ConfigService.getInstance().getConfigYmlClass("restore");
    }
    private Mysql getMysql() {
        return (Mysql) ConfigService.getInstance().getConfigYmlClass("mysql");
    }
}
