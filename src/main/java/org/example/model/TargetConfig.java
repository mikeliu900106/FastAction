package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.annotation.CheckValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetConfig {
    @CheckValue(type = "ip")
    private String ip;
    @CheckValue()
    private String os;
}