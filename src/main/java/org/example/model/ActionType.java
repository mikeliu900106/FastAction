package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum ActionType {
    ZIP("壓縮","A"),
//    BACKUP_TABLE_CREATE("創造表並且備份表資料","B"),
//    BACKUP_TABLE_NO_CREATE("沒有創造表並且備份表資料","C"),
//    BACKUP_TABLE_NO_DATA("創造表但是沒有備份表資料","D"),
//
//    BACKUP_TABLE_USE_SCHEMA_TABLE_CREATE("使用資料庫沒有創造表也沒有備份表資料","E"),
//    BACKUP_TABLE_USE_SCHEMA_TABLE_NO_CREATE("使用資料庫沒有創造表也沒有備份表資料","F"),
//    BACKUP_TABLE_USE_SCHEMA_NO_DATA("使用資料庫創造表但是沒有備份表資料","G"),

    UNZIP("解壓縮","H"),
//    RESTORE_TABLE_CREATE("創造表並且還原表資料","I"),
//    RESTORE_TABLE_NO_CREATE("沒有創造表並且還原表資料","J"),
//    RESTORE_TABLE_NO_DATA("創造表但是沒有還原表資料","K"),
//
//    RESTORE_TABLE_USE_SCHEMA_TABLE_CREATE("使用資料庫沒有創造表也沒有還原表資料","L"),
//    RESTORE_TABLE_USE_SCHEMA_TABLE_NO_CREATE("使用資料庫沒有創造表也沒有還原表資料","M"),
//    RESTORE_TABLE_USE_SCHEMA_NO_DATA("使用資料庫創造表但是沒有還原表資料","N"),

    MV("移動","O"),
    TAR("壓縮","P"),


    private String name;

    ActionType(String name, String value) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

}
