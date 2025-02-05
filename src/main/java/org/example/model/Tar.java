package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Tar implements CongigYaml{
    private List<Zip> zip;
}
