package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Database implements CongigYaml {
    private String name;
    private List<Table> tables;
}
