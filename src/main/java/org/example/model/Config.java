package org.example.model;

import lombok.Data;

@Data
public class Config implements CongigYaml{
    public Mysql mysql;
    public Restore restore;
    public Backup backup;
    public Tar tar;
    public Move move;
}
