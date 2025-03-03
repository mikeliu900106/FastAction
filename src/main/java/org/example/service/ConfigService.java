package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.model.Config;
import org.example.model.ConfigYaml;
import org.example.util.JarLocationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigService {
    private static Logger logger = LoggerFactory.getLogger(JarLocationUtil.class.getName());
    private static final ConfigService INSTANCE = new ConfigService();
    private static Config config;
    private  ConfigService() {
        String jarLocation = JarLocationUtil.getInstance().getJarLocation();
        File configFile = new File(jarLocation, "config.yaml");
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            this.config = mapper.readValue(configFile, Config.class);
        } catch (FileNotFoundException ex) {
            logger.error("config.properties NOT FOUND");
            logger.error("", ex);
        } catch (IOException ex) {
            logger.error("config.properties IO EXCEPTION");
            logger.error("", ex);
        }

    }

    public static ConfigService getInstance() {
        return INSTANCE;
    }

    public Config getConfig() {
        return config;
    }

    public ConfigYaml getConfigYmlClass(String key)  {
        Class<?> clazz = config.getClass();
        try {
            return (ConfigYaml) clazz.getField(key).get(config);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}
