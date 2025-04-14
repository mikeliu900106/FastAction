package org.example.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.TargetConfig;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportDatabaseTask extends Task{
    private String engine;
    private String host;
    private String user;
    private String password;
    private String database;
    private Set<String> table;
    private String file;
    private TargetConfig targetConfig;
}
