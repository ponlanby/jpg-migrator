package com.ponlanby.jpgmigrator.domain.aggregate.migrate;

import java.util.List;

/**
 * 迁移任务repository
 * 模拟DB，保存迁移文件夹的映射关系，供定时任务查询使用
 */
public interface MigrateTaskRepository {

    /**
     * 保存迁移任务聚合
     */
    MigrateTask save(MigrateTask migrateTask);

    /**
     * 加载迁移任务聚合
     */
    MigrateTask load(String sourcePath, String destPath);

    /**
     * 查询所有迁移任务聚合
     */
    List<MigrateTask> listAll();
}
