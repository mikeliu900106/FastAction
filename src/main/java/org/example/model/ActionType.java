package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum ActionType {
    BACKUP("備份","A"),
//    BACKUP_TABLE_CREATE("創造表並且備份表資料","B"),
//    BACKUP_TABLE_NO_CREATE("沒有創造表並且備份表資料","C"),
//    BACKUP_TABLE_NO_DATA("創造表但是沒有備份表資料","D"),
//
//    BACKUP_TABLE_USE_SCHEMA_TABLE_CREATE("使用資料庫沒有創造表也沒有備份表資料","E"),
//    BACKUP_TABLE_USE_SCHEMA_TABLE_NO_CREATE("使用資料庫沒有創造表也沒有備份表資料","F"),
//    BACKUP_TABLE_USE_SCHEMA_NO_DATA("使用資料庫創造表但是沒有備份表資料","G"),

    RESTORE("還原","H"),
//    RESTORE_TABLE_CREATE("創造表並且還原表資料","I"),
//    RESTORE_TABLE_NO_CREATE("沒有創造表並且還原表資料","J"),
//    RESTORE_TABLE_NO_DATA("創造表但是沒有還原表資料","K"),
//
//    RESTORE_TABLE_USE_SCHEMA_TABLE_CREATE("使用資料庫沒有創造表也沒有還原表資料","L"),
//    RESTORE_TABLE_USE_SCHEMA_TABLE_NO_CREATE("使用資料庫沒有創造表也沒有還原表資料","M"),
//    RESTORE_TABLE_USE_SCHEMA_NO_DATA("使用資料庫創造表但是沒有還原表資料","N"),

    TAR_CXVF("壓縮","O"),
    MOVE("移動","P"),
    TAR_ZXVF("解壓縮","Q");

    private String name;
    private String value;

    ActionType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static String getAllActionType() {
        String allValue = "";
        for (ActionType action : ActionType.values()) {
            allValue += action.getValue();
        }
        return allValue;
    }
}
