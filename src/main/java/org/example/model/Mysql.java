package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mysql implements ConfigYaml {
    private String host = "localhost";
    private String port = "3306";
    private String username = "root";
    private String password = "eland1234";
}
