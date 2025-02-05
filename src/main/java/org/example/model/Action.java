package org.example.model;

public enum Action {
    MYSQL_RESTORE("mysql -u <USER> -p<PASSWORD> <HOST> <FEATURE>  <SOCKET> <DATABASE> <TABLE> < <FILE>"),
    MYSQL_BACKUP("mysqldump -u <USER> -p<PASSWORD> <HOST> <FEATURE> <SOCKET> <DATABASE> <TABLE> > <FILE>"),
    TAR_CXVF("tar -cxvf <TARGETFILE> <SOURCEFILE>"),
    TAR_ZXVF("tar -zxvf <TARGETFILE> <SOURCEFILE>"),
    MOVE("mv <SOURCEFILE> <TARGETFILE>");

    private String command;
    Action(String command) {
        this.command = command;
    }
    public String getCommand() {
        return command;
    }
}
