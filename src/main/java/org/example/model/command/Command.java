package org.example.model.command;

import org.example.model.ActionType;

public enum Command {
    TAR_CXVF_LINUX(ActionType.TAR, "tar -cvf <TARGETFILE> <SOURCEFILE>", "Linux"),
    TAR_ZXVF_LINUX(ActionType.TAR, "tar -zxvf <TARGETFILE> <SOURCEFILE>", "Linux"),
    MOVE_LINUX(ActionType.MV, "mv <SOURCEFILE> <TARGETFILE>", "Linux"),
    MOVE_WINDOWS(ActionType.MV, "move <SOURCEFILE> <TARGETFILE>", "Windows"),
    UNZIP_LINUX(ActionType.UNZIP, "unzip <SOURCEFILE> -d <TARGETDIR>", "Linux"),
    UNZIP_WINDOWS(ActionType.UNZIP, "powershell Expand-Archive -Path <SOURCEFILE> -DestinationPath <TARGETDIR>", "Windows"),
    ZIP_LINUX(ActionType.ZIP, "zip -r <TARGETFILE> <SOURCEFILE>", "Linux"),
    ZIP_WINDOWS(ActionType.ZIP, "powershell Compress-Archive -Path <SOURCEFILE> -DestinationPath <TARGETFILE>", "Windows");

    private ActionType actionType;
    private String command;
    private String os;

    // 構造函數參數順序應該為 ActionType, command, os
    Command(ActionType actionType, String command, String os) {
        this.actionType = actionType;
        this.command = command;
        this.os = os;
    }

    public String getCommand() {
        return command;
    }

    public String getOs() {
        return os;
    }

    public String buildCommand(String... params) {
        String command = this.command;
        for (int i = 0; i < params.length; i++) {
            command = command.replaceFirst("<[^>]+>", params[i]);
        }
        return command;
    }

    public static Command getCommandByOs(String os) {
        for (Command command : Command.values()) {
            if (command.getOs().equalsIgnoreCase(os)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Unsupported OS: " + os);
    }
}