package org.example.model.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({

        @JsonSubTypes.Type(value = MoveTask.class, name = "move"),
        @JsonSubTypes.Type(value = TarTask.class, name = "tar"),
        @JsonSubTypes.Type(value = ImportDatabaseTask.class, name = "importDatabase")
})
public abstract class Task {
    private String type;
}
