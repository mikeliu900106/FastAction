package org.example.model;

import lombok.Data;

@Data
public class Zip implements CongigYaml{
    private String sourceFile;
    private String targetFile;
}
