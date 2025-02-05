package org.example.model;

import lombok.Data;

@Data
public class Move implements CongigYaml{
    private String source;
    private String target;
}
