package org.example.util;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JarLocationUtil {
    private ProtectionDomain domain;
    private CodeSource codeSource;
    private String path;
    private URL location;
    private static Logger logger = LoggerFactory.getLogger(JarLocationUtil.class.getName());

    private JarLocationUtil() {
        domain = JarLocationUtil.class.getProtectionDomain();
        codeSource = domain.getCodeSource();
        location = codeSource.getLocation();
        logger.info("location: " + location);
        path = location.getPath();
        logger.info("path: " + path);
    }

    public static JarLocationUtil getInstance() {
        return new JarLocationUtil();
    }
    public String getJarLocation() {
        if(path == null) {
            logger.error("Jar location is null");
            throw new IllegalArgumentException("Jar location is null");
        }
        if(path.endsWith(".jar")) {
            switch (getOS()) {
                case "windows":
                    logger.info("windows origin path");
                    String withoutJarNamPath = path.substring(0, path.lastIndexOf("/"));
                    path = withoutJarNamPath.substring(withoutJarNamPath.indexOf("/") + 1,withoutJarNamPath.length());
                    return path;
                case "linux":
                    return path.substring(0, path.lastIndexOf("/"));
                default:
                    logger.error("unknown OS");
                    throw new IllegalArgumentException("unknown OS");
            }
        }
        return path;
    }

    private String getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
            return "windows";
        } else if(os.contains("nix") || os.contains("nux")) {
            return "linux";
        }
        return "unknown";
    }
}
