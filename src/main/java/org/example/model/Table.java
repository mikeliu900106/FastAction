package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Table implements CongigYaml{
    private List<String> name;
}
