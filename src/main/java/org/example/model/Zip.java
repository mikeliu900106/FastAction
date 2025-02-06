package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zip implements CongigYaml{
    private String sourceFile = "";
    private String targetFile = "";
}
