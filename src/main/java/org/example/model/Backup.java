package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Backup implements ConfigYaml {
    public Feature feacture;
    public String socket = "";
    public List<Database> database = new ArrayList<>();
    public List<String> file = new ArrayList<>();
}
