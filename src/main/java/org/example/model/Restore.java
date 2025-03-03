package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restore implements ConfigYaml {
    private Feature feature;
    private String socket = "";
    private List<Database> database = new ArrayList<>();
    private List<String> file = new ArrayList<>();
}
