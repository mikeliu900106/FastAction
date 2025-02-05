package org.example.service;

import org.example.model.*;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestoreServiceImpl extends AbstractFileHandle implements BackupRestoreService {
    private static Logger logger = LoggerFactory.getLogger(RestoreServiceImpl.class.getName());

    public RestoreServiceImpl() {
        //這裡透過建構子抓取properties資料
        this.sourceDir = "source";
    }

    public void pickupFile() {
        System.out.println("Restore");
    }

    public Map<String,List<String>> getDatabase() {
        Restore restore = getRestore();
        if(restore == null) {
            logger.info("Config Restore is null");
            return null;
        }
        List<Database> databases = restore.getDatabase();
        if(databases == null) {
            logger.info("Config Database is null");
            return null;
        }
        Map<String, List<String>> map = databases.stream()
                .collect(Collectors.groupingBy(
                        Database::getName,
                        Collectors.flatMapping(
                                db -> db.getTables().stream().flatMap(t -> t.getName().stream()), // 提取 name 列表中的所有元素
                                Collectors.toList()
                        )
                ));
        return map;
    }

    @Override
    public String getUserName() {
        Mysql mysql = getMysql();
        if(mysql == null) {
            logger.info("Config Mysql is null");
            return null;
        }
        String username = mysql.getUsername();
        return username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public String getPort() {
        return null;
    }

    public String getSocket() {
        Restore restore = getRestore();
        if(restore == null) {
            logger.info("Config Restore is null");
            return null;
        }
        String socket = restore.getSocket();
        if(socket == null) {
            logger.info("Socket is null");
            return null;
        }
        return socket;
    }

    public List<String> getFeature() {
        Restore restore = getRestore();
        if(restore == null) {
            logger.info("Config Restore is null");
            return null;
        }
        Feature feature = restore.getFeature();
        if(feature == null) {
            logger.info("feature is null");
            return null;
        }
        return feature.getName();
    }

    @Override
    public Queue<String> getSource() {
        return null;
    }

    @Override
    public Queue<String> getTarget() {
        return null;
    }

    @Override
    public String CheckUpFile(String file) {
        return null;
    }
    private Restore getRestore() {
        Restore restore =(Restore) ConfigService.getInstance().getConfigYmlClass("restore");
        return restore;
    }
    private Mysql getMysql() {
        Mysql mysql =(Mysql) ConfigService.getInstance().getConfigYmlClass("mysql");
        return mysql;
    }
}
