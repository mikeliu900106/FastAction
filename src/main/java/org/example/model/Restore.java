package org.example.model;

import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public class Restore implements CongigYaml{
    private Feature feature;
    private String socket;
    private List<Database> database;
    private String file;
}
