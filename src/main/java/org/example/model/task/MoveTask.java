package org.example.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.TargetConfig;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveTask extends Task{
    private String source;
    private String target;
    private TargetConfig targetConfig;
}
