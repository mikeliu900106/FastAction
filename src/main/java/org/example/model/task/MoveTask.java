package org.example.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.TargetConfig;
import org.example.model.annotation.CheckValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveTask extends Task{
    @CheckValue()
    private String source;
    @CheckValue()
    private String target;
    private TargetConfig targetConfig;

    @Override
    public String toString() {
        return "MoveTask{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", targetConfig=" + targetConfig +
                '}';
    }
}
