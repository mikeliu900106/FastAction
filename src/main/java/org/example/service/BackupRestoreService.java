package org.example.service;

import org.example.model.Database;

import java.util.List;
import java.util.Map;

public interface BackupRestoreService {
    String getUserName();
    String getPassword();
    String getHost();
    String getPort();
    String getSocket();
    List<String> getFeature();
    Map<String,List<String>> getDatabase();
}
