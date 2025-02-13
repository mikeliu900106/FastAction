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
    private static Queue<Map<String,String>> sourceDir = new ConcurrentLinkedQueue<>();
    private static Queue<Map<String,String>> targetDir = new ConcurrentLinkedQueue<>();
    private static final String HOST_PREFIX = "-h ";
    private static final String PORT_PREFIX = "-P ";
    private static final String SOCKET_PREFIX = "--socket=";
    private static final String DATABASE_PREFIX = "--databases ";
    private static final String TABLE_PREFIX = "--tables ";

    public RestoreServiceImpl() {
        //這裡透過建構子抓取properties資料
        this.sourceDir = getSource();
        this.targetDir = getTarget();
    }

    public void pickupFile() {
        System.out.println("Restore");
    }

    public List<Map<String,Optional<String>> getDatabaseAndTable() {
        List<String> files = getFiles();
        if (files.isEmpty()) {
            throw new RuntimeException("No file to restore");
        }
        return files.stream().map(
                file -> {
                    String databaseName = "";
                    String tableName = "";
                    String path = file;
                    String name = file.substring(file.lastIndexOf("/") + 1);
                    String[] split = name.split("-");
                    databaseName = split[0];
                    if(split.length != 2) {
                        tableName = split[1];
                    }
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


    @Override
    public Queue<Map<String,String>> getSource() {
        List<Map<String,Optional<String>>> databasesAndTables = getDatabaseAndTable();
        String userName = getUserName();
        String password = getPassword();
        String host = getHost();
        String port = getPort();
        String socket = getSocket();
        String feature = getFeature().orElse(" ");
        for (int i = 0; i < databasesAndTables.size(); i++) {
            String path = databasesAndTables.get(i).get("path");
            String databaseName = databasesAndTables.get(i).get("databaseName");
            String tableName = databasesAndTables.get(i).get("tableName").is;
            String name = databasesAndTables.get(i).get("name");
            String restoreSql = Action.MYSQL_RESTORE.getCommand();
            restoreSql = restoreSql
                    .replace("<USER>", userName)
                    .replace("<PASSWORD>", password)
                    .replace("<HOST>", host)
                    .replace("<PORT>", port)
                    .replace("<SOCKET>", socket)

        }
    }

    @Override
    public Queue<Map<String,String>> getTarget() {
        return null;
    }

    @Override
    public String checkUpFile(List<String> file) {

    }
    private Restore getRestore() {
        return (Restore) ConfigService.getInstance().getConfigYmlClass("restore");
    }
    private Mysql getMysql() {
        return (Mysql) ConfigService.getInstance().getConfigYmlClass("mysql");
    }
}
