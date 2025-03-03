package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zip implements ConfigYaml {
    private String name = "";
    private String source = "";
    private String target = "";
}
