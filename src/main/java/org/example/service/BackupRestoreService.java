package org.example.service;

import org.example.model.Database;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BackupRestoreService {
    String getUserName();
    String getPassword();
    String getHost();
    String getPort();
    String getSocket();
    Optional<String> getFeature();
    List<Map<String, Optional<String>>> getDatabaseAndTable();
    List<String> getFiles();
}
