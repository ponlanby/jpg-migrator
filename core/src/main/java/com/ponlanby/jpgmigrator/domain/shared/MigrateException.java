package com.ponlanby.jpgmigrator.domain.shared;

import lombok.Data;

/**
 * @Author tonruochen
 * @Date 2021/1/3
 **/

@Data
public class MigrateException extends RuntimeException {

    private String message;

    public MigrateException(String message) {
        this.message = message;
    }
}
