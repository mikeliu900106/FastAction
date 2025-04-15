package org.example.model.command;

public enum Command {
    TAR_CXVF_LINUX("tar", "tar -cvf <TARGETFILE> <SOURCEFILE>", "Linux"),
    TAR_ZXVF_LINUX("tar", "tar -zxvf <TARGETFILE> <SOURCEFILE>", "Linux"),
    MOVE_LINUX("mv", "mv <SOURCEFILE> <TARGETFILE>", "Linux"),
    MOVE_WINDOWS("mv", "move <SOURCEFILE> <TARGETFILE>", "Windows"),
    UNZIP_LINUX("unzip", "unzip <SOURCEFILE> -d <TARGETDIR>", "Linux"),
    UNZIP_WINDOWS("unzip", "powershell Expand-Archive -Path <SOURCEFILE> -DestinationPath <TARGETDIR>", "Windows"),
    ZIP_LINUX("zip", "zip -r <TARGETFILE> <SOURCEFILE>", "Linux"),
    ZIP_WINDOWS("zip", "powershell Compress-Archive -Path <SOURCEFILE> -DestinationPath <TARGETFILE>", "Windows");

    private String type ;
    private String command;
    private String os;

    // 構造函數參數順序應該為 ActionType, command, os
    Command(String type, String command, String os) {
        this.type = type;
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