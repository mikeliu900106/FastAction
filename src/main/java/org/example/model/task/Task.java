package org.example.model.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({

        @JsonSubTypes.Type(value = MoveTask.class, name = "move"),
        @JsonSubTypes.Type(value = TarTask.class, name = "tar"),
        @JsonSubTypes.Type(value = ImportDatabaseTask.class, name = "importDatabase")
})
public abstract class Task {
    private String type;
    public static List<Task> getTaskList(String fileName) throws IOException {
        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        ConfigTask config = mapper.readValue(file, ConfigTask.class);
        return config.getTasks();
    }
}
