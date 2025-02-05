package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Backup implements CongigYaml{
    public Feature feacture;
    public String socket;
    public List<Database> database;
    public String file;
}
