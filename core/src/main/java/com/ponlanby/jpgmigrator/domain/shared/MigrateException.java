package com.ponlanby.jpgmigrator.domain.shared;

import lombok.Data;

@Data
public class MigrateException extends RuntimeException {

    private String message;

    public MigrateException(String message) {
        this.message = message;
    }
}
