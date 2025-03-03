package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Config implements ConfigYaml {
    public Mysql mysql;
    public Restore restore;
    public Backup backup;
    public Tar tar;
    public Move move;
}
