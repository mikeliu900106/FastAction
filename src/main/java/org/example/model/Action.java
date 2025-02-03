package org.example.model;

public enum Action {
    BACKUP("備份","A"),
    BACKUP_TABLE_CREATE("創造表並且備份表資料","B"),
    BACKUP_TABLE_NO_CREATE("沒有創造表並且備份表資料","C"),
    BACKUP_TABLE_NO_DATA("創造表但是沒有備份表資料","D"),

    BACKUP_TABLE_USE_SCHEMA_TABLE_CREATE("使用資料庫沒有創造表也沒有備份表資料","E"),
    BACKUP_TABLE_USE_SCHEMA_TABLE_NO_CREATE("使用資料庫沒有創造表也沒有備份表資料","F"),
    BACKUP_TABLE_USE_SCHEMA_NO_DATA("使用資料庫創造表但是沒有備份表資料","G"),

    TAR_CXVF("壓縮","H"),
    MOVE("移動","I"),
    TAR_ZXVF("解壓縮","J"),
    RESTORE("還原",   "K");

    private String name;
    private String value;

    Action(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
