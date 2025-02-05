package org.example.model;

import lombok.Data;

@Data
public class Mysql implements CongigYaml{
    private String host;
    private int port;
    private String username;
    private String password;
}
