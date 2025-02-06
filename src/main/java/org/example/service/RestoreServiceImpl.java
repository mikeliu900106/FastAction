package org.example.service;

import org.example.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestoreServiceImpl extends AbstractFileHandle implements BackupRestoreService {
    private static Logger logger = LoggerFactory.getLogger(RestoreServiceImpl.class.getName());
    private static final String HOST_PREFIX = "-h ";
    private static final String PORT_PREFIX = "-P ";
    private static final String SOCKET_PREFIX = "--socket=";
    public RestoreServiceImpl() {
        //這裡透過建構子抓取properties資料
        this.sourceDir = "source";
    }

    public void pickupFile() {
        System.out.println("Restore");
    }

    public Map<String,List<String>> getDatabase() {
        return getRestore().getDatabase().stream()
                .collect(Collectors.groupingBy(
                        Database::getName,
                        Collectors.flatMapping(
                                db -> db.getTables().stream()
                                        .flatMap(t -> t.getName().stream()),
                                Collectors.toList()
                        )
                ));
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

    public String getFeature() {
        List<String> featureList = getRestore().getFeature().getName();
        String feature = "  ";
        if(featureList.isEmpty()) {
            return "";
        }
        for (int i = 0; i < featureList.size(); i++) {
            feature = feature.concat(featureList.get(i));
            feature = feature.concat(" ");
        }
        return feature;
    }


    @Override
    public Queue<Map<String,String>> getSource() {

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
