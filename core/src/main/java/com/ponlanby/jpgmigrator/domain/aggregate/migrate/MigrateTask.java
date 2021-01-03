package com.ponlanby.jpgmigrator.domain.aggregate.migrate;

import com.google.common.base.Objects;
import lombok.Data;

/**
 * 迁移任务聚合，保存迁移路径的映射关系
 */
@Data
public class MigrateTask {

    /**
     * PK
     */
    private Integer id;

    /**
     * 原路径
     */
    private String sourcePath;

    /**
     * 目标路径
     */
    private String destPath;

    public MigrateTask(String sourcePath, String destPath) {
        this.sourcePath = sourcePath;
        this.destPath = destPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MigrateTask that = (MigrateTask) o;
        return Objects.equal(sourcePath, that.sourcePath) &&
                Objects.equal(destPath, that.destPath);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sourcePath, destPath);
    }
}
