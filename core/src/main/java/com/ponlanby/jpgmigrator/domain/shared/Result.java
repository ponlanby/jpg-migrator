package com.ponlanby.jpgmigrator.domain.shared;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author tonruochen
 * @Date 2021/1/3
 **/

@Data
public class Result implements Serializable {

    private boolean success = false;
    private String msg = "";

    private Result() {
    }

    public static Result success(String msg) {
        Result r = new Result();
        r.setSuccess(true);
        r.setMsg(msg);
        return r;
    }

    public static Result failed(String msg) {
        Result r = new Result();
        r.setSuccess(false);
        r.setMsg(msg);
        return r;
    }
}
