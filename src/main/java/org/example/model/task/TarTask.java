package org.example.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.TargetConfig;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarTask extends Task{
    public enum TarMode{
        compress,
        extract
    }
    public TarMode tarMode;
    public String source;
    public String target;
    public TargetConfig targetConfig;
}
